package br.com.caelum.ingresso.model;

import java.util.*;
import java.math.*;

import java.math.BigDecimal;

public enum	TipoDeIngresso	{
	INTEIRO(new	SemDesconto()),
	ESTUDANTE(new	DescontosParaEstudantes()),
	BANCO(new	DescontosParaBanco());
	private final	Desconto	desconto;
	
	TipoDeIngresso(Desconto	desconto)	{
					this.desconto	=	desconto;
	}
	
	public	BigDecimal	aplicaDesconto(BigDecimal	valor){
		return	desconto.aplicarDescontoSobre(valor);
	}
	
	public	String	getDescricao(){
					return	desconto.getDescricao();
	}
}