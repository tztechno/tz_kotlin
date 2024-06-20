###
# kodein
###

---

---

---

---

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
