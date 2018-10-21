package com.recipe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.recipe.domain.Ingredients;
import com.recipe.domain.Recipes;
import com.recipe.entities.Ingredient;
import com.recipe.entities.IngredientList;
import com.recipe.entities.Recipe;
import com.recipe.exceptions.NotFoundException;
import com.recipe.repository.IngredientRepository;
import com.recipe.repository.RecipeRepository;
import com.recipe.service.RecipeService;

@Service
public class RecipeServiceImpl implements RecipeService {
	
	@Autowired
	RecipeRepository recipeRepository;
	
	@Autowired
	IngredientRepository ingredientRepository;

	@Override
	public Recipe getRecipe(Integer id, Integer people) {
		Recipe recipe = recipeRepository.findById(id).orElseThrow(() -> new NotFoundException("Recipe", id));
		if(people != null) {
			double recipeForPeople = recipe.getPeople();
			for(IngredientList ingredientList: recipe.getIngredients()) {
				int amount = ingredientList.getAmount();
				double factor = amount / recipeForPeople;
				amount = (int) Math.round(factor * people);
				ingredientList.setAmount(amount);
			}
			recipe.setPeople(people);
		}
		return recipe;
	}

	@Override
	public Recipe createOrUpdateRecipe(Recipe recipe) {
		try {
			return recipeRepository.save(recipe);
		}catch(DataIntegrityViolationException e) {
			System.out.println(e.getMessage());
			throw new NotFoundException("Ingredient");
		}
	}
	
	@Override
	public Recipes searchRecipe(String name) {
		return new Recipes(recipeRepository.findByNameIgnoreCaseContaining(name));
	}

	@Override
	public Ingredients getAllIngredients() {
		return new Ingredients(ingredientRepository.findAll());
	}

	@Override
	public Ingredients searchIngredients(String query) {
		return new Ingredients(ingredientRepository.findByNameIgnoreCaseContaining(query));
	}

	@Override
	public Ingredient createOrUpdateIngredient(Ingredient ingredient) {
		return ingredientRepository.save(ingredient);
	}

	@Override
	public Ingredient getIngredient(int id) {
		return ingredientRepository.findById(id).orElseThrow(() -> new NotFoundException("Ingredient", id));
	}

}
