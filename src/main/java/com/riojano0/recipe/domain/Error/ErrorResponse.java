package com.riojano0.recipe.domain.Error;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class ErrorResponse {

    String key;
    String value;
    String message;

}
