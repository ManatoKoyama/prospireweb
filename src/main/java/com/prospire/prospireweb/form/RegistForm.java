package com.prospire.prospireweb.form;
import java.util.List;

import lombok.Data;

/*
*予算登録Formクラス
*/

@Data
public class RegistForm {

    //予算リスト
    private List<BudgetForm> budgets;

}