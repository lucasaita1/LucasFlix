package br.com.lucasflix.Config;


import br.com.lucasflix.Exception.UsernameAndPasswordInvalid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(UsernameAndPasswordInvalid.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleNotFoundExeception (UsernameAndPasswordInvalid ex){
        return ex.getMessage();
    }
}
