package br.mil.mar.kanban.label;

import jakarta.validation.constraints.NotBlank;

public record LabelRecordDto(String icon, @NotBlank  String color, String description) {

}
