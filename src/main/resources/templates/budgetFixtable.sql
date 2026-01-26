------------------------------
-- 予算テーブル (budget)
------------------------------
CREATE TABLE IF NOT EXISTS budget (
ki VARCHAR(3) NOT NULL COMMENT '対象期',
bu VARCHAR(10) NOT NULL COMMENT '部門',
customer VARCHAR(30) NOT NULL COMMENT '顧客',
project VARCHAR(90) NOT NULL COMMENT '案件名',
order_probability VARCHAR(1) NOT NULL COMMENT '確度',
order_m DATE NOT NULL COMMENT '受注月',
record_m DATE NOT NULL COMMENT '売上計上月',
expense_item VARCHAR(20) NOT NULL COMMENT '費目',
details VARCHAR(30) NOT NULL COMMENT '細目',
price Integer(7) NOT NULL COMMENT '金額',
status VARCHAR(1) COMMENT '状況区分',
delete_flg VARCHAR(1) DEFAULT 0 COMMENT '削除フラグ',
reg_user VARCHAR(30) COMMENT '登録者',
reg_date TIMESTAMP DEFAULT (CURRENT_TIMESTAMP()) COMMENT '登録日時',
upd_user VARCHAR(30) COMMENT '更新者',
upd_date TIMESTAMP DEFAULT (CURRENT_TIMESTAMP()) COMMENT '更新日時'
,PRIMARY KEY(ki, bu, record_m, expense_item, details)
);