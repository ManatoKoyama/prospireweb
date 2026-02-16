package com.prospire.prospireweb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.prospire.prospireweb.form.RevisionOfBudgetInputForm;
import com.prospire.prospireweb.repository.RevisionOfBudgetInputMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RevisionOfBudgetInputServiceImpl implements RevisionOfBudgetInputService{
    /** RevisionOfBudgetInputMapper: DBアクセス用マッパー */
    private final RevisionOfBudgetInputMapper mapper;

    /**
     * 指定の期(`ki`)と部署(`bu`)に紐づく見込データを取得します。
     * @param ki 期
     * @param bu 部署
     * @return 見込データのリスト（存在しない場合は空リスト）
     */
    @Override
    public List<RevisionOfBudgetInputForm> findByKiAndBu(String ki, String bu){
        // 見込テーブルデータ取得
        List<RevisionOfBudgetInputForm> previousBudget = mapper.findByKiAndBu(ki, bu);
        return previousBudget;
    }

    /**
     * 指定期・部署に見込データが存在するかどうかを判定します。
     * @param ki 期
     * @param bu 部署
     * @return 存在する場合は true
     */
    @Override
    public boolean hasBudgets(String ki, String bu){
        List<RevisionOfBudgetInputForm> budgets = mapper.findByKiAndBu(ki, bu);
        return budgets != null && !budgets.isEmpty();
    }

}