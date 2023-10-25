package br.marinha.casnav.kanban.label;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;


public interface LabelRepository extends JpaRepository<Label, UUID>{

}
