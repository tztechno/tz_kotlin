###
# kodein
###

---

---

---

---

Visual Studio Code (VS Code)を使用して、Kotlinプロジェクトを作成し、Kodeinを設定する手順を説明します。以下の手順に従って、macOS上でVS Codeを使用してKotlinプロジェクトをセットアップし、Kodeinをインストールします。

### 1. 必要なツールをインストールする

#### a. Java Development Kit (JDK) をインストール

まず、JDKがインストールされていることを確認します。インストールされていない場合、[AdoptOpenJDK](https://adoptopenjdk.net/)からJDKをインストールします。

```bash
brew install openjdk@11
```

#### b. Gradleをインストール

Gradleがインストールされていることを確認します。インストールされていない場合、Homebrewを使用してインストールします。

```bash
brew install gradle
```

#### c. Kotlin PluginをVS Codeにインストール

VS Codeの拡張機能マーケットプレイスから「Kotlin」プラグインをインストールします。

### 2. プロジェクトの作成

#### a. 新しいプロジェクトディレクトリを作成

ターミナルで新しいディレクトリを作成し、その中に移動します。

```bash
mkdir MyKodeinProject
cd MyKodeinProject
```

#### b. Gradleプロジェクトを初期化

以下のコマンドを実行して、Gradleプロジェクトを初期化します。

```bash
gradle init --type java-application
```

Gradleの初期化ウィザードに従って、プロジェクトを設定します。例えば、プロジェクト名を `MyKodeinProject` とし、他の設定はデフォルトのままにします。

### 3. Gradle設定ファイルの編集

#### a. `build.gradle` ファイルを編集

`build.gradle` ファイルを以下のように編集し、KotlinとKodeinの依存関係を追加します。

```groovy
plugins {
    id 'org.jetbrains.kotlin.jvm' version '1.8.0' // 最新バージョンを確認してください
    id 'application'
}

repositories {
    mavenCentral()
}

dependencies {
    implementation "org.jetbrains.kotlin:kotlin-stdlib"
    implementation "org.kodein.di:kodein-di:7.10.0" // 最新バージョンを確認してください
    testImplementation 'junit:junit:4.13.2'
}

application {
    mainClass = 'com.example.MainKt'
}
```

### 4. Kotlinファイルの作成

#### a. プロジェクト構造の設定

以下のディレクトリ構造を作成します。

```
MyKodeinProject
├── build.gradle
├── gradle
├── gradlew
├── gradlew.bat
├── settings.gradle
└── src
    ├── main
    │   └── kotlin
    │       └── com
    │           └── example
    │               └── Main.kt
    └── test
        └── kotlin
```

#### b. `Main.kt` ファイルを作成

`src/main/kotlin/com/example/Main.kt` ファイルを作成し、以下のコードを追加します。

```kotlin
package com.example

import org.kodein.di.*
import org.kodein.di.bindings.*
import org.kodein.di.jxinject.jx

fun main() {
    val kodein = DI {
        bind<String>() with singleton { "Hello, Kodein!" }
    }

    val hello: String by kodein.instance()
    println(hello) // Prints "Hello, Kodein!"
}
```

### 5. プロジェクトのビルドと実行

ターミナルでプロジェクトのルートディレクトリに移動し、以下のコマンドを実行してプロジェクトをビルドし、実行します。

```bash
./gradlew run
```

これで、Kotlinプロジェクトが正常にビルドされ、Kodeinによる依存性注入が機能していることを確認できます。

---


Kotlin Kodeinは、Kotlin向けの依存性注入（DI）フレームワークです。以下の手順でKodeinをプロジェクトにインストールできます。

### 1. Gradleプロジェクトの場合

#### a. プロジェクトの `build.gradle` ファイルを開く

ルートプロジェクトの `build.gradle` ファイルを開き、リポジトリと依存関係を追加します。

```groovy
buildscript {
    ext {
        kodein_version = "7.10.0" // 最新バージョンを確認してください
    }
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        // 他の依存関係がある場合はここに追加
        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kodein_version"
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}
```

#### b. モジュールの `build.gradle` ファイルを開く

```groovy
apply plugin: 'kotlin'

dependencies {
    implementation "org.kodein.di:kodein-di:$kodein_version"
    // Androidの場合
    implementation "org.kodein.di:kodein-di-framework-android-x:$kodein_version"
}
```

### 2. 使用例

#### シンプルな例

KotlinファイルでKodeinを使用するには、以下のように設定します。

```kotlin
import org.kodein.di.*
import org.kodein.di.bindings.*
import org.kodein.di.jxinject.jx

val kodein = DI {
    bind<String>() with singleton { "Hello Kodein!" }
}

fun main() {
    val hello: String by kodein.instance()
    println(hello) // Prints "Hello Kodein!"
}
```

#### Androidプロジェクトの場合

```kotlin
import android.app.Application
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.x.AndroidXModule
import org.kodein.di.bind
import org.kodein.di.singleton

class MyApplication : Application(), DIAware {
    override val di by DI.lazy {
        import(AndroidXModule(this@MyApplication))

        bind<String>("appName") with singleton { "My Kodein App" }
    }
}
```

### 3. プロジェクトを同期する

依存関係を追加した後、Gradleプロジェクトを同期してください。Android Studioの場合、「Sync Now」をクリックします。

### 4. 依存性の注入を使用する

必要なクラスで依存性を注入します。以下は、Android Activityでの例です。

```kotlin
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.instance

class MainActivity : AppCompatActivity(), DIAware {

    override val di: DI by closestDI()
    private val appName: String by instance("appName")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        println(appName) // Prints "My Kodein App"
    }
}
```

これで、KotlinプロジェクトにKodeinをインストールし、使用する準備が整いました。

---
