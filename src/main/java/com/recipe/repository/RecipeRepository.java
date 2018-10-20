package com.recipe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.recipe.entities.Recipe;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
	
	List<Recipe> findByNameIgnoreCaseContaining(String name);
}
