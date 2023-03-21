package com.techreturners.weatheripe.external.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class DummyReceipeQueryDTO extends ResponseDTO{
    private String query;

}
