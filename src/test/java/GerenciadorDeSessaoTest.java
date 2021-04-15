package br.com.caelum.ingresso.validacao;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Collections;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;

public class GerenciadorDeSessaoTest {

	@Test
	public void naoDevePermitirSessoesQuandoElasTerminaremNoDiaSeguinte() {
		// ARRANJAR o cenário
		Sala sala = new Sala("Sala 3D");
		Filme filme = new Filme("O Resgate do Soldado Ryan", Duration.ofMinutes(120), "Guerra");
		
		Sessao sessaoQueTeminaNoDiaSeguinte = new Sessao(LocalTime.of(23, 0), filme, sala);
		
		// AGIR na funcionalidade
		GerenciadorDeSessao gerenciador = new GerenciadorDeSessao(Collections.emptyList());
		boolean cabe = gerenciador.cabe(sessaoQueTeminaNoDiaSeguinte);
				
		// ASSEGURAR os resultados esperados
		Assert.assertFalse(cabe); // Hamcrest
	}
	
	@Test
	public void devePermitirSessoesQueTerminaNoMesmoDia() {
		// ARRANJAR o cenário
		Sala sala = new Sala("Sala 3D");
		Filme filme = new Filme("O Resgate do Soldado Ryan", Duration.ofMinutes(120), "Guerra");
		
		Sessao sessao = new Sessao(LocalTime.of(19, 0), filme, sala);
		
		// AGIR na funcionalidade
		GerenciadorDeSessao gerenciador = new GerenciadorDeSessao(Collections.emptyList());
		boolean cabe = gerenciador.cabe(sessao);
				
		// ASSEGURAR os resultados esperados
		Assert.assertTrue(cabe); // Hamcrest
	}
      
       
 //   public	void	preparaSessoes(){
 //   	this.rogueOne	=	new	Filme("Rogue	One",	Duration.ofMinutes(120),	"SCI-FI",BigDecimal.ONE);
 //       this.sala3D	=	new	Sala("Sala	3D",	BigDecimal.TEN);
	//     this.sessaoDasDez	=	new	Sessao(LocalTime.parse("10:00:00"),	rogueOne,	sala3D);
	// 	this.sessaoDasTreze	=	new	Sessao(LocalTime.parse("13:00:00"),	rogueOne,	sala3D);
	// 	this.sessaoDasDezoito	=	new	Sessao(LocalTime.parse("18:00:00"),	rogueOne,	sala3D);
	// }
	
}



