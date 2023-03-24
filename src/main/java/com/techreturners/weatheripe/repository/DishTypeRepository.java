package com.techreturners.weatheripe.repository;

import com.techreturners.weatheripe.model.DishType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishTypeRepository extends JpaRepository<DishType, Long> {

    DishType findDishTypeById(long id);

    List<DishType> findAll();
}
