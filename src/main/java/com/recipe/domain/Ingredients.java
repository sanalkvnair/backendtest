package com.recipe.domain;

import java.util.List;

import com.recipe.entities.Ingredient;

public class Ingredients {

	private List<Ingredient> ingredients;

	public Ingredients(List<Ingredient> ingredients) {
		super();
		this.ingredients = ingredients;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	
}
