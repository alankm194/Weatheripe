package com.techreturners.weatheripe.external.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExternalRequestDto {
    private String url;
    private ResponseDTO responseDTO;

}
