package com.riojano0.recipe.controller;

import com.riojano0.recipe.domain.Recipe;
import com.riojano0.recipe.proxy.RecipeProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RecipeController {

    @Autowired
    private RecipeProxy recipeProxy;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<String> getWelcome() {
        return new ResponseEntity<String>("Welcome", HttpStatus.OK);
    }

    @RequestMapping(value = "/recipe/id/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable long id) {
        Recipe recipe = recipeProxy.getRecipeById(id);
        return new ResponseEntity<>(recipe, HttpStatus.OK);
    }

    @RequestMapping(value = "/recipe", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Recipe>> getRecipes(@RequestParam(value = "ingredients", required = false) List<String> ingredients) {
        List<Recipe> recipes = recipeProxy.getRecipes(ingredients);
        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }

    @RequestMapping(value = "/recipe", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> saveRecipeNew(@RequestBody Recipe recipe) {
        return recipeProxy.saveRecipe(recipe);
    }

}
