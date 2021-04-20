package com.fabrizio.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.fabrizio.bookstore.domain.Categoria;
import com.fabrizio.bookstore.dtos.CategoriaDTO;
import com.fabrizio.bookstore.repositories.CategoriaRepository;
import com.fabrizio.bookstore.service.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repository;
	
	public Categoria findById(Integer id) {
		Optional<Categoria> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
	
	public List<Categoria> findAll() {
		return repository.findAll();
	}
	
	// Método Create
	public Categoria create(Categoria obj) {
		obj.setId(null);
		return repository.save(obj);
	}

	// Método Update
	public Categoria update(Integer id, CategoriaDTO objDto) {
		Categoria obj = findById(id);
		obj.setNome(objDto.getNome());
		obj.setDescricao(objDto.getDescricao());
		return repository.save(obj);
	}

	// Método Delete
	public void delete(Integer id) {
		findById(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new com.fabrizio.bookstore.service.exceptions.DataIntegrityViolationException(
					"Categoria não pode ser deletada! ... Possui itens associados");
		}
		
		
	}
}
