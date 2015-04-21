package com.kata.emsemantic.main.service;

import com.hp.hpl.jena.query.QueryExecution;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Created by manavleslie on 21/04/15.
 */
public class QueryProcessorImplTest {

    QueryProcessor queryProcessor = new QueryProcessorImpl();

    @Mock
    QueryExecution queryExec;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

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
        String response = queryProcessor.queryDbpedia("David Cameron");
        assertEquals("London, England, UK", response);
    }

    @Test
    public void testQueryDBPediaForInvalidEntry() throws Exception {
        String response = queryProcessor.queryDbpedia("Rafter Simpleton");
        assertEquals(null, response);
    }

    @Test
    public void testProcessUserInputNameForHelp() throws Exception {
        String response = queryProcessor.processUserInputName(QueryProcessorImpl.CONSTANT_HELP);
        assertEquals(QueryProcessorImpl.CONSTANT_SAMPLE_QUESTION, response);
    }


    @Test
    public void testProcessUserInputNameForParseFailure() throws Exception {
        String response = queryProcessor.processUserInputName("What is this?");
        assertEquals(QueryProcessorImpl.CONSTANT_PARSE_FAILURE, response);
    }



    @Test
    public void testProcessUserInputNameForSuccess() throws Exception {
        String response = queryProcessor.processUserInputName("What is the birth place of David Cameron?");
        assertTrue(response.contains(QueryProcessorImpl.CONSTANT_BIRTH_PLACE_IS));
    }



    @Test
    public void testProcessUserInputNameForNoResults() throws Exception {
        String response = queryProcessor.processUserInputName("What is the birth place of DavidCameronII ?");
        assertTrue(response.contains(QueryProcessorImpl.CONSTANT_NOT_FOUND));
    }


}