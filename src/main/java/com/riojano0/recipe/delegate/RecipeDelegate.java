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

    private ResponseEntity response;

    public ResponseEntity getRecipes(List<String> ingredients) {

        List<Recipe> recipes;
        if (ingredients == null) {
            recipes = recipeRepository.findAll();
            logger.info("GET - /recipe - Recipe All");
        } else {
            recipes = recipeRepository.findDistinctByIngredientsNameInIgnoreCase(ingredients);
            if (recipes.isEmpty()){
                logger.info("GET - /recipe - Recipe by ingredients: " + ingredients + " - Cant find recipes");
            }
            else{
                logger.info("GET - /recipe - Recipe by ingredients: " + ingredients);
            }

        }

        return recipeTransformer.transform(recipes);
    }

    public ResponseEntity getRecipeById(Long id) {

        Recipe recipe;
        recipe = recipeRepository.findOne(id);

        if (recipe == null) {
            logger.info("GET - /recipe - Recipe by ID: " + id + " - Cant find the recipe");
        } else {
            logger.info("GET - /recipe - Recipe by ID: " + id);
        }
        response = recipeTransformer.transform(recipe);
        return response;

    }

    public ResponseEntity saveRecipe(Recipe recipe) {

        try {
            recipeRepository.save(recipe);
            response = recipeTransformer.responseCreated();
            logger.info("POST - /recipe - Save a new recipe");
        } catch (Exception e) {
            response = recipeTransformer.responseUnknownError();
            logger.warn("POST - /recipe - cant save the new recipe");
        }

        return response;
    }


}
