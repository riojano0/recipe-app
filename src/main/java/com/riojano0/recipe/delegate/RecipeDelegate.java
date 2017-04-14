package com.riojano0.recipe.delegate;

import com.riojano0.recipe.domain.Recipe;
import com.riojano0.recipe.repository.RecipeRepository;
import com.riojano0.recipe.transformer.RecipeTransformer;
import lombok.NoArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@NoArgsConstructor
@Component
public class RecipeDelegate {

    private final static Logger logger = Logger.getLogger(RecipeDelegate.class);

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private RecipeTransformer recipeTransformer;

    public ResponseEntity getRecipes(List<String> ingredients) {

        List<Recipe> recipes;
        if (ingredients == null) {
            recipes = recipeRepository.findAll();
            logger.info("GET - /recipe - Recipe All");
        } else {
            recipes = recipeRepository.findDistinctByIngredientsNameInIgnoreCase(ingredients);
            logger.info("GET - /recipe - Recipe by ingredients: " + ingredients);
        }

        return recipeTransformer.transform(recipes);
    }

    public ResponseEntity getRecipeById(Long id) {

        logger.info("GET - /recipe - Recipe by ID: " + id);
        return recipeTransformer.transform(recipeRepository.findOne(id));

    }

    public ResponseEntity saveRecipe(Recipe recipe) {
        ResponseEntity response;

        try {
            recipeRepository.save(recipe);
            response = recipeTransformer.responseNoContent();
            logger.info("POST - /recipe - Save a new recipe");
        } catch (Exception e) {
            response = recipeTransformer.responseUnknowError();
            logger.warn("POST - /recipe - cant save the new recipe");
        }

        return response;
    }


}
