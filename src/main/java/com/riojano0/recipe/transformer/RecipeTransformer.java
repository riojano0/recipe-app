package com.riojano0.recipe.transformer;

import com.riojano0.recipe.domain.Error.ErrorResponse;
import com.riojano0.recipe.domain.Recipe;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RecipeTransformer {


    public ResponseEntity transform(Recipe recipe) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(recipe);

    }

    public ResponseEntity transform(List<Recipe> recipes) {

        return ResponseEntity.
                status(HttpStatus.OK)
                .body(recipes);
    }

    public ResponseEntity responseNoContent() {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    public ResponseEntity responseUnknowError() {

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse("Interal Server", "Unknow", "Something go wrong"));
    }

}
