package br.marinha.casnav.kanban.team;

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
public class TeamController {
	
	@Autowired
	TeamRepository teamRepository;
	
	@PostMapping("team")
	@Transactional
	public ResponseEntity<Team> saveTeam(@RequestBody @Valid TeamRecordDto data){
		var team = new Team();
		BeanUtils.copyProperties(data, team);
		return ResponseEntity.status(HttpStatus.CREATED).body(teamRepository.save(team));
	}
	
	@GetMapping("team")
	public ResponseEntity<List<Team>> getAllTeam(){
		return ResponseEntity.status(HttpStatus.OK).body(teamRepository.findAll());
	}
	
	@GetMapping("team/{id}")
	public ResponseEntity<Object> getOneTeam(@PathVariable(value="id") UUID id){
		Optional<Team> team = teamRepository.findById(id);
		if (team.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Team not found!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(team.get());
	}
	
	@PutMapping("team/{id}")
	@Transactional
	public ResponseEntity<Object> updateTeam(@PathVariable(value="id") UUID id, @RequestBody @Valid TeamRecordDto data) {
		Optional<Team> team = teamRepository.findById(id);
		if (team.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Team not found!");
		}
		var teamModel = team.get();
		BeanUtils.copyProperties(data, teamModel);
		return ResponseEntity.status(HttpStatus.OK).body(teamRepository.save(teamModel));
		
	}
	
	@DeleteMapping("team/{id}")
	@Transactional
	public ResponseEntity<Object> deleteTeam(@PathVariable(value="id") UUID id){
		Optional<Team> team = teamRepository.findById(id);
		if (team.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Team not found!");
		}
		teamRepository.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Team deleted suscessfully!");
	}

}
