package com.example.photoapp.controller;

import com.example.photoapp.service.StringUtilService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "StringUtil controller", description = "String manipulating API")
@RestController
@RequestMapping("/stringUtil")
public class StringUtilController {
    public static final String REVERSE_WORDS = "Reverse the order of the words";

    private static final String REVERSE_STRING = "Reverses string";

    @Autowired
    private StringUtilService stringUtilService;

    @Operation(summary = REVERSE_WORDS, description = REVERSE_WORDS)
    @PostMapping("/reverseWordsOrder")
    public ResponseEntity reverseWordsOrder(@RequestBody String sentence) {
        return new ResponseEntity(stringUtilService
                .reverseWordsOrder(sentence), HttpStatus.OK);
    }

    @Operation(summary = REVERSE_STRING, description = REVERSE_STRING)
    @PostMapping("/reverseString")
    public ResponseEntity reverseString(@RequestBody String string) {
        return new ResponseEntity(stringUtilService
                .reverseString(string), HttpStatus.OK);
    }
}
