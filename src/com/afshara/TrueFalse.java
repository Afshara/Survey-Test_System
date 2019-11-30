package com.afshara;

import java.util.ArrayList;

//Concrete class from Abstract Question Class
public class TrueFalse extends MultipleChoice {
    String questionType = "TrueFalse";
    private ArrayList<String> choices = new ArrayList<String>();
    private ArrayList<String> correctChoice= new ArrayList<>();
    private int numberOfCChoice = 1;

    public int getNumberOfCChoices(){
        return this.numberOfCChoice;
    }

    @Override
    public Integer getnumberOfChoice() {
        return this.numberOfChoice;
    }

    @Override
    public String getquestionType() {
        return this.questionType;
    }

    @Override
    public ArrayList<String> getChoices(){return this.choices;}

    //Inserts a TrueFalse question
    @Override
    public void insertQuestion(String SurveyOrTest) {
        ioOutput.getOutput("Enter the prompt for your " + questionType + " Question: ");
        prompt = ioInput.checkEmpty();
        choices.add("True");
        choices.add("False");
//        numberOfCChoice = 1;
        //Check for Test
        if (SurveyOrTest == "Test") {
            ioOutput.getOutput("Enter correct choice, choice TRUE or FALSE");
            String choice = ioInput.getInput();
            while(!choice.toUpperCase().equals("TRUE") & !choice.toUpperCase().equals("FALSE")){
                ioOutput.getOutput("Enter correct choice, choice TRUE or FALSE");
                choice = ioInput.getInput().toUpperCase();
            }
            correctChoice.add(choice);
        }
        correctAnswers.add(correctChoice);
        //End of TEST
    }
    //End of insertQuestion

    //Prints to console a TrueFalse question
    @Override
    public void outputQuestions() {
        ioOutput.getOutput("Question: " + prompt);
        for (int i = 0; i < choices.size(); i++) {
            ioOutput.getOutput(i+1 + ")" + choices.get(i));
        }
        if (correctAnswers.size()!= 0) {
            ioOutput.getOutput("The correct answer is: " + correctAnswers);
            ioOutput.getOutput("");
        }
    }

    public void outputQOnly() {
        ioOutput.getOutput("Question: " + prompt);
        for (int i = 0; i < choices.size(); i++) {
            ioOutput.getOutput(i+1 + ")" + choices.get(i));
        }
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

        if(nameS.equals("TEST")){
            while(true){
                ioOutput.getOutput("Current correct answer");
                ioOutput.getOutput(correctChoice.get(0));
                ioOutput.getOutput("Do you wish to modify choices? Type 'Y'/'YES' for yes.");
                userResponse = ioInput.checkEmpty();

                if(userResponse.toUpperCase().equals("YES")|(userResponse.toUpperCase().equals("Y"))){
                    ioOutput.getOutput("Enter correct choice, choice TRUE or FALSE");
                    userResponse = ioInput.checkEmpty();
                    while(!userResponse.toUpperCase().equals("TRUE") & !userResponse.toUpperCase().equals("FALSE")){
                        ioOutput.getOutput("Enter correct choice, choice TRUE or FALSE");
                        userResponse= ioInput.checkEmpty().toUpperCase();
                        }
                    correctChoice.add(userResponse);
                    correctAnswers.add(correctChoice);
                }
                else if(userResponse.toUpperCase().equals("NO")|(userResponse.toUpperCase().equals("N"))){
                    break;
                }
                else{
                    ioOutput.getOutput("Please type 'Y'/'Yes' for yes or 'N'/'No' for no!");
                }
            }

        }

        return;
    }

    @Override
    public ArrayList getCorrectAnswers() {
        return this.correctAnswers;
    }

}
