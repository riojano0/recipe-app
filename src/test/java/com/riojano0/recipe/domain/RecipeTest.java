package com.riojano0.recipe.domain;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;


public class RecipeTest {

    @Mock
    private Preparation preparation;

    @InjectMocks
    private Recipe recipe;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getPreparation() throws Exception {
        assertEquals(preparation, recipe.getPreparation());
    }

}