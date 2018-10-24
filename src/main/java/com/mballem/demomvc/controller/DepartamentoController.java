package com.mballem.demomvc.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.annotation.JsonView;
import com.mballem.demomvc.domain.Departamento;
import com.mballem.demomvc.domain.jsonviews.GenericViews;
import com.mballem.demomvc.service.DepartamentoService;

@Controller
@RequestMapping("/departamentos")
public class DepartamentoController {

	@Autowired
	private DepartamentoService service;

	@GetMapping("/cadastrar")
	public String cadastrar(Departamento departamento) {
		return "departamento/cadastro";
	}

	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("departamentos", service.buscarTodos());
		return "departamento/lista";
	}

	@GetMapping("/api/listar")
	@JsonView(GenericViews.LazyView.class)
	public ResponseEntity<List<Departamento>> listar() {
		return new ResponseEntity<>(service.buscarTodos(), HttpStatus.OK);
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Departamento departamento, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			return "departamento/cadastro";
		}

		service.salvar(departamento);
		attr.addFlashAttribute("success", "Departamento inserido com sucesso.");
		return "redirect:/departamentos/cadastrar";
	}

	@PostMapping("/api/salvar")
	@JsonView(GenericViews.LazyView.class)
	public ResponseEntity<Departamento> salvar(@Valid Departamento departamento) {
		return new ResponseEntity<Departamento>(service.salvar(departamento), HttpStatus.CREATED);
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("departamento", service.buscarPorId(id));
		return "departamento/cadastro";
	}

	@PostMapping("/editar")
	public String editar(@Valid Departamento departamento, BindingResult result, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return "departamento/cadastro";
		}

		service.editar(departamento);
		attr.addFlashAttribute("success", "Departamento editado com sucesso.");
		return "redirect:/departamentos/cadastrar";
	}

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {

		if (service.departamentoTemCargos(id)) {
			model.addAttribute("fail", "Departamento não removido. Possui cargo(s) vinculado(s).");
		} else {
			service.excluir(id);
			model.addAttribute("success", "Departamento excluído com sucesso.");
		}

		return listar(model);
	}

	@GetMapping("/api/excluir/{id}")
	public ResponseEntity<String> excluir(@PathVariable("id") Long id) {
		if (service.departamentoTemCargos(id)) {
			return ResponseEntity.badRequest().body("Departamento não removido. Possui cargo(s) vinculado(s).");
		} else {
			service.excluir(id);
			return ResponseEntity.ok().build();
		}
	}

}
