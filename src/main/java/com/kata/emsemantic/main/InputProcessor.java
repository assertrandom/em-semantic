package com.kata.emsemantic.main;

import com.kata.emsemantic.main.service.QueryProcessor;
import com.kata.emsemantic.main.service.QueryProcessorImpl;

import java.util.Scanner;

/**
 * Created by manavleslie on 21/04/15.
 */
public class InputProcessor {

    public static void main(String[] args) {

        QueryProcessor queryProcessor  = new QueryProcessorImpl();
        String inputString = "";
        while (!inputString.equalsIgnoreCase("exit")) {
            System.out.println("Enter the question (Enter 'exit' to Exit the program )   :: ");
            Scanner in = new Scanner(System.in);
            inputString = in.nextLine();
            String parsedFullName = queryProcessor.parseUserInput(inputString);

        }


    }
}
