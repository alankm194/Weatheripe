package com.techreturners.weatheripe.dish;

import com.techreturners.weatheripe.model.recipe.DishType;
import com.techreturners.weatheripe.repository.DishTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class DishServiceImpl implements DishService {
    @Autowired
    DishTypeRepository dishTypeRepository;

    public List<DishType> getAllDishTypes(){
        return dishTypeRepository.findAll();
    }

}
