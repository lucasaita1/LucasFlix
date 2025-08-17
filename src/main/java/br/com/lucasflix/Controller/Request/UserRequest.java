package br.com.lucasflix.Controller.Request;



public record UserRequest(Long id, String name, String email, String password) {
}
