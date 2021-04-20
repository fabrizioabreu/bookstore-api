package com.fabrizio.bookstore.dtos;

import java.io.Serializable;

import com.fabrizio.bookstore.domain.Livro;

public class LivroDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String titulo;

	public LivroDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LivroDTO(Livro obj) {
		super();
		this.id = obj.getId();
		this.titulo = obj.getTitulo();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

}
