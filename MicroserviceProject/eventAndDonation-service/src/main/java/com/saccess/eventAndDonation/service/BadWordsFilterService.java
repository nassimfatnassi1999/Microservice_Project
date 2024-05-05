package com.saccess.eventAndDonation.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class BadWordsFilterService {

    private static final List<String> BAD_WORDS = Arrays.asList("badword1", "badword2", "badword3");

    public boolean containsBadWords(String text) {
        String lowercaseText = text.toLowerCase();
        for (String word : BAD_WORDS) {
            if (lowercaseText.contains(word)) {
                return true;
            }
        }
        return false;
    }

    public String filterBadWords(String text) {
        String filteredText = text.toLowerCase();
        for (String word : BAD_WORDS) {
            filteredText = filteredText.replaceAll(word, "*".repeat(word.length()));
        }
        return filteredText;
    }

}
