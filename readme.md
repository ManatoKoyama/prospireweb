# 開発Tips

## 各種権限とユーザー情報

- ユーザーによってメニューの表示／非表示の制御をしているため、実装時は以下で確認すると良いです。（999999ユーザーがやりやすいかと）

| 権限 | ユーザーID | パスワード | 備考 |
| ---- | ---- | ---- | ---- |
| 全権限 | 999999 | 999999 | 全ボタン表示されます |
| BU長権限 | 201049 | 201049 | 実績登録、予算FIXが非表示 |
| 経営層権限 | 460110 | 460110 | 実績登録が非表示 |
| 経理部権限 | 900648 | 900648 | 予算FIXが非表示 |

ログイン画面のURL
http://localhost:8080/
※一旦、現時点はlocalhostになります

## 「schema.sql」と「data.sql」について

Spring Bootを起動する際に、データベースの初期化を自動で行うためには、「schema.sql」と「data.sql」に必要なCREATE文やINSERT文を追加しておくと便利です。
これにより、毎回手動でテーブル作成やデータ投入を行う手間が省けます。

- 「schema.sql」にはテーブルやインデックスなどの定義（CREATE文）を記述します。
- 「data.sql」には初期データの挿入（INSERT文）を記述します。

これらのファイルは、`src/main/resources`ディレクトリに配置してください。

※注意点
起動のたびに新規作成されるため、以下例のようにテーブルが存在しない場合に作成するCreate文としておくこと。
CREATE TABLE IF NOT EXISTS 既存名 (
    カラム1 データ型 制約,
    カラム2 データ型 制約
);
また、データも毎回、INSERT文が実行され、一意キー制約に違反しないように、「INSERT IGNORE INTO * VALUES *」を利用して定義しておくこと。

## HTMLでのJavaScriptの呼び出し方法

以下は、JavaScriptファイルをHTMLから呼び出す例です。
`<script th:src="@{/js/main.js}"></script>`

## ソースコードの構成
📦src
 ┣ 📂main
 ┃ ┣ 📂java
 ┃ ┃ ┗ 📂com
 ┃ ┃ ┃ ┗ 📂prospire
 ┃ ┃ ┃ ┃ ┗ 📂prospireweb
 ┃ ┃ ┃ ┃ ┃ ┣ 📂config　　　　　　　　　　　　共通的に利用する設定ファイル等の格納先
 ┃ ┃ ┃ ┃ ┃ ┣ 📂controller　　　　　　　　　　コントローラーの格納先（画面ごとに作る）
 ┃ ┃ ┃ ┃ ┃ ┣ 📂model　　　　　　　　　　　　 テーブル等、エンティティの格納先
 ┃ ┃ ┃ ┃ ┃ ┣ 📂repository　　　　　　　　　　Mybatisのマッパークラスの格納先
 ┃ ┃ ┃ ┃ ┃ ┣ 📂service　　　　　　　　　　　 サービスクラスの格納先
 ┃ ┃ ┃ ┃ ┃ ┣ 📜ProspirewebApplication.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜ServletInitializer.java
 ┃ ┗ 📂resources
 ┃ ┃ ┣ 📂mapper　　　　　　　　　　　　　　　　 Mybatisのxmlの格納先（テーブルごとに作る）
 ┃ ┃ ┣ 📂static
 ┃ ┃ ┃ ┣ 📂css　　　　　　　　　　　　　　　　 静的cssの格納先
 ┃ ┃ ┃ ┣ 📂images　　　　　　　　　　　　　　　画像ファイル等の格納先
 ┃ ┃ ┃ ┗ 📜js　　　　　　　　　　　　　　　　　JavaScriptの格納先
 ┃ ┃ ┣ 📂templates　　　　　　　　　　　　　　　各画面のHTML格納先
 ┃ ┃ ┃ ┣ 📂fragments　　　　　　　　　　　　　 共通画面など、複数HTMLから利用されるHTML格納先
 ┃ ┃ ┣ 📜application.properties　　　　　　　　 「application.properties」
 ┃ ┃ ┣ 📜data.sql
┃ ┃ ┗ 📜schema.sql

## メニューサイドバーの実装方法

サイドバーのメニューについて、他画面で読み込み利用出来るように実装をしています。（fragments/menu_structure.html）
各画面では以下の実装に倣って、メインコンテンツの実装をお願いします。（省略1、2の部分）

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org" xmlns:sec="http://www.thymeleaf.org/extras/spring-security" lang="ja">
<head>
    省略1
</head>
<body>
    
    <div class="app-container">
        <div th:insert="fragments/menu_structure :: layoutContent"></div>
        
        省略2
        
    </div>
    </body>
</html>
```
