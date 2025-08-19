package br.com.lucasflix.Service;

import br.com.lucasflix.Repository.UserRepository;
import br.com.lucasflix.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

   public User saveUser(User user){
       String password = user.getPassword();
       user.setPassword(passwordEncoder.encode(password));
       return repository.save(user);
   }
}


