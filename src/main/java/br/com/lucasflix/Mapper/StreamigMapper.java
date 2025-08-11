package br.com.lucasflix.Mapper;

import br.com.lucasflix.Controller.Request.StreamingRequest;
import br.com.lucasflix.Controller.Response.StreamingResponse;
import br.com.lucasflix.entity.Streaming;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StreamigMapper {

    public static Streaming toStreaming (StreamingRequest streamingRequest){
        return Streaming
                .builder()
                .name(streamingRequest.name())
                .build();
    }

    public static StreamingResponse streamingResponse (Streaming streaming){
        return StreamingResponse
                .builder()
                .id(streaming.getId())
                .name(streaming.getName())
                .build();
    }
}
