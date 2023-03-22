package com.techreturners.weatheripe.user.dto;

import com.techreturners.weatheripe.external.dto.ResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRecipeBookResponseDTO extends ResponseDTO {

    List<UserRecipeBookDTO> userRecipes ;
}
