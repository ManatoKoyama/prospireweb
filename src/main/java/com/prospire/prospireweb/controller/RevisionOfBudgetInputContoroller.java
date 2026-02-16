package com.prospire.prospireweb.controller;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.prospire.prospireweb.constant.MessageConst;
import com.prospire.prospireweb.form.RevisionOfBudgetInputForm;
import com.prospire.prospireweb.service.RevisionOfBudgetInputService;

import lombok.RequiredArgsConstructor;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("")
@RequiredArgsConstructor
/**
 * 見込入力関連の画面遷移を担当するコントローラ。
 */
public class RevisionOfBudgetInputContoroller {

    //予算登録確認画面Service
    private final RevisionOfBudgetInputService service;
    //セッション
    private final HttpSession session;
    //メッセージソース
    private final MessageSource messageSource;

    @GetMapping("/revisionOfBudgetInput")
    public String revisionOfBudgetInput(@RequestParam(defaultValue = "38") String ki,
                                        @RequestParam(defaultValue = "1") String bu, 
                                        Model model,
                                        Locale locale){

        // セッションに保存
        session.setAttribute("ki", ki);
        session.setAttribute("bu", bu);

        // 目標金額
        int goalAmount = 1000;

        // 数値をフォーマット
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.JAPAN);
        String goalValue = numberFormat.format(goalAmount);


        List<RevisionOfBudgetInputForm> previousBudget = service.findByKiAndBu(ki, bu);

        if(!service.hasBudgets(ki, bu)){
            String errorMessage = messageSource.getMessage(MessageConst.REVISION_OF_BUDGET_NO_DATA_ERROR, null, locale);
            model.addAttribute("errorMessage", errorMessage);
        }

        model.addAttribute("ki", ki);
        model.addAttribute("bu", bu);
        model.addAttribute("goalValue", goalValue);
        model.addAttribute("previousBudget", previousBudget);

        return "revisionOfBudgetInput";
    }
}