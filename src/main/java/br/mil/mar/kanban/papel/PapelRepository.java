package br.mil.mar.kanban.papel;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;


public interface PapelRepository extends JpaRepository<Papel, UUID>{

}
