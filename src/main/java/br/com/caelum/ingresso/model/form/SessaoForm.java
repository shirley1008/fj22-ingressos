package br.com.caelum.ingresso.model.form;

import br.com.caelum.ingresso.dao.SalaDao;
import br.com.caelum.ingresso.dao.FilmeDao;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.time.LocalTime;
import javax.validation.constraints.NotNull;
import br.com.caelum.ingresso.model.Lugar;
import br.com.caelum.ingresso.model.Sala;
import org.springframework.format.annotation.DateTimeFormat;

public class SessaoForm {
    @NotNull
    private Integer salaId;
    
    @DateTimeFormat(pattern="HH:mm")
    @NotNull
    private LocalTime	horario;

    @NotNull
    private Integer filmeId;
    
    public Integer getSalaId (){
      return  this.salaId;
    }
    
    public void setSalaId (Integer salaId){
        this.salaId = salaId;
    }
     public Integer getFilmeId (){
      return  this.filmeId;
    }
    
    public void setFilmeId (Integer filmeId){
        this.filmeId = filmeId;
    }
    
     public LocalTime getHorario (){
      return  this.horario;
    }
    
    public void setHorario (LocalTime horario){
        this.horario = horario;
    }
    
    public	Sessao	toSessao(SalaDao	salaDao,	FilmeDao	filmeDao){
	    Filme	filme	=	filmeDao.findOne(filmeId);
        Sala	sala	=	salaDao.findOne(salaId);
        Sessao	sessao	=	new	Sessao(horario,	sala,	filme);
        
	    return	sessao;

    }

}
