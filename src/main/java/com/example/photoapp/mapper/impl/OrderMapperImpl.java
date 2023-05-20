package com.example.photoapp.mapper.impl;

import com.example.photoapp.entity.Order;
import com.example.photoapp.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.NoSuchElementException;

@Component
public class OrderMapperImpl implements OrderMapper {
    @Value("#{${package.map}}")
    Map<String, Double> packagePriceMap;

    @Override
    public Order mapToOrder(String orderRequest)
            throws NoSuchElementException {
        Double price = packagePriceMap.get(orderRequest);
        if(price == null) {
            throw new NoSuchElementException("Package type not supported");
        }
        return Order.builder()
                .packageType(orderRequest)
                .price(price)
                .orderDateTime(LocalDateTime.now())
                .build();
    }
}
