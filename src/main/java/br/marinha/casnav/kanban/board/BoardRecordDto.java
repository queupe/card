package br.marinha.casnav.kanban.board;

import java.util.List;

import br.marinha.casnav.kanban.column.ColumnRecordDto;
import br.marinha.casnav.kanban.queue.QueueRecordDto;
import jakarta.validation.constraints.NotBlank;

public record BoardRecordDto(@NotBlank String title, List<ColumnRecordDto> columns, List<QueueRecordDto> queues) {

}
