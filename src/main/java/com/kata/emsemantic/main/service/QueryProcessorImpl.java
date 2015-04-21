package com.kata.emsemantic.main.service;

import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.RDFNode;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by manavleslie on 21/04/15.
 */
public class QueryProcessorImpl implements QueryProcessor {

    public static final String CONSTANT_HELP = "help";
    public static final String CONSTANT_SAMPLE_QUESTION = "What is the birth place of Tony Blair?";
    public static final String CONSTANT_PARSE_FAILURE = "Unable to parse the query";
    public static final String CONSTANT_BIRTH_PLACE_IS = "Birth Place is : ";
    public static final String CONSTANT_NOT_FOUND = "No entry found for ";

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

    @Override
    public String processUserInputName(String userInput) {
        if (userInput.equalsIgnoreCase(CONSTANT_HELP)) {
            return CONSTANT_SAMPLE_QUESTION;
        } else  if (StringUtils.isNotBlank(userInput)){
            String parsedFullName = parseUserInput(userInput);
            if (StringUtils.isBlank(parsedFullName)) {
                return CONSTANT_PARSE_FAILURE;
            } else {
                String birthPlace = queryDbpedia(parsedFullName);
                if (StringUtils.isNotEmpty(birthPlace))
                    return CONSTANT_BIRTH_PLACE_IS +birthPlace;
                else
                    return CONSTANT_NOT_FOUND +parsedFullName;
            }
        }
        return "";
    }

    @Override
    public String queryDbpedia(String parsedFullName) {

        String parsedQuery = birthplaceQuery.replace("userInput", parsedFullName);
        QueryExecution queryExec = QueryExecutionFactory.sparqlService(dbpediaEndpoint, parsedQuery);
        try {
            ResultSet resultSet = queryExec.execSelect();

            if (resultSet.hasNext()) {
                RDFNode placeNode = resultSet.next().get("place");
                if (placeNode.isLiteral())
                    return placeNode.asLiteral().getString();
                else if (placeNode.isResource())
                    return placeNode.asResource().getLocalName();
            }

        } catch (Exception e) {
            System.out.println("Error Fetching results " + e.getMessage());
        } finally {
            queryExec.close();
        }
        return null;
    }
}
