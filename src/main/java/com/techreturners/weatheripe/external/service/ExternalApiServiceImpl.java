package com.techreturners.weatheripe.external.service;

import com.techreturners.weatheripe.external.dto.ExternalRequestDto;
import com.techreturners.weatheripe.external.dto.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Service
public class ExternalApiServiceImpl implements ExternalApiService {
    @Autowired
    private WebClient.Builder webClientBuilder;

    public ResponseDTO getResourcesByUri(ExternalRequestDto dto){
        ResponseDTO responseDTO = webClientBuilder.build()
                .get()
                .uri(dto.getUrl())
                .retrieve()
                .bodyToMono(dto.getResponseDTO().getClass())
                .block();
        return responseDTO;
    }
}
