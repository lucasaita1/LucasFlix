package br.com.lucasflix.Service;

import br.com.lucasflix.Repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository repository;
}
