package com.example.recipe.services;

import com.example.recipe.commands.RecipeCommand;
import com.example.recipe.domain.Recipe;
import java.util.Set;

public interface RecipeService {

        Set<Recipe> getRecipes();
        Recipe findById(Long l);
        RecipeCommand saveRecipeCommand(RecipeCommand command);
        RecipeCommand findCommandById(Long l);
        void deleteById(Long idToDelete);

}
