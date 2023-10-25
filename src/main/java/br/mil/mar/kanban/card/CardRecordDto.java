package br.mil.mar.kanban.card;

import br.mil.mar.kanban.papel.PapelRecordDto;
import jakarta.validation.constraints.NotBlank;

public record CardRecordDto(@NotBlank String title, String description,@NotBlank String coverColor, PapelRecordDto owner) {

}
