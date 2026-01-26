package com.prospire.prospireweb.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import com.prospire.prospireweb.form.BudgetForm;

@Mapper
public interface BudgetMapper {
    //変更前予算データ取得
    List<BudgetForm> findByKiAndBu(String ki, String bu);

    // 対象ki/buの既存データを削除
    int deleteByKiAndBu(@Param("ki") String ki, @Param("bu") String bu);

    //予算データ登録
    void registBudget(@Param("registForm") List<BudgetForm> registForm);
}