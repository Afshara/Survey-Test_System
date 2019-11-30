package com.afshara;

import java.util.ArrayList;

public class Ranking extends Matching {
    ArrayList<String> correctColumn = new ArrayList<String>();
    char[] alphabet = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    ArrayList<String> letters = new ArrayList<String>();

    String questionType = "Ranking";
    Integer numberOfChoice;
    public Integer numberOfCChoice;

    @Override
    public int getNumberOfCChoices(){
        return 1;
    }
    public ArrayList<String> getLetters(){return this.letters;}


    @Override
    public ArrayList<String> getChoices(){return this.letters;}

    @Override
    public ArrayList<String> getqColumn() {
        return this.qColumn;
    }

    @Override
    public ArrayList<String> getaColumn() {
        return this.aColumn;
    }

    @Override
    public String getquestionType() {
        return this.questionType;
    }

    @Override
    public Integer getnumberOfChoice() {
        return this.numberOfChoice;
    }

    @Override
    public void insertQuestion(String SurveyOrTest) {
        ioOutput.getOutput("Enter the prompt for your " + questionType + " Question: ");
        prompt = ioInput.checkEmpty();
        ioOutput.getOutput("Enter the number of choices for your " + questionType + " Question: ");
        boolean ok = Boolean.FALSE;

        //Check if the number of choices is int and more than 2
        while(!ok){
            try {
                numberOfChoice = Integer.parseInt(ioInput.getInput());
                if (numberOfChoice >=2){
                    ok = true;
                }
                if (numberOfChoice < 2){
                    ioOutput.getOutput("Enter 2 or more number of choices.");
                }
            } catch (Exception e) {
                ioOutput.getOutput("Enter the number of choices for your " + questionType + " Question: ");
            }
        }
        int count;
        for (int i = 0 ; i < numberOfChoice; i++){
            count = i+1;
            //ioOutput.getOutput("Enter question for question column at line " + count);
            qColumn.add(String.valueOf(count));
        }


        for (int i = 0; i < numberOfChoice; i++){
            ioOutput.getOutput("Enter the choices " + alphabet[i]);
            letters.add(String.valueOf(alphabet[i]));
            aColumn.add(ioInput.checkEmpty());
        }
        numberOfCChoice = qColumn.size();

        String numberC = null;
        if (SurveyOrTest == "Test") {
            for (int i = 0; i < qColumn.size(); i ++) {
                ioOutput.getOutput("Rank the correct entry for " + qColumn.get(i));
                for(int j = 0; j < aColumn.size(); j++){
                    ioOutput.getOutput(letters.get(j) + " "+ aColumn.get(j));
                }
                ok = Boolean.FALSE;
                while(!ok){
                    try {
                        numberC = ioInput.checkEmpty();
                        if (letters.contains(numberC)){
                            if(correctColumn.contains(numberC )){
                                ioOutput.getOutput("The answer is being duplicated");
                            }
                            else{
                                ok = true;
                            }
                        }
                        else{
                            ioOutput.getOutput("Enter a letter");
                        }
                    } catch (Exception e) {
                        ioOutput.getOutput("Enter a letter for " + questionType + " Question: ");
                    }
                }
                correctColumn.add(numberC);
                numberOfCChoice = correctColumn.size();
            }
        }
        correctAnswers.add(correctColumn);
        //End of TEST
    }
    @Override
    public void outputQuestions() {
        ioOutput.getOutput("Question: " + prompt);
        ioOutput.getOutput("Choices");
        for (int i =0; i < aColumn.size(); i++){
            ioOutput.getOutput( letters.get(i) + ") " + aColumn.get(i));
        }
        if (correctAnswers.size() != 0) {
            ioOutput.getOutput("The correct answers are ");
            for (int i=0; i<correctColumn.size();i++) {
                ioOutput.getOutput(qColumn.get(i) + " - " + correctColumn.get(i));
            }
        }
    }
    @Override
    public void outputQOnly() {
        ioOutput.getOutput("Question: " + prompt);
        ioOutput.getOutput("Choices");
        for (int i =0; i < aColumn.size(); i++){
            ioOutput.getOutput( letters.get(i) + ") " + aColumn.get(i));
        }
    }


