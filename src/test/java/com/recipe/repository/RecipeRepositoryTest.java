package com.recipe.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.recipe.entities.Recipe;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RecipeRepositoryTest {

	@Autowired
	private RecipeRepository recipeRepository;
	
	@Test
	public void whenFindById_thenReturnRecipe() {
		//given
		String recipeName = "Mild Curry Omelet";
		
		//when
		Recipe found = recipeRepository.findById(2).get();
		
		//then
		assertThat(found.getName()).isEqualTo(recipeName);
	}
	
	@Test
	public void whenFindById_thenReturnRecipeWithIngredient() {
		//given
		int noOfIngredients = 10;
		
		//when
		Recipe found = recipeRepository.findById(2).get();
		
		//then
		assertThat(found.getIngredients()).isNotEmpty();
		assertThat(found.getIngredients().size()).isEqualTo(noOfIngredients);
	}
	
	@Test
	public void whenSearchExistingRecipe_thenReturnRecipes() {
		//given
		String searchString = "Omelet";
		
		//when
		List<Recipe> found = recipeRepository.findByNameIgnoreCaseContaining(searchString);
		
		//then
		assertThat(found).isNotEmpty();
	}
	
	@Test
	public void whenSearchNonExistingRecipe_thenReturnEmpty() {
		//given
		String searchString = "non";
		
		//when
		List<Recipe> found = recipeRepository.findByNameIgnoreCaseContaining(searchString);
		
		//then
		assertThat(found).isEmpty();
	}
}
