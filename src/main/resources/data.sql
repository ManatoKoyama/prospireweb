-- ----------------------------------------------------
-- ユーザーテーブル (mst_user)
-- ----------------------------------------------------
-- パスワードは必ずハッシュ化された値を使用してください。
-- 例: 'password' を BCrypt でハッシュ化した場合のダミー値
INSERT IGNORE INTO mst_user(username,kanji_name_1,kanji_name_2,kana_name_1,kana_name_2,role,organization,mail_adress,authority,password,password_expiration,last_login_date,delete_flg,reg_user,reg_date,upd_user,upd_date) VALUES 
    ('201049','坂瀬','一則','ｻｶｾ','ｶｽﾞﾉﾘ','30','31','kazunori.sakase@pro-spire.co.jp','10','$2a$08$SxmJF31Zd4mU/sA/FbgqOeGBEg3Q2P5yvybeMp.QI6PvGDQHOlXoi',TIMESTAMP '2037-12-31 23:59:59.000',TIMESTAMP '2025-09-12 16:53:38.000','0','sysadmin',TIMESTAMP '2025-09-12 16:53:38.000','sysadmin',TIMESTAMP '2025-09-12 16:53:38.000')
  , ('460110','根本','秀章','ﾈﾓﾄ','ﾋﾃﾞｱｷ','00','30','hideaki.nemoto@pro-spire.co.jp','00','$2a$08$gyZIFDmttrrkbI8TPHy3RO1.FV2HCpknKffugNo7riFxu7uJ8vj7q',TIMESTAMP '2037-12-31 23:59:59.000',TIMESTAMP '2025-09-12 17:06:22.000','0','sysadmin',TIMESTAMP '2025-09-12 17:06:22.000','sysadmin',TIMESTAMP '2025-09-12 17:06:22.000')
  , ('900648','渡辺','英次','ﾜﾀﾅﾍﾞ','ｴｲｼﾞ','10','20','eiji.watanabe@pro-spire.co.jp','20','$2a$08$IKKElKQANyy.boyKKDH8A.VdVr7xdIRrSenObEG3EKY6xgpTesFUS',TIMESTAMP '2037-12-31 23:59:59.000',TIMESTAMP '2025-09-12 17:07:18.000','0','sysadmin',TIMESTAMP '2025-09-12 17:07:18.000','sysadmin',TIMESTAMP '2025-09-12 17:07:18.000')
  , ('999999','権限','ＡＬＬ','ｹﾝｹﾞﾝ','ALL','00','00','admin@pro-spire.co.jp','99','$2a$08$aaNKdqmKJMl/6EnLzuom1.KjWksGILWmTW5x21mLVpX.tuC13MqCO',TIMESTAMP '2037-12-31 23:59:59.000',TIMESTAMP '2025-09-12 17:07:18.000','0','sysadmin',TIMESTAMP '2025-10-11 17:07:18.000','sysadmin',TIMESTAMP '2025-09-12 17:07:18.000');

-- ----------------------------------------------------
-- 見込テーブル (tbl_prospect)
-- ----------------------------------------------------
INSERT IGNORE INTO tbl_prospect(ki,bu,customer,project,order_probability,order_m,record_m,expense_item,details,price,status,change_dete_flg,delete_flg,reg_user,reg_date) VALUES
  ('38期','SI1BU','システムズ・デザイン株式会社','販売計画・実績管理の新規システム構築','A','2025-10-01','2025-11-01','売上','第１　太郎','900','0','0','0','sysadmin',TIMESTAMP '2025-10-11 17:07:18.000')
, ('38期','SI1BU','システムズ・デザイン株式会社','販売計画・実績管理の新規システム構築','A','2025-11-01','2025-12-01','売上','第１　太郎','900','0','0','0','sysadmin',TIMESTAMP '2025-10-11 17:07:18.000')
, ('38期','SI1BU','システムズ・デザイン株式会社','販売計画・実績管理の新規システム構築','A','2025-10-01','2025-11-01','売上','第１　太郎','900','0','1','0','sysadmin',TIMESTAMP '2025-10-11 17:07:18.000')
, ('38期','SI1BU','システムズ・デザイン株式会社','販売計画・実績管理の新規システム構築','A','2025-11-01','2025-12-01','売上','第１　太郎','1000','0','1','0','sysadmin',TIMESTAMP '2025-10-11 17:07:18.000')
, ('38期','SI1BU','システムズ・デザイン株式会社','販売計画・実績管理の新規システム構築','A','2025-12-01','2026-01-01','売上','第１　太郎','1000','0','1','0','sysadmin',TIMESTAMP '2025-10-11 17:07:18.000')
;
