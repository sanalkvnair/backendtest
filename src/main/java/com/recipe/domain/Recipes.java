package com.recipe.domain;

import java.util.List;

import com.recipe.entities.Recipe;

public class Recipes {

	private List<Recipe> recipes;

	public Recipes(List<Recipe> recipes) {
		super();
		this.recipes = recipes;
	}

	public List<Recipe> getRecipes() {
		return recipes;
	}

	public void setRecipes(List<Recipe> recipes) {
		this.recipes = recipes;
	}
	
}
