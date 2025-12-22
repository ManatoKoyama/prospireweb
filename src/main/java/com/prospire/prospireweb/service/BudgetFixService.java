
package com.prospire.prospireweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prospire.prospireweb.repository.BudgetFixMapper;
import com.prospire.prospireweb.form.BudgetFixForm;
import com.prospire.prospireweb.model.GetBudgetFix;

@Service
public class BudgetFixService {
  
  //BudgetFixMapperに依存してもらう
  @Autowired
	BudgetFixMapper mapper;
  //select全件
  public List<GetBudgetFix> getList() {
    return mapper.find();
  }

  public void saveBudgetFix(BudgetFixForm sessionForm){
    //TODO 予算FIXの保存ロジックを実装する
   
  }
  //予算FIXのバリデーションロジックを書いてみる
  public void ValidateBudgetFix(BudgetFixForm sessionForm, org.springframework.validation.BindingResult bindingResult){
    //TODO 予算FIXのバリデーションロジックを実装する
    /*/
     String com = sessionForm.getComments();
     if(com.length() > 1500){
      bindingResult.addError(new org.springframework.validation.ObjectError("comments", "コメントは1500文字以内で入力してください"));
       //後で考える throw new IllegalArgumentException("コメントは1500文字以内で入力してください");
     }
  
  
    */}
}
