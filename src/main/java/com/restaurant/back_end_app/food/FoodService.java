package com.restaurant.back_end_app.food;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FoodService {

    private final FoodRepository foodRepository;

    public List<Food> getAllFoods() {
        return foodRepository.findAll();
    }

    public Optional<Food> getFoodById(Long id) {
        return foodRepository.findById(id);
    }

    public Food createFood(Food food) {
        return foodRepository.save(food);
    }

    public Food updateFood(Long id, Food foodDetails) {
        return foodRepository.findById(id)
                .map(food -> {
                    food.setName(foodDetails.getName());
                    food.setPicture(foodDetails.getPicture());
                    food.setPrice(foodDetails.getPrice());
                    food.setDescription(foodDetails.getDescription());
                    food.setCategory(foodDetails.getCategory());
                    return foodRepository.save(food);
                })
                .orElseThrow(() -> new RuntimeException("Food not found"));
    }

    public void deleteFood(Long id) {
        foodRepository.deleteById(id);
    }
}