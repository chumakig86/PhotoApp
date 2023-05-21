package com.example.photoapp.controller;

import com.example.photoapp.entity.Order;
import com.example.photoapp.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Tag(name = "Order controller", description = "Order manipulating API")
@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Operation(summary = "create order",
            description = "if win returns 3 orders (2 as gift) otherwise 1")
    @PostMapping("/{order}")
    public ResponseEntity createOrder(@PathVariable String order) {
        List<Order> createdOrder;
        try {
            createdOrder = orderService.createOrder(order);
        }
        catch (NoSuchElementException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
        return ResponseEntity.ok(createdOrder);
    }

    @Operation(summary = "calculate revenue by month",
            description = "calculate revenue tax by LocalDate of month. " +
                    "Example input = 2023-05-21")
    @GetMapping("/revenue")
    public ResponseEntity<Double> calculateRevenueByMonth(
            @RequestParam LocalDate startDate) {
        double revenue = orderService.calculateRevenueByMonth(startDate);
        return ResponseEntity.ok(revenue);
    }

    @Operation(summary = "calculate revenue tax by month",
            description = "calculate revenue by LocalDate of month. " +
                    "Example input = 2023-05-21")
    @GetMapping("/tax")
    public ResponseEntity<Double> calculateTaxOwedByMonth(
            @RequestParam LocalDate startDate) {
        double taxOwed = orderService.calculateTaxOwedByMonth(startDate);
        return ResponseEntity.ok(taxOwed);
    }
}
