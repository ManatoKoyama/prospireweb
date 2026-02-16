package com.prospire.prospireweb.service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.prospire.prospireweb.exception.BudgetDuplicateException;
import com.prospire.prospireweb.form.BudgetForm;
import com.prospire.prospireweb.repository.BudgetMapper;

import lombok.RequiredArgsConstructor;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class RegistServiceImpl implements RegistService{
    
    private final BudgetMapper mapper;
    private final HttpSession session;

    /**
     * 指定の期(`ki`)と部署(`bu`)に紐づく予算データを取得します。
     * データが存在しない場合は、初期値を持つ1件のフォームを返します。
     * @param ki 期
     * @param bu 部署
     * @return 取得した予算フォームのリスト
     */
    @Override
    public List<BudgetForm> findByKiAndBu(String ki, String bu){
        List<BudgetForm> previousBudget = mapper.findByKiAndBu(ki, bu);

        if(previousBudget.isEmpty()){
            BudgetForm newForm = new BudgetForm();

            newForm.setKi(ki);
            newForm.setBu(bu);
            newForm.setCustomer("");
            newForm.setProject("");
            newForm.setOrderProbability("");
            newForm.setOrderM(null);
            newForm.setRecordM(null);
            newForm.setExpenseItem("");
            newForm.setDetails("");
            newForm.setPrice(0);
            newForm.setStatus("");
            newForm.setDeleteFlg("");
            newForm.setRegUser("");
            newForm.setRegDate(null);
            newForm.setUpdUser("");
            newForm.setUpdDate(null);

            previousBudget.add(newForm);
        }

        return previousBudget;
 
    }

    /**
     * 予算リストを登録します。既存データは同一の期・部署で削除され、
     * 与えられたリストがそのまま登録されます。
     * 一意制約（重複）を検出した場合は BudgetDuplicateException をスローします。
     * @param budgets 登録対象の予算フォームリスト
     * @return 成功した場合 true
     */
    @Override
    @Transactional
    public boolean registBudget(List<BudgetForm> budgets){
        
        // セッションからkiとbuを取得
        String ki = (String) session.getAttribute("ki");
        String bu = (String) session.getAttribute("bu");

        if (ki == null || bu == null) {
            return false;
        }

        Set<String> seen = new HashSet<>();
        List<String> dupKeys = new ArrayList<>();
        for (BudgetForm form : budgets) {
            String key = checkPkKey(ki, bu, form.getRecordM(), form.getExpenseItem(), form.getDetails());
            if (!seen.add(key)) {
                dupKeys.add(key);
             }
        }

        if (!dupKeys.isEmpty()) {
            throw new BudgetDuplicateException(String.join(", ", dupKeys));
        }

        // 現在のユーザーと日時を設定
        String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        LocalDateTime now = LocalDateTime.now();

         // 既存データ削除
        mapper.deleteByKiAndBu(ki, bu);
        
        for (BudgetForm budget : budgets) {
            budget.setKi(ki);
            budget.setBu(bu);
            budget.setStatus("0"); // ステータス
            budget.setDeleteFlg("0"); // 削除フラグ
            budget.setRegUser(currentUser); //登録者
            budget.setRegDate(now); //登録日時
            budget.setUpdUser(currentUser); //更新者
            budget.setUpdDate(now); //更新日時

        }
        
        //予算登録
        mapper.registBudget(budgets);

        return true;
    }

    /**
     * 重複検出用のキーを生成します。
     */
    private static String checkPkKey(String ki, String bu, Date recordM, String expenseItem, String details) {
        // 比較用のキー文字列（trim して正規化）
        return "期=" + ki
            + ", 部門=" + bu
            + ", 計上月=" + recordM
            + ", 費目=" + expenseItem.trim()
            + ", 細目=" + details.trim();
     }
}