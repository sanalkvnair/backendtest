package com.recipe.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.recipe.entities.IngredientList;
import com.recipe.entities.Recipe;
import com.recipe.repository.IngredientRepository;
import com.recipe.repository.RecipeRepository;
import com.recipe.service.impl.RecipeServiceImpl;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RecipeServiceTest {

	@TestConfiguration
	static class RecipeServiceTestContextConfiguration {
		@Bean
		public RecipeService recipeService() {
			return new RecipeServiceImpl();
		}
	}
	
	@Autowired
	RecipeService recipeService;
	
	@Autowired
	RecipeRepository recipeRepository;
	
	@Autowired
	IngredientRepository ingredientRepository;
	
	
	@Test
	public void givenRecipeId_whenGetRecipe_thenReturnRecipe() {
		//given
		Integer id = 2;
		
		//when
		Recipe found = recipeService.getRecipe(id, null);
		
		//then
		assertThat(found.getName()).isEqualTo("Mild Curry Omelet");
	}
	
	@Test
	public void givenRecipeIdPeople_whenGetRecipe_thenCalculateRecipeAmount() {
		//given
		Integer id = 3;
		Integer people = 4;
		
		//when
		Recipe found = recipeService.getRecipe(id, people);
		
		//then
		List<IngredientList> ingredienList = new ArrayList<>();
		IngredientList ingList = new IngredientList();
		ingList.setAmount(3);
		ingredienList.add(ingList);
		ingList = new IngredientList();
		ingList.setAmount(15);
		ingredienList.add(ingList);
		ingList = new IngredientList();
		ingList.setAmount(15);
		ingredienList.add(ingList);
		ingList = new IngredientList();
		ingList.setAmount(11);
		ingredienList.add(ingList);
		ingList = new IngredientList();
		ingList.setAmount(8);
		ingredienList.add(ingList);
		
		assertThat(found.getPeople()).isEqualTo(people);
		assertThat(found.getIngredients()).hasSize(ingredienList.size());
		assertThat(found.getIngredients()).extracting("amount").contains(3,15,15,11,8);
	}
}
