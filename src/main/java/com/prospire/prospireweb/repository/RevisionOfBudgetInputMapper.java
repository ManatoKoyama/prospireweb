package com.prospire.prospireweb.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.prospire.prospireweb.form.RevisionOfBudgetInputForm;

@Mapper
public interface RevisionOfBudgetInputMapper {
    //見込テーブルデータ取得
    List<RevisionOfBudgetInputForm> findByKiAndBu(String ki, String bu);

}