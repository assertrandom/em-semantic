package com.kata.emsemantic.main;

import com.kata.emsemantic.main.service.QueryProcessorImpl;

import java.util.Scanner;

/**
 * Created by manavleslie on 21/04/15.
 */
public class InputProcessor {

    QueryProcessorImpl queryProcessor  = new QueryProcessorImpl();

    public static void main(String[] args) {

        InputProcessor inputProcessor = new InputProcessor();
        inputProcessor.runInteractiveTerminal();
    }


    private void runInteractiveTerminal() {
        System.out.println("Program to fetch the birth place of famous people.");
        System.out.println("Enter 'help' to show sample queries:");


        String userInput = fetchUserInput();

        while (!userInput.equalsIgnoreCase("exit")) {
            System.out.println(queryProcessor.processUserInputName(userInput));
            userInput = fetchUserInput();

        }
    }



    private String fetchUserInput() {
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("Enter the question (Enter 'exit' to Exit the program )   :: ");
        return  inputScanner.nextLine();
    }
}
