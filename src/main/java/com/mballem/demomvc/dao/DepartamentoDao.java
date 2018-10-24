package com.mballem.demomvc.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mballem.demomvc.domain.Departamento;


public interface DepartamentoDao extends CrudRepository<Departamento, Long>{
	
	List<Departamento> findAll();
	
}
