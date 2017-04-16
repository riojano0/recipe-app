package com.riojano0.recipe.delegate;

import com.riojano0.recipe.domain.Error.ErrorResponse;
import com.riojano0.recipe.domain.Recipe;
import com.riojano0.recipe.repository.RecipeRepository;
import com.riojano0.recipe.transformer.RecipeTransformer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.*;

public class RecipeDelegateTest {

    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private RecipeTransformer recipeTransformer;

    @InjectMocks
    private RecipeDelegate recipeDelegate;

    private ResponseEntity responseEntity;
    private Recipe recipe;
    private List<Recipe> recipes;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        responseEntity = ResponseEntity.status(HttpStatus.OK).body(recipes);

    }


    @Test
    public void shouldReturnAllMatchRecipesWhenIngredientsListIsNotNull() throws Exception {

        List<String> ingredientsList = new ArrayList<>();
        ingredientsList.add("Papa");

        recipe = Recipe.builder().build();

        recipes = new ArrayList<>();
        recipes.add(recipe);

        responseEntity = ResponseEntity.status(HttpStatus.OK).body(recipes);
        when(recipeTransformer.transform(anyListOf(Recipe.class))).thenReturn(responseEntity);
        when(recipeRepository.findDistinctByIngredientsNameInIgnoreCase(anyList())).thenReturn(recipes);

        assertEquals(HttpStatus.OK, recipeDelegate.getRecipes(ingredientsList).getStatusCode());
        assertEquals(recipes, recipeDelegate.getRecipes(ingredientsList).getBody());

        verify(recipeTransformer, times(2)).transform(anyListOf(Recipe.class));
        verify(recipeRepository, times(0)).findAll();
        verify(recipeRepository, times(2)).findDistinctByIngredientsNameInIgnoreCase(anyList());

    }

    @Test
    public void shouldReturnAllRecipesWhenIngredientsListIsNull() throws Exception {

        when(recipeTransformer.transform(anyListOf(Recipe.class))).thenReturn(responseEntity);

        assertEquals(HttpStatus.OK, recipeDelegate.getRecipes(null).getStatusCode());
        assertEquals(recipes, recipeDelegate.getRecipes(null).getBody());

        verify(recipeTransformer, times(2)).transform(anyListOf(Recipe.class));
        verify(recipeRepository, times(2)).findAll();
        verify(recipeRepository, times(0)).findDistinctByIngredientsNameInIgnoreCase(anyList());

    }

    @Test
    public void shouldReturnRecipesListEmptyWhenIngredientsListIsNotNullButNotMatchAny() throws Exception {

        List<String> ingredientsList = new ArrayList<>();
        ingredientsList.add("papa");

        recipes = new ArrayList<>();

        responseEntity = ResponseEntity.status(HttpStatus.OK).body(recipes);

        when(recipeTransformer.transform(anyListOf(Recipe.class))).thenReturn(responseEntity);
        when(recipeRepository.findDistinctByIngredientsNameInIgnoreCase(anyList())).thenReturn(recipes);

////REVIZAR PARA MATAR AL MUTANTE DE EMPTY:LIST

        assertEquals(HttpStatus.OK, recipeDelegate.getRecipes(ingredientsList).getStatusCode());
        assertEquals(recipes, recipeDelegate.getRecipes(ingredientsList).getBody());

        verify(recipeTransformer, times(2)).transform(anyListOf(Recipe.class));
        verify(recipeRepository, times(0)).findAll();
        verify(recipeRepository, times(2)).findDistinctByIngredientsNameInIgnoreCase(anyList());

    }

    @Test
    public void shouldReturnOneRecipeWhenMatchById() throws Exception {

        responseEntity = ResponseEntity.status(HttpStatus.OK).body(recipe);
        when(recipeTransformer.transform(any(Recipe.class))).thenReturn(responseEntity);
        when(recipeRepository.findOne(anyLong())).thenReturn(recipe);

        assertEquals(HttpStatus.OK, recipeDelegate.getRecipeById(anyLong()).getStatusCode());
        assertEquals(recipe, recipeDelegate.getRecipeById(anyLong()).getBody());

        verify(recipeTransformer, times(2)).transform(any(Recipe.class));
        verify(recipeRepository, times(2)).findOne(anyLong());

    }

    @Test
    public void shouldReturnNoneRecipeWhenNoMatchAnyById() throws Exception {

        responseEntity = ResponseEntity.status(HttpStatus.OK).body(null);
        when(recipeTransformer.transform(any(Recipe.class))).thenReturn(responseEntity);
        when(recipeRepository.findOne(anyLong())).thenReturn(null);

        assertEquals(HttpStatus.OK, recipeDelegate.getRecipeById(anyLong()).getStatusCode());
        assertNull(recipeDelegate.getRecipeById(anyLong()).getBody());

        verify(recipeTransformer, times(2)).transform(any(Recipe.class));
        verify(recipeRepository, times(2)).findOne(anyLong());

    }

    @Test
    public void shouldFailSaveRecipeWhenDatabaseIsTurnedOff() throws Exception {

        ErrorResponse errorResponse = new ErrorResponse("Internal Server", "Unknown", "Something go wrong");

        responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        when(recipeTransformer.responseUnknownError()).thenReturn(responseEntity);

        doThrow(new DataAccessException("") {
        }).when(recipeRepository).save(any(Recipe.class));

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, recipeDelegate.saveRecipe(any(Recipe.class)).getStatusCode());
        assertEquals(errorResponse, recipeDelegate.saveRecipe(any(Recipe.class)).getBody());

        verify(recipeRepository, times(2)).save(any(Recipe.class));

    }

    @Test
    public void shouldSaveRecipeWhenDatabaseIsTurnedOn() throws Exception {

        responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(null);
        when(recipeTransformer.responseCreated()).thenReturn(responseEntity);

        assertEquals(HttpStatus.CREATED, recipeDelegate.saveRecipe(any(Recipe.class)).getStatusCode());
        assertNull(recipeDelegate.saveRecipe(any(Recipe.class)).getBody());

        verify(recipeRepository, times(2)).save(any(Recipe.class));

    }

}