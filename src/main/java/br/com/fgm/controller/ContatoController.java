package br.com.fgm.controller;

import javax.validation.Valid; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.fgm.dao.ContatoDao;
import br.com.fgm.model.Contato;


@Controller
public class ContatoController {

	private final ContatoDao dao;

	@Autowired
	public ContatoController(ContatoDao dao) {
		System.out.println("ContatoController");
		this.dao = dao;
	}
	
	@ModelAttribute("contato")
	public Contato createContatoModel() {
		System.out.println("ContatoController-createContatoModel");
		return new Contato();
	}
	
	@RequestMapping("ctl_contatoNovo")
	public String form() {
		return "contato/contato-formAdiciona";
	}
	
	@RequestMapping("ctl_contatoAdiciona")
	public String adiciona(@Valid Contato contato, BindingResult result) {
		//result.hasFieldErrors("nome")
		if (result.hasErrors()) {
			return "contato/contato-formAdiciona";			
		}
		dao.adiciona(contato);
		return "contato/contato-adicionado";
		//return "redirect:listaContato";
	}

	@RequestMapping("ctl_contatoMostra")
	public String mostra(Long id, Model model) {
		model.addAttribute("contato", dao.getContatoPorID(id));
		return "contato/contato-formAltera";
	}

	@RequestMapping("ctl_contatoAltera")
	public String altera(@Valid Contato contato, BindingResult result) {
		//result.hasFieldErrors("nome")
		if (result.hasErrors()) {
			return "contato/contato-formAltera";			
		}		
		dao.altera(contato);
		return "redirect:ctl_contatoLista";
	}

	@RequestMapping("ctl_contatoLista")
	public String lista(Model model) {
		model.addAttribute("contato", dao.Lista());
		return "contato/contato-lista";
	}

	@RequestMapping("ctl_contatoExclui")
	public String remove(Contato contato) {
		dao.exclui(contato);
	/* Podemos fazer um redirecionamento
	na lado do servidor (forward) ou pelo navegador, 
	no lado do cliente (redirect).
	*/		
		return "redirect:ctl_contatoLista";
	}
	
}
