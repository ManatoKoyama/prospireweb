package com.prospire.prospireweb.repository;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Param;

import com.prospire.prospireweb.model.GetBudget;


@Mapper
public interface BudgetFixMapper {
  //select全件
	public List<GetBudget> find();
}
