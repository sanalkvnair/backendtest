package com.recipe.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

@Entity
@Table(name = "ingredient_list")
public class IngredientList {

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonIgnore
	private Integer ingredientListId;
	@ManyToOne
	@JoinColumn(name = "recipe_id", referencedColumnName = "id")
	@JsonBackReference
	private Recipe recipe;
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "ingredient_id", referencedColumnName = "id")
	@JsonUnwrapped
	private Ingredient ingredient;
	@Column(name = "amount", nullable = false)
	private Integer amount;

	public Integer getIngredientListId() {
		return ingredientListId;
	}

	public void setIngredientListId(Integer ingredientListId) {
		this.ingredientListId = ingredientListId;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public Ingredient getIngredient() {
		return ingredient;
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

}
