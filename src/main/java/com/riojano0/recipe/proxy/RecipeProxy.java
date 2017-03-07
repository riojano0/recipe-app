package com.riojano0.recipe.proxy;

import com.riojano0.recipe.domain.Recipe;
import com.riojano0.recipe.repository.RecipeRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@NoArgsConstructor
@Component
public class RecipeProxy {

    @Autowired
    private RecipeRepository recipeRepository;

    public List<Recipe> getRecipes(List<String> ingredients) {
        List<Recipe> recipes;
        if (ingredients == null) {
            recipes = recipeRepository.findAll();
        } else {
            recipes = recipeRepository.findDistinctByIngredientsNameInIgnoreCase(ingredients);
        }
        return recipes;
    }

    public Recipe getRecipeById(Long id) {

        return recipeRepository.findOne(id);
    }

    public ResponseEntity<String> saveRecipe(Recipe recipe) {
        ResponseEntity<String> response;
        try {
            recipeRepository.save(recipe);
            response = new ResponseEntity<>("Save Succesfull", HttpStatus.CREATED);
        } catch (Exception e) {
            response = new ResponseEntity<>("Save Failed", HttpStatus.BAD_REQUEST);
        }

        return response;
    }


}
