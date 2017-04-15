package com.riojano0.recipe.resource.validator;

import com.riojano0.recipe.domain.Error.ErrorResponse;
import com.riojano0.recipe.domain.Ingredient;
import com.riojano0.recipe.domain.Preparation;
import com.riojano0.recipe.domain.Recipe;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class RecipeValidatorTest {

    @Mock
    private ResponseEntity responseEntity;

    @InjectMocks
    private RecipeValidator recipeValidator;

    @Mock
    private Recipe recipe;

    private ErrorResponse errorResponse;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Ingredient ingredient = Ingredient.builder().name("Papa").build();
        List<Ingredient> ingredientList = new ArrayList<>();
        ingredientList.add(ingredient);
        Preparation preparation = Preparation.builder().build();

        when(recipe.getName()).thenReturn("Recipe Valid");
        when(recipe.getDescription()).thenReturn("Description Valid");
        when(recipe.getIngredients()).thenReturn(ingredientList);
        when(recipe.getPreparation()).thenReturn(preparation);

    }

    @Test
    public void shouldValidateWithCorrectRecipeAndReturnNullResponse() {

        assertEquals(null, recipeValidator.validate(recipe));
    }


    @Test
    public void shouldValidateWithNameIsNullAndReturnErrorResponse() {

        when(recipe.getName()).thenReturn(null);

        errorResponse = ErrorResponse.builder().key("name").value(null).message("Cant be null").build();

        assertEquals(HttpStatus.BAD_REQUEST, recipeValidator.validate(recipe).getStatusCode());
        assertEquals(errorResponse, recipeValidator.validate(recipe).getBody());

    }

    @Test
    public void shouldValidateWithNameHaveLengthLessThanFourAndReturnErrorResponse() {

        when(recipe.getName()).thenReturn("aa");

        errorResponse = ErrorResponse.builder().key("name").value("aa").message("Incorrect name length 4 - 25").build();

        assertEquals(HttpStatus.BAD_REQUEST, recipeValidator.validate(recipe).getStatusCode());
        assertEquals(errorResponse, recipeValidator.validate(recipe).getBody());

    }

    @Test
    public void shouldValidateWithNameHaveLengthEqualThanThreeAndReturnErrorResponse() {

        when(recipe.getName()).thenReturn("aaa");

        errorResponse = ErrorResponse.builder().key("name").value("aaa").message("Incorrect name length 4 - 25").build();

        assertEquals(HttpStatus.BAD_REQUEST, recipeValidator.validate(recipe).getStatusCode());
        assertEquals(errorResponse, recipeValidator.validate(recipe).getBody());

    }

    @Test
    public void shouldValidateWithNameHaveLengthMoreThanTwentyFiveAndReturnErrorResponse() {

        when(recipe.getName()).thenReturn("aaaaaaaaaaaaaaaaaaaaaaaaaa");

        errorResponse = ErrorResponse.builder().key("name").value("aaaaaaaaaaaaaaaaaaaaaaaaaa").message("Incorrect name length 4 - 25").build();

        assertEquals(HttpStatus.BAD_REQUEST, recipeValidator.validate(recipe).getStatusCode());
        assertEquals(errorResponse, recipeValidator.validate(recipe).getBody());

    }

    @Test
    public void shouldValidateWithNameHaveLengthEqualThanTwentySixAndReturnErrorResponse() {

        when(recipe.getName()).thenReturn("aaaaaaaaaaaaaaaaaaaaaaaaaa");

        errorResponse = ErrorResponse.builder().key("name").value("aaaaaaaaaaaaaaaaaaaaaaaaaa").message("Incorrect name length 4 - 25").build();

        assertEquals(HttpStatus.BAD_REQUEST, recipeValidator.validate(recipe).getStatusCode());
        assertEquals(errorResponse, recipeValidator.validate(recipe).getBody());

    }

    @Test
    public void shouldValidateWithDescriptionNullAndReturnErrorResponse() {

        when(recipe.getDescription()).thenReturn(null);

        errorResponse = ErrorResponse.builder().key("description").value(null).message("Cant be null").build();

        assertEquals(HttpStatus.BAD_REQUEST, recipeValidator.validate(recipe).getStatusCode());
        assertEquals(errorResponse, recipeValidator.validate(recipe).getBody());

    }

    @Test
    public void shouldValidateWithDescriptionHaveLengthMoreThanSixHundredTwentyFiveAndReturnErrorResponse() {

        String largeDescription = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";

        when(recipe.getDescription()).thenReturn(largeDescription);

        errorResponse = ErrorResponse.builder().key("description").value(largeDescription).message("Cant be more than 650 characters").build();

        assertEquals(HttpStatus.BAD_REQUEST, recipeValidator.validate(recipe).getStatusCode());
        assertEquals(errorResponse, recipeValidator.validate(recipe).getBody());

    }

    @Test
    public void shouldValidateWithDescriptionHaveLengthEqualThanSixHundredTwentyFiveAndReturnErrorResponse() {

        String largeDescription = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaa";

        when(recipe.getDescription()).thenReturn(largeDescription);

        errorResponse = ErrorResponse.builder().key("description").value(largeDescription).message("Cant be more than 650 characters").build();

        assertEquals(HttpStatus.BAD_REQUEST, recipeValidator.validate(recipe).getStatusCode());
        assertEquals(errorResponse, recipeValidator.validate(recipe).getBody());

    }


    @Test
    public void shouldValidateWithPreparationIsNullAndReturnErrorResponse() {

        when(recipe.getPreparation()).thenReturn(null);

        errorResponse = ErrorResponse.builder().key("Preparation").value(null).message("Cant be null").build();

        assertEquals(HttpStatus.BAD_REQUEST, recipeValidator.validate(recipe).getStatusCode());
        assertEquals(errorResponse, recipeValidator.validate(recipe).getBody());

    }


    @Test
    public void shouldValidateWithIngredientsIsNullAndReturnErrorResponse() {

        when(recipe.getIngredients()).thenReturn(null);

        errorResponse = ErrorResponse.builder().key("Ingredients").value(null).message("Cant be null the list of ingredients").build();

        assertEquals(HttpStatus.BAD_REQUEST, recipeValidator.validate(recipe).getStatusCode());
        assertEquals(errorResponse, recipeValidator.validate(recipe).getBody());

    }

    @Test
    public void shouldValidateWithIngredientsIsEmptyAndReturnErrorResponse() {

        when(recipe.getIngredients()).thenReturn(new ArrayList<Ingredient>());

        errorResponse = ErrorResponse.builder().key("Ingredients").value(null).message("The recipe need at least 1 ingredient").build();

        assertEquals(HttpStatus.BAD_REQUEST, recipeValidator.validate(recipe).getStatusCode());
        assertEquals(errorResponse, recipeValidator.validate(recipe).getBody());

    }

}