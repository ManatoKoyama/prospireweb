------------------------------
-- ユーザーテーブル (mst_user)
------------------------------
CREATE TABLE IF NOT EXISTS mst_user (
username VARCHAR(30) PRIMARY KEY COMMENT 'ユーザID',
kanji_name_1 VARCHAR(90) NOT NULL COMMENT '漢字氏名（苗）',
kanji_name_2 VARCHAR(90) NOT NULL COMMENT '漢字氏名（名）',
kana_name_1 VARCHAR(30) NOT NULL COMMENT 'カナ氏名（苗）',
kana_name_2 VARCHAR(30) NOT NULL COMMENT 'カナ氏名（名）',
role CHAR(2) NOT NULL COMMENT '役職',
organization CHAR(2) NOT NULL COMMENT '所属組織',
mail_adress VARCHAR(50) NOT NULL COMMENT 'メールアドレス',
authority CHAR(2) NOT NULL COMMENT '権限',
password VARCHAR(120) NOT NULL COMMENT 'パスワード',
password_expiration TIMESTAMP NOT NULL COMMENT 'パスワード有効期限',
last_login_date TIMESTAMP NOT NULL  DEFAULT (CURRENT_TIMESTAMP()) COMMENT '最終ログイン日時',
delete_flg VARCHAR(1) DEFAULT 0 COMMENT '削除フラグ',
reg_user VARCHAR(30) COMMENT '登録者',
reg_date TIMESTAMP DEFAULT (CURRENT_TIMESTAMP()) COMMENT '登録日時',
upd_user VARCHAR(30) COMMENT '更新者',
upd_date TIMESTAMP DEFAULT (CURRENT_TIMESTAMP()) COMMENT '更新日時'
);

------------------------------
-- 論理名 (物理名)
------------------------------


