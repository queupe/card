package br.marinha.casnav.kanban.board;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;


public interface BoardRepository extends JpaRepository<Board, UUID>{

}
