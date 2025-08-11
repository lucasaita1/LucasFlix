package br.com.lucasflix.Controller;


import br.com.lucasflix.Controller.Request.StreamingRequest;
import br.com.lucasflix.Controller.Response.StreamingResponse;
import br.com.lucasflix.Mapper.StreamigMapper;
import br.com.lucasflix.Service.StreamingService;
import br.com.lucasflix.entity.Streaming;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/streaming")
@RequiredArgsConstructor
public class StreamingController {

    private final StreamingService service;

    @GetMapping("/list")
    public ResponseEntity<List<StreamingResponse>> findAll (){
        List<Streaming> streamings = service.findAll();
        List<StreamingResponse> responses = streamings.stream()
                .map(streaming -> StreamigMapper.streamingResponse(streaming))
                .toList();
        return ResponseEntity.ok(responses);
    }

    @PostMapping("/create")
    public ResponseEntity<StreamingResponse> createNewStreaming(@RequestBody StreamingRequest request){
        Streaming streaming = StreamigMapper.toStreaming(request);
        Streaming createdStreaming = service.newStreaming(streaming);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(StreamigMapper.streamingResponse(streaming));
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<StreamingResponse> findById (@PathVariable Long id){
        return service.streamingFindById(id)
                .map(streaming -> ResponseEntity.ok(StreamigMapper.streamingResponse(streaming)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
}
