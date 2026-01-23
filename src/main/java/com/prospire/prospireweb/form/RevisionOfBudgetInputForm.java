package com.prospire.prospireweb.form;
import java.sql.Date;
import java.time.LocalDateTime;

import lombok.Data;

/*
*見込修正入力画面Formクラス
*/

@Data
public class RevisionOfBudgetInputForm {

    //期
    private String ki;

    //部門
    private String bu;

    //顧客
    private String customer;

    //案件名
    private String project;

    //確度
    private String orderProbability;

    //受注月
    private Date orderM;

    //売上計上月
    private Date recordM;

    //費目
    private String expenseItem;

    //細目
    private String details;

    //金額
    private Integer price;

    //状態区分
    private String status;

    //変更判別フラグ
    private String changeDataFlg;

    //削除フラグ
    private String deleteFlg;

    //登録者
    private String regUser;

    //登録日時
    private LocalDateTime regDate;

    //更新者
    private String updUser;

    //更新日時
    private LocalDateTime updDate;
  
    //表示用金額
    public String getFormattedPrice(){
        return java.text.NumberFormat.getNumberInstance(java.util.Locale.JAPAN).format(this.price);
    }
}