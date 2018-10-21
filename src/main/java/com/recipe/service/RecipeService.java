package com.recipe.service;

import com.recipe.domain.Ingredients;
import com.recipe.domain.Recipes;
import com.recipe.entities.Ingredient;
import com.recipe.entities.Recipe;

public interface RecipeService {

	/**
	 * Service method for getting recipe based on ID and 
	 * if number of people passed then recalculate amount of ingredient.
	 * 
	 * @param Integer
	 * @param Integer
	 * @return {@link Recipe}
	 */
	public Recipe getRecipe(Integer id, Integer people);
	/**
	 * Service method for creating or updating recipe.
	 * 
	 * @param {@link Recipe}
	 * @return {@link Recipe}
	 */
	public Recipe createOrUpdateRecipe(Recipe recipe);
	/**
	 * Service method for searching recipes based on recipe name.
	 * 
	 * @param String
	 * @return {@link Recipes}
	 */
	public Recipes searchRecipe(String name);
	/**
	 * Service method for getting all ingredients.
	 * 
	 * @return {@link Ingredients}
	 */
	public Ingredients getAllIngredients();
	/**
	 * Service method for searching ingredients based on ingredient name.
	 * 
	 * @param String
	 * @return {@link Ingredients}
	 */
	public Ingredients searchIngredients(String query);
	/**
	 * Service method for creating or updating Ingredient.
	 * 
	 * @param {@link Ingredient}
	 * @return {@link Ingredient}
	 */
	public Ingredient createOrUpdateIngredient(Ingredient ingredient);
	/**
	 * Service method for fetching ingredient based on ID.
	 * 
	 * @param Integer
	 * @return {@link Ingredient}
	 */
	public Ingredient getIngredient(int id);
}
