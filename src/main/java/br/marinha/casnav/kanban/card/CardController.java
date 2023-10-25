package br.marinha.casnav.kanban.card;

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

import br.marinha.casnav.kanban.label.Label;
import br.marinha.casnav.kanban.label.LabelRepository;
import br.marinha.casnav.kanban.member.Member;
import br.marinha.casnav.kanban.member.MemberRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/card")
public class CardController {
	
	@Autowired
	CardRepository cardRepository;
	
	@Autowired
	LabelRepository labelRepository;
	
	@Autowired
	MemberRepository memberRepository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<Card> saveCard(@RequestBody @Valid CardRecordDto data){
		var card = new Card(data);
		return ResponseEntity.status(HttpStatus.CREATED).body(cardRepository.save(card));
	}
	
	@GetMapping
	public ResponseEntity<List<Card>> getAllCard(){
		return ResponseEntity.status(HttpStatus.OK).body(cardRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getOneCard(@PathVariable(value="id") UUID id){
		Optional<Card> card0 = cardRepository.findById(id);
		if (card0.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Card not found!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(card0.get());
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<Object> updateCard (@PathVariable(value="id") UUID id, @RequestBody @Valid CardRecordDto data) {
		Optional<Card> card = cardRepository.findById(id);
		if (card.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Card not found!");
		}
		var cardModel = card.get();
		BeanUtils.copyProperties(data, cardModel);
		return ResponseEntity.status(HttpStatus.OK).body(cardRepository.save(cardModel));		
	}
	
	@PutMapping("/{id}/label/{label_id}")
	@Transactional
	public ResponseEntity<Object> setLabelOnCard (@PathVariable(value="id") UUID id, @PathVariable(value="label_id") UUID label_id) {
		Optional<Card> card = cardRepository.findById(id);
		if (card.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Card not found!");
		}
		Optional<Label> label = labelRepository.findById(label_id);
		if (label.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Label not found!");
		}
		var cardM = card.get();
		var labelM = label.get();
		cardM.getLabels().add(labelM);
		labelM.getCards().add(cardM);
		labelRepository.save(labelM);
		cardRepository.save(cardM);
		return ResponseEntity.status(HttpStatus.OK).body(cardM);		
	}
	
	@PutMapping("/{id}/member/{member_id}")
	@Transactional
	public ResponseEntity<Object> setMemberOnCard (@PathVariable(value="id") UUID id, @PathVariable(value=",member_id") UUID member_id) {
		Optional<Card> card = cardRepository.findById(id);
		if (card.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Card not found!");
		}
		Optional<Member> member = memberRepository.findById(member_id);
		if (member.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Member not found!");
		}
		var cardModel = card.get();
		cardModel.setMember(member.get());
		return ResponseEntity.status(HttpStatus.OK).body(cardRepository.save(cardModel));		
	}
	
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Object> deleteCard (@PathVariable(value="id") UUID id){
		Optional<Card> card0 = cardRepository.findById(id);
		if (card0.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Card not found!");
		}
		cardRepository.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Card deleted suscessfully!");
	}
	
	@DeleteMapping("/{id}/label/{label_id}")
	@Transactional
	public ResponseEntity<Object> removeLabelFromCard (@PathVariable(value="id") UUID id, @PathVariable(value="label_id") UUID label_id){
		Optional<Card> card = cardRepository.findById(id);
		if (card.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Card not found!");
		}
		Optional<Label> label = labelRepository.findById(label_id);
		if (label.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Label not found!");
		}
		var cardModel = card.get();
		if (cardModel.getLabels().remove(label.get())) {
			return ResponseEntity.status(HttpStatus.OK).body(cardRepository.save(cardModel));
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(cardModel);
		}			
	}

}
