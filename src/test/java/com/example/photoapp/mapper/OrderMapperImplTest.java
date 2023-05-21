package com.example.photoapp.mapper;

import com.example.photoapp.entity.Order;
import com.example.photoapp.mapper.impl.OrderMapperImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Map;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OrderMapperImplTest {
    private static final String WRONG_ORDER_REQUEST = "order request";

    private static final String ORDER_REQUEST = "strips";


    static OrderMapper orderMapper = new OrderMapperImpl();

    @BeforeAll
    public static void setUp() {
        ReflectionTestUtils.setField(orderMapper, "packagePriceMap",
                Map.of("prints", 5.0, "panoramas", 7.0, "strips", 5.0));
    }


    @Test
    public void shouldThrowExceptionOnWrongRequest() {
        Exception exception = assertThrows(NoSuchElementException.class,
                () -> orderMapper.mapToOrder(WRONG_ORDER_REQUEST));

        String expectedMessage = "Package type not supported";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void shouldBuildOrder() {
        Order result = orderMapper.mapToOrder(ORDER_REQUEST);

        assertEquals(ORDER_REQUEST, result.getPackageType());
        assertEquals(5.0, result.getPrice());
        assertFalse(result.isGift());
    }
}