    @Override
    public void modify(Question question, String nameS) {
        String userResponse;
        Boolean testMatch = Boolean.TRUE;
        while(true){
            ioOutput.getOutput("The current prompt: " + question.prompt);
            ioOutput.getOutput("Do you wish to modify the prompt? Type 'Y'/'YES' for yes.");
            userResponse = ioInput.checkEmpty();
            if(userResponse.toUpperCase().equals("YES")|(userResponse.toUpperCase().equals("Y"))){
                ioOutput.getOutput("Enter a new prompt:");
                question.prompt = ioInput.checkEmpty();
                break;
            }
            else if(userResponse.toUpperCase().equals("NO")|(userResponse.toUpperCase().equals("N"))){
                break;
            }
            else{
                ioOutput.getOutput("Please type 'Y'/'Yes' for yes or 'N'/'No' for no!");
            }
        }

        //===============================================================
        //choices
        //qColumn aColumn correctColumn =
        while(true){
            ioOutput.getOutput("Choices");
            ioOutput.getOutputA(aColumn);
            ioOutput.getOutput("Do you wish to modify choices? Type 'Y'/'YES' for yes.");
            userResponse = ioInput.checkEmpty();


            if(userResponse.toUpperCase().equals("YES")|(userResponse.toUpperCase().equals("Y"))){
                String numberC;
                while(true){
                    ioOutput.getOutput("To modify an existing option from qcolumn/acolumn type 'M'");
                    ioOutput.getOutput("To add a choice, type add. To remove a choice, type remove.");
                    ioOutput.getOutput("When done modifying, type 'done'");

                    ioOutput.getOutput("Choices");
                    ioOutput.getOutputA(aColumn);

                    try {
                        String cc = ioInput.checkEmpty();

                        if (cc.toUpperCase().equals("ADD")){
                            ioOutput.getOutput("Type the choice you want to add");
                            aColumn.add(ioInput.checkEmpty());
                            letters.add(String.valueOf(alphabet[(aColumn.size()-1)]));
                            testMatch = Boolean.FALSE;

                        }

                        else if (cc.toUpperCase().equals("DONE")){
                            break;
                        }

                        else if(cc.toUpperCase().equals("REMOVE")){
                            String removeChoice;
                            ioOutput.getOutput("Type the choice you want to remove");
                            removeChoice = ioInput.checkEmpty().toUpperCase();
                            if (letters.indexOf(removeChoice) < aColumn.size()){
                                aColumn.remove(letters.indexOf(removeChoice));
                                testMatch = Boolean.FALSE;
                            }
                            else{
                                ioOutput.getOutput("Index not in range");
                            }
                        }
                        //Modify
                        else if (cc.toUpperCase().equals("M")){
                            ioOutput.getOutput("Answer column");
                            ioOutput.getOutputA(aColumn);
                            ioOutput.getOutput("Type the option you want to modify");
                            numberC  = ioInput.checkEmpty();
                            if ((letters.indexOf(numberC) < aColumn.size())) {
                                ioOutput.getOutput("Modify the choice with: ");
                                aColumn.set((letters.indexOf(numberC)),ioInput.checkEmpty());
                                break;
                            } else {
                                ioOutput.getOutput("The answer is not in range. Enter again");
                            }
                        }
                    } catch (Exception e) {
                        ioOutput.getOutput("Enter a integer type for " + questionType + " Question: ");
                    }
                }
            }
            else if(userResponse.toUpperCase().equals("NO")|(userResponse.toUpperCase().equals("N"))){
                break;
            }
            else{
                ioOutput.getOutput("Please type 'Y'/'Yes' for yes or 'N'/'No' for no!");
            }
        }

        //testMatch = Boolean.FALSE;
        //---------------------------------------------------------------------------------------------

        if(nameS.equals("TEST")){

            while(true){
                if (testMatch == false){
                    ioOutput.getOutput("Since you have added new options in choices, please complete the answer for the matching question again");
                    userResponse = "YES";
                }
                else {
                    ioOutput.getOutput("Do you wish to modify correct answer? Type 'Y'/'YES' for yes.");
                    userResponse = ioInput.checkEmpty();
                }
                if(userResponse.toUpperCase().equals("YES")|(userResponse.toUpperCase().equals("Y"))){
                    String numberC = null;
                    ArrayList clonedCorrectAnswers = new ArrayList();
                    clonedCorrectAnswers = correctAnswers;
                    correctAnswers.clear();
                    correctColumn.clear();
                    //The correctAnswers column
                    if (clonedCorrectAnswers.size() != 0) {
                        ioOutput.getOutput("The current correct answers are ");
                        for (int i=0; i<correctColumn.size();i++) {
                            ioOutput.getOutput(qColumn.get(i) + " - " + aColumn.get(letters.indexOf(correctColumn.get(i))));
                        }
                    }

                    for (int i = 0; i < aColumn.size(); i ++) {
                        ioOutput.getOutput("Enter the correct entry for " + qColumn.get(i));
                        for(int j = 0; j < aColumn.size(); j++){
                            ioOutput.getOutput("Type the number which correct answer to this matching question #"+ letters.get(j)+ ") "+ aColumn.get(j));
                        }

                        Boolean ok = Boolean.FALSE;
                        while(!ok){
                            try {
                                numberC = ioInput.checkEmpty();
                                if ((letters.indexOf(numberC)< aColumn.size())){
                                    if(correctColumn.contains(numberC )){
                                        ioOutput.getOutput("The answer is being duplicated");
                                    }
                                    else{
                                        ok = true;
                                    }
                                }
                                else{
                                    ioOutput.getOutput("The answer is not in range. Enter again");
                                }
                            } catch (Exception e) {
                                ioOutput.getOutput("Enter a integer type for " + questionType + " Question: ");
                            }
                        }
                        correctColumn.add(numberC);

                    }
                    break;

                }

                else if(userResponse.toUpperCase().equals("NO")|(userResponse.toUpperCase().equals("N"))){
                    break;
                }
                else{
                    ioOutput.getOutput("Please type 'Y'/'Yes' for yes or 'N'/'No' for no!");
                }
            }

            correctAnswers.add(correctColumn);
            return;
        }
    }

}
