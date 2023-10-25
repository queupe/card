package br.marinha.casnav.kanban.column;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ColumnRepository extends JpaRepository<Column, UUID>{

}
