package com.example.photoapp.service;

import jakarta.validation.Valid;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class StringUtilService {
    public String reverseWordsOrder(@Valid String sentence) {
        String[] words = sentence.split(StringUtils.SPACE);
        ArrayUtils.reverse(words);
        return StringUtils.join(words, StringUtils.SPACE);
    }

    public String reverseString(String string) {
        return StringUtils.reverse(string);
    }
}
