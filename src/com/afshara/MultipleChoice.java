package com.afshara;

import java.util.ArrayList;

//Concrete class from Abstract Question Class
public class MultipleChoice extends Question {
    Integer numberOfChoice;
    String questionType = "MultipleChoice";
    protected ArrayList<String> choices = new ArrayList<String>();
    protected ArrayList<Integer> correctChoice = new ArrayList<Integer>();
//    public Integer numberOfCChoice;
    private Integer numberOfCChoice;

    public int getNumberOfCChoices(){
        return this.numberOfCChoice;
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
    public ArrayList<String> getChoices(){return this.choices;}

    //Inserts a MultipleChoice question

    @Override
    public void insertQuestion(String SurveyOrTest) {
        ioOutput.getOutput("Enter the prompt for your " + questionType + " Question: ");
        prompt = ioInput.checkEmpty();;
        ioOutput.getOutput("Enter the number of choices for your " +questionType+ " Question: ");
        boolean ok = Boolean.FALSE;

        //Check if the number of choices is int and more than 2
        while(!ok){
            try {
                numberOfChoice = Integer.parseInt(ioInput.getInput());
                if (numberOfChoice >=2){
                    ok = true;
                }
                if (numberOfChoice <2){
                    ioOutput.getOutput("Enter 2 or more number of choices");
                }
            } catch (Exception e) {
                ioOutput.getOutput("Enter the number of choices for your " +questionType+ " Question: ");

            }
        }
        Integer numberC = 1;
        //Adds to the choices array
        while (!numberC.equals(numberOfChoice+1)){
            ioOutput.getOutput("Enter choice option for number "+ numberC);
            //If index comparison does not worl, add i infront of io.get input
            choices.add(ioInput.checkEmpty());
            numberC = numberC + 1;
        }

        ioOutput.getOutput("How many answer will you have?");
        ok = Boolean.FALSE;
        //Checks for how many answers is int
        //numberofCChoice is how many correct answers the user can have
        numberOfCChoice= null;
        while(!ok){
            try {
                numberOfCChoice = Integer.parseInt(ioInput.getInput());
                if(numberOfCChoice > 0 & numberOfCChoice<= numberOfChoice){
                    ok = Boolean.TRUE;
                }
                if( numberOfCChoice > numberOfChoice){
                    ioOutput.getOutput("Number of possible answer should be less than or equal to the number of questions");
                    ioOutput.getOutput("How many answer will you have?");
                }
            } catch (Exception e) {
                ioOutput.getOutput("How many answer will you have?");}}

        //Check for Test
        if (SurveyOrTest == "Test") {
            //loops to enter to correct choice
            numberC = 1;
            while (!numberC.equals(numberOfCChoice+1)){
                ioOutput.getOutput("Enter correct choice option number");

                ok = Boolean.FALSE;
                Integer numCheck = null;
                while(!ok){
                    try {
                        numCheck = Integer.parseInt(ioInput.getInput());
                        //numCheck - 1 is the index of the correct Answer
                        if ((numCheck > 0 ) & (numCheck <= numberOfChoice )){
                                ok = Boolean.TRUE;
                        }
                        else{
                            ioOutput.getOutput("Option out of range. Enter the option in range.");
                        }
                    } catch (Exception e) {
                        ioOutput.getOutput("Enter correct choice");}}

                Boolean sameSame = Boolean.FALSE;

                //checks if correctChoice's array has a answer and the admin trys toi add the same answer, then it notifies the user about duplciate answer in the array
                while(!sameSame){
                    if(correctChoice.contains(numCheck)){
                        ioOutput.getOutput("You are trying to add a duplicate answer!");
                        Boolean tooMuch = Boolean.FALSE;
                        while(!tooMuch){
                            try {
                                numCheck = Integer.parseInt(ioInput.getInput());
                                if ((numCheck > 0 ) & (numCheck <= numberOfChoice )){
                                    tooMuch = Boolean.TRUE;
                                }
                                else {
                                    ioOutput.getOutput("Option out of range. Enter the option in range.");
                                }
                            } catch (Exception e) {
                                ioOutput.getOutput("Enter correct choice number");}}
                    }
                    else{correctChoice.add(numCheck);
                        sameSame = Boolean.TRUE;}
                }

                numberC = numberC + 1;
            }
        }
        correctAnswers.add(correctChoice);
        //End of TEST
    }
    //End of insertQuestion


    //Outputs a MultipleChoice question
    @Override
    public void outputQuestions() {
        ioOutput.getOutput("Question: " + prompt);
        for (int i = 0; i < choices.size(); i++) {
            ioOutput.getOutput(i+1 + ")" + choices.get(i));
        }
        if (correctChoice.size() != 0) {
            for (int i=0; i<correctChoice.size();i++) {
                ioOutput.getOutput("The correct answer is " + correctChoice.get(i).toString());
            }
        }
    }

    public void outputQOnly() {
        ioOutput.getOutput("Question: " + prompt);
        for (int i = 0; i < choices.size(); i++) {
            ioOutput.getOutput(i+1 + ")" + choices.get(i));
        }
    }

    @Override
    public ArrayList getCorrectAnswers() {
        return this.correctAnswers;
    }

    @Override
    public void modify(Question question, String nameS) {
        String userResponse;
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

        while(true){
            ioOutput.getOutput("The current choices ");
            ioOutput.getOutputC(choices);
            ioOutput.getOutput("Do you wish to modify choices? Type 'Y'/'YES' for yes.");
            userResponse = ioInput.checkEmpty();

            if(userResponse.toUpperCase().equals("YES")|(userResponse.toUpperCase().equals("Y"))){
                Integer numberC;
                while(true){
                    ioOutput.getOutput("Which choice do you want to modify?");
                    ioOutput.getOutput("To add a choice, type add. To remove a choice, type remove.");
                    ioOutput.getOutput("When done modifying, type 'done'");
                    ioOutput.getOutputC(choices);
                    try {
                        String cc = ioInput.checkEmpty();
                        if (cc.toUpperCase().equals("ADD")){
                            ioOutput.getOutput("Type the choice you want to add");
                            choices.add(ioInput.checkEmpty());
                        }
                        else if (cc.toUpperCase().equals("DONE")){
                            ioOutput.getOutput("Type the choice you want to add");
                            break;
                        }
                        else if(cc.toUpperCase().equals("REMOVE")){
                            ioOutput.getOutput("Which choice do you want to remove?");
                            Integer removeChoice = ioInput.checkNum();
                            if (removeChoice <=  choices.size() & removeChoice>0){
                                choices.remove(removeChoice-1);
                            }
                            else{
                                ioOutput.getOutput("Index not in range");
                            }
                        }
                        else {
                            numberC = Integer.parseInt(cc);
                            if ((numberC <= choices.size()) & (numberC > 0)) {
                                ioOutput.getOutput("Modify the choice with: ");
                                choices.set(numberC-1,ioInput.checkEmpty());
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

        if(nameS.equals("TEST")){

            while(true){
                ioOutput.getOutput("Current correct answer in choice number");
                ioOutput.getOutputI(correctChoice);

                ioOutput.getOutput("Do you wish to modify correct answer? Type 'Y'/'YES' for yes.");
                userResponse = ioInput.checkEmpty();

                if(userResponse.toUpperCase().equals("YES")|(userResponse.toUpperCase().equals("Y"))){
                    ///--------------------------------------------------------------------------------------------------------
                    Integer numberC;
                    while(true){

                        try {
                            ioOutput.getOutput("Which correct answer do you want to modify?");
                            ioOutput.getOutput("To add a choice, type add. To remove a choice, type remove.");
                            ioOutput.getOutput("When done modifying, type 'done'");
                            ioOutput.getOutputI(correctChoice);
                            String cc = ioInput.checkEmpty();

                            if (cc.toUpperCase().equals("ADD")){
                                ioOutput.getOutput("Type the choice option you want to add");
                                //choices.add(ioInput.checkEmpty());
                                Integer addChoice = ioInput.checkNum();
                                if (addChoice <=  choices.size() & addChoice>0){
                                    correctChoice.add(addChoice);
                                    numberOfCChoice = correctChoice.size();
                                }
                                else{
                                    ioOutput.getOutput("Index not in range");
                                }
                            }
                            else if (cc.toUpperCase().equals("DONE")){
                                ioOutput.getOutput("Type the choice you want to add");
                                break;
                            }
                            else if(cc.toUpperCase().equals("REMOVE")){
                                ioOutput.getOutput("Which choice option do you want to remove?");
                                Integer removeChoice = ioInput.checkNum();
                                if (removeChoice <=  choices.size() & removeChoice>0){
                                    correctChoice.remove(removeChoice);
                                    numberOfCChoice = correctChoice.size();
                                }
                                else{
                                    ioOutput.getOutput("Index not in range");
                                }
                            }
                            else {
                                numberC = Integer.parseInt(cc);
                                if ((numberC <= choices.size()) & (numberC > 0)) {
                                    ioOutput.getOutput("Modify the choice with: ");
                                    Integer ccc = ioInput.checkNum();
                                    if ((ccc <= choices.size()) & (numberC > 0)) {
                                        if (!correctChoice.contains(ccc)){
                                            correctChoice.set(numberC-1,ccc);
                                        }
                                        else{
                                            ioOutput.getOutput("Duplicate answer. Please input a different number");
                                        }
                                    }

                                    break;
                                } else {
                                    ioOutput.getOutput("The answer is not in range. Enter again");
                                }
                            }
                        } catch (Exception e) {
                            ioOutput.getOutput("Enter a integer type for " + questionType + " Question: ");
                        }
                    }}
                else if(userResponse.toUpperCase().equals("NO")|(userResponse.toUpperCase().equals("N"))){
                    break;
                }
                else{
                    ioOutput.getOutput("Please type 'Y'/'Yes' for yes or 'N'/'No' for no!");
                }
            }
            return;
        }

    }
}
