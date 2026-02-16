package com.prospire.prospireweb.form;

import java.io.Serializable;

/**
 * 予算FIX画面で利用するフォームデータを保持するクラス。
 */
public class BudgetFixForm implements Serializable {
  private static final long serialVersionUID = 1L;
  
  //基本的には固定値が来る
  private String ki;                //対象期
  //基本的には固定値が来る
  private String bu;                //対象BU
  
  //@NotNull 依存関係を追加してよくなったら実装しようと思う
  private String approval_flg;      //承認判定フラグ

  //@SizeMax(max = 1500) 依存関係を追加してよくなったら実装しようと思う
  private String comments;          //コメント

  //getter setterの実装
  //対象期のgetter setter
  public String getKi() {
    return ki;
  }
  public void setKi(String ki) {
        this.ki = ki;
  }
  //部署名のgetter setter
  public String getBu() {
    return bu;
  }
    public void setBu(String bu) {
        this.bu = bu;
  }
  //承認判定フラグのgetter setter
  public String getApproval_flg() {
        return approval_flg;
  }
  public void setApproval_flg(String approval_flg) {
        this.approval_flg = approval_flg;
  }
  //コメントのgetter setter
  public String getComments() {
            return comments;
  }
  public void setComments(String comments) {
            this.comments = comments;
  }

}

