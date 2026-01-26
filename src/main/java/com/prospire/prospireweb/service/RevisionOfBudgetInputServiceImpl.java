package com.prospire.prospireweb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.prospire.prospireweb.form.RevisionOfBudgetInputForm;
import com.prospire.prospireweb.repository.RevisionOfBudgetInputMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RevisionOfBudgetInputServiceImpl implements RevisionOfBudgetInputService{
    
    private final RevisionOfBudgetInputMapper mapper;

    @Override
    public List<RevisionOfBudgetInputForm> findByKiAndBu(String ki, String bu){

        //見込テーブルデータ取得
        List<RevisionOfBudgetInputForm> previousBudget = mapper.findByKiAndBu(ki, bu);

        return previousBudget;
    }

    @Override
    public boolean hasBudgets(String ki, String bu){

        List<RevisionOfBudgetInputForm> budgets = mapper.findByKiAndBu(ki, bu);

        return budgets != null && !budgets.isEmpty();
    }

}