package com.example.photoapp.service;

import com.example.photoapp.entity.Order;
import com.example.photoapp.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class PromotionService {
    @Autowired
    private OrderRepository orderRepository;

    @Value("#{${package.map}}")
    Map<String, Double> packagePriceMap;

    public List<Order> addPromotionPackages(Order order) {
        List<Order> orders = new ArrayList<>();
        orders.add(order);
        boolean isWinner = determineIfWonFreePrints();
        if(isWinner) {
         Set<String> packages = packagePriceMap.keySet();
            for (String packageName : packages) {
                if (packageName.equals(order.getPackageType())) continue;
                Order freeOrder = Order.builder()
                        .orderDateTime(LocalDateTime.now())
                        .price(0)
                        .packageType(packageName)
                        .gift(true)
                        .build();
                orders.add(freeOrder);
            }
        }
        return orders;
    }

    private boolean determineIfWonFreePrints() {
        // Check if enough time has passed since the last win
        LocalDateTime lastWinTime = orderRepository.findLastWinTime();
        LocalDateTime now = LocalDateTime.now();
        boolean enoughTimePassed = lastWinTime == null ||
                ChronoUnit.HOURS.between(lastWinTime, now) >= 1;

        // If enough time has passed, simulate a random chance of winning (1 in 3)
        boolean wonFreePrints = false;
        if (enoughTimePassed) {
            int randomNum = (int) (Math.random() * 3) + 1;
            wonFreePrints = randomNum == 1;
        }

        return wonFreePrints;
    }
}
