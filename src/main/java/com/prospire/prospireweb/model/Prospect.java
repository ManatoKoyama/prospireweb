package com.prospire.prospireweb.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 見込（Prospect）を表すモデルクラス。
 */
@Data
@NoArgsConstructor
public class Prospect {

    private String ki; // 対象期
    private String bu; // 部門
    private String customer; // 顧客
    private String project; // 案件名
    private String orderProbability; // 確度
    private String orderM; // 受注月
    private String recordM; // 売上計上月
    private String expenseItem; // 費目
    private String details; // 細目
    private String price; // 金額
    private String status; // 状況区分
    private String changeDeteFlg; // 変更判別フラグ

}