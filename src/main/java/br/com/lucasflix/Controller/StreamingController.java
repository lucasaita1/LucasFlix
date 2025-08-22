package br.com.lucasflix.Controller;


import br.com.lucasflix.Controller.Request.StreamingRequest;
import br.com.lucasflix.Controller.Response.StreamingResponse;
import br.com.lucasflix.Mapper.StreamigMapper;
import br.com.lucasflix.Service.StreamingService;
import br.com.lucasflix.entity.Streaming;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/streaming")
@RequiredArgsConstructor
@Tag(name = "Streamings", description = "Gerenciamento de plataformas de streaming")
public class StreamingController {

    private final StreamingService service;

    @Operation(summary = "Listar todos os streamings")
    @ApiResponse(responseCode = "200", description = "Lista de streamings retornada com sucesso")
    @GetMapping("/list")
    public ResponseEntity<List<StreamingResponse>> findAll (){
        List<Streaming> streamings = service.findAll();
        List<StreamingResponse> responses = streamings.stream()
                .map(streaming -> StreamigMapper.streamingResponse(streaming))
                .toList();
        return ResponseEntity.ok(responses);
    }

    @Operation(summary = "Criar um novo streaming")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Streaming criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping("/create")
    public ResponseEntity<StreamingResponse> createNewStreaming(@Valid @RequestBody StreamingRequest request){
        Streaming streaming = StreamigMapper.toStreaming(request);
        Streaming createdStreaming = service.newStreaming(streaming);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(StreamigMapper.streamingResponse(streaming));
    }

    @Operation(summary = "Buscar streaming por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Streaming encontrado"),
            @ApiResponse(responseCode = "404", description = "Streaming não encontrado")
    })
    @GetMapping("/list/{id}")
    public ResponseEntity<StreamingResponse> findById (@PathVariable Long id){
        return service.streamingFindById(id)
                .map(streaming -> ResponseEntity.ok(StreamigMapper.streamingResponse(streaming)))
                .orElse(ResponseEntity.notFound().build());
    }


    @Operation(summary = "Excluir streaming por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Streaming excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Streaming não encontrado")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
}
