package br.com.lucasflix.Controller;

import br.com.lucasflix.Controller.Request.UserRequest;
import br.com.lucasflix.Controller.Response.UserResponse;
import br.com.lucasflix.Mapper.UserMapper;
import br.com.lucasflix.Service.UserService;
import br.com.lucasflix.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService service;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> saveUser(@RequestBody UserRequest request){
        User user = UserMapper.toUser(request);
        User newUser = service.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(UserMapper.toUserResponse(user));
    }
}
