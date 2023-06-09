package com.techreturners.weatheripe.repository;

import com.techreturners.weatheripe.model.recipe.RecipeBook;
import com.techreturners.weatheripe.model.user.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeBookRepository extends JpaRepository<RecipeBook, Long> {

    List<RecipeBook> findByUserId(UserAccount userId);

    List<RecipeBook> findAllByRecipeIdIn(List<Long> recipeIds);
}
