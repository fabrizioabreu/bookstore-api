package com.fabrizio.bookstore.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fabrizio.bookstore.domain.Livro;
import com.fabrizio.bookstore.dtos.LivroDTO;
import com.fabrizio.bookstore.service.LivroService;

@RestController
@RequestMapping(value = "/livros")
public class LivroResource {

	@Autowired
	private LivroService service;
	
	// Busca por ID		http://localhost:8080/livros/
	@GetMapping(value = "/{id}")
	public ResponseEntity<Livro> findById(@PathVariable Integer id) {
		Livro obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	// FIND ALL		http://localhost:8080/livros?categoria=1
	@GetMapping	
	public ResponseEntity<List<LivroDTO>> findAll(@RequestParam(value = "categoria", defaultValue = "0") Integer id_cat) {
		List<Livro> list = service.findAll(id_cat);		// Transformando lista de Livro para LivroDTO
		List<LivroDTO> listDTO = list.stream().map(obj -> new LivroDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
}








