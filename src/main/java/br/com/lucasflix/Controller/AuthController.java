package br.com.lucasflix.Controller;

import br.com.lucasflix.Config.TokenComponent;
import br.com.lucasflix.Controller.Request.LoginRequest;
import br.com.lucasflix.Controller.Request.UserRequest;
import br.com.lucasflix.Controller.Response.UserResponse;
import br.com.lucasflix.Exception.UsernameAndPasswordInvalid;
import br.com.lucasflix.Mapper.UserMapper;
import br.com.lucasflix.Service.UserService;
import br.com.lucasflix.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("lucasflix/auth")
@RequiredArgsConstructor
@Tag(name = "Autenticação", description = "Endpoints de autenticação e registro de usuários")
public class AuthController {

    private final UserService service;
    private final AuthenticationManager authenticationManager;
    private final TokenComponent tokenComponent;

    @Operation(summary = "Registrar novo usuário", description = "Cria um novo usuário no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping("/register")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest request){
        User user = UserMapper.toUser(request);
        User newUser = service.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(UserMapper.toUserResponse(user));
    }

    @Operation(summary = "Login de usuário", description = "Autentica o usuário e retorna um token JWT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login bem-sucedido"),
            @ApiResponse(responseCode = "401", description = "Usuário ou senha inválidos")
    })
    //Rota para gerar Token
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest){

        try {
            UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password());
            Authentication authentication = authenticationManager.authenticate(userAndPass);

            User user = (User) authentication.getPrincipal();

            String token = tokenComponent.genereteToken(user);

            return ResponseEntity.ok(token);

        } catch (BadCredentialsException e){
            throw new UsernameAndPasswordInvalid("user or password invalids");
        }

    }
}
