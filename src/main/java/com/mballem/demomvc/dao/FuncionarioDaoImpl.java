package com.mballem.demomvc.dao;

import org.springframework.stereotype.Repository;

import com.mballem.demomvc.domain.Funcionario;

@Repository
public class FuncionarioDaoImpl extends AbstractDao<Funcionario, Long> implements FuncionarioDao {

}
