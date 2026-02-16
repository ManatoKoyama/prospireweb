package com.prospire.prospireweb.repository;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Param;

import com.prospire.prospireweb.model.GetBudgetFix;


@Mapper
/**
 * 予算FIX関連のデータアクセス用 MyBatis マッパーインタフェース。
 */
public interface BudgetFixMapper {
  //select全件
	public List<GetBudgetFix> find();
}
