package br.marinha.casnav.kanban.card;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;


public interface CardRepository extends JpaRepository<Card, UUID>{

}
