package com.techreturners.weatheripe.external.service;

import com.techreturners.weatheripe.external.dto.ExternalRequestDto;
import com.techreturners.weatheripe.external.dto.ResponseDTO;
import org.springframework.stereotype.Service;

public interface ExternalApiService {
    ResponseDTO getResourcesByUri(ExternalRequestDto dto) ;
}
