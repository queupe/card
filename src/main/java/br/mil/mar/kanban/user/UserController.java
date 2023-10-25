package br.mil.mar.kanban.user;

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
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@PostMapping("user")
	@Transactional
	public ResponseEntity<User> saveUser(@RequestBody @Valid UserRecordDto data){
		var user = new User();
		BeanUtils.copyProperties(data, user);
		return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(user));
	}
	
	@GetMapping("user")
	public ResponseEntity<List<User>> getAllUser(){
		return ResponseEntity.status(HttpStatus.OK).body(userRepository.findAll());
	}
	
	@GetMapping("user/{id}")
	public ResponseEntity<Object> getOneUser(@PathVariable(value="id") UUID id){
		Optional<User> user = userRepository.findById(id);
		if (user.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(user.get());
	}
	
	@PutMapping("user/{id}")
	@Transactional
	public ResponseEntity<Object> updateUser(@PathVariable(value="id") UUID id, @RequestBody @Valid UserRecordDto data) {
		Optional<User> user = userRepository.findById(id);
		if (user.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
		}
		var userModel = user.get();
		BeanUtils.copyProperties(data, userModel);
		return ResponseEntity.status(HttpStatus.OK).body(userRepository.save(userModel));
		
	}
	
	@DeleteMapping("user/{id}")
	@Transactional
	public ResponseEntity<Object> deleteUser(@PathVariable(value="id") UUID id){
		Optional<User> user = userRepository.findById(id);
		if (user.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
		}
		userRepository.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("User deleted suscessfully!");
	}

}
