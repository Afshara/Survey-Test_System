package com.afshara;

import java.io.File;
import java.util.ArrayList;

//Concrete Implementor from Abstract Menu class
public class TestMenu extends AbsMenu {
    //Input & Output for the implementation (Bridge Pattern)
    AbsInput ioInput = new IOInput();
    AbsOutput ioOutput = new IOOutput();

    protected static Save save = new Save();
    protected static Load load = new Load();

    //Menu 2
    String menu = String.join("\n"
            , "||=============================================||"
            ,"1) Create a new Test"
            , "2) Display a Test"
            , "3) Load a Test"
            , "4) Save a Test"
            , "5) Modify an Existing Test"
            , "6) Take a Test"
            , "7) Tabulate a Test"
            , "8) Grade a Test"
            , "9) Return"
            , "||=============================================||"
    );

    Boolean choice = Boolean.TRUE;
    //Current test of the system, which get updated and keeps track as user creates/loads new files
    Survey currentTest = new Test();
    Boolean loadModifier = Boolean.TRUE;
    String option;

    @Override
    public void createMenu() {


        while(choice){
            if (loadModifier == true){
                ioOutput.getOutput(menu);
                option = ioInput.getInput();
            }
            else{
                option = "3";
                //loadModifier = Boolean.TRUE;
            }

//            ioOutput.getOutput(menu);
//            String option = ioInput.getInput();

            //1) Create a new test
            if (option.equals("1")){
                ioOutput.getOutput("Name your Test");
                String testName = ioInput.checkEmpty();;

                //-------------------------------
                //https://stackoverflow.com/questions/5694385/getting-the-filenames-of-all-files-in-a-folder
                //Stores the names of all the files stored in the Test folder
                String PATH = "./SavedSurveyorTest/TEST";
                File folder = new File(PATH);
                File[] listOfFiles = folder.listFiles();
                ArrayList<String> testNameList = new ArrayList<String>();

                for (int i = 0; i < listOfFiles.length; i++) {
                    if (listOfFiles[i].isFile()) {
                        testNameList.add(listOfFiles[i].getName());
                    }
                }
                //Used this snipet of code from the link above
                //----------------------------

                Boolean ok = Boolean.FALSE;
                while (!ok){
                    if(testNameList.contains(testName)){
                        ioOutput.getOutput("The name is taken, give a different name to you Test.");
                        testName = ioInput.getInput();
                    } else{
                        ok = Boolean.TRUE;
                    }
                }

                currentTest = new Test();
                currentTest.setName(testName);
                currentTest.setQuestion();

                //To save the currentTest user has to type y/Y/yes/YES
                ioOutput.getOutput("Do you want to save your Test? (Type 'Y' for saving)");
                String response = ioInput.getInput();

                //Checks for y/Y/yes/YES
                if (response.toUpperCase().equals("Y") | response.toUpperCase().equals("YES")){
                    save.save(currentTest,"Test");}
            }


            //2) Display a Test
            if (option.equals("2")){
                try {
                    ioOutput.getOutput("Test Name: " + currentTest.getName());
                    currentTest.Display();
                }
                catch (NullPointerException ex) {
                    ioOutput.getOutput("Need to load or create the Test first");
                }
            }

            //3) Load a Test
            //WORK HERE! FIx: the situation with currentSurvey = new TEST thus not stoping at the flag for currentSURvey== null
            if (option.equals("3")){
                String SurveyOrTest = "TEST";

                //--------------------------------------------------------------
                //https://stackoverflow.com/questions/5694385/getting-the-filenames-of-all-files-in-a-folder
                // Stores and prints  the names of all the files stored in the SURVEY folder
                String PATH = "./SavedSurveyorTest/TEST";
                File folder = new File(PATH);
                File[] listOfFiles = folder.listFiles();
                ArrayList<String> surveyNameList = new ArrayList<String>();
                Integer listOfFilesLength = listOfFiles.length;

                if (listOfFilesLength > 0 ){
                    ioOutput.getOutput("The list of "+ SurveyOrTest.toLowerCase() +" to load from.");
                    for (int i = 0; i < listOfFiles.length; i++) {
                        if (listOfFiles[i].isFile()) {
                            surveyNameList.add(listOfFiles[i].getName());
                            ioOutput.getOutput(surveyNameList.get(i));
                        }
                    }
                    //Used this snipet of code from the link above
                    //----------------------------------------------------------------

                    //Asks for a survey name from the user, if no file is created already

                    if (listOfFilesLength.equals(0)){
                        ioOutput.getOutput("To load a "+ SurveyOrTest.toLowerCase() +", create a " + SurveyOrTest.toLowerCase() +" first.");
                    }
                    else if (currentTest==null & loadModifier){
                        ioOutput.getOutput("To load a "+ SurveyOrTest.toLowerCase() +", create a " + SurveyOrTest.toLowerCase() +" first.");
                    }
                    else{
                        ioOutput.getOutput("To load the "+ SurveyOrTest.toLowerCase() +", type the name of the Test as listed, that you want to load.");
                        String name = ioInput.getInput();
                        currentTest = load.Load(name, SurveyOrTest);
                        if(loadModifier == false){
                            ioOutput.getOutput(name + " has been loaded as your CurrentTest");
                            option = "5";
                            loadModifier = Boolean.TRUE;
                        }
                    }
                }
            }

            //4) Save a Test
            //Saves a test
            if (option.equals("4")){
                if (currentTest == null) {
                    ioOutput.getOutput("Create or load a Test to save");
                }
                else {
                    String nameS = "TEST";
                    ioOutput.getOutput("Saving the Test called " + currentTest.getName());
                    save.save(currentTest, nameS);
                }
            }

            //5) Modify an Existing Test
            if (option.equals("5")){
                if (currentTest != null){
                    //start from here, check if the user want to modify the current survey
                    //get rid of name of the survey functionaluty from next prompt
                    ioOutput.getOutput("Do you wish to modify the current Test?");
                    String userResponse = ioInput.checkEmpty();
                    if (userResponse.toUpperCase().equals("Y") | userResponse.toUpperCase().equals("YES")){
                        currentTest.Modify(currentTest);
                    }
                }
                while(true){
                    String modifySelection = String.join("\n"
                            , "||=============================================||"
                            , "You can either modify the CurrentTest or a Test that you load."
                            , "Type the number associated with you choice"
                            , "1) CurrentTest"
                            , "2) Load a new Test"
                            , "3) Return to Menu 2"
                            , "||=============================================||");

                    ioOutput.getOutput(modifySelection);
                    String modifyME = ioInput.checkEmpty();
                    //CurrentSurvey

                    if(modifyME.equals("1")){
                        if(currentTest==null){
                            ioOutput.getOutput("CurrentTest is empty. Please create or load a survey.");
                            //break;
                        }
                        else if (currentTest.questionArrayList.isEmpty()){
                            ioOutput.getOutput("Empty Test. Nothing to Print");
                            break;
                        }
                        else{
                            currentTest.Modify(currentTest);
                        }
                    }

                    //Load
                    else if (modifyME.equals("2")){
                        loadModifier = Boolean.FALSE;
                        break;
                    }
                    //Return

                    else if (modifyME.equals("3")){
                        break;
                    }
                    //Input again
                }
            }

            //6) Take a Test
            if (option.equals("6")){
                //=======================================

                String SurveyOrTest = "TEST";
                //--------------------------------------------------------------
                //https://stackoverflow.com/questions/5694385/getting-the-filenames-of-all-files-in-a-folder
                // Stores and prints  the names of all the files stored in the SURVEY folder
                String PATH = "./SavedSurveyorTest/TEST";
                File folder = new File(PATH);
                File[] listOfFiles = folder.listFiles();
                ArrayList<String> surveyNameList = new ArrayList<String>();
                Integer listOfFilesLength = listOfFiles.length;

                ioOutput.getOutput("Enter the name of the test you wish to take:");

                if (listOfFilesLength > 0 ){
                    //ioOutput.getOutput("The list of "+ SurveyOrTest.toLowerCase() +" to load from.");
                    for (int i = 0; i < listOfFiles.length; i++) {
                        if (listOfFiles[i].isFile()) {
                            surveyNameList.add(listOfFiles[i].getName());
                            ioOutput.getOutput(surveyNameList.get(i));
                        }
                    }
                    //Used this snipet of code from the link above
                    //----------------------------------------------------------------

                    //Asks for a survey name from the user, if no file is created already

                    if (listOfFilesLength.equals(0)){
                        ioOutput.getOutput("To load a "+ SurveyOrTest.toLowerCase() +", create a " + SurveyOrTest.toLowerCase() +" first.");
                    }
                    else{
                        String name = ioInput.getInput();
                        Survey takeSurvey = load.Load(name, SurveyOrTest);
                        if(!takeSurvey.equals(null)){
                            takeSurvey.Take(takeSurvey);
                            takeSurvey.Grade(takeSurvey);
                            save.save(takeSurvey,"TESTTAKEN");
                        }
                        else{
                            ioOutput.getOutput("Could not load the survey");
                        }
                    }
                }
            }

            //7) Tabulate a Test
            if (option.equals("7")){
                //==========================
                String SurveyOrTest = "TEST";
                //--------------------------------------------------------------
                //https://stackoverflow.com/questions/5694385/getting-the-filenames-of-all-files-in-a-folder
                // Stores and prints  the names of all the files stored in the SURVEY folder
                String PATH = "./SavedSurveyorTest/TEST";
                File folder = new File(PATH);
                File[] listOfFiles = folder.listFiles();
                ArrayList<String> surveyNameList = new ArrayList<String>();
                Integer listOfFilesLength = listOfFiles.length;

                ioOutput.getOutput("Enter the name of the test you wish to tabulate:");

                if (listOfFilesLength > 0 ){
                    //ioOutput.getOutput("The list of "+ SurveyOrTest.toLowerCase() +" to load from.");
                    for (int i = 0; i < listOfFiles.length; i++) {
                        if (listOfFiles[i].isFile()) {
                            surveyNameList.add(listOfFiles[i].getName());
                            ioOutput.getOutput(surveyNameList.get(i));
                        }
                    }
                    //Used this snipet of code from the link above
                    //----------------------------------------------------------------
                    //Asks for a survey name from the user, if no file is created already
                    if (listOfFilesLength.equals(0)){
                        ioOutput.getOutput("To load a "+ SurveyOrTest.toLowerCase() +", create a " + SurveyOrTest.toLowerCase() +" first.");
                    }
                    else{
                        String name = ioInput.getInput();
                        //choose the name from survey to tabulate but load the files from survey taken that is before first -
                        Survey tabulateSurvey = load.Load(name, SurveyOrTest);
                        if(!tabulateSurvey.equals(null)){
                            tabulateSurvey.Tabulate(tabulateSurvey, name);
                            //save.save(tabulateSurvey,"SURVEYTAKEN");
                        }
                        else{
                            ioOutput.getOutput("Could not load the test");
                        }
                    }
                }


                //==============================
            }

            //8) Grade a Test
            if (option.equals("8")){
                //--------------------------------------------------------------
                //https://stackoverflow.com/questions/5694385/getting-the-filenames-of-all-files-in-a-folder
                // Stores and prints  the names of all the files stored in the SURVEY folder
                String PATH = "./SavedSurveyorTest/TESTTAKEN";
                File folder = new File(PATH);
                File[] listOfFiles = folder.listFiles();
                ArrayList<String> surveyNameList = new ArrayList<String>();
                Integer listOfFilesLength = listOfFiles.length;

                ioOutput.getOutput("Enter the name of the test you wish to grade:");

                if (listOfFilesLength > 0 ){
                    for (int i = 0; i < listOfFiles.length; i++) {
                        if (listOfFiles[i].isFile()) {
                            surveyNameList.add(listOfFiles[i].getName());
                            ioOutput.getOutput(surveyNameList.get(i));
                        }
                    }
                    //Used this snipet of code from the link above
                    //----------------------------------------------------------------

                    //Asks for a survey name from the user, if no file is created already

                    if (listOfFilesLength.equals(0)){
                        ioOutput.getOutput("To grade you need to take a test first.");
                    }
                    else{
                        String name = ioInput.getInput();
                        //a condition that returns null from load and does not save the returned null
                        Survey gradeSurvey = load.Load(name, "TESTTAKEN");
                        gradeSurvey.Grade(gradeSurvey);
                        if(!gradeSurvey.equals(null)){
                            gradeSurvey.Grade(gradeSurvey);
                            save.save(gradeSurvey,"TESTTAKEN");
                        }
                        else{
                            ioOutput.getOutput("Could not load the survey");
                        }

                    }
                }

            }

            //9) Returns to Menu 1
            if (option.equals("9")){
                return;
            }

        }
    }
}
