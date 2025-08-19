package br.com.lucasflix.Config;

import br.com.lucasflix.entity.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class TokenComponent {

    private String secret;


    public String genereteToken (User user){
        Dotenv dotenv = Dotenv.load();
        this.secret = dotenv.get("JWT_SECRET");
        Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT.create()
                .withSubject(user.getEmail())
                .withClaim("userId", user.getId())
                .withExpiresAt(Instant.now().plusSeconds(86400))
                .withIssuedAt(Instant.now())
                .sign(algorithm);
    }
}
