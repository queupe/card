package br.marinha.casnav.kanban.queue;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;


public interface QueueRepository extends JpaRepository<Queue, UUID>{

}
