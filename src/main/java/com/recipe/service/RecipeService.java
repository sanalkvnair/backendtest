package com.recipe.service;

import com.recipe.domain.Ingredients;
import com.recipe.domain.Recipes;
import com.recipe.entities.Ingredient;
import com.recipe.entities.Recipe;

public interface RecipeService {

	public Recipe getRecipe(Integer id, Integer people);
	public Recipe createRecipe(Recipe recipe);
	public Recipes searchRecipe(String name);
	public Ingredients getAllIngredients();
	public Ingredients searchIngredients(String query);
	public Ingredient createOrUpdateIngredient(Ingredient ingredient);
	public Ingredient getIngredient(int id);
}
