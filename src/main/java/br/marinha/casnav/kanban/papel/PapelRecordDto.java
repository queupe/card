package br.marinha.casnav.kanban.papel;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;

public record PapelRecordDto(@NotBlank String codigo, String titulo, @NotBlank String abrev, UUID superior ) {

}
