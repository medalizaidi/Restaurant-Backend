package com.restaurant.back_end_app.food;

import com.restaurant.back_end_app.order.OrderItemRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FoodService {

    private final FoodRepository foodRepository;
    private final OrderItemRepository orderItemRepository;

    public List<Food> getAllFoods() {
        return foodRepository.findAll();
    }

    public Optional<Food> getFoodById(Long id) {
        return foodRepository.findById(id);
    }

    public void createFood(String name, String description, Double price, MultipartFile picture) throws IOException {
        Food food = new Food();
        food.setName(name);
        food.setDescription(description);
        food.setPrice(price);

        // Convert the MultipartFile to a byte array and set it in the entity
        food.setPicture(picture.getBytes());

        // Save the food entity
        foodRepository.save(food);
    }

    public void updateFoodDetails(Long id, String name, String description, Double price) {
        Food food = foodRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Food not found"));

        food.setName(name);
        food.setDescription(description);
        food.setPrice(price);

        foodRepository.save(food);
    }

    public void updateFoodImage(Long id, MultipartFile picture) throws IOException {
        Food food = foodRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Food not found"));

        // Convert the MultipartFile to a byte array and set it in the entity
        food.setPicture(picture.getBytes());

        foodRepository.save(food);
    }
    public void deleteFood(Long id) {
        orderItemRepository.deleteByFoodId(id);
        foodRepository.deleteById(id);
    }
}