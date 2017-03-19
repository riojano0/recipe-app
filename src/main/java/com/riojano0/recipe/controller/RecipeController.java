package com.riojano0.recipe.controller;

import com.riojano0.recipe.domain.Recipe;
import com.riojano0.recipe.delegate.RecipeDelegate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "recipes")
@RestController
public class RecipeController {

    @Autowired
    private RecipeDelegate recipeDelegate;

    @ApiOperation(notes = "Get recipe by ID", value = "/recipe/id/{id}")
    @RequestMapping(value = "/recipe/id/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable long id) {
        Recipe recipe = recipeDelegate.getRecipeById(id);
        return new ResponseEntity<>(recipe, HttpStatus.OK);
    }

    @ApiOperation(notes = "Get all recipes or a list if recibe ingredients in query params", value = "/recipe")
    @RequestMapping(value = "/recipe", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Recipe>> getRecipes(@RequestParam(value = "ingredients", required = false) List<String> ingredients) {
        List<Recipe> recipes = recipeDelegate.getRecipes(ingredients);
        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }

    @ApiOperation(notes = "Save a recipe (delete the id fields)", value = "/recipe")
    @RequestMapping(value = "/recipe", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> saveRecipeNew(@RequestBody Recipe recipe) {
        return recipeDelegate.saveRecipe(recipe);
    }

}
