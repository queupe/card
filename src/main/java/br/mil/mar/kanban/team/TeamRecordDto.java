package br.mil.mar.kanban.team;

import jakarta.validation.constraints.NotBlank;

public record TeamRecordDto(@NotBlank String name) {

}
