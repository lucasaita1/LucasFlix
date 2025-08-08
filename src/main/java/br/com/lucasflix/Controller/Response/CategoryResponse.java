package br.com.lucasflix.Controller.Response;

import lombok.Builder;

@Builder
public record CategoryResponse(Long id, String name) {
}
