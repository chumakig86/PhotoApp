package com.example.photoapp.controller;

import com.example.photoapp.config.TestConfig;
import com.example.photoapp.service.StringUtilService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StringUtilController.class)
@Import(TestConfig.class)
public class StringUtilControllerTest {
    public static final String INPUT = "Hello world";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StringUtilService stringUtilService;

    @Test
    public void reverseWordsInSentance() throws Exception {
        String expectedOutput = "world Hello";
        String urlTemplate = "/stringUtil/reverseWordsOrder";

        this.mockMvc.perform(post(urlTemplate)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(INPUT))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(expectedOutput)));
    }

    @Test
    public void reverseString() throws Exception {
        String expectedOutput = "dlrow olleH";
        String urlTemplate = "/stringUtil/reverseString";

        this.mockMvc.perform(post(urlTemplate)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(INPUT))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(expectedOutput)));
    }
}
