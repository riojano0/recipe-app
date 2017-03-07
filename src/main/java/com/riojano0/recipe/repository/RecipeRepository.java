package com.riojano0.recipe.repository;

import com.riojano0.recipe.domain.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
public interface RecipeRepository extends CrudRepository<Recipe, Long> {

    List<Recipe> findAll();

    List<Recipe> findDistinctByIngredientsNameInIgnoreCase(List<String> IngredientList);

}
