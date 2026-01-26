package com.prospire.prospireweb.repository;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Param;

import com.prospire.prospireweb.model.GetBudgetFix;


@Mapper
public interface BudgetFixMapper {
  //select全件
	public List<GetBudgetFix> find();
}
