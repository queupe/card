package br.mil.mar.kanban.member;

import java.util.List;

import br.mil.mar.kanban.team.TeamRecordDto;
import jakarta.validation.constraints.NotBlank;

public record MemberRecordDto(@NotBlank String name, List<TeamRecordDto> teams) {

}
