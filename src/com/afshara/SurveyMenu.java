package com.afshara;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

//Concrete Implementor from Abstract Menu class
public class SurveyMenu extends AbsMenu {

    //Input & Output for the implementation (Bridge Pattern)
    AbsInput ioInput = new IOInput();
    AbsOutput ioOutput = new IOOutput();

    protected Save save = new Save();
    protected Load load = new Load();

    //Menu 2
    String menu = String.join("\n"
            , "||=============================================||"
            ,"1) Create a new Survey"
            , "2) Display a Survey"
            , "3) Load a Survey"
            , "4) Save a Survey"
            , "5) Modify an Existing Survey"
            , "6) Take a Survey"
            , "7) Tabulate a Survey"
            , "8) Return"
            , "||=============================================||"
    );

    Boolean choice = Boolean.TRUE;
    //Current survey of the system, which get updated and keeps track as user creates/loads new files
    Survey currentSurvey = null;
    Boolean loadModifier = Boolean.TRUE;
    String option;
    @Override
    public void createMenu() throws IOException, ClassNotFoundException {

        while(choice){

            if (loadModifier == true){
                ioOutput.getOutput(menu);
                option = ioInput.getInput();}
            else{
                option = "3";
                //loadModifier = Boolean.TRUE;
            }


            //1) Create a new Survey
            if (option.equals("1")){
                ioOutput.getOutput("Name your Survey");
                String surveyName = ioInput.checkEmpty();

                //-------------------------------
                //https://stackoverflow.com/questions/5694385/getting-the-filenames-of-all-files-in-a-folder
                //Stores the names of all the files stored in the SURVEY folder
                String PATH = "./SavedSurveyorTest/SURVEY";
                File folder = new File(PATH);
                File[] listOfFiles = folder.listFiles();
                ArrayList<String> surveyNameList = new ArrayList<String>();

                for (int i = 0; i < listOfFiles.length; i++) {
                    if (listOfFiles[i].isFile()) {
                        surveyNameList.add(listOfFiles[i].getName());
                    }
                }
                //Used this snipet of code from the link above
                //----------------------------

                Boolean ok = Boolean.FALSE;
                while (!ok){
                    if(surveyNameList.contains(surveyName)){
                        ioOutput.getOutput("The name is taken, give a different name to you Survey.");
                        surveyName = ioInput.checkEmpty();
                    } else{
                        ok = Boolean.TRUE;
                    }
                }

                currentSurvey = new Survey();
                currentSurvey.setName(surveyName);
                currentSurvey.setQuestion();

                //To save the currentSurvey user has to type y/Y/yes/YES
                ioOutput.getOutput("Do you want to save your Survey/Test? (Type 'Y' for saving)");
                String response = ioInput.getInput();

                //Checks for y/Y/yes/YES
                if (response.toUpperCase().equals("Y") | response.toUpperCase().equals("YES")){
                    save.save(currentSurvey,"Survey");}

            }


            //2) Display a Survey
            if (option.equals("2")){
                //Checks if there is a currentsurvey to display
                try {
                    ioOutput.getOutput(" Survey Name: " + currentSurvey.getName());
                    currentSurvey.Display();
                }
                catch (NullPointerException ex) {
                    ioOutput.getOutput("Need to load or create the Survey first");
                }
            }

            //3) Load a Survey
            if (option.equals("3")){
                String SurveyOrTest = "SURVEY";

                //--------------------------------------------------------------
                //https://stackoverflow.com/questions/5694385/getting-the-filenames-of-all-files-in-a-folder
                // Stores and prints  the names of all the files stored in the SURVEY folder
                String PATH = "./SavedSurveyorTest/SURVEY";
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
//                else if (currentSurvey==null){
//                    ioOutput.getOutput("To load a "+ SurveyOrTest.toLowerCase() +", create a " + SurveyOrTest.toLowerCase() +" first.");
//                }
                    else{
                        ioOutput.getOutput("To load the "+ SurveyOrTest.toLowerCase() +", type the name of the survey as listed, that you want to load.");
                        String name = ioInput.getInput();
                        currentSurvey = load.Load(name, SurveyOrTest);
                        if(loadModifier == false){
                            ioOutput.getOutput(name + " has been loaded as your CurrentSurvey");
                            option = "5";
                            loadModifier = Boolean.TRUE;
                        }
                    }
                }}

            //4) Save a Survey
            //Saves a survey
            if (option.equals("4")){
                if (currentSurvey == null) {
                    ioOutput.getOutput("Create or load a Survey/Test to save");
                }
                else {
                    String nameS = "SURVEY";
                    //ioOutput.getOutput("Saving the survey/Test called " + currentSurvey.getName());
                    save.save(currentSurvey,nameS);
                }
            }

            //5) Modify an Existing Survey
            if (option.equals("5")){
                if (currentSurvey != null){
                    //start from here, check if the user want to modify the current survey
                    //get rid of name of the survey functionaluty from next prompt
                    ioOutput.getOutput("Do you wish to modify the current survey?");
                    String userResponse = ioInput.checkEmpty();
                    if (userResponse.toUpperCase().equals("Y") | userResponse.toUpperCase().equals("YES")){
                        currentSurvey.Modify(currentSurvey);
                    }
                }
                while(true){
                    String modifySelection = String.join("\n"
                            , "||=============================================||"
                            , "You can either modify the CurrentSurvey or a survey that you load."
                            , "Type the number associated with you choice"
                            , "1) CurrentSurvey"
                            , "2) Load a new survey"
                            , "3) Return to Menu 2"
                            , "||=============================================||");

                    ioOutput.getOutput(modifySelection);
                    String modifyME = ioInput.checkEmpty();
                    //CurrentSurvey

                    if(modifyME.equals("1")){
                        if(currentSurvey==null){
                            ioOutput.getOutput("CurrentSurvey is empty. Please create or load a survey.");
                            //break;
                        }
                        else if (currentSurvey.questionArrayList.isEmpty()){
                            ioOutput.getOutput("Empty Survey. Nothing to Print");
                            break;
                        }
                        else{
                            currentSurvey.Modify(currentSurvey);
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

            //6) Take a Survey
            if (option.equals("6")){
                //=======================================

                String SurveyOrTest = "SURVEY";
                //--------------------------------------------------------------
                //https://stackoverflow.com/questions/5694385/getting-the-filenames-of-all-files-in-a-folder
                // Stores and prints  the names of all the files stored in the SURVEY folder
                String PATH = "./SavedSurveyorTest/SURVEY";
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
                            save.save(takeSurvey,"SURVEYTAKEN");
                        }
                        else{
                            ioOutput.getOutput("Could not load the survey");
                        }
                    }
                }
                //=======================================
            }

            //7) Tabulate a Survey
            if (option.equals("7")){
                //=======================================
                String SurveyOrTest = "SURVEY";
                //--------------------------------------------------------------
                //https://stackoverflow.com/questions/5694385/getting-the-filenames-of-all-files-in-a-folder
                // Stores and prints  the names of all the files stored in the SURVEY folder
                String PATH = "./SavedSurveyorTest/SURVEY";
                File folder = new File(PATH);
                File[] listOfFiles = folder.listFiles();
                ArrayList<String> surveyNameList = new ArrayList<String>();
                Integer listOfFilesLength = listOfFiles.length;

                ioOutput.getOutput("Enter the name of the survey you wish to tabulate:");

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
                            ioOutput.getOutput("Could not load the survey");
                        }
                    }
                }
            }

            //8) Returns to Menu 1
            if (option.equals("8")){
                return;
            }

        }
    }
}
