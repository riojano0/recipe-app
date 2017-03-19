package com.riojano0.recipe.delegate;

import com.riojano0.recipe.domain.Recipe;
import com.riojano0.recipe.repository.RecipeRepository;
import lombok.NoArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@NoArgsConstructor
@Component
public class RecipeDelegate {

    final static Logger logger = Logger.getLogger(RecipeDelegate.class);

    @Autowired
    private RecipeRepository recipeRepository;

    public List<Recipe> getRecipes(List<String> ingredients) {

        List<Recipe> recipes;
        if (ingredients == null) {
            recipes = recipeRepository.findAll();
            logger.info("GET - /recipe - Recipe All");
        } else {
            recipes = recipeRepository.findDistinctByIngredientsNameInIgnoreCase(ingredients);
            logger.info("GET - /recipe - Recipe by ingredients: "+ingredients);
        }

        return recipes;
    }

    public Recipe getRecipeById(Long id) {

        logger.info("GET - /recipe - Recipe by ID: "+id);
        return recipeRepository.findOne(id);

    }

    public ResponseEntity<String> saveRecipe(Recipe recipe) {
        ResponseEntity<String> response;
        try {
            recipeRepository.save(recipe);
            response = new ResponseEntity<>("Save Succesfull", HttpStatus.CREATED);
            logger.info("POST - /recipe - Save a new recipe");
        } catch (Exception e) {
            response = new ResponseEntity<>("Save Failed", HttpStatus.BAD_REQUEST);
            logger.warn("POST - /recipe - cant save the new recipe");
        }

        return response;
    }


}
