package com.afshara;

import java.io.IOException;


public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //Input & Output for the implementation (Bridge Pattern)
        AbsOutput ioOutput = new IOOutput();
        AbsInput ioInput = new IOInput();
        Boolean choice = Boolean.TRUE;
        String surveyOrText = "None";
        ioOutput.getOutput("||======Welcome to the Creator's System========||");


        while (choice) {
            //The choices for Menu 1
            ioOutput.getOutput("||=============================================||");
            ioOutput.getOutput("Choose 1 or 2");
            ioOutput.getOutput("1) Survey");
            ioOutput.getOutput("2) Test");
            ioOutput.getOutput("3) Quit");
            ioOutput.getOutput("||=============================================||");

            //User response for Menu 1
            surveyOrText = ioInput.getInput();

            if (surveyOrText.equals("1")){
                SurveyMenu survey = new SurveyMenu();
                survey.createMenu();
            }
            else if (surveyOrText.equals("2")){
                TestMenu test = new TestMenu();
                test.createMenu();
            }
            else if(surveyOrText.equals("3")){
                ioOutput.getOutput("||=============Exiting the System==============||");
                System.exit(0);
            }
        }

    }
}
