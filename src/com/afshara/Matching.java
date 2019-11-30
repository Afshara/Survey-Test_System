package com.afshara;

import java.util.ArrayList;

//Concrete class from Abstract Question Class
public class Matching extends Question {
    ArrayList<String> qColumn = new ArrayList<String>();
    ArrayList<String> aColumn = new ArrayList<String>();
    ArrayList<Integer> correctColumn = new ArrayList<Integer>();
    String questionType = "Matching";
    Integer numberOfChoice;
    public Integer numberOfCChoice;

    public int getNumberOfCChoices(){
        return this.numberOfCChoice;
    }

    @Override
    public ArrayList<String> getChoices(){return this.qColumn;}

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

    //Inserts a Matching question
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
            ioOutput.getOutput("Enter question for question column at line " + count);
            qColumn.add(ioInput.checkEmpty());

        }
        count = 0;
        for (int i = 0; i < numberOfChoice; i++){
            count = i+1;
            ioOutput.getOutput("Enter answer for answer column at line " + count);
            aColumn.add(ioInput.checkEmpty());
        }

        numberOfCChoice = qColumn.size();

        Integer numberC = null;
        if (SurveyOrTest == "Test") {
            for (int i = 0; i < qColumn.size(); i ++) {
                ioOutput.getOutput("Enter the correct entry for " + qColumn.get(i));
                for(int j = 0; j < aColumn.size(); j++){
                    int jj = j+1;

                    ioOutput.getOutput("Type the number which correct answer to this matching question #"+ jj + ") "+ aColumn.get(j));
                }

                ok = Boolean.FALSE;
                while(!ok){
                    try {
                        numberC = Integer.parseInt(ioInput.getInput());
                        if ((numberC <= numberOfChoice) & (numberC >0) ){
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

                numberOfCChoice = correctColumn.size();
            }
        }
        correctAnswers.add(correctColumn);
        //End of TEST
    }
    //End of insertQuestion

    //Prints to console a Matching question
    @Override
    public void outputQuestions() {
        ioOutput.getOutput("Question: " + prompt);
        int count;
        ioOutput.getOutput("question column");
        for (int i = 0; i < qColumn.size(); i++) {
             count = i+1;
            ioOutput.getOutput(count + ") " + qColumn.get(i));

        }
        ioOutput.getOutput("Answer column");
        for (int i =0; i < aColumn.size(); i++){
            count = i+1;
            ioOutput.getOutput( count + ") " + aColumn.get(i));
        }

        if (correctAnswers.size() != 0) {
            ioOutput.getOutput("The correct answers are ");
            for (int i=0; i<correctColumn.size();i++) {
                ioOutput.getOutput(qColumn.get(i) + " - " + aColumn.get(correctColumn.get(i)-1));
            }
        }
    }

    //here
    public void outputQOnly() {
        ioOutput.getOutput("Question: " + prompt);
        int count;
        ioOutput.getOutput("question column");
        for (int i = 0; i < qColumn.size(); i++) {
            count = i+1;
            ioOutput.getOutput(count + ") " + qColumn.get(i));

        }
        ioOutput.getOutput("Answer column");
        for (int i =0; i < aColumn.size(); i++){
            count = i+1;
            ioOutput.getOutput( count + ") " + aColumn.get(i));
        }
    }

    @Override
    public ArrayList getCorrectAnswers() {
        return this.correctAnswers;
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
            ioOutput.getOutput("question column");
            ioOutput.getOutputC(qColumn);
            ioOutput.getOutput("answer column");
            ioOutput.getOutputC(aColumn);
            ioOutput.getOutput("Do you wish to modify choices? Type 'Y'/'YES' for yes.");
            userResponse = ioInput.checkEmpty();


            if(userResponse.toUpperCase().equals("YES")|(userResponse.toUpperCase().equals("Y"))){
                Integer numberC;
                while(true){
                    ioOutput.getOutput("To modify an existing option from qcolumn/acolumn type 'M'");
                    ioOutput.getOutput("To add a choice, type add. To remove a choice, type remove.");
                    ioOutput.getOutput("When done modifying, type 'done'");

                    ioOutput.getOutput("question column");
                    ioOutput.getOutputC(qColumn);
                    ioOutput.getOutput("answer column");
                    ioOutput.getOutputC(aColumn);

                    try {
                        String cc = ioInput.checkEmpty();

                        if (cc.toUpperCase().equals("ADD")){
                            ioOutput.getOutput("Type the question you want to add");
                            qColumn.add(ioInput.checkEmpty());
                            ioOutput.getOutput("Type the answer you want to add");
                            aColumn.add(ioInput.checkEmpty());
                            testMatch = Boolean.FALSE;
                        }

                        else if (cc.toUpperCase().equals("DONE")){
                            break;
                        }

                        else if(cc.toUpperCase().equals("REMOVE")){

                            ioOutput.getOutput("Type the question number you want to remove");
                            Integer removeChoice = ioInput.checkNum();
                            if (removeChoice <=  qColumn.size() & removeChoice>0){
                                qColumn.remove(removeChoice-1);
                                testMatch = Boolean.FALSE;
                            }
                            else{
                                ioOutput.getOutput("Index not in range");
                            }


                            ioOutput.getOutput("Type the answer number you want to remove");
                            removeChoice = ioInput.checkNum();
                            if (removeChoice <=  aColumn.size() & removeChoice>0){
                               aColumn.remove(removeChoice-1);
                                testMatch = Boolean.FALSE;
                            }
                            else{
                                ioOutput.getOutput("Index not in range");
                            }
                        }
                        //Modify
                        else if (cc.toUpperCase().equals("M")){
                            ioOutput.getOutput("Type 'Q' to modify question column and 'A' to modify answer column");
                            String ccc = ioInput.checkEmpty();
                            if (ccc.toUpperCase().equals("Q")){
                                ioOutput.getOutput("question column");
                                ioOutput.getOutputC(qColumn);
                                ioOutput.getOutput("Type the option you want to modify");
                                numberC  = ioInput.checkNum();
                                if ((numberC <= qColumn.size()) & (numberC > 0)) {
                                    ioOutput.getOutput("Modify the choice with: ");
                                    qColumn.set(numberC-1,ioInput.checkEmpty());
                                    break;
                                } else {
                                    ioOutput.getOutput("The answer is not in range. Enter again");
                                }

                            }
                            else if (ccc.toUpperCase().equals("A")){
                                ioOutput.getOutput("Answer column");
                                ioOutput.getOutputC(aColumn);
                                ioOutput.getOutput("Type the option you want to modify");
                                numberC  = ioInput.checkNum();
                                if ((numberC <= aColumn.size()) & (numberC > 0)) {
                                    ioOutput.getOutput("Modify the choice with: ");
                                    aColumn.set(numberC-1,ioInput.checkEmpty());
                                    break;
                                } else {
                                    ioOutput.getOutput("The answer is not in range. Enter again");
                                }
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
                    Integer numberC = null;
                    ArrayList clonedCorrectAnswers = new ArrayList();
                    clonedCorrectAnswers = correctAnswers;
                    correctAnswers.clear();
                    correctColumn.clear();
                        //The correctAnswers column
                        if (clonedCorrectAnswers.size() != 0) {
                            ioOutput.getOutput("The current correct answers are ");
                            for (int i=0; i<correctColumn.size();i++) {
                                ioOutput.getOutput(qColumn.get(i) + " - " + aColumn.get(correctColumn.get(i)-1));
                            }
                        }

                        for (int i = 0; i < qColumn.size(); i ++) {
                            ioOutput.getOutput("Enter the correct entry for " + qColumn.get(i));
                            for(int j = 0; j < aColumn.size(); j++){
                                int jj = j+1;

                                ioOutput.getOutput("Type the number which correct answer to this matching question #"+ jj + ") "+ aColumn.get(j));
                            }

                            Boolean ok = Boolean.FALSE;
                            while(!ok){
                                try {
                                    numberC = Integer.parseInt(ioInput.getInput());
                                    if ((numberC <= qColumn.size()) & (numberC >0) ){
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
