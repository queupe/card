package br.marinha.casnav.kanban.column;

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

import br.marinha.casnav.kanban.card.CardRepository;
import br.marinha.casnav.kanban.card.Card;
import jakarta.validation.Valid;

@RestController
public class ColumnController {
	
	@Autowired
	ColumnRepository columnRepository;
	@Autowired
	CardRepository cardRepository;
	
	@PostMapping("/column")
	@Transactional
	public ResponseEntity<Column> saveColumn(@RequestBody @Valid ColumnRecordDto data){
		var column = new Column();
		BeanUtils.copyProperties(data, column);
		return ResponseEntity.status(HttpStatus.CREATED).body(columnRepository.save(column));
	}
	
	@GetMapping("/column")
	public ResponseEntity<List<Column>> getAllColumn(){
		return ResponseEntity.status(HttpStatus.OK).body(columnRepository.findAll());
	}
	
	@GetMapping("/column/{id}")
	public ResponseEntity<Object> getOneColumn(@PathVariable(value="id") UUID id){
		Optional<Column> column = columnRepository.findById(id);
		if (column.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Column not found!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(column.get());
	}
	
	@GetMapping("/column/{id}/card/")
	public ResponseEntity<Object> getCardFromColumn(@PathVariable(value="id") UUID id){
		Optional<Column> column = columnRepository.findById(id);
		if (column.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Column not found!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(column.get().getCards());
	}
	
	@PutMapping("/column/{id}")
	@Transactional
	public ResponseEntity<Object> updateColumn(@PathVariable(value="id") UUID id, @RequestBody @Valid ColumnRecordDto data) {
		Optional<Column> column = columnRepository.findById(id);
		if (column.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Column not found!");
		}
		var columnModel = column.get();
		BeanUtils.copyProperties(data, columnModel);
		return ResponseEntity.status(HttpStatus.OK).body(columnRepository.save(columnModel));
		
	}
	
	@PutMapping("/column/{id}/card/{card_id}")
	@Transactional
	public ResponseEntity<Object> addCardInColumn(@PathVariable(value="id") UUID id, @PathVariable(value="card_id") UUID card_id) {
		Optional<Column> column = columnRepository.findById(id);
		if (column.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Column not found!");
		}
		Optional<Card> card = cardRepository.findById(card_id);
		if (card.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Card not found!");
		}
		var columnModel = column.get();
		columnModel.getCards().add(card.get());
		
		return ResponseEntity.status(HttpStatus.OK).body(columnRepository.save(columnModel));
	}
	
	@DeleteMapping("/column/{id}")
	@Transactional
	public ResponseEntity<Object> deleteColumn(@PathVariable(value="id") UUID id){
		Optional<Column> column = columnRepository.findById(id);
		if (column.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Column not found!");
		}
		columnRepository.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Column deleted suscessfully!");
	}
	
	@DeleteMapping("/column/{id}/card/{card_id}")
	@Transactional
	public ResponseEntity<Object> removeCardFromColumn(@PathVariable(value="id") UUID id, @PathVariable(value="card_id") UUID card_id) {
		Optional<Column> column = columnRepository.findById(id);
		if (column.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Column not found!");
		}
		Optional<Card> card = cardRepository.findById(card_id);
		if (card.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Card not found!");
		}
		var columnModel = column.get();
		if (columnModel.getCards().remove(card.get())) {
			return ResponseEntity.status(HttpStatus.OK).body(columnRepository.save(columnModel));
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno!");
		
	}

}
