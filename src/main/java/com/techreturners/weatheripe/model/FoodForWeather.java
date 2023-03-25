package com.techreturners.weatheripe.model;

import com.techreturners.weatheripe.model.recipe.*;
import com.techreturners.weatheripe.model.weather.Weather;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FoodForWeather {

    @Id
    @GeneratedValue
    @Column(updatable = false, nullable = false)
    Long foodWeatherId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "weather_id", referencedColumnName = "id")
    Weather weatherId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diet_Type", referencedColumnName = "id")
    DietType dietType;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "health_Type", referencedColumnName = "id")
    HealthType healthType;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meal_Type", referencedColumnName = "id")
    MealType mealType;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dish_Type", referencedColumnName = "id")
    DishType dishType;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cuisine_Type", referencedColumnName = "id")
    CuisineType cuisineType;

}
