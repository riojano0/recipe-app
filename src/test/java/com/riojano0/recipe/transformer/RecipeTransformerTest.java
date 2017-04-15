package com.riojano0.recipe.transformer;

import com.riojano0.recipe.domain.Error.ErrorResponse;
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

import static org.junit.Assert.*;

/**
 * Created by Daniel on 15/04/2017.
 */
public class RecipeTransformerTest {

    @Mock
    private Recipe recipe;

    @InjectMocks
    private RecipeTransformer recipeTransformer;

    private ResponseEntity responseEntity;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void transformWithRecipe() throws Exception {

        responseEntity = ResponseEntity
                .status(HttpStatus.OK)
                .body(recipe);

        assertEquals(responseEntity,recipeTransformer.transform(recipe));

    }

    @Test
    public void transformWithListOfRecipes() throws Exception {
        List<Recipe> recipeList = new ArrayList<>();
        recipeList.add(recipe);

        responseEntity = ResponseEntity
                .status(HttpStatus.OK)
                .body(recipeList);

        assertEquals(responseEntity,recipeTransformer.transform(recipeList));

    }

    @Test
    public void responseNoContent() throws Exception {

        responseEntity = ResponseEntity
                .status(HttpStatus.CREATED)
                .body(null);

        assertEquals(responseEntity,recipeTransformer.responseCreated());

    }

    @Test
    public void responseUnknownError() throws Exception {

        responseEntity = ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse("Internal Server", "Unknown", "Something go wrong"));

        assertEquals(responseEntity,recipeTransformer.responseUnknownError());

    }

}