import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.BindingResult;
import br.com.caelum.ingresso.dao.FilmeDao;
import br.com.caelum.ingresso.dao.SalaDao;
import br.com.caelum.ingresso.dao.SessaoDao;
import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.form.SessaoForm;
import javax.validation.Valid;

@Controller
public class SessaoController	{
    
	@Autowired
	private	SalaDao	salaDao;

	@Autowired
	private	FilmeDao	filmeDao;
	
	@Autowired
	private	SessaoDao	sessaoDao;
	
	
	//demais	métodos
	@PostMapping(value	=	"/admin/sessao")
	@Transactional
	public	ModelAndView	salva(@Valid	SessaoForm	form, BindingResult	result) {
	    if	(result.hasErrors())	return	form(form.getSalaId(),form);
		Sessao	sessao	=	form.toSessao(salaDao,	filmeDao);
		sessaoDao.save(sessao);
		return	new	ModelAndView("redirect:/admin/sala/"	+	form.getSalaId()	+	"/sessoes");
	}

    @GetMapping("/admin/sessao")
	public	ModelAndView	form(@RequestParam("salaId")	Integer	salaId, SessaoForm form)	{
		ModelAndView	modelAndView	=	new	ModelAndView("sessao/sessao");
		modelAndView.addObject("sala",	salaDao.findOne(salaId));
		modelAndView.addObject("filmes",	filmeDao.findAll());
		modelAndView.addObject("form",	form);

		return	modelAndView;
}
}


