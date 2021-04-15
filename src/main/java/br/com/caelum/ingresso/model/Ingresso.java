package br.com.caelum.ingresso.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.*;

import br.com.caelum.ingresso.model.Desconto;

@Entity
public class Ingresso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Sessao sessao;
	private BigDecimal preco = BigDecimal.ZERO;
	
	@ManyToOne
	private	Lugar	lugar;
	
	@Enumerated(EnumType.STRING)
	private	TipoDeIngresso	tipoDeIngresso;
	
	/**
	 * @deprecated Somente JPA
	 */
	public Ingresso() {
		
	}
	
	/* SOLID: Open-Close Principle -> Aberta para extensão e fechada para alteração */ 
	// public Ingresso(Sessao sessao, Desconto tipoDeDesconto) {
	// 	this.sessao = sessao;
	// 	//this.preco = desconto.calculaPrecoFinal(sessao);
	// 	this.preco	=	tipoDeDesconto.aplicarDescontoSobre(sessao.getPreco());
		
		
	// 	/*
	// 	if (desconto.equals("ESTUDANTE")) {
	// 		this.preco = sessao.getPreco().multiply(new BigDecimal("0.5"));
	// 	} else if (desconto.equals("BANCARIO")) {
	// 		this.preco = sessao.getPreco().multiply(new BigDecimal("0.7"));
	// 	} else {
	// 		this.preco = sessao.getPreco();
	// 	}
	// 	*/
	// }
	
	public	Ingresso(Sessao	sessao,	TipoDeIngresso	tipoDeIngresso,	Lugar	lugar) {
		this.sessao	=	sessao;
		this.preco	=	tipoDeIngresso.aplicaDesconto(sessao.getPreco());
		this.lugar	=	lugar;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Sessao getSessao() {
		return sessao;
	}

	public void setSessao(Sessao sessao) {
		this.sessao = sessao;
	}

	public BigDecimal getPreco() {
		return preco.setScale(2, RoundingMode.HALF_UP);
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

}
