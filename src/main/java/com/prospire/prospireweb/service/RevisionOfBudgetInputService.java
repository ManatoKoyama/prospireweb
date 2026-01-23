package com.prospire.prospireweb.service;

import java.util.List;

import com.prospire.prospireweb.form.RevisionOfBudgetInputForm;

public interface RevisionOfBudgetInputService{

    //見込テーブルデータ取得
    public List<RevisionOfBudgetInputForm> findByKiAndBu(String ki, String bu);

    //見込テーブルデータ確認
    public boolean hasBudgets(String ki, String bu);

}