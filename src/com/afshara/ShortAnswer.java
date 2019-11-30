package com.afshara;

import javax.swing.*;
import java.util.ArrayList;

//Concrete class from Abstract Question Class
public class ShortAnswer extends Essay {
    private int WordLimit = 20;
    String correctAnswer;
    String questionType = "ShortAnswer";
    ArrayList<String> answers = new ArrayList<String>();
    Integer numberOfChoice;
    public int numberOfCChoice;
    //Inserts a ShortAnswer question
    //Start of insertQuestion

    @Override
    public Integer getnumberOfChoice() {
        return this.numberOfChoice;
    }

    @Override
    public String getquestionType() {
        return this.questionType;
    }

    public int getNumberOfCChoices(){
        return this.numberOfCChoice;
    }

    @Override
    public void insertQuestion(String SurveyOrTest) {
        ioOutput.getOutput("Enter the prompt for your " + questionType + " Question: ");
        prompt = ioInput.checkEmpty();;
        ioOutput.getOutput("Enter the number of choices for your " + questionType + " Question: ");

        //-------------------------------------


            boolean ok = Boolean.FALSE;
            //Check if the number of choices is int
            while(!ok){
                try {
                    numberOfCChoice = Integer.parseInt(ioInput.getInput());
                    ok = Boolean.TRUE;
                } catch (Exception e) {
                    ioOutput.getOutput("Enter the number of correct answers for your " + questionType + " Question: ");
                }
            }
            //---------------------------------------------
        if (SurveyOrTest == "Test") {
            for (int i = 0; i < numberOfCChoice; i ++) {
                ioOutput.getOutput("Provide answer for the question");
                correctAnswer = ioInput.essayOrSA().nextLine();
                while (correctAnswer.length() > WordLimit) {
                    ioOutput.getOutput("The answer should be shorter than 20 words");
                    correctAnswer = ioInput.essayOrSA().nextLine();
                }
                Boolean tooMuch = Boolean.FALSE;
                while (!tooMuch){
                    if(answers.contains(correctAnswer )){
                        ioOutput.getOutput("The answer is being duplicated");
                        correctAnswer = ioInput.essayOrSA().nextLine();
                        while (correctAnswer.length() > WordLimit) {
                            ioOutput.getOutput("The answer should be shorter than 50 words");
                            correctAnswer = ioInput.essayOrSA().nextLine();
                        }
                    }
                    else{
                        tooMuch = Boolean.TRUE;
                    }
                }
                answers.add(correctAnswer);
            }
        }
        correctAnswers.add(answers);
        //End of TEST
    }
    //End of insertQuestion


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
        if(nameS.equals("TEST")){

            while(true){
                ioOutput.getOutput("Current correct answer");
                ioOutput.getOutputC(answers);
                ioOutput.getOutput("Do you wish to modify choices? Type 'Y'/'YES' for yes.");
                userResponse = ioInput.checkEmpty();

                if(userResponse.toUpperCase().equals("YES")|(userResponse.toUpperCase().equals("Y"))){
                    ioOutput.getOutput("What choice do you wish to make the new correct answer? Type 'D' when you are done modifying the correct answers");
                    //ioOutput.getOutput(correctAnswer);
                    Integer numberC;

                    while(true) {
                        ioOutput.getOutput("Which choice do you want to modify?");
                        ioOutput.getOutput("To add a choice, type add. To remove a choice, type remove.");
                        ioOutput.getOutput("When done modifying, type 'done'");
                        ioOutput.getOutputC(answers);
                        try {
                            String cc = ioInput.checkEmpty();
                            if (cc.toUpperCase().equals("ADD")) {
                                ioOutput.getOutput("Type the choice you want to add");
                                answers.add(ioInput.checkEmpty());
                                numberOfCChoice = answers.size();
                            } else if (cc.toUpperCase().equals("DONE")) {
                                ioOutput.getOutput("Type the choice you want to add");
                                break;
                            } else if (cc.toUpperCase().equals("REMOVE")) {
                                ioOutput.getOutput("Which choice do you want to remove?");
                                Integer removeChoice = ioInput.checkNum();
                                if (removeChoice <= answers.size() & removeChoice > 0) {
                                    answers.remove(removeChoice - 1);
                                    numberOfCChoice = answers.size();
                                } else {
                                    ioOutput.getOutput("Index not in range");
                                }
                            } else {
                                numberC = Integer.parseInt(cc);
                                if ((numberC <= answers.size()) & (numberC > 0)) {
                                    ioOutput.getOutput("Modify the choice with: ");
                                    answers.set(numberC - 1, ioInput.checkEmpty());
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

        }
        correctAnswers.remove(answers);
        correctAnswers.add(answers);
    }

    @Override
    public ArrayList getCorrectAnswers() {
        return this.correctAnswers;
    }
}

