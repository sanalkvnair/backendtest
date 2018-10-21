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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 
 * @author Sanal Nair
 *
 * API Controller class for recipes and ingredients operation.
 */
@RestController
@RequestMapping(value = "/api", produces = "application/json")
@Api(value = "Recipe Backend", produces="application/json")
public class RecipeApiController {
	
	@Autowired
	RecipeService recipeService;

	/**
	 * The method use to verify API service is working.
	 * 
	 * @return String
	 */
	@ApiOperation(value = "To verify API availablity", response = ResponseEntity.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 404, message = "The resources not found.")
	})
	@GetMapping(value = "/ping", produces = "text/plain")
	ResponseEntity<String> getPing() {
		return new ResponseEntity<String>("pong", HttpStatus.OK);
	}
	
	/**
	 * The API method returns a recipe if it is available with status HttpStatus.OK else return HttpStatus.NOT_FOUND
	 * @param id
	 * @param people (optional)
	 * @return Recipe
	 */
	@ApiOperation(value = "Get Recipe based on ID and optional people paramter to recalculate amount", response = ResponseEntity.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 404, message = "Recipe not found.")
	})
	@GetMapping(value = "/recipe/{recipe_id}")
	public ResponseEntity<Recipe> getRecipeById(@PathVariable("recipe_id") int id,
			@RequestParam(value = "p", required = false) Integer people) {
		System.out.println(people);
		return new ResponseEntity<Recipe>(recipeService.getRecipe(id, people), HttpStatus.OK);
	}
	
	/**
	 * The API method creates a recipe with the passed ingredient(s), assuming ingredient(s) already exists.
	 * 
	 * @param recipe
	 * @return Recipe
	 */
	@ApiOperation(value = "Create recipe using already created ingredients", response = ResponseEntity.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "CREATED"),
			@ApiResponse(code = 404, message = "Ingredient not found.")
	})
	@PostMapping(value = "/recipe", consumes = "application/json")
	public ResponseEntity<Recipe> createRecipe(@RequestBody Recipe recipe) {
		return new ResponseEntity<Recipe>(recipeService.createOrUpdateRecipe(recipe), HttpStatus.CREATED);
	}
	
	/**
	 * The API method updates recipe name and ingredient(s), assuming ingredient(s) already exists.
	 * 
	 * @param id
	 * @param recipe
	 * @return Recipe
	 */
	@ApiOperation(value = "Update recipe with exiting ingredients", response = ResponseEntity.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 404, message = "Ingredient not found.")
	})
	@PutMapping(value = "/recipe/{recipe_id}", consumes = "application/json")
	public ResponseEntity<Recipe> updateRecipe(@PathVariable("recipe_id") int id, @RequestBody Recipe recipe) {
		recipe.setId(id);
		return new ResponseEntity<Recipe>(recipeService.createOrUpdateRecipe(recipe), HttpStatus.OK);
	}
	
	/**
	 * The API method return one or more list of recipe(s) if exists else return an empty list.
	 * 
	 * @param query
	 * @return List of Recipes
	 */
	@ApiOperation(value = "Search recipes by name", response = ResponseEntity.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 404, message = "Resource not found.")
	})
	@GetMapping(value = "/recipe")
	public ResponseEntity<Recipes> searchRecipeByName(@RequestParam(value = "q") String query) {
		return new ResponseEntity<Recipes>(recipeService.searchRecipe(query), HttpStatus.OK);
	}
	
	/**
	 * The API method returns the list of all ingredients available,
	 * if queried then retrun one or more ingredients else return empty list.
	 * 
	 * @param query
	 * @return List of Ingredients
	 */
	@ApiOperation(value = "Get all ingredients list, else search for ingredients using query parameter", response = ResponseEntity.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 404, message = "Resource not found.")
	})
	@GetMapping(value = "/ingredient")
	public ResponseEntity<Ingredients> getIngredients(@RequestParam(value = "q", required = false) String query) {
		if(query != null) {
			return new ResponseEntity<Ingredients>(recipeService.searchIngredients(query), HttpStatus.OK);
		}
		return new ResponseEntity<Ingredients>(recipeService.getAllIngredients(), HttpStatus.OK);
	}
	
	/**
	 * The API method return ingredient of passed ID, if available return HttpStatus.OK else HttpStatus.NOT_FOUND.
	 * 
	 * @param id
	 * @return Ingredient
	 */
	@ApiOperation(value = "Get ingredient for the passed ID", response = ResponseEntity.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 404, message = "Ingredient not found.")
	})
	@GetMapping(value = "/ingredient/{ingredient_id}")
	public ResponseEntity<Ingredient> getIngredientById(@PathVariable("ingredient_id") int id) {
		return new ResponseEntity<Ingredient>(recipeService.getIngredient(id), HttpStatus.OK);
	}
	
	/**
	 * The API method for creating Ingredient.
	 * 
	 * @param ingredient
	 * @return Ingredient
	 */
	@ApiOperation(value = "Create ingredient", response = ResponseEntity.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "CREATED"),
			@ApiResponse(code = 404, message = "Resource not found.")
	})
	@PostMapping(value = "/ingredient", consumes = "application/json")
	public ResponseEntity<Ingredient> createIngredient(@RequestBody Ingredient ingredient) {
		return new ResponseEntity<Ingredient>(recipeService.createOrUpdateIngredient(ingredient), HttpStatus.CREATED);
	}
	
	/**
	 * The API method for updating Ingredient.
	 * 
	 * @param id
	 * @param ingredient
	 * @return Ingredient
	 */
	@ApiOperation(value = "Update ingredient", response = ResponseEntity.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 404, message = "Recipe not found.")
	})
	@PutMapping(value = "/ingredient/{ingredient_id}", consumes = "application/json")
	public ResponseEntity<Ingredient> updateIngredient(@PathVariable("ingredient_id") int id, @RequestBody Ingredient ingredient) {
		ingredient.setId(id);
		return new ResponseEntity<Ingredient>(recipeService.createOrUpdateIngredient(ingredient), HttpStatus.OK);
	}
}
