package br.marinha.casnav.kanban.member;

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
public class MemberController {
	
	@Autowired
	MemberRepository memberRepository;
	
	@PostMapping("member")
	@Transactional
	public ResponseEntity<Member> saveMember(@RequestBody @Valid MemberRecordDto data){
		var member = new Member();
		BeanUtils.copyProperties(data, member);
		return ResponseEntity.status(HttpStatus.CREATED).body(memberRepository.save(member));
	}
	
	@GetMapping("member")
	public ResponseEntity<List<Member>> getAllMember(){
		return ResponseEntity.status(HttpStatus.OK).body(memberRepository.findAll());
	}
	
	@GetMapping("member/{id}")
	public ResponseEntity<Object> getOneMember(@PathVariable(value="id") UUID id){
		Optional<Member> member = memberRepository.findById(id);
		if (member.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Member not found!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(member.get());
	}
	
	@PutMapping("member/{id}")
	@Transactional
	public ResponseEntity<Object> updateMember(@PathVariable(value="id") UUID id, @RequestBody @Valid MemberRecordDto data) {
		Optional<Member> member = memberRepository.findById(id);
		if (member.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Member not found!");
		}
		var memberModel = member.get();
		BeanUtils.copyProperties(data, memberModel);
		return ResponseEntity.status(HttpStatus.OK).body(memberRepository.save(memberModel));
		
	}
	
	@DeleteMapping("member/{id}")
	@Transactional
	public ResponseEntity<Object> deleteMember(@PathVariable(value="id") UUID id){
		Optional<Member> member = memberRepository.findById(id);
		if (member.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Member not found!");
		}
		memberRepository.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Member deleted suscessfully!");
	}

}
