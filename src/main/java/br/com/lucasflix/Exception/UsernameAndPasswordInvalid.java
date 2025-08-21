package br.com.lucasflix.Exception;

public class UsernameAndPasswordInvalid extends RuntimeException {
    public UsernameAndPasswordInvalid(String message) {
        super(message);
    }
}
