---

はい、指定のHTMLをホストして表示するように改造することができます。Spring Bootでは、静的なHTMLファイルを簡単にホストできます。以下の手順で実装できます：

1. 静的ファイルの配置:
   `src/main/resources/static` ディレクトリを作成し、そこにHTMLファイルを配置します。

   例えば、`index.html` というファイルを作成する場合：
   ```
   src/main/resources/static/index.html
   ```

2. HTMLファイルの作成:
   `index.html` に以下のような内容を記述します（例）：

   ```html
   <!DOCTYPE html>
   <html>
   <head>
       <title>My Spring Boot App</title>
   </head>
   <body>
       <h1>Welcome to my Spring Boot application!</h1>
       <p>This is a static HTML page served by Spring Boot.</p>
   </body>
   </html>
   ```

3. コントローラーの修正:
   既存の `HelloController.kt` を以下のように変更します：

   ```kotlin
   package myapp

   import org.springframework.stereotype.Controller
   import org.springframework.web.bind.annotation.GetMapping

   @Controller
   class HelloController {

       @GetMapping("/")
       fun home(): String {
           return "index"  // これは "index.html" を指します
       }
   }
   ```

   注意: `@RestController` を `@Controller` に変更しています。

4. 依存関係の確認:
   `build.gradle.kts` に以下の依存関係が含まれていることを確認します：

   ```kotlin
   dependencies {
       implementation("org.springframework.boot:spring-boot-starter-web")
       // 他の依存関係...
   }
   ```

5. アプリケーションの再起動:
   ```
   ./gradlew bootRun
   ```

6. ブラウザでアクセス:
   `http://localhost:8080` にアクセスすると、作成したHTMLページが表示されるはずです。

注意点:
- 静的ファイルは `src/main/resources/static` ディレクトリに配置することで、自動的に公開されます。
- 複数のHTMLファイルや、CSS、JavaScript、画像などの他の静的リソースも同様にこのディレクトリに配置できます。
- より複雑なテンプレート処理が必要な場合は、Thymeleafなどのテンプレートエンジンを使用することもできます。

これらの変更を行った後、指定のHTMLファイルがホストされ、ブラウザで表示されるはずです。何か問題が発生したり、さらに詳しい説明が必要な場合は、お知らせください。

---
