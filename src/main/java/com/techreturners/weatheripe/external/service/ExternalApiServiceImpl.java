package com.techreturners.weatheripe.external.service;

import com.techreturners.weatheripe.exception.ResourceNotFoundException;
import com.techreturners.weatheripe.external.dto.ExternalRequestDto;
import com.techreturners.weatheripe.external.dto.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class ExternalApiServiceImpl implements ExternalApiService {
    @Autowired
    private WebClient webClient;

    public ResponseDTO getResourcesByUri(ExternalRequestDto dto){
        ResponseDTO responseDTO = webClient
                .get()
                .uri(dto.getUrl())
                .retrieve()
                .onStatus(HttpStatusCode::isError, response -> response.bodyToMono(String.class) // error body as String or other class
                        .flatMap(error -> Mono.error(new ResourceNotFoundException(error)))) // throw a functional exception
                .bodyToMono(dto.getResponseDTO().getClass())
                .block();
        return responseDTO;
    }
}
