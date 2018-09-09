package com.mballem.demomvc.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.mballem.demomvc.domain.Departamento;
import com.mballem.demomvc.service.DepartamentoService;

@Component
public class StringToDepartamentoConverter implements Converter<String, Departamento> {
	
	@Autowired
	private DepartamentoService departamentoService;

	@Override
	public Departamento convert(String text) {
		if(text.isEmpty()) {
			return null;
		}
		
		Long id = Long.valueOf(text);
		return departamentoService.buscarPorId(id);
	}

}
