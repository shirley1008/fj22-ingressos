package br.com.caelum.ingresso.model;


import java.math.BigDecimal;
import java.math.RoundingMode;

public class DescontosParaEstudantes implements Desconto {
   
    public BigDecimal aplicarDescontoSobre(BigDecimal precoOriginal) {
        return precoOriginal.divide(new BigDecimal("2.0"));
    
    }
    
	public	String	getDescricao() {
	    return "Desconto Estudante";
	}
}