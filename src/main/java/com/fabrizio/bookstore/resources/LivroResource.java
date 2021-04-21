package com.fabrizio.bookstore.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fabrizio.bookstore.domain.Livro;
import com.fabrizio.bookstore.dtos.LivroDTO;
import com.fabrizio.bookstore.service.LivroService;

@CrossOrigin("*")	// Para poder receber requisições de diversas fontes. Por ex: pelo Angular
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
	
	// UPDATE	PUT
	@PutMapping(value = "/{id}")
	public ResponseEntity<Livro> update(@PathVariable Integer id, @Valid  @RequestBody Livro obj) {
		Livro newObj = service.update(id, obj);
		return ResponseEntity.ok().body(newObj);
	}
	
	// UPDATE	PATCH
	@PatchMapping(value = "/{id}")
	public ResponseEntity<Livro> updatePatch(@PathVariable Integer id, @Valid  @RequestBody Livro obj) {
		Livro newObj = service.update(id, obj);
		return ResponseEntity.ok().body(newObj);
	}
	
	// POST	-- Criando Livros	http://localhost:8080/livros?categoria=1
	@PostMapping
	public ResponseEntity<Livro> create(@RequestParam(value = "categoria", defaultValue = "0") Integer id_cat,
			@Valid @RequestBody Livro obj) {
		Livro newObj = service.create(id_cat, obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/livros/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	// DELETE		http://localhost:8080/livros/5
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}








