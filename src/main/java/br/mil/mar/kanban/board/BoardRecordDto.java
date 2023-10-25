package br.mil.mar.kanban.board;

import java.util.List;

import br.mil.mar.kanban.column.ColumnRecordDto;
import br.mil.mar.kanban.queue.QueueRecordDto;
import jakarta.validation.constraints.NotBlank;

public record BoardRecordDto(@NotBlank String title, List<ColumnRecordDto> columns, List<QueueRecordDto> queues) {

}
