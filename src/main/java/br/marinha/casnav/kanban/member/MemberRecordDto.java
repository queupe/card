package br.marinha.casnav.kanban.member;

import java.util.List;
import br.marinha.casnav.kanban.team.TeamRecordDto;
import jakarta.validation.constraints.NotBlank;

public record MemberRecordDto(@NotBlank String name, List<TeamRecordDto> teams) {

}
