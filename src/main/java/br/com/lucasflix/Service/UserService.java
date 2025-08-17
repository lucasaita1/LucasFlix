package br.com.lucasflix.Service;

import br.com.lucasflix.Repository.UserRepository;
import br.com.lucasflix.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

   public User saveUser(User user){
       return repository.save(user);
   }
}


