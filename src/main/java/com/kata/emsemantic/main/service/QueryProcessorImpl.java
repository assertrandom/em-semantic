package com.kata.emsemantic.main.service;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by manavleslie on 21/04/15.
 */
public class QueryProcessorImpl implements  QueryProcessor {

    String queryPattern;
    String dbpediaEndpoint;
    String birthplaceQuery;

    public QueryProcessorImpl() {
        try {
            PropertiesConfiguration config = new PropertiesConfiguration("application.properties");
            queryPattern = config.getString("query.pattern");
            dbpediaEndpoint = config.getString("dbpedia.endpoint");
            birthplaceQuery = config.getString("dbpedia.birthplace.query");
        } catch (ConfigurationException e) {
            System.out.println("Error loading properties");
        }

    }

    @Override
    public String parseUserInput(String userInput) {
        Pattern pattern = Pattern.compile(queryPattern);
        Matcher matcher = pattern.matcher(userInput);

        if (matcher.matches()) {
            return matcher.group(2);
        }
        return null;

    }
}
