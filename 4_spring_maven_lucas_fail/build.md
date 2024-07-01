
```
initializerにて
project: maven
lang: kotlin

src/main/kotlin/myapp/HelloController.kt を設置

cd myapp
./mvnw spring-boot:run

http://localhost:8080 helloworld成功

次にsrc/main/resources/static/index.htmlをホストする

Spring initializerでproject maven, lang kotlinで生成させたものを置きhello表示に成功したので、次にsrc/main/resources/static/index.htmlをホストする
```

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
- 以前作成した `HelloController` クラスがルートURL（`/`）にマッピングされている場合、それを変更または削除する必要があります。静的ファイルよりもコントローラーのマッピングが優先されるためです。

HTMLファイルを作成した後、何か問題が発生した場合や、さらに機能を追加したい場合は、お知らせください。サポートいたします。


---

cd myapp
./mvnw clean install
./mvnw spring-boot:run
http://localhost:8080

---

次のステップとして、バックエンドでLucas数を計算し、計算時間を測定し、フロントエンドとAJAX通信を行います。以下に実装例を示します。

### バックエンド (`HelloController.kt`)

```kotlin
package myapp

import org.springframework.web.bind.annotation.*
import kotlin.system.measureNanoTime

@RestController
class HelloController {

    @PostMapping("/calculate")
    fun calculateLucas(@RequestBody request: LucasRequest): LucasResponse {
        val n = request.n
        var result: Long = 0
        val time = measureNanoTime {
            result = calculateLucasNumber(n)
        }
        return LucasResponse(result, time)
    }

    fun calculateLucasNumber(n: Int): Long {
        if (n == 0) return 2
        if (n == 1) return 1
        var a = 2L
        var b = 1L
        for (i in 2..n) {
            val temp = a + b
            a = b
            b = temp
        }
        return b
    }
}

data class LucasRequest(val n: Int)
data class LucasResponse(val result: Long, val process_time: Long)
```

### 説明

1. **`calculateLucas`メソッド**:
   - POSTリクエストを受け取り、Lucas数を計算し、計算時間を測定します。
   - `measureNanoTime`を使用して計算時間を測定し、結果と共に返します。

2. **`calculateLucasNumber`メソッド**:
   - Lucas数を計算します。
   - 0番目と1番目のLucas数はそれぞれ2と1で、以降は再帰的に計算されます。

### フロントエンド (`index.html`)

```html
<!DOCTYPE html>
<html>
<head>
    <title>Lucas Number Calculator</title>
</head>
<body>
    <p>AJAX Lucas Number</p>
    <input type="number" id="inputN" placeholder="Enter a number">
    <button onclick="sendRequest()">Calculate</button>
    <div id="result"></div>
    <div id="time"></div>

    <script>
    function sendRequest() {
        const n = document.getElementById('inputN').value;
        fetch('/calculate', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ n: parseInt(n) })
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            document.getElementById('result').innerText = `Lucas Number L${n} = ${data.result}`;
            document.getElementById('time').innerText = `Time: ${(data.process_time / 1e9).toFixed(3)} sec`;
        })
        .catch(error => {
            console.error('Error:', error);
            document.getElementById('result').innerText = 'An error occurred. Please try again.';
            document.getElementById('time').innerText = '';
        });
    }
    </script>
</body>
</html>
```

### 説明

- **`sendRequest`関数**:
  - 入力値を取得し、AJAXリクエストを送信します。
  - サーバーからのレスポンスを受け取って結果と処理時間を表示します。

### 実行手順

1. **Spring Bootアプリケーションの起動**:
   - `./mvnw spring-boot:run`を実行します。

2. **ブラウザで確認**:
   - `http://localhost:8080`にアクセスし、数値を入力して`Calculate`ボタンをクリックすると、Lucas数と計算時間が表示されます。

---
```
cd myapp
./mvnw clean install
./mvnw spring-boot:run
http://localhost:8080
```
---

caluculateボタンを押したところ、
An error occurred. Please try again.



