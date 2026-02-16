package com.prospire.prospireweb.service;

import java.util.List;

import com.prospire.prospireweb.form.RevisionOfBudgetInputForm;

/**
 * 見込入力に関するサービスのインタフェース定義。
 */
public interface RevisionOfBudgetInputService{

    //見込テーブルデータ取得
    public List<RevisionOfBudgetInputForm> findByKiAndBu(String ki, String bu);

    //見込テーブルデータ確認
    public boolean hasBudgets(String ki, String bu);

}