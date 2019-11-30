package com.afshara;

import java.io.Serializable;
import java.util.ArrayList;

public class Grade implements Serializable {

    public static String grade(Survey gradeSurvey){
        Integer finalGrade = 0;
        Integer totalQuestion = 0;
        AbsOutput ioOutput = new IOOutput();
        AbsInput ioInput = new IOInput();
        ArrayList<Question> questions = gradeSurvey.questionArrayList;
        ArrayList<ArrayList> userAnswers = gradeSurvey.getUserAnswers();
        ArrayList<ArrayList> correctAnswers;
        int arraySize = questions.size();

        for (int i = 0 ; i < arraySize; i++) {
            Question question = questions.get(i);
            Integer numberOfCChoices = question.getNumberOfCChoices();
            String questionType = question.getquestionType();
            correctAnswers = question.getCorrectAnswers();
            ArrayList userAns  = userAnswers.get(i);
            ArrayList correctAns= null;

            if (!questionType.equals("Essay")){
                correctAns = correctAnswers.get(0);
            }


            if(questionType.equals("TrueFalse")){
                if(userAns.equals(correctAnswers.get(i))){
                    finalGrade = finalGrade + 10;
                    totalQuestion = totalQuestion +1;
                }
            }

            else if(questionType.equals("MultipleChoice")){
                Integer eachChoice = 10 / numberOfCChoices;
                for (int j = 0; j < correctAns.size(); j++) {
                    if(userAns.contains(Integer.toString((Integer) correctAns.get(j)))){
                        finalGrade = finalGrade + eachChoice;
                    }
                }
                totalQuestion = totalQuestion +1;
            }

            else if(questionType.equals("Essay")){
                finalGrade = finalGrade + 0;
            }

            else if(questionType.equals("ShortAnswer")){
                Integer eachChoice = 10 / numberOfCChoices;
                for (int j = 0; j < correctAns.size(); j++) {
                    if(userAns.contains(correctAns.get(j))){
                        finalGrade = finalGrade + eachChoice;
                    }
                }
                totalQuestion = totalQuestion +1;
            }

            else if(questionType.equals("Matching")){
                Integer eachChoice = 10 / numberOfCChoices;
                for (int j = 0; j < userAns.size(); j++) {
                    if (userAns.get(j).equals(Integer.toString((Integer) correctAns.get(j)))){
                        finalGrade = finalGrade + eachChoice;
                    }
                }
                totalQuestion = totalQuestion +1;
            }

            else if(questionType.equals("Ranking")){
                double eachChoice = ((double)10)/ question.getaColumn().size();
                for (int j = 0; j < userAns.size(); j++) {
                    if (userAns.get(j).equals(correctAns.get(j))){
                        finalGrade = finalGrade + (int)eachChoice;
                    }
                }
                totalQuestion = totalQuestion +1;
            }

            else if(questionType.equals("Emoji")){
                finalGrade = finalGrade + 10;
                totalQuestion = totalQuestion +1;
            }
        }

        return (finalGrade + "/" + (totalQuestion*10) );
    }
}
