package br.mil.mar.kanban.papel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
public class PapelController {
	
	@Autowired
	PapelRepository papelRepository;
	
	@PostMapping("papel")
	@Transactional
	public ResponseEntity<Papel> savePapel(@RequestBody @Valid PapelRecordDto data){
		var papel = new Papel();
		BeanUtils.copyProperties(data, papel);
		return ResponseEntity.status(HttpStatus.CREATED).body(papelRepository.save(papel));
	}
	
	@GetMapping("papel")
	public ResponseEntity<List<Papel>> getAllPapel(){
		return ResponseEntity.status(HttpStatus.OK).body(papelRepository.findAll());
	}
	
	@GetMapping("papel/{id}")
	public ResponseEntity<Object> getOnePapel(@PathVariable(value="id") UUID id){
		Optional<Papel> papel = papelRepository.findById(id);
		if (papel.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Papel not found!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(papel.get());
	}
	
	@PutMapping("papel/{id}")
	@Transactional
	public ResponseEntity<Object> updatePapel(@PathVariable(value="id") UUID id, @RequestBody @Valid PapelRecordDto data) {
		Optional<Papel> papel = papelRepository.findById(id);
		if (papel.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Papel not found!");
		}
		var papelModel = papel.get();
		BeanUtils.copyProperties(data, papelModel);
		return ResponseEntity.status(HttpStatus.OK).body(papelRepository.save(papelModel));
		
	}
	
	@DeleteMapping("papel/{id}")
	@Transactional
	public ResponseEntity<Object> deletePapel(@PathVariable(value="id") UUID id){
		Optional<Papel> papel = papelRepository.findById(id);
		if (papel.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Papel not found!");
		}
		papelRepository.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Papel deleted suscessfully!");
	}

}
