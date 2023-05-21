package com.example.photoapp.service;

import com.example.photoapp.entity.Order;
import com.example.photoapp.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class PromotionServiceTest {
    private static final Order TEST_ORDER = Order.builder()
            .packageType("prints")
            .build();

    @Mock
    OrderRepository orderRepository;

    @InjectMocks
    static PromotionService promotionService;

    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(promotionService, "packagePriceMap",
                Map.of("prints", 5.0, "panoramas", 7.0, "strips", 5.0));
    }

    @Test
    public void shouldReturnOrderList() {
        List<Order> result = promotionService.addPromotionPackages(TEST_ORDER);

        assertTrue(result.contains(TEST_ORDER));
    }

    @Test
    public void havePossibilityToWin() {
        List<Order> result = new ArrayList<>();

        for (int i=0; i<10; i++) {
            result.addAll(promotionService.addPromotionPackages(TEST_ORDER));
        }

        assertTrue(result.stream().anyMatch(Order::isGift));
    }
}
