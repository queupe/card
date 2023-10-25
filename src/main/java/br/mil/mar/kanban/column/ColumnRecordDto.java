package br.mil.mar.kanban.column;

import java.util.List;

import br.mil.mar.kanban.card.CardRecordDto;
import jakarta.validation.constraints.NotBlank;

public record ColumnRecordDto (@NotBlank String title, List<CardRecordDto> cards) {

}
