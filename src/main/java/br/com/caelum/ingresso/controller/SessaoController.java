package br.com.caelum.ingresso.controller;

import java.util.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.ingresso.dao.FilmeDao;
import br.com.caelum.ingresso.dao.SalaDao;
import br.com.caelum.ingresso.dao.SessaoDao;
import br.com.caelum.ingresso.model.*;
import br.com.caelum.ingresso.model.form.SessaoForm;
import br.com.caelum.ingresso.validacao.GerenciadorDeSessao;
import br.com.caelum.ingresso.rest.OmdbClient;



@Transactional
@Controller // Anotação: configurar o código
public class SessaoController {
	
	@Autowired
	private FilmeDao filmeDao;
	
	@Autowired
	private SalaDao salaDao;
	
	@Autowired
	private SessaoDao sessaoDao;
	
	@Autowired
    private	OmdbClient	client;

	@GetMapping("/admin/sessao")
	public ModelAndView formularioNovaSessao(@RequestParam("salaId") Integer salaId) {
		List<Filme> filmes = filmeDao.findAll();
		Sala sala = salaDao.findOne(salaId);
		
		ModelAndView view = new ModelAndView("sessao/sessao");
		view.addObject("filmes", filmes);
		view.addObject("sala", sala);
		
		return view;
	}
	
	@PostMapping("/admin/sessao")
	public ModelAndView salvaSessao(@Valid SessaoForm sessaoForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return formularioNovaSessao(sessaoForm.getSalaId());
		}
				
		Sessao novaSessao = sessaoForm.toSessao(salaDao, filmeDao);
		List<Sessao> sessoesExistentes = sessaoDao.buscaSessoesDaSala(novaSessao.getSala());
		
		GerenciadorDeSessao gerenciador = new GerenciadorDeSessao(sessoesExistentes);
		if (gerenciador.cabe(novaSessao)) {
			sessaoDao.save(novaSessao);
			return new ModelAndView("redirect:/admin/sala/" + sessaoForm.getSalaId() + "/sessoes");
		}
		
		return formularioNovaSessao(sessaoForm.getSalaId());
	}
	
	@GetMapping("/sessao/{id}/lugares")
	public	ModelAndView	lugaresNaSessao(@PathVariable("id")	Integer	sessaoId){
      
      ModelAndView	modelAndView	=	new	ModelAndView("sessao/lugares");
      
      Sessao	sessao	=	sessaoDao.findOne(sessaoId);
      	
      Optional<ImagemCapa>	imagemCapa	=	client.request(sessao.getFilme(),	ImagemCapa.class);
      
      modelAndView.addObject("sessao",	sessao);
      
      modelAndView.addObject("imagemCapa",	imagemCapa.orElse(new	ImagemCapa()));
      
      return	modelAndView;
	}
}
