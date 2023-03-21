package com.techreturners.weatheripe.repository;

import com.techreturners.weatheripe.model.DishType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishTypeRepository extends JpaRepository<DishType, Long> {

//    List<DishType> findDishTypesByWeathers(long weatherId);
}
