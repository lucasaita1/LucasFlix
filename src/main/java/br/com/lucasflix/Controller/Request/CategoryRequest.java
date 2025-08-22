package br.com.lucasflix.Controller.Request;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequest(@NotBlank(message = "Category cannot be empty") String name) {
}
