package br.marinha.casnav.kanban.queue;

import java.util.List;

import br.marinha.casnav.kanban.card.CardRecordDto;
import jakarta.validation.constraints.NotBlank;

public record QueueRecordDto(@NotBlank String title, List<CardRecordDto> cards) {

}
