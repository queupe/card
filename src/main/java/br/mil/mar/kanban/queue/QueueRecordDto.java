package br.mil.mar.kanban.queue;

import java.util.List;

import br.mil.mar.kanban.card.CardRecordDto;
import jakarta.validation.constraints.NotBlank;

public record QueueRecordDto(@NotBlank String title, List<CardRecordDto> cards) {

}
