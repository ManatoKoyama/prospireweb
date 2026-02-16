package com.prospire.prospireweb.controller;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import com.prospire.prospireweb.constant.MessageConst;
import com.prospire.prospireweb.exception.BudgetDuplicateException;
import com.prospire.prospireweb.form.BudgetForm;
import com.prospire.prospireweb.form.RegistForm;
import com.prospire.prospireweb.service.RegistService;
import lombok.RequiredArgsConstructor;

import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("")
@RequiredArgsConstructor
public class BudgetRegistCheckController {

    // 予算登録確認画面用サービス
    private final RegistService service;
    // セッション
    private final HttpSession session;
    // メッセージソース
    private final MessageSource messageSource;

    /**
     * 予算登録確認画面を表示します（テストデータをセットして表示）。
     * @param ki 期（デフォルト: 38）
     * @param bu 部署（デフォルト: 1）
     */
    @GetMapping("/budgetRegistCheck")
    public String budgetRegistCheck(@RequestParam(defaultValue = "38") String ki, @RequestParam(defaultValue = "1") String bu, Model model) throws ParseException{

        // セッションに保存
        session.setAttribute("ki", ki);
        session.setAttribute("bu", bu);

        RegistForm registForm = new RegistForm();
        List<BudgetForm> budgets = new ArrayList<>();

        // サンプル日付の設定
        java.sql.Date orderDate = java.sql.Date.valueOf("2024-06-01");
        java.sql.Date recordDate = java.sql.Date.valueOf("2024-07-01");
        LocalDateTime regDate = LocalDateTime.parse("2025-10-01T00:00:00");

        // テストデータ生成
        BudgetForm test1 = new BudgetForm();
        test1.setKi("38"); test1.setBu("1"); test1.setCustomer("顧客"); test1.setProject("案件"); test1.setOrderProbability("A");
        test1.setOrderM(orderDate); test1.setRecordM(recordDate); test1.setExpenseItem("費用1"); test1.setDetails("費用1"); test1.setPrice(100000000);
        test1.setStatus("1"); test1.setDeleteFlg("0"); test1.setRegUser("user"); test1.setRegDate(regDate); test1.setUpdUser(null); test1.setUpdDate(null);
        budgets.add(test1);
        
        BudgetForm test2 = new BudgetForm();
        test2.setKi("38"); test2.setBu("1"); test2.setCustomer("顧客"); test2.setProject("案件"); test2.setOrderProbability("A");
        test2.setOrderM(orderDate); test2.setRecordM(recordDate); test2.setExpenseItem("費用2"); test2.setDetails("費用2"); test2.setPrice(10000);
        test2.setStatus("1"); test2.setDeleteFlg("0"); test2.setRegUser("user"); test2.setRegDate(regDate); test2.setUpdUser(null); test2.setUpdDate(null);
        budgets.add(test2);

        BudgetForm test3 = new BudgetForm();
        test3.setKi("38"); test3.setBu("1"); test3.setCustomer("顧客"); test3.setProject("案件"); test3.setOrderProbability("A");
        test3.setOrderM(orderDate); test3.setRecordM(recordDate); test3.setExpenseItem("費用3"); test3.setDetails("費用3"); test3.setPrice(100000);
        test3.setStatus("1"); test3.setDeleteFlg("0"); test3.setRegUser("user"); test3.setRegDate(regDate); test3.setUpdUser(null); test3.setUpdDate(null);
        budgets.add(test3);

        BudgetForm test4 = new BudgetForm();
        test4.setKi("38"); test4.setBu("1"); test4.setCustomer("顧客"); test4.setProject("案件"); test4.setOrderProbability("A");
        test4.setOrderM(orderDate); test4.setRecordM(recordDate); test4.setExpenseItem("費用4"); test4.setDetails("費用4"); test4.setPrice(100);
        test4.setStatus("1"); test4.setDeleteFlg("0"); test4.setRegUser("user"); test4.setRegDate(regDate); test4.setUpdUser(null); test4.setUpdDate(null);
        budgets.add(test4);

        registForm.setBudgets(budgets);
        model.addAttribute("registForm", registForm);

        // ここで共通データをセッションに保存
        setCommonAttributes(model, ki, bu);

        return "budgetRegistCheck";
    }


    /**
     * フォームから受け取った予算を登録します。
     */
    @PostMapping(value="regist", params = "regist")
    public String registBudget(@ModelAttribute("registForm") RegistForm registForm,
                               BindingResult bindingResult,
                               @RequestParam String ki, 
                               @RequestParam String bu, 
                               Model model,
                               Locale locale) {
        
        List<BudgetForm> budgets = registForm.getBudgets();

        //予算登録
        try{
        boolean success =service.registBudget(budgets);

            if(!success){
                // 共通データを再設定
                setCommonAttributes(model, ki, bu);

                // エラーメッセージを追加
                String errorMessage = messageSource.getMessage(MessageConst.BUDGET_REGIST_ERROR, null, locale);
                model.addAttribute("errorMessage", errorMessage);
                //本来は予算登録入力画面に遷移する
                return "budgetRegistCheck";
            }
        }catch(BudgetDuplicateException e){
            setCommonAttributes(model, ki, bu);
            String msg = messageSource.getMessage(
            MessageConst.BUDGET_DUPLICATE,
            new Object[]{e.getDetails()},
            locale
            );
            model.addAttribute("errorMessage", msg);
            //本来は予算登録入力画面に遷移する
            return "budgetRegistCheck"; 
        }

        //本来は予算登録完了画面に遷移する
        return "top";
    }

    
    /**
     * 予算登録のキャンセル処理。セッションをクリアします。
     */
    @PostMapping(value="regist", params = "cancel")
    public String cancelRegistBudget(@ModelAttribute("registForm") RegistForm registForm, Model model, SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        
        // セッションからkiとbuを削除
        session.removeAttribute("ki");
        session.removeAttribute("bu");
        
        //本来は予算登録画面に戻る
        return "top";
    }

     //共通データ
    private void setCommonAttributes(Model model, String ki, String bu) {
        // 目標金額
        int goalAmount = 1000;

        // 数値をフォーマット
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.JAPAN);
        String goalValue = numberFormat.format(goalAmount);

        List<BudgetForm> previousBudget = service.findByKiAndBu(ki, bu);

        model.addAttribute("ki", ki);
        model.addAttribute("bu", bu);
        model.addAttribute("goalValue", goalValue);
        model.addAttribute("previousBudget", previousBudget);
    }
}