package com.afshara;
import java.util.ArrayList;

//Concrete class from Abstract Question Class
public class Emoji extends MultipleChoice {
    String questionType = "Emoji";
    private ArrayList<String> choices = new ArrayList<String>();
    public Integer numberOfCChoice;
    //Inserts an Emoji question


    @Override
    public ArrayList<String> getChoices(){return this.choices;}

    @Override
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
    public void insertQuestion(String SurveyOrTest) {
        ioOutput.getOutput("Enter the prompt for your " + questionType + " Question: ");
        prompt = ioInput.checkEmpty();
        choices.add("1) Smiles");
        choices.add("2) Frowns");
        choices.add("3) Angry");
        choices.add("4) Surprised");
        choices.add("5) Sad");

        //=========================================================================
        //====================================================================
        ioOutput.getOutput("How many number of correct choices do you want?");
        while(true){
            numberOfCChoice = ioInput.checkNum();
            if(numberOfCChoice > 0 & numberOfCChoice<= 5){
                break;
            }
            if( numberOfCChoice > 5){
                ioOutput.getOutput("Number of possible answer should be less than or equal to the number of questions");
                ioOutput.getOutput("How many answer will you have?");
            }
        }
        correctAnswers.add(choices);
    }
    //End of insertQuestion

    // Prints to console an Emoji question
    @Override
    public void outputQuestions() {
        ioOutput.getOutput("Question: " + prompt);
        for (int i = 0; i < choices.size(); i++) {
            ioOutput.getOutput( choices.get(i));
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
            ioOutput.getOutput( choices.get(i));
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
        return;
    }

    @Override
    public ArrayList getCorrectAnswers() {
        return this.correctAnswers;
    }
}
