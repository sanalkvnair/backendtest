package com.recipe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recipe.entities.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {

	List<Ingredient> findByNameIgnoreCaseContaining(String name);
}
