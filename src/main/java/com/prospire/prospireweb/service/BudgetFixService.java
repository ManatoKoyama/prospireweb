package com.prospire.prospireweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prospire.prospireweb.repository.BudgetFixMapper;
import com.prospire.prospireweb.model.GetBudget;

public class BudgetFixService {
  
  //BudgetFixMapperに依存してもらう
  @Autowired
	BudgetFixMapper mapper;
  //select全件
  public List<GetBudget> getList() {
    return mapper.find();
  }
}
