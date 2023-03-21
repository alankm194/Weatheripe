package com.techreturners.weatheripe.external.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@AllArgsConstructor
public class ExternalRequestDto {
    private String url;
    private ResponseDTO responseDTO;

}
