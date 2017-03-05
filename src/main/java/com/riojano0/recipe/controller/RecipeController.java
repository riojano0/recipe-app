package com.riojano0.recipe.controller;

import com.riojano0.recipe.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RecipeController {

    private Ingredient papa;
    private Ingredient cebolla;
    private List<Ingredient> ingredientList = new ArrayList<>();

    private Step primerPaso;
    private Step segundoPaso;
    private List<Step> stepList = new ArrayList<>();

    private Preparation preparation;

    private Recipe recipe;


    public void fillRecipe() {
        papa = Ingredient.builder()
                .name("Papa")
                .portionUnit(PortionUnit.UNIT)
                .portion(1)
                .build();

        cebolla = Ingredient.builder()
                .name("Cebolla")
                .portionUnit(PortionUnit.UNIT)
                .portion(1)
                .build();

        ingredientList.add(papa);
        ingredientList.add(cebolla);

        primerPaso = Step.builder()
                .description("Primera cosa")
                .number(1)
                .build();

        segundoPaso = Step.builder()
                .description("Segunda cosa")
                .number(2)
                .build();

        stepList.add(primerPaso);
        stepList.add(segundoPaso);

        preparation = Preparation.builder()
                .timePreparation(30)
                .timeCooking(100)
                .readyIn(168)
                .steps(stepList)
                .build();

        recipe = Recipe.builder()
                .name("Una receta de ejemplo")
                .description("Esta recesa es facil")
                .ingredients(ingredientList)
                .preparation(preparation)
                .build();
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<String> getWelcome() {

        return new ResponseEntity<String>("Welcome", HttpStatus.OK);
    }


    @RequestMapping(value = "/test", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Recipe> getRecipe() {

        fillRecipe();
        return new ResponseEntity<Recipe>(recipe, HttpStatus.OK);
    }

}
