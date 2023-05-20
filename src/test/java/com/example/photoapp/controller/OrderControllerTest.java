package com.example.photoapp.controller;

import com.example.photoapp.entity.Order;
import com.example.photoapp.service.OrderService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    private static final String TEST_PACKAGE = "strips";

    private static final Double TEST_DOUBLE = 5.0;

    private static final LocalDate TEST_DATE = LocalDate.now();

    private static final String EXCEPTION_MESSAGE = "Exception!!!";

    private static Order testOrder;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @BeforeAll
    static void setUp() {
        testOrder = new Order();
        testOrder.setPackageType(TEST_PACKAGE);
    }

    @Test
    public void shouldCreateOrder() throws Exception {
        when(orderService.createOrder(TEST_PACKAGE))
                .thenReturn(List.of(testOrder));
        String urlTemplate = "/orders/" + TEST_PACKAGE;

        this.mockMvc.perform(post(urlTemplate)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(content()
                                .string(containsString(TEST_PACKAGE)));
    }

    @Test
    public void shouldReturnBadRequestOnException() throws Exception {
        when(orderService.createOrder(TEST_PACKAGE))
                .thenThrow(new NoSuchElementException(EXCEPTION_MESSAGE));
        String urlTemplate = "/orders/" + TEST_PACKAGE;

        this.mockMvc.perform(post(urlTemplate)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content()
                        .string(containsString(EXCEPTION_MESSAGE)));
    }

    @Test
    public void shouldCalculateRevenueByMonth() throws Exception {
        when(orderService.calculateRevenueByMonth(TEST_DATE))
                .thenReturn(TEST_DOUBLE);
        String urlTemplate = "/orders/revenue";

        this.mockMvc.perform(get(urlTemplate)
                        .param("startDate", String.valueOf(TEST_DATE)))
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(containsString(String.valueOf(TEST_DOUBLE))));
    }

    @Test
    public void shouldCalculateTaxOwedByMonth() throws Exception {
        when(orderService.calculateTaxOwedByMonth(TEST_DATE))
                .thenReturn(TEST_DOUBLE);
        String urlTemplate = "/orders/tax";

        this.mockMvc.perform(get(urlTemplate)
                        .param("startDate", String.valueOf(TEST_DATE)))
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(containsString(String.valueOf(TEST_DOUBLE))));
    }
}
