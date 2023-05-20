package com.example.photoapp.service;


import com.example.photoapp.entity.Order;
import com.example.photoapp.mapper.OrderMapper;
import com.example.photoapp.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private PromotionService promotionService;

    @Value("${tax.rate}")
    private double taxRate;

    public List<Order> createOrder(String orderRequest)
            throws NoSuchElementException {
        Order order = orderMapper.mapToOrder(orderRequest);
        List<Order> orders = promotionService.addPromotionPackages(order);
        return  orderRepository.saveAll(orders);
    }

    public double calculateRevenueByMonth(LocalDate startDate) {
        LocalDateTime startDateTime = LocalDateTime
                .of(startDate, LocalTime.MIN);
        LocalDateTime endDateTime = startDateTime.plusMonths(1);

        // Get the total revenue for the specified month and year
        return orderRepository
                .calculateRevenueByDateTimeRange(startDateTime, endDateTime)
                .orElse(0.0);
    }

    public double calculateTaxOwedByMonth(LocalDate startDate) {
        double revenue = calculateRevenueByMonth(startDate);
        return revenue * taxRate;
    }
}
