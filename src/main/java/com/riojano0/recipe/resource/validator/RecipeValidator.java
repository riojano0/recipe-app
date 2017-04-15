package com.riojano0.recipe.resource.validator;

import com.riojano0.recipe.domain.Error.ErrorResponse;
import com.riojano0.recipe.domain.Recipe;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


@Component
public class RecipeValidator {

    private ResponseEntity response;
    private Boolean checked;

    public ResponseEntity validate(Recipe request) {

        response = null;
        checked = false;
        validateName(request);
        validateDescription(request);
        validatePreparation(request);
        validateIngredients(request);

        return response;
    }

    private void validateName(Recipe request) {

        if (!checked) {
            if (request.getName() == null) {
                response = ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(ErrorResponse.builder().key("name").value(request.getName())
                                .message("Cant be null").build());
                checked = true;
            } else if (request.getName().length() <= 3 || request.getName().length() >= 26) {
                response = ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(ErrorResponse.builder().key("name").value(request.getName())
                                .message("Incorrect name length 4 - 25").build());
                checked = true;
            }
        }

    }

    private void validateDescription(Recipe request) {
        if (!checked) {
            if (request.getDescription() == null) {
                response = ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(ErrorResponse.builder().key("description").value(request.getDescription())
                                .message("Cant be null").build());
                checked = true;
            } else if (request.getDescription().length() >= 650) {
                response = ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(ErrorResponse.builder().key("description").value(request.getDescription())
                                .message("Cant be more than 650 characters").build());
                checked = true;
            }

        }
    }

    private void validatePreparation(Recipe request) {
        if (!checked) {
            if (request.getPreparation() == null) {
                response = ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(ErrorResponse.builder().key("Preparation").value(null).message("Cant be null").build());
                checked = true;
            }
        }
    }

    private void validateIngredients(Recipe request) {

        if (!checked) {
            if (request.getIngredients() == null) {
                response = ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(ErrorResponse.builder().key("Ingredients").value(null)
                                .message("Cant be null the list of ingredients").build());
                checked = true;
            } else if (request.getIngredients().isEmpty()) {
                response = ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(ErrorResponse.builder().key("Ingredients").value(null)
                                .message("The recipe need at least 1 ingredient").build());
                checked = true;
            }
        }
    }
}
