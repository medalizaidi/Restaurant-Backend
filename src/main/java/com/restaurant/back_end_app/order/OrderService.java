package com.restaurant.back_end_app.order;

import com.restaurant.back_end_app.dto.OrderDTO;
import com.restaurant.back_end_app.food.Food;
import com.restaurant.back_end_app.food.FoodRepository;
import com.restaurant.back_end_app.repository.UserRepository;
import com.restaurant.back_end_app.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FoodRepository foodRepository;
    public Order createOrder(OrderDTO request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Order order = new Order();
        order.setClient(user);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(Status.PENDING);

        Order savedOrder = orderRepository.save(order);

        List<OrderItem> orderItems = request.getItems().stream().map(itemDTO -> {
            Food food = foodRepository.findById(itemDTO.getFoodId())
                    .orElseThrow(() -> new IllegalArgumentException("Food item not found"));

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(savedOrder);
            orderItem.setFood(food);
            orderItem.setQuantity(itemDTO.getQuantity());

            return orderItem;
        }).collect(Collectors.toList());

        orderItemRepository.saveAll(orderItems);

        return savedOrder;
    }
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order updateOrder(Long id, Order updatedOrder) {
        if (orderRepository.existsById(id)) {
            updatedOrder.setId(id);
            return orderRepository.save(updatedOrder);
        } else {
            return null; // or throw an exception
        }
    }

    public void deleteOrder(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
        } else {
            // Handle the case where the order doesn't exist
        }
    }
}