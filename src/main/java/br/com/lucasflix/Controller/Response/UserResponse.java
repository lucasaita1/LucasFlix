package br.com.lucasflix.Controller.Response;

import lombok.Builder;

@Builder
public record UserResponse(Long id, String name, String email) {
}
