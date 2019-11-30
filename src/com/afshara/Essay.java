package com.afshara;

import java.util.ArrayList;

//Concrete class from Abstract Question Class
public class Essay extends Question {
    String correctAnswer;
    String questionType = "Essay";
    public Integer numberOfCChoice = 1;

    public int getNumberOfCChoices(){
        return this.numberOfCChoice;
    }

    @Override
    public String getquestionType() {
        return this.questionType;
    }

    @Override
    public Integer getnumberOfChoice() {
        return null;
    }


    //Inserts an Essay question
    @Override
    public void insertQuestion(String SurveyOrTest) {
        ioOutput.getOutput("Enter the prompt for your " + questionType + " Question: ");
        prompt = ioInput.checkEmpty();
    }
    //End of insertQuestion

    //Prints to console an Essay question
    @Override
    public void outputQuestions() {
        ioOutput.getOutput("Question: " + prompt);
        if (correctAnswers.size() != 0) {
            ioOutput.getOutput("The correct answer is: " + correctAnswer);
        }
    }

    public void outputQOnly() {
        ioOutput.getOutput("Question: " + prompt);
    }

    @Override
    public ArrayList getCorrectAnswers() {
        return correctAnswers;
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

        return;
    }

}
