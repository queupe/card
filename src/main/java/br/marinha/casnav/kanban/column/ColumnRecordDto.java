package br.marinha.casnav.kanban.column;

import java.util.List;

import br.marinha.casnav.kanban.card.CardRecordDto;
import jakarta.validation.constraints.NotBlank;

public record ColumnRecordDto (@NotBlank String title, List<CardRecordDto> cards) {

}
