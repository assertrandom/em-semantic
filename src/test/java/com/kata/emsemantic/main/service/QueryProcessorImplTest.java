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
        String parsedText = queryProcessor.parseUserInput("What is the birth place of David Cameron?");
        assertEquals("David Cameron", parsedText);

    }

    @Test
    public void testParseUserValidInputWithSpaces() throws Exception {
        String parsedText = queryProcessor.parseUserInput("What is the birth place of   Tony Blair  ?");
        assertEquals("Tony Blair", parsedText);

    }


    @Test
    public void testParseUserInvalidInput() throws Exception {
        String parsedText = queryProcessor.parseUserInput("What is the birth place David Cameron  ?");
        assertEquals(null, parsedText);

    }

    @Test
    public void testQueryDBPediaForValidEntry() throws Exception {
        String response = queryProcessor.queryDBPedia("David Cameron");
        assertEquals("London, England, U.K.", response);
    }

    @Test
    public void testQueryDBPediaForInvalidEntry() throws Exception {
        String response = queryProcessor.queryDBPedia("Rafter Simpleton");
        assertEquals(null, response);
    }
}