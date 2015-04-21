package com.kata.emsemantic.main;

import com.kata.emsemantic.main.service.QueryProcessor;
import com.kata.emsemantic.main.service.QueryProcessorImpl;
import org.apache.commons.lang.StringUtils;

import java.util.Scanner;

/**
 * Created by manavleslie on 21/04/15.
 */
public class InputProcessor {

    public static void main(String[] args) {

        QueryProcessor queryProcessor  = new QueryProcessorImpl();
        String userInput = "";
        while (!userInput.equalsIgnoreCase("exit")) {
            System.out.println("Enter the question (Enter 'exit' to Exit the program )   :: ");
            Scanner in = new Scanner(System.in);
            userInput = in.nextLine();
            String parsedFullName = queryProcessor.parseUserInput(userInput);
            if (StringUtils.isBlank(parsedFullName)) {
                System.out.println("Unable to parse the query");
            } else {
                String birthPlace = queryProcessor.queryDBPedia(parsedFullName);
                System.out.println(birthPlace);
            }

        }


    }
}
