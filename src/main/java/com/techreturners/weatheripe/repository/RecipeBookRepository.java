package com.techreturners.weatheripe.repository;

import com.techreturners.weatheripe.model.RecipeBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeBookRepository extends JpaRepository<RecipeBook, Long> {
}
