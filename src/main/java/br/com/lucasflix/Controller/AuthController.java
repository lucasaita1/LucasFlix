package br.com.lucasflix.Controller;

import br.com.lucasflix.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth/user")
@RequiredArgsConstructor
public class AuthController {

    private final UserService service;
}
