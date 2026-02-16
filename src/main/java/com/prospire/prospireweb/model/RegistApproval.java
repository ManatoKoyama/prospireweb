package com.prospire.prospireweb.model;

import java.util.Date;

import lombok.Data;

/* 承認、差戻判定取得用
 * 
 * 
 */

/**
 * 登録承認情報を表すモデルクラス。
 */
@Data
public class RegistApproval {
  private String ki;                //対象期
  private String bu;                //対象BU
  private String approval_flg;      //承認判定フラグ
  private String comments;          //コメント
  private Date upd_date;            //更新日時 
}