package com.example.photoapp.service;

import com.example.photoapp.mapper.OrderMapper;
import com.example.photoapp.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
    public static final double TAX_RATE = 0.08625;

    private static final String ORDER_REQUEST = "strips";

    @Mock
    OrderMapper orderMapper;

    @Mock
    OrderRepository orderRepository;

    @Mock
    PromotionService promotionService;

    @InjectMocks
    OrderService orderService;

    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(orderService, "taxRate", TAX_RATE);
    }

    @Test
    public void shouldCalculateTax() {
        double testRevenue = 7.0;
        when(orderRepository.calculateRevenueByDateTimeRange(any(), any()))
                .thenReturn(Optional.of(testRevenue));
        Double result = orderService.calculateTaxOwedByMonth(LocalDate.now());

        assertEquals(testRevenue * TAX_RATE, result);
    }

    @Test
    public void shouldCreateOneOrder() {
        orderService.createOrder(ORDER_REQUEST);

        verify(orderMapper, times(1)).mapToOrder(ORDER_REQUEST);
        verify(promotionService, times(1)).addPromotionPackages(any());
        verify(orderRepository, times(1)).saveAll(anyList());
    }
}
