package com.afshara;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Tabulate implements Serializable {
    private static final long serialVersionUID=11L;
    AbsOutput ioOutput = new IOOutput();
    AbsInput ioInput = new IOInput();


    public void Tabulate(Survey survey,String name, String SurveyorTestTaken) {
        Load load = new Load();
        String PATH = "./SavedSurveyorTest";
        int nameEndIndex = name.length();

        if(SurveyorTestTaken.toUpperCase().equals("TESTTAKEN")){PATH = "./SavedSurveyorTest/TESTTAKEN";}
        else if(SurveyorTestTaken.toUpperCase().equals("SURVEYTAKEN")){PATH = "./SavedSurveyorTest/SURVEYTAKEN";}

        File folder = new File(PATH);
        File[] listOfFiles = folder.listFiles();
        ArrayList<String> surveyNameList = new ArrayList<String>();
        ArrayList<String> finalList = new ArrayList<String>();
        ArrayList<Survey> loadList = new ArrayList<Survey>();
        Integer listOfFilesLength = listOfFiles.length;

        if (listOfFilesLength > 0 ) {
            //here is changed
            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile()) {
                    surveyNameList.add(listOfFiles[i].getName());
                    if (surveyNameList.get(i).substring(0, surveyNameList.get(i).indexOf('-')).equals(name)) {
                        finalList.add(surveyNameList.get(i));
//----------------->
                    }
                }
            }
        }
        else if (listOfFilesLength.equals(0)) {
            ioOutput.getOutput("no files with this name to load");
            return;
        }

        if (!listOfFilesLength.equals(0)) {
            //here is changed
            for(int j = 0; j < finalList.size(); j++){
                loadList.add(load.Load(finalList.get(j), SurveyorTestTaken));
            }
        }

        Integer numberOfQuestions = survey.questionArrayList.size();
        ArrayList allAnswer = new ArrayList();
        for (int i = 0; i < numberOfQuestions; i++) {
            ArrayList eachAnswer  = new ArrayList();
            for (int j = 0; j < loadList.size(); j++) {
                ArrayList uAnswers= loadList.get(j).getUserAnswers();
                ArrayList uAns  = (ArrayList) uAnswers.get(i);
                for(int k = 0; k < uAns.size(); k++){
                    eachAnswer.add(uAns.get(k));
                }
            }
            allAnswer.add(eachAnswer);
        }


        Integer countM = 0;
        ArrayList<Question> questions = survey.questionArrayList;
        for (int i = 0; i < numberOfQuestions; i++){
            ioOutput.getOutput("               ");
            Question question = questions.get(i);
            //
            Integer totalTrue = 0;
            Integer totalFalse = 0;
            Integer totalSmiles = 0;
            Integer totalFrowns = 0;
            Integer totalAngry = 0;
            Integer totalSurprised = 0;
            Integer totalSad = 0;
            ArrayList<String> totalEssay = new ArrayList<String>();
            ArrayList<String> totalMultiple = new ArrayList<String>();
            ArrayList<String> totalShortAnswer = new ArrayList<String>();
            ArrayList<String> uniqueShortAnswer = new ArrayList<String>();
            ArrayList<String> totalMatch = new ArrayList<String>();
            ArrayList<String> uniqueMatch = new ArrayList<String>();
            String matchingCombination = "";
            String rankingCombination = "";
            ArrayList<String> totalRank = new ArrayList<String>();
            ArrayList<String> uniqueRank = new ArrayList<String>();


            question.outputQOnly();
            ioOutput.getOutput("                                          ");
            ioOutput.getOutput("Replies:");
            ArrayList aAns= (ArrayList) allAnswer.get(i);
            for (int j = 0; j < aAns.size(); j++){
                ioOutput.getOutput((String) aAns.get(j));
//-----------------------------------------------------------------
                if(question.getquestionType().equals("TrueFalse")){
                    if(((String) aAns.get(j)).toUpperCase().equals("TRUE")){
                    totalTrue = totalTrue + 1;
                    }
                    else if (((String) aAns.get(j)).toUpperCase().equals("FALSE")) {
                        totalFalse = totalFalse + 1;
                    }
                }

                else if(question.getquestionType().equals("Emoji")){
                    if(((String) aAns.get(j)).equals("1")){
                        totalSmiles = totalSmiles + 1;
                    }
                    else if (((String) aAns.get(j)).equals("2")) {
                        totalFrowns = totalFrowns + 1;
                    }
                    else if (((String) aAns.get(j)).equals("3")) {
                        totalAngry = totalAngry +1;
                    }
                    else if (((String) aAns.get(j)).equals("4")) {
                        totalSurprised = totalSurprised+1;
                    }else if (((String) aAns.get(j)).equals("5")) {
                        totalSad = totalSad +1;
                    }
                }

                else if(question.getquestionType().equals("MultipleChoice")){
                    totalMultiple.add((String) aAns.get(j));
                }

                else if(question.getquestionType().equals("Essay")){
                    totalEssay.add((String) aAns.get(j));
                }
                else if(question.getquestionType().equals("ShortAnswer")){
                    totalShortAnswer.add((String) aAns.get(j));
                }
                else if(question.getquestionType().equals("Matching")){
                    if(countM < question.getnumberOfChoice()){
                        countM = countM + 1;
                        matchingCombination = matchingCombination + (String) aAns.get(j);
                    }
                    else{
                        countM= 0;
                        totalMatch.add(matchingCombination);
                        matchingCombination = "";
                    }
                }
                else if(question.getquestionType().equals("Ranking")){
                    if(countM < question.getnumberOfChoice()){
                        countM = countM + 1;
                        rankingCombination = rankingCombination + (String) aAns.get(j);
                        if(j+1 == aAns.size()){
                            countM= 0;
                            totalRank.add(rankingCombination);
                            rankingCombination = "";
                        }

                    }
                    else{
                        countM= 1;
                        totalRank.add(rankingCombination);
                        rankingCombination = "";
                        if(aAns.size() > j )
                        {
                            rankingCombination = rankingCombination + (String) aAns.get(j);
                        }

                    }
                }


                //------------------------------------------------------
            }
            ioOutput.getOutput("                                          ");
            ioOutput.getOutput("Tabulation:");

            if(question.getquestionType().equals("TrueFalse")){
             ioOutput.getOutput(question.getChoices().get(0) + " : " + totalTrue);
             ioOutput.getOutput(question.getChoices().get(1) + " : " + totalFalse);
            }
            else if(question.getquestionType().equals("Emoji")){
                ioOutput.getOutput(question.getChoices().get(0) + " : " + totalSmiles);
                ioOutput.getOutput(question.getChoices().get(1) + " : " + totalFrowns);
                ioOutput.getOutput(question.getChoices().get(2) + " : " + totalAngry);
                ioOutput.getOutput(question.getChoices().get(3) + " : " + totalSurprised);
                ioOutput.getOutput(question.getChoices().get(4) + " : " + totalSad);
            }
            else if(question.getquestionType().equals("Essay")){
                int totalEssayLength = totalEssay.size();
                for (int j = 0; j < totalEssay.size(); j++){
                    ioOutput.getOutput(totalEssay.get(j));
                }
            }

            else if(question.getquestionType().equals("ShortAnswer")){
                Integer totalShortAnswerLength = totalShortAnswer.size();
                for (int j = 0; j < totalShortAnswerLength; j++){
                    if(!uniqueShortAnswer.contains(totalShortAnswer.get(j))){
                        uniqueShortAnswer.add(totalShortAnswer.get(j));
                    }
                }
                for (int j = 0 ; j < uniqueShortAnswer.size(); j++){
                    ioOutput.getOutput( uniqueShortAnswer.get(j) + " : " + Collections.frequency(totalShortAnswer,uniqueShortAnswer.get(j)));
                }
            }

            else if(question.getquestionType().equals("MultipleChoice")){
                Integer numberOfChoice = question.getnumberOfChoice();
                for (int j = 1; j <= numberOfChoice; j++){
                    ioOutput.getOutput(String.valueOf(j) + " : " + Collections.frequency(totalMultiple,String.valueOf(j)));
                }
            }

            else if(question.getquestionType().equals("Matching")){
                Integer  totalMatchLength = totalMatch.size();
                for (int j = 0; j < totalMatchLength; j++){
                    if(!uniqueMatch.contains(totalMatch.get(j))){
                        uniqueMatch.add(totalMatch.get(j));
                    }
                }
                for (int j = 0 ; j < uniqueMatch.size(); j++){
                    ioOutput.getOutput("               ");
                    ioOutput.getOutput(String.valueOf(Collections.frequency(totalMatch,uniqueMatch.get(j))));
                    for(int z=1; z<=uniqueMatch.get(j).length(); z++){
                        String characters = uniqueMatch.get(j);
                        ioOutput.getOutput(question.getChoices().get(z-1) +" " + characters.substring(z-1,z));
                    }
                }

            }


            else if(question.getquestionType().equals("Ranking")){
//                countM= 0;
//                totalRank.add(rankingCombination);
//                rankingCombination = "";
                Integer  totalRankLength = totalRank.size();
                for (int j = 0; j < totalRankLength; j++){
                    if(!uniqueRank.contains(totalRank.get(j))){
                        uniqueRank.add(totalRank.get(j));
                    }
                }
                for (int j = 0 ; j < uniqueRank.size(); j++){
                    ioOutput.getOutput("               ");
                    ioOutput.getOutput(String.valueOf(Collections.frequency(totalRank,uniqueRank.get(j))));
                    //change here
                    for(int z=0 ; z<uniqueRank.get(j).length(); z++){
                        String characters = uniqueRank.get(j);
                        ioOutput.getOutput( z+1 + " " + characters.substring(z,z+1));
                    }
                }

            }
        }



    }
}

