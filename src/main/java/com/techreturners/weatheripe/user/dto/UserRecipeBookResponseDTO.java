package com.techreturners.weatheripe.user.dto;

import com.techreturners.weatheripe.external.dto.ResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserRecipeBookResponseDTO extends ResponseDTO {

    List<UserRecipeBookDTO> userRecipes ;
}
