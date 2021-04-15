package br.com.caelum.ingresso.model;


import java.math.BigDecimal;
import java.math.RoundingMode;

public class SemDesconto implements Desconto {
    @Override
    public BigDecimal aplicarDescontoSobre(BigDecimal precoOriginal) {
        return precoOriginal;
    }
    
    @Override
	public	String	getDescricao() {
	    return "Normal";
	}
}