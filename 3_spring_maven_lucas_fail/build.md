initializerにて
project: maven
lang: kotlin

src/main/kotlin/myapp/HelloController.kt を設置

cd myapp
./mvnw spring-boot:run

http://localhost:8080 helloworld成功

次にsrc/main/resources/static/index.htmlをホストする

Spring initializerでproject maven, lang kotlinで生成させたものを置きhello表示に成功したので、次にsrc/main/resources/static/index.htmlをホストする

---

素晴らしいですね！次のステップとして、静的なHTMLファイルをホストしましょう。以下の手順で `index.html` をホストできます：

1. ファイルの作成:
   `src/main/resources/static/` ディレクトリに `index.html` ファイルを作成します。

2. HTMLコンテンツの追加:
   `index.html` に以下のような基本的なHTMLコンテンツを追加します：

```html
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Spring Boot App</title>
</head>
<body>
    <h1>Welcome to My Spring Boot Application</h1>
    <p>This is a static HTML page served by Spring Boot.</p>
</body>
</html>
```

3. アプリケーションの再起動:
   Spring Bootアプリケーションを再起動します（前回と同じ方法で実行してください）。

4. ブラウザでアクセス:
   アプリケーションが起動したら、ブラウザで `http://localhost:8080` にアクセスします。

この手順を実行すると、先ほど作成した `index.html` の内容がブラウザに表示されるはずです。

注意点：
- Spring Bootは、デフォルトで `src/main/resources/static/` ディレクトリ内の静的ファイルを自動的に提供します。
- `index.html` はルートURL（`/`）にマッピングされるため、別途コントローラーを作成する必要はありません。
- 以前作成した `HelloController` クラスがルートURL（`/`）にマッピングされているので、それを変更する必要があります。
- 静的ファイルよりもコントローラーのマッピングが優先されるためです。

@GetMapping("/hello")
fun hello(): String {
    return "Hello, World!"
}

HTMLファイルを作成した後、何か問題が発生した場合や、さらに機能を追加したい場合は、お知らせください。サポートいたします。


---

cd myapp
./mvnw clean install
./mvnw spring-boot:run
http://localhost:8080
