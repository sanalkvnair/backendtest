package com.recipe.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.recipe.entities.Ingredient;

@RunWith(SpringRunner.class)
@DataJpaTest
public class IngredientRepositoryTest {

	@Autowired
	private IngredientRepository ingredientRepository;
	
	@Test
	public void whenListingAllIngredient_thenReturnNonEmptyIngredientList() {
		//when
		List<Ingredient> found = ingredientRepository.findAll();
		
		//then
		assertThat(found).isNotEmpty();
	}
	
	@Test
	public void whenSearchExistingIngredient_thenReturnNonEmptyIngredientList() {
		//given
		String searchQuery = "cheese";
		
		//when
		List<Ingredient> found = ingredientRepository.findByNameIgnoreCaseContaining(searchQuery);
		
		//then
		assertThat(found).isNotEmpty();
	}
	
	@Test
	public void whenSearchNonExistingIngredient_thenReturnEmptyIngredientList() {
		//given
		String searchQuery = "rice";
		
		//when
		List<Ingredient> found = ingredientRepository.findByNameIgnoreCaseContaining(searchQuery);
		
		//then
		assertThat(found).isEmpty();
	}
	
	@Test
	public void whenFindById_thenReturnIngredient() {
		//given
		int id = 5;
		
		//when
		Ingredient found = ingredientRepository.findById(id).get();
		
		//then
		assertThat(found.getName()).isEqualTo("basil");
	}
}
