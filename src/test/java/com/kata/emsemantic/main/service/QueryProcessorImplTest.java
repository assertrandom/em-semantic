package com.kata.emsemantic.main.service;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by manavleslie on 21/04/15.
 */
public class QueryProcessorImplTest {

    QueryProcessor queryProcessor = new QueryProcessorImpl();

    @Test
    public void testParseUserValidInput() throws Exception {
        String parsedText = queryProcessor.parseUserInput("What is the birth place of David Cameron ?");
        assertEquals("David Cameron", parsedText);

    }
}