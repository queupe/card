package br.mil.mar.kanban.queue;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/queue")
public class QueueController {
	
	@Autowired
	QueueRepository queueRepository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<Queue> saveQueue(@RequestBody @Valid QueueRecordDto data){
		var queue = new Queue(data);
		return ResponseEntity.status(HttpStatus.CREATED).body(queueRepository.save(queue));
	}
	
	@GetMapping
	public ResponseEntity<List<Queue>> getAllQueue(){
		return ResponseEntity.status(HttpStatus.OK).body(queueRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getOneQueue(@PathVariable(value="id") UUID id){
		Optional<Queue> queue = queueRepository.findById(id);
		if (queue.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Queue not found!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(queue.get());
	}
	
	@GetMapping("/{id}/card/")
	public ResponseEntity<Object> getCardFromQueue(@PathVariable(value="id") UUID id){
		Optional<Queue> queue = queueRepository.findById(id);
		if (queue.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Queue not found!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(queue.get().getCards());
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<Object> updateQueue(@PathVariable(value="id") UUID id, @RequestBody @Valid QueueRecordDto data) {
		Optional<Queue> queue = queueRepository.findById(id);
		if (queue.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Queue not found!");
		}
		var queueModel = queue.get();
		BeanUtils.copyProperties(data, queueModel);
		return ResponseEntity.status(HttpStatus.OK).body(queueRepository.save(queueModel));
		
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Object> deleteQueue(@PathVariable(value="id") UUID id){
		Optional<Queue> queue = queueRepository.findById(id);
		if (queue.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Queue not found!");
		}
		queueRepository.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Queue deleted suscessfully!");
	}

}
