package com.kata.emsemantic.main.service;

/**
 * Created by manavleslie on 21/04/15.
 */
public interface  QueryProcessor {

    String parseUserInput(String userInput);

    String processUserInputName(String userInput);

    String queryDbpedia(String parsedFullName);
}
