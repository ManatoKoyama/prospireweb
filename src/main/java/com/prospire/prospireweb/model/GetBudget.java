package com.prospire.prospireweb.model;

import java.util.Date;

import lombok.Data;

/* 登録された予算取得用
 * 
 * 
 */

@Data
public class GetBudget {
  private String ki;                //対象期
  private String bu;                //対象BU
  private String customer;          //顧客
  private String project;           //案件名
  private String order_probability; //確度
  private Date order_m;             //受注日
  private Date record_m;            //売上計上月
  private String expense_item;      //費目
  private String details;           //細目
  private int price;                //金額
  private String status;            //状況区分
  private Date upd_date;            //更新日時
}
