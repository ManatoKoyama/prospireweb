package com.prospire.prospireweb.service;

import java.util.List;

import com.prospire.prospireweb.form.BudgetForm;

public interface RegistService{

    public List<BudgetForm> findByKiAndBu(String ki, String bu);

    public boolean registBudget(List<BudgetForm> budgets);

}