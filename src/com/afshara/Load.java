package com.afshara;

import java.io.*;

public class Load implements Serializable {
    //Output for the implementation (Bridge Pattern)
    private AbsOutput ioOutput = new IOOutput();
    private String PATH = "./SavedSurveyorTest";
    Survey survey = new Survey();

    //loadName is the name of the survey/test obj that the user wanted to load
    public Survey Load(String loadName, String SurveyOrTest) {
        //https://javatpoint.com/serialization-in-java?fbclid=IwAR0De6HtW3sF9kizuNxE-r7uJqb27Fgdj6f8plXl51ai-JRP1WrftFUXdgU
        //===========================================================================================
        if (SurveyOrTest.toUpperCase().equals("SURVEY")){PATH = "./SavedSurveyorTest/SURVEY";}
        else if(SurveyOrTest.toUpperCase().equals("TEST")){PATH = "./SavedSurveyorTest/TEST";}
        else if(SurveyOrTest.toUpperCase().equals("TESTTAKEN")){PATH = "./SavedSurveyorTest/TESTTAKEN";}
        else if(SurveyOrTest.toUpperCase().equals("SURVEYTAKEN")){PATH = "./SavedSurveyorTest/SURVEYTAKEN";}
        String folderSave = PATH + File.separator + loadName ;

        try {
            FileInputStream file = new FileInputStream(folderSave);
            ObjectInputStream in = new ObjectInputStream(file);
            survey = (Survey)in.readObject();
            in.close();
            file.close();
            ioOutput.getOutput("Loading done for "+ SurveyOrTest + " : " + loadName );
            ioOutput.getOutput("Current"+ SurveyOrTest + " : " + loadName );
        }
        catch (ClassNotFoundException | IOException ex) {
            ioOutput.getOutput("OPPs I'm worried the "+ SurveyOrTest +" name you wanted to load does NOT exist!");
            ioOutput.getOutput("IOException/Object/Class not found exception");
        }
        //===========================================================================================
        return survey;
    }


}
