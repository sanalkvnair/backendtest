package com.recipe.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="recipe")
public class Recipe {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "people", nullable = false)
	private Integer people;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "recipe_id")
	@JsonManagedReference
	private List<IngredientList> ingredients;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPeople() {
		return people;
	}

	public void setPeople(Integer people) {
		this.people = people;
	}

	public List<IngredientList> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<IngredientList> ingredients) {
		this.ingredients = ingredients;
	}

}
