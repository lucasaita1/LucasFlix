package br.com.lucasflix.Service;

import br.com.lucasflix.Repository.StreamingRepository;
import br.com.lucasflix.entity.Streaming;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StreamingService {

    private final StreamingRepository repository;

    public List<Streaming> findAll (){
        return repository.findAll();
    }

    public Streaming newStreaming (Streaming streaming){
        return repository.save(streaming);
    }

    public Optional<Streaming> streamingFindById (Long id){
        return repository.findById(id);
    }

    public void deleteById (Long id){
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
    }
}
