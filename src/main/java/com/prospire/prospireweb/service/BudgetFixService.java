
package com.prospire.prospireweb.service;

import java.util.List;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import com.prospire.prospireweb.repository.BudgetFixMapper;
import com.prospire.prospireweb.form.BudgetFixForm;
import com.prospire.prospireweb.model.GetBudgetFix;

@Service
@RequiredArgsConstructor
public class BudgetFixService {

  /** BudgetFixMapper: MyBatis マッパー（読み取り中心） */
  private final BudgetFixMapper mapper;
  /**
   * すべての予算FIXレコードを取得します。
   * @return 全件のリスト
   */
  public List<GetBudgetFix> getList() {
    return mapper.find();
  }

  /**
   * 指定した部署でリストをフィルタリングします。department が null/空白の場合は全件を返します。
   * @param department 部署名
   * @return フィルタ済みのリスト
   */
  public List<GetBudgetFix> findByDepartment(String department) {
    if (department == null || department.isBlank()) {
      return getList();
    }
    return getList().stream()
      .filter(b -> department.equals(b.getBu()))
      .toList();
  }

  /**
   * 登録されている部署一覧（ユニーク）を返します。
   * @return 部署名リスト
   */
  public List<String> getDepartments() {
    return getList().stream()
      .map(GetBudgetFix::getBu)
      .filter(d -> d != null && !d.isBlank())
      .distinct()
      .toList();
  }

  /**
   * 登録されている期一覧（ユニーク）を返します。
   * @return 期のリスト
   */
  public List<String> getTerms() {
    return getList().stream()
      .map(GetBudgetFix::getKi)
      .filter(t -> t != null && !t.isBlank())
      .distinct()
      .toList();
  }

  /**
   * 指定部署の合計金額を算出します。
   * @param department 部署名（null/空の場合は全件対象）
   * @return 合計金額
   */
  public long getTotalByDepartment(String department) {
    return getList().stream()
      .filter(b -> department == null || department.isBlank() || department.equals(b.getBu()))
      .mapToLong(b -> b.getPrice())
      .sum();
  }

  /**
   * 予算FIXを保存します（未実装）。
   * @param sessionForm 保存対象フォーム
   */
  public void saveBudgetFix(BudgetFixForm sessionForm){
    // TODO: 予算FIXの保存ロジックを実装する
  }
  //予算FIXのバリデーションロジックを書いてみる
  /**
   * 予算FIXのバリデーションを行います。
   * @param sessionForm 対象フォーム
   * @param bindingResult バリデーション結果格納用
   */
  public void ValidateBudgetFix(BudgetFixForm sessionForm, org.springframework.validation.BindingResult bindingResult){
    // TODO: 予算FIXのバリデーションロジックを実装する
    /*/
     String com = sessionForm.getComments();
     if(com.length() > 1500){
      bindingResult.addError(new org.springframework.validation.ObjectError("comments", "コメントは1500文字以内で入力してください"));
       //後で考える throw new IllegalArgumentException("コメントは1500文字以内で入力してください");
     }
  
  
    */}
}
