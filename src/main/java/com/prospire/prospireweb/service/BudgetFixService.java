
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

  // 部署で絞り込み（サーバ側フィルタ）
  public List<GetBudgetFix> findByDepartment(String department) {
    if (department == null || department.isBlank()) {
      return getList();
    }
    return getList().stream()
      .filter(b -> department.equals(b.getBu()))
      .toList();
  }

  // 部署一覧を取得（ユニークな部署を返す）
  public List<String> getDepartments() {
    return getList().stream()
      .map(GetBudgetFix::getBu)
      .filter(d -> d != null && !d.isBlank())
      .distinct()
      .toList();
  }

  // 期一覧を取得（ユニークな期を返す）。モデルに期フィールドがない場合は空リスト
  public List<String> getTerms() {
    return getList().stream()
      .map(GetBudgetFix::getKi)
      .filter(t -> t != null && !t.isBlank())
      .distinct()
      .toList();
  }

  // 部署ごとの合計金額を返す（存在しない場合は 0）
  public long getTotalByDepartment(String department) {
    return getList().stream()
      .filter(b -> department == null || department.isBlank() || department.equals(b.getBu()))
      .mapToLong(b -> b.getPrice())
      .sum();
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
