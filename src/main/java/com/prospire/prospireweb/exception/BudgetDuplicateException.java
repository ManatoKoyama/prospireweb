package com.prospire.prospireweb.exception;

public class BudgetDuplicateException extends RuntimeException{

    private final String details;

    public BudgetDuplicateException(String detail) {
    super(detail);
    this.details = detail;
    }

    public String getDetails() {
    return details;
    }

}
