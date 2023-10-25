package br.marinha.casnav.kanban.card;

import br.marinha.casnav.kanban.papel.PapelRecordDto;
import jakarta.validation.constraints.NotBlank;

public record CardRecordDto(@NotBlank String title, String description,@NotBlank String coverColor, PapelRecordDto owner) {

}
