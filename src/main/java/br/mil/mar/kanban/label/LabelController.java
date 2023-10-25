package br.mil.mar.kanban.label;

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
public class LabelController {
	
	@Autowired
	LabelRepository labelRepository;
	
	@PostMapping("label")
	@Transactional
	public ResponseEntity<Label> saveLabel(@RequestBody @Valid LabelRecordDto data){
		var label = new Label(data);
		return ResponseEntity.status(HttpStatus.CREATED).body(labelRepository.save(label));
	}
	
	@GetMapping("label")
	public ResponseEntity<List<Label>> getAllLabel(){
		return ResponseEntity.status(HttpStatus.OK).body(labelRepository.findAll());
	}
	
	@GetMapping("label/{id}")
	public ResponseEntity<Object> getOneLabel(@PathVariable(value="id") UUID id){
		Optional<Label> label = labelRepository.findById(id);
		if (label.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Label not found!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(label.get());
	}
	
	@PutMapping("label/{id}")
	@Transactional
	public ResponseEntity<Object> updateLabel(@PathVariable(value="id") UUID id, @RequestBody @Valid LabelRecordDto data) {
		Optional<Label> label = labelRepository.findById(id);
		if (label.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Label not found!");
		}
		var labelModel = label.get();
		BeanUtils.copyProperties(data, labelModel);
		return ResponseEntity.status(HttpStatus.OK).body(labelRepository.save(labelModel));
		
	}
	
	@DeleteMapping("label/{id}")
	@Transactional
	public ResponseEntity<Object> deleteLabel(@PathVariable(value="id") UUID id){
		Optional<Label> label = labelRepository.findById(id);
		if (label.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Label not found!");
		}
		labelRepository.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Label deleted suscessfully!");
	}

}
