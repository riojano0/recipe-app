package com.riojano0.recipe.resource.validator;

import com.riojano0.recipe.domain.Error.ErrorResponse;
import com.riojano0.recipe.domain.Recipe;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


@Component
public class RecipeValidator {

    public ResponseEntity validate(Recipe request) {

        ResponseEntity response = null;
        if (request.getName() == null) {
            response = ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("name", request.getName(), "Cant be null"));
        } else if (request.getName().length() < 4 || request.getName().length() > 25) {
            response = ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("name", request.getName(), "Incorrect name lenght 4 - 25"));
        } else if (request.getDescription() == null) {
            response = ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("description", request.getDescription(), "Cant be null"));
        } else if (request.getDescription().length() > 650) {
            response = ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("description", request.getDescription(), "Cant be more than 650 characters"));
        } else if (request.getPreparation() == null) {
            response = ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Preparation", request.getPreparation().toString(), "Cant be null "));
        } else if (request.getIngredients() == null) {
            response = ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Ingredients", null, "Cant be null the list of ingredients"));
        } else if (request.getIngredients().isEmpty()) {
            response = ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Ingredients", null, "The recipe need at least 1 ingredient"));
        }

        return response;
    }
}
