package com.kata.emsemantic.main.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by manavleslie on 21/04/15.
 */
public class QueryProcessorImpl implements  QueryProcessor {

    String PATTERN_QUERY = "(What is the birth place of)\\s*(.+?)\\s*[?]";

    @Override
    public String parseUserInput(String userInput) {
        Pattern pattern = Pattern.compile(PATTERN_QUERY);
        Matcher matcher = pattern.matcher(userInput);

        if (matcher.matches()) {
            return matcher.group(2);
        }
        return null;

    }
}
