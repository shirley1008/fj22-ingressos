package br.com.caelum.ingresso.model;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public class Sessao {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalTime horario;
	private BigDecimal preco = BigDecimal.ZERO;
	
	@ManyToOne
	private Sala sala;
	
	@ManyToOne
	private Filme filme;
	
	@OneToMany(mappedBy = "sessao")
	private Set<Ingresso> ingressos = new HashSet<>();
	
	public Sessao() {
		
	}
	
	// Good Citizen
	public Sessao(LocalTime horario, Filme filme, Sala sala) {
		this.horario = horario;
		this.filme = filme;
		this.sala = sala;
		this.preco = filme.getPreco().add(sala.getPreco());
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalTime getHorario() {
		return horario;
	}

	public void setHorario(LocalTime horario) {
		this.horario = horario;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public Filme getFilme() {
		return filme;
	}

	public void setFilme(Filme filme) {
		this.filme = filme;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Set<Ingresso> getIngressos() {
		return ingressos;
	}
	
	public void setIngressos(Set<Ingresso> ingressos) {
		this.ingressos = ingressos;
	}
	
   public	Map<String,	List<Lugar>>	getMapaDeLugares(){
					return	sala.getMapaDeLugares();
	}
}