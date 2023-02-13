package org.generation.db_loja_de_games.controller;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.generation.db_loja_de_games.model.temaGames;
import org.generation.db_loja_de_games.repository.GamesTemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/tema")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class temaController {
	
		@Autowired
		private GamesTemaRepository repository;

		@GetMapping
		public ResponseEntity<List<temaGames>> getAll() {
			return ResponseEntity.ok(repository.findAll());
		}

		@GetMapping("/{id}")
		public ResponseEntity<temaGames> getById(@PathVariable Long id) {
			return repository.findById(id).map(resposta -> ResponseEntity.ok(resposta))
					.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
		}

		@GetMapping("/descricao/{descricao}")
		public ResponseEntity<List<temaGames>> getByTitle(@PathVariable String descricao) {
			return ResponseEntity.ok(repository.findAllByDescricaoContainingIgnoreCase(descricao));
		}

		@PostMapping
		public ResponseEntity<temaGames> post(@Valid @RequestBody temaGames tema) {
			return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(tema));
		}

		   @PutMapping
		    public ResponseEntity<temaGames> put(@Valid @RequestBody temaGames tema){
		        return repository.findById(tema.getId())
		            .map(resposta -> ResponseEntity.status(HttpStatus.CREATED)
		            .body(repository.save(tema)))
		            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
		    }

		@ResponseStatus(HttpStatus.NO_CONTENT)
	    @DeleteMapping("/{id}")
	    public void delete(@PathVariable Long id) {
	        Optional<temaGames> tema = repository.findById(id);
	        
	        if(tema.isEmpty())
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	        
	        repository.deleteById(id);              
	    }
	}