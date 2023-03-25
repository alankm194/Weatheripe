package com.techreturners.weatheripe.recipe.service;


import com.techreturners.weatheripe.configuration.SecretConfiguration;
import com.techreturners.weatheripe.external.service.ExternalApiServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.web.reactive.function.client.WebClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@DataJpaTest
public class RecipeServiceTest {

    @InjectMocks
    private RecipeServiceImpl recipeService;

    @Mock
    private SecretConfiguration secretConfiguration;

    @Mock
    private WebClient webClient;

//    @Mock
@InjectMocks
private ExternalApiServiceImpl externalApiService;

//    @Test
//    public void getRecipeByWeatherCondition(){
//        String query = "https://api.edamam.com/api/recipes/v2?dishType=desserts&dishType=drinks&dishType=egg&dishType=pancake";
//
//        ReflectionTestUtils.setField(recipeService,
//                "RECIPE_APP_KEY", "67cafd22003829f89f36cf1800d9f7ca");
//        ReflectionTestUtils.setField(recipeService,
//                "API_TYPE", "public");
//        ReflectionTestUtils.setField(recipeService,
//                "RECIPE_APP_ID", "8a3753d7");
//
//        RecipeQueryDTO recipeQueryDTO = new RecipeQueryDTO(query);
//
//        RecipeResponseDTO recipeResponseDTO = (RecipeResponseDTO) recipeService.getRecipeByWeatherCondition(recipeQueryDTO);
//        assertThat(recipeResponseDTO.getHits().length).isEqualTo(20);
//    }


//    @Test
//    public void getRecipeByWeatherCondition() throws IOException {
//
//        ReflectionTestUtils.setField(recipeService,
//                "API_TYPE", "public");
//        when(secretConfiguration.recipeAppKey()).thenReturn("67cafd22003829f89f36cf1800d9f7ca");
//        when(secretConfiguration.recipeAppId()).thenReturn("8a3753d7");
//        String query = "https://api.edamam.com/api/recipes/v2?dishType=desserts&dishType=drinks&dishType=egg&dishType=pancake";
//
//        // To read the json file to form the weatherApiObj
////        Resource resource = new ClassPathResource("/edamam_sample_recipe_success.json");
////
////        ObjectMapper objectMapper = new ObjectMapper();
////        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
////        RecipeResponseDTO recipeResponseDTO = objectMapper.readValue(resource.getInputStream(), RecipeResponseDTO.class);
//
//        RecipeQueryDTO recipeQueryDTO = new RecipeQueryDTO(query);
//        RecipeResponseDTO recipeResponseDTO = (RecipeResponseDTO) recipeService.getRecipeByWeatherCondition(recipeQueryDTO);
//        System.out.println(recipeResponseDTO.getHits().length);
//        assertEquals(recipeResponseDTO.getHits().length,20);
//
//    }




//    @Test
//    public void getRecipeByWeatherConditionErrorNoRecipeFound() {
//        String query = "https://api.edamam.com/api/recipes/v2?app_key=dummykey&app_id=ba324f9b&type=any&dishType=salad";
//
//        ReflectionTestUtils.setField(recipeService,
//                "RECIPE_API_URL", query);
//
//        when(secretConfiguration.recipeAppId()).thenReturn("8a3753d7");
//        when(secretConfiguration.recipeAppKey()).thenReturn("67cafd22003829f89f36cf1800d9f7ca");
//
//        RecipeQueryDTO recipeQueryDTO = new RecipeQueryDTO(query);
//        assertThrows(NoMatchingCriteriaException.class, new Executable() {
//            @Override
//            public void execute() throws Throwable {
//                RecipeResponseDTO recipeResponseDTO = (RecipeResponseDTO) recipeService.getRecipeByWeatherCondition(recipeQueryDTO);
//            }
//        });
//
//    }




}
