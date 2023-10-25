package br.marinha.casnav.kanban.board;

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

import br.marinha.casnav.kanban.column.Column;
import br.marinha.casnav.kanban.column.ColumnRepository;
import br.marinha.casnav.kanban.queue.Queue;
import br.marinha.casnav.kanban.queue.QueueRepository;
import jakarta.validation.Valid;

@RestController
public class BoardController {
	
	@Autowired
	BoardRepository boardRepository;
	@Autowired
	ColumnRepository columnRepository;
	@Autowired
	QueueRepository queueRepository;
	
	@PostMapping("/board")
	@Transactional
	public ResponseEntity<Board> saveBoard(@RequestBody @Valid BoardRecordDto data){
		var board = new Board(data);
		return ResponseEntity.status(HttpStatus.CREATED).body(boardRepository.save(board));
	}
	
	@GetMapping("/board")
	public ResponseEntity<List<Board>> getAllBoard(){
		return ResponseEntity.status(HttpStatus.OK).body(boardRepository.findAll());
	}
	
	@GetMapping("/board/{id}")
	public ResponseEntity<Object> getOneBoard(@PathVariable(value="id") UUID id){
		Optional<Board> board = boardRepository.findById(id);
		if (board.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Board not found!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(board.get());
	}
	
	@GetMapping("/board/{id}/column/")
	public ResponseEntity<Object> getColumnFromBoard(@PathVariable(value="id") UUID id){
		Optional<Board> board = boardRepository.findById(id);
		if (board.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Board not found!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(board.get().getColunms());
	}

	@GetMapping("/board/{id}/queue/")
	public ResponseEntity<Object> getQueueFromBoard(@PathVariable(value="id") UUID id){
		Optional<Board> board = boardRepository.findById(id);
		if (board.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Board not found!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(board.get().getQueues());
	}
	
	@PutMapping("/board/{id}")
	@Transactional
	public ResponseEntity<Object> updateBoard (@PathVariable(value="id") UUID id, @RequestBody @Valid BoardRecordDto data) {
		Optional<Board> board = boardRepository.findById(id);
		if (board.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Board not found!");
		}
		var boardModel = board.get();
		BeanUtils.copyProperties(data, boardModel);
		return ResponseEntity.status(HttpStatus.OK).body(boardRepository.save(boardModel));
	}
	
	@PutMapping("/board/{id}/column/{colum_id}")
	@Transactional
	public ResponseEntity<Object> setColumnInBoard (@PathVariable(value="id") UUID id, @PathVariable(value="colum_id") UUID colum_id) {
		Optional<Board> board = boardRepository.findById(id);
		if (board.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Board not found!");
		}
		Optional<Column> column = columnRepository.findById(colum_id);
		if (column.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Column not found!");
		}
		var boardModel = board.get();
		boardModel.getColunms().add(column.get());
		return ResponseEntity.status(HttpStatus.OK).body(boardRepository.save(boardModel));
	}

	@PutMapping("/board/{id}/queue/{queue_id}")
	@Transactional
	public ResponseEntity<Object> setQueueInBoard (@PathVariable(value="id") UUID id, @PathVariable(value="queue_id") UUID queue_id) {
		Optional<Board> board = boardRepository.findById(id);
		if (board.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Board not found!");
		}
		Optional<Queue> queue = queueRepository.findById(queue_id);
		if (queue.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Queue not found!");
		}
		var boardModel = board.get();
		boardModel.getQueues().add(queue.get());
		return ResponseEntity.status(HttpStatus.OK).body(boardRepository.save(boardModel));
	}
	
	@DeleteMapping("/board/{id}")
	@Transactional
	public ResponseEntity<Object> deleteBoard (@PathVariable(value="id") UUID id){
		Optional<Board> card0 = boardRepository.findById(id);
		if (card0.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Board not found!");
		}
		boardRepository.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Board deleted suscessfully!");
	}

}
