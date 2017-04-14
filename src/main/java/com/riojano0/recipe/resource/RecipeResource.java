package com.riojano0.recipe.resource;

import com.riojano0.recipe.delegate.RecipeDelegate;
import com.riojano0.recipe.domain.Error.ErrorResponse;
import com.riojano0.recipe.domain.Recipe;
import com.riojano0.recipe.resource.validator.RecipeValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "recipes")
@RestController
public class RecipeResource {

    @Autowired
    private RecipeDelegate recipeDelegate;

    @Autowired
    private RecipeValidator recipeValidator;

    @ApiOperation(notes = "Get recipe by ID", value = "/recipe/id/{id}", response = Recipe.class)
    @RequestMapping(value = "/recipe/id/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getRecipeById(@PathVariable long id) {

        return recipeDelegate.getRecipeById(id);
    }

    @ApiOperation(notes = "Get all recipes or a list if recibe ingredients in query params", value = "/recipe",
            response = Recipe.class, responseContainer = "List")
    @RequestMapping(value = "/recipe", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getRecipes(
            @RequestParam(value = "ingredients", required = false) List<String> ingredients) {


        return recipeDelegate.getRecipes(ingredients);
    }

    @ApiOperation(notes = "Save a recipe (delete the id fields)",
            value = "/recipe")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "For cases like bad request body or problem with connection", response = ErrorResponse.class)
    })
    @RequestMapping(value = "/recipe", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity saveRecipeNew(@RequestBody Recipe recipe) {
        ResponseEntity response;

        response = recipeValidator.validate(recipe);

        if (response == null) {
            response = recipeDelegate.saveRecipe(recipe);
        }

        return response;

    }

}
