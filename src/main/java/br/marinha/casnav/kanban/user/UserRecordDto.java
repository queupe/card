package br.marinha.casnav.kanban.user;

import jakarta.validation.constraints.NotBlank;

public record UserRecordDto(@NotBlank String login, String name, String abrev, String fullName,@NotBlank  String email, Rank rank) {

}
