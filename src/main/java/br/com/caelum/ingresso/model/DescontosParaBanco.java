package br.com.caelum.ingresso.model;


import java.math.BigDecimal;
import java.math.RoundingMode;

public class DescontosParaBanco implements Desconto {
    
    public BigDecimal aplicarDescontoSobre(BigDecimal	precoOriginal) {
        return precoOriginal.subtract(trintaPorCentoSobre(precoOriginal));
    }
    
    private BigDecimal trintaPorCentoSobre(BigDecimal precoOriginal) {
        return precoOriginal.multiply(new BigDecimal("0.3"));
    }
    
	public	String	getDescricao() {
	    return "Desconto Banco";
	}
}