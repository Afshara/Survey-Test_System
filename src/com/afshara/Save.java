package com.afshara;

import java.io.*;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Save implements Serializable {
    //Output for the implementation (Bridge Pattern)
    private  AbsOutput output = new IOOutput();
    private  AbsInput input = new IOInput();

    private String PATH = "./SavedSurveyorTest";
    public void save(Survey survey,String nameS) {
        //https://javatpoint.com/serialization-in-java?fbclid=IwAR0De6HtW3sF9kizuNxE-r7uJqb27Fgdj6f8plXl51ai-JRP1WrftFUXdgU
        //===========================================================================================
        if (nameS.toUpperCase().equals("SURVEY")){PATH = "./SavedSurveyorTest/SURVEY";}
        else if (nameS.toUpperCase().equals("TEST")){PATH = "./SavedSurveyorTest/TEST";}
        else if (nameS.toUpperCase().equals("TESTTAKEN")){PATH = "./SavedSurveyorTest/TESTTAKEN";}
        else {PATH = "./SavedSurveyorTest/SURVEYTAKEN";}

        Format formatter = new SimpleDateFormat("-yyyy-MM-dd-HH-mm-ss");
        Date today = Calendar.getInstance().getTime();
        String s = survey.getName()+formatter.format(today);
        String dir;
        if (nameS.toUpperCase().equals("TESTTAKEN")|nameS.toUpperCase().equals("SURVEYTAKEN")){
            dir = PATH + File.separator + s;
        }
        else{
            dir = PATH + File.separator + survey.getName();
        }

        try {
            FileOutputStream file = new FileOutputStream(dir);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(survey);
            out.flush();
            out.close();
            file.close();
            output.getOutput("Successfully saved");
        }

        catch(IOException ex) {
            output.getOutput("IOException. Not saved");
        }

        //===========================================================================================
    }

}
