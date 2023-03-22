package com.techreturners.weatheripe.model;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecipeBook {

    @Id
    @GeneratedValue
    @Column(updatable = false, nullable = false)
    Long recipeId;

    @Column
    String recipeURL;

    @Column
    String recipeName;

    @Column
    double calories;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diet_Type", referencedColumnName = "id")
    DietType dietType;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "health_Type", referencedColumnName = "id")
    HealthType healthType;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meal_Type", referencedColumnName = "id")
    MealType mealType;

    //    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "dish_Type", referencedColumnName = "id")
//    DishType dishType;
    @Column
    String dishType;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cuisine_Type", referencedColumnName = "id")
    CuisineType cuisineType;

    @Column
    double rating;

    @Column
    Boolean is_favourite;

    @Column
    LocalDateTime timestamp;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    UserAccount userId;


}
