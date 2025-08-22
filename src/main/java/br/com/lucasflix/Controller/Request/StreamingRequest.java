package br.com.lucasflix.Controller.Request;

import jakarta.validation.constraints.NotBlank;

public record StreamingRequest(@NotBlank(message = "Streaming cannot be empty") String name) {
}
