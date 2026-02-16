package com.prospire.prospireweb.exception;

/**
 * 予算の重複登録を表す例外クラス。
 */
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
