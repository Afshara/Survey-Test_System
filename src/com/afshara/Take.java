package com.afshara;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Take implements Serializable {
     AbsOutput ioOutput = new IOOutput();
     AbsInput ioInput = new IOInput();

    public static ArrayList<ArrayList> take(Survey takeSurvey){
        //surveyOrTest= SURVEY or TEST
        AbsOutput ioOutput = new IOOutput();
        AbsInput ioInput = new IOInput();
        ArrayList<Question> questions = takeSurvey.questionArrayList;
        ArrayList<ArrayList> allAnswers = new ArrayList<ArrayList>();

        int arraySize = questions.size();
        for (int i = 0 ; i < arraySize; ++i) {
            ArrayList<String> answerArray = new ArrayList<>();
            System.out.println(i+1+ ")");
            questions.get(i).outputQOnly();
            System.out.println("\n");
            int count = 0;
            ioOutput.getOutput("Enter you response");

            Question question1 = questions.get(i);
            int cc = question1.getNumberOfCChoices();

            while (count < cc){

                if(question1.getquestionType().equals("Essay")){
                    String userResponse = ioInput.essayOrSA().nextLine();
                    answerArray.add(userResponse);
                }

                else if(question1.getquestionType().equals("ShortAnswer")){
                    while(true){
                        String userResponse = ioInput.essayOrSA().nextLine();
                        if (answerArray.size() <= question1.getNumberOfCChoices()){
                            answerArray.add(userResponse);
                            break;
                        }
                        else{
                            ioOutput.getOutput("Answer cannot be accepted");
                        }
                    }
                }

                else if(question1.getquestionType().equals("Matching")){
                    ArrayList<String> aColumn = question1.getaColumn();
                    ArrayList<String>  qColumn = question1.getqColumn();
                    for (int ii = 1; ii <= qColumn.size(); ++ii) {
                        ioOutput.getOutput("Enter the correct entry for " + qColumn.get(ii-1));
                        for(int j = 0; j < aColumn.size(); ++j){
                            int jj = j+1;
                            ioOutput.getOutput("Type the number which correct answer to this matching question #"+ jj + ") "+ aColumn.get(j));
                        }
                        Boolean ok = Boolean.FALSE;
                        while(!ok){
                            try {
                                Integer userResponse = Integer.parseInt(ioInput.getInput());
                                if ((userResponse <= question1.getNumberOfCChoices()) & (userResponse >0) ){
                                    if(answerArray.contains(userResponse)){
                                        ioOutput.getOutput("The answer is being duplicated");
                                    }
                                    else{
                                        answerArray.add(Integer.toString (userResponse));
                                        ok = true;
                                    }
                                }
                                else{
                                    ioOutput.getOutput("The answer is not in range. Enter again");
                                }
                            } catch (Exception e) {
                                ioOutput.getOutput("Enter a integer type for Matching Question: ");
                            }
                        }
                    }
                    count = count +  qColumn.size();
                }

                else if(question1.getquestionType().equals("Ranking")){
                    ArrayList<String> aColumn = question1.getaColumn();
                    ArrayList<String>  qColumn = question1.getqColumn();
                    ArrayList<String> letters = question1.getLetters();
                    for (int ii = 0; ii < aColumn.size(); ++ii) {
                        ioOutput.getOutput("Enter answer for rank " + qColumn.get(ii));
                        for(int j = 0; j < aColumn.size(); ++j){
                            ioOutput.getOutput(letters.get(j) + " "+ aColumn.get(j));
                        }
                        Boolean ok = Boolean.FALSE;
                        while(!ok){
                            try {
                                String userResponse = ioInput.checkEmpty();
                                if (letters.contains(userResponse)){
                                    if(answerArray.contains(userResponse)){
                                        ioOutput.getOutput("The answer is being duplicated");
                                    }
                                    else{
                                        answerArray.add(userResponse);
                                        ok = true;
                                    }
                                }
                                else{
                                    ioOutput.getOutput("Enter a letter");
                                }
                            } catch (Exception e) {
                                ioOutput.getOutput("Enter a integer type for Matching Question: ");
                            }
                        }
                    }


                }

                else if(question1.getquestionType().equals("MultipleChoice")){
                    int c = 0;
                    //while(c < question1.getNumberOfCChoices()){
                    while(true){
                        Integer userResponse = ioInput.checkNum();
                        if (userResponse >0 & userResponse <= question1.getnumberOfChoice()){
                            answerArray.add(Integer.toString (userResponse));
                            //c = c+1;
                            break;
                        }
                        else{
                            ioOutput.getOutput("Answer is out of range");
                        }
                    }

                }

                else if(question1.getquestionType().equals("TrueFalse")){
                    while(true){
                        String userResponse = ioInput.checkEmpty();
                        if (userResponse.toUpperCase().equals("TRUE")|userResponse.toUpperCase().equals("FALSE")){
                            answerArray.add(userResponse);
                            break;
                        }
                        else{
                            ioOutput.getOutput("Answer should be true or false");
                        }
                    }
                }

                else if(question1.getquestionType().equals("Emoji")){
                    int c = 0;
                    while(true){
                        Integer userResponse = ioInput.checkNum();
                        if (userResponse >0 & userResponse <= 5){
                            answerArray.add(Integer.toString (userResponse));
                            break;
                        }
                        else{
                        ioOutput.getOutput("Answer is out of range");
                        }
                    }
                }
                count +=1;
            }

            allAnswers.add(answerArray);
        }

        return(allAnswers);
    }

}

