package com.riojano0.recipe.resource;

import com.riojano0.recipe.delegate.RecipeDelegate;
import com.riojano0.recipe.domain.Ingredient;
import com.riojano0.recipe.domain.Preparation;
import com.riojano0.recipe.domain.Recipe;
import com.riojano0.recipe.resource.validator.RecipeValidator;
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
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;

public class RecipeResourceTest {

    @Mock
    private RecipeDelegate recipeDelegate;
    @Mock
    private RecipeValidator recipeValidator;

    @InjectMocks
    private RecipeResource recipeResource;

    private ResponseEntity responseEntity;
    private Recipe recipe;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        Ingredient ingredient = Ingredient.builder().name("Papa").build();
        List<Ingredient> ingredientList = new ArrayList<>();
        ingredientList.add(ingredient);
        Preparation preparation = Preparation.builder().build();
        recipe = Recipe.builder()
                .name("valid")
                .description("valid")
                .preparation(preparation)
                .ingredients(ingredientList)
                .build();
    }


    @Test
    public void shouldReturnResponseWithTheMatchById() {

        responseEntity = ResponseEntity.status(HttpStatus.OK).body(recipe);
        when(recipeDelegate.getRecipeById(anyLong())).thenReturn(responseEntity);

        assertEquals(HttpStatus.OK, recipeResource.getRecipeById(1).getStatusCode());
        assertEquals(recipe, recipeResource.getRecipeById(1).getBody());

    }

    @Test
    public void shouldReturnEmptyResponseWithNoMatchById() {

        responseEntity = ResponseEntity.status(HttpStatus.OK).body(null);
        when(recipeDelegate.getRecipeById(anyLong())).thenReturn(responseEntity);

        assertEquals(HttpStatus.OK, recipeResource.getRecipeById(1).getStatusCode());
        assertEquals(null, recipeResource.getRecipeById(1).getBody());
    }

    @Test
    public void shouldReturnResponseWithMachByIngredients() {
        List<Recipe> recipes = new ArrayList<>();
        recipes.add(recipe);
        List<String> ingredients = new ArrayList<String>();
        ingredients.add("Papa");
        ingredients.add("Tomate");

        responseEntity = ResponseEntity.status(HttpStatus.OK).body(recipes);
        when(recipeDelegate.getRecipes(anyObject())).thenReturn(responseEntity);

        assertEquals(HttpStatus.OK, recipeResource.getRecipes(ingredients).getStatusCode());
        assertEquals(recipes, recipeResource.getRecipes(ingredients).getBody());
    }

    @Test
    public void shouldReturnEmptyResponseWithNoMachByIngredients() {
        List<String> ingredients = new ArrayList<String>();
        ingredients.add("Papa");
        ingredients.add("Tomate");

        responseEntity = ResponseEntity.status(HttpStatus.OK).body(null);
        when(recipeDelegate.getRecipes(anyObject())).thenReturn(responseEntity);

        assertEquals(HttpStatus.OK, recipeResource.getRecipes(ingredients).getStatusCode());
        assertEquals(null, recipeResource.getRecipes(ingredients).getBody());
    }

    @Test
    public void shouldReturnResponseStatusCreateWithValidRequest() {

        responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(null);
        when(recipeDelegate.saveRecipe(anyObject())).thenReturn(responseEntity);


        assertEquals(HttpStatus.CREATED, recipeResource.saveRecipe(recipe).getStatusCode());
        assertEquals(null, recipeResource.saveRecipe(recipe).getBody());
    }

    @Test
    public void shouldReturnResponseStatusBadRequestWithInvalidRequest() {

        responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        when(recipeDelegate.saveRecipe(anyObject())).thenReturn(responseEntity);


        assertEquals(HttpStatus.BAD_REQUEST, recipeResource.saveRecipe(recipe).getStatusCode());
        assertEquals(null, recipeResource.saveRecipe(recipe).getBody());
    }

}