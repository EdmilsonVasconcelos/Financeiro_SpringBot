package com.mballem.demomvc.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.mballem.demomvc.domain.Cargo;
import com.mballem.demomvc.service.CargoService;

@Component
public class StringToCargoConversor implements Converter<String, Cargo> {
	
	@Autowired
	private CargoService cargoService;
	
	public Cargo convert(String text) {
		if(text.isEmpty()) {
			return null;
		}
		
		Long id = Long.valueOf(text);
		return cargoService.buscarPorId(id);
		
	}
}
