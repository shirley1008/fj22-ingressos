package br.com.caelum.ingresso.validacao;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Collections;

import org.junit.*;

import br.com.caelum.ingresso.model.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DescontoTest	{

	@Test
	public	void	naoDeveConcederDescontoParaIngressoNormal(){
		Lugar	lugar	=	new	Lugar("A",1);
		Sala	sala	=	new	Sala("Eldorado	-	IMAX",	new	BigDecimal("20.5"));
		Filme	filme	=	new	Filme("Rogue	One",	Duration.ofMinutes(120),		
																		"SCI-FI",	new	BigDecimal("12"));
		Sessao	sessao	=	new	Sessao(LocalTime.parse("10:00:00"),	filme,	sala);
		Ingresso	ingresso	=	new	Ingresso(sessao,	TipoDeIngresso.INTEIRO,lugar);
		BigDecimal	precoEsperado	=	new	BigDecimal("32.5");
		Assert.assertEquals(precoEsperado,	ingresso.getPreco());
	}
}