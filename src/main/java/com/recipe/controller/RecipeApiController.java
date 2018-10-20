package com.recipe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.recipe.domain.Ingredients;
import com.recipe.domain.Recipes;
import com.recipe.entities.Ingredient;
import com.recipe.entities.Recipe;
import com.recipe.service.RecipeService;

@RestController
@RequestMapping(value = "/api", consumes = "application/json", produces = "application/json")
public class RecipeApiController {
	
	@Autowired
	RecipeService recipeService;

	@GetMapping(value = "/ping")
	ResponseEntity<String> getPing() {
		return new ResponseEntity<String>("pong", HttpStatus.OK);
	}
	
	@GetMapping(value = "/recipe/{recipe_id}")
	public ResponseEntity<Recipe> getRecipeById(@PathVariable("recipe_id") int id,
			@RequestParam(value = "p", required = false) Integer people) {
		System.out.println(people);
		return new ResponseEntity<Recipe>(recipeService.getRecipe(id, people), HttpStatus.OK);
	}
	
	@PostMapping(value = "/recipe")
	public ResponseEntity<Recipe> createRecipe(@RequestBody Recipe recipe) {
		return new ResponseEntity<Recipe>(recipeService.createRecipe(recipe), HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/recipe/{recipe_id}")
	public ResponseEntity<Recipe> updateRecipe(@PathVariable("recipe_id") int id, @RequestBody Recipe recipe) {
		recipe.setId(id);
		return new ResponseEntity<Recipe>(recipeService.createRecipe(recipe), HttpStatus.OK);
	}
	
	@GetMapping(value = "/recipe")
	public ResponseEntity<Recipes> searchRecipeByName(@RequestParam(value = "q") String query) {
		return new ResponseEntity<Recipes>(recipeService.searchRecipe(query), HttpStatus.OK);
	}
	
	@GetMapping(value = "/ingredient")
	public ResponseEntity<Ingredients> getIngredients(@RequestParam(value = "q", required = false) String query) {
		if(query != null) {
			return new ResponseEntity<Ingredients>(recipeService.searchIngredients(query), HttpStatus.OK);
		}
		return new ResponseEntity<Ingredients>(recipeService.getAllIngredients(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/ingredient/{ingredient_id}")
	public ResponseEntity<Ingredient> getIngredientById(@PathVariable("ingredient_id") int id) {
		return new ResponseEntity<Ingredient>(recipeService.getIngredient(id), HttpStatus.OK);
	}
	
	@PostMapping(value = "/ingredient")
	public ResponseEntity<Ingredient> createIngredient(@RequestBody Ingredient ingredient) {
		return new ResponseEntity<Ingredient>(recipeService.createOrUpdateIngredient(ingredient), HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/ingredient/{ingredient_id}")
	public ResponseEntity<Ingredient> updateIngredient(@PathVariable("ingredient_id") int id, @RequestBody Ingredient ingredient) {
		ingredient.setId(id);
		return new ResponseEntity<Ingredient>(recipeService.createOrUpdateIngredient(ingredient), HttpStatus.OK);
	}
}
