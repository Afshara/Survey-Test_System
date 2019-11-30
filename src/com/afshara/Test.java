package com.afshara;

import java.util.ArrayList;

//Test Class
public class Test extends Survey {
    Take take = new Take();
//    ArrayList<ArrayList> allAnswers;
    ArrayList<ArrayList> userAnswers;
    String grade = null;

    @Override
    public String getGrade(){
        return this.grade;
    }


    @Override
    public void setQuestion(){
        //Menu 3
        String createTest = String.join("\n"
                ,  "||=============================================||"
                ,"1) Add a new T/F question"
                , "2) Add a new multiple-choice question"
                , "3) Add a new short answer question"
                , "4) Add a new essay question"
                , "5) Add a new emoji question"
                , "6) Add a new matching question"
                , "7) Add a new ranking question"
                , "8) Return"
                , "||=============================================||"
        );

        Boolean choice2 = Boolean.TRUE;
        while (choice2){
            ioOutput.getOutput(createTest);
            option = ioInput.checkEmpty();

            //1) Add a new T/F question
            if (option.equals("1")){
                Question trueFalse = new TrueFalse();
                trueFalse.insertQuestion("Test");
                questionArrayList.add(trueFalse);
            }
            //2) Add a new multiple-choice question
            if (option.equals("2")){
                Question multipleChoice = new MultipleChoice();
                multipleChoice.insertQuestion("Test");
                questionArrayList.add(multipleChoice);
            }
            //3) Add a new short answer question
            if (option.equals("3")){
                Question shortAnswer = new ShortAnswer();
                shortAnswer.insertQuestion("Test");
                questionArrayList.add(shortAnswer);
            }
            //4) Add a new essay question
            if (option.equals("4")){
                Question essay = new Essay();
                essay.insertQuestion("Test");
                questionArrayList.add(essay);
            }
            //5) Add a new emoji question
            if (option.equals("5")){
                Question emoji= new Emoji();
                emoji.insertQuestion("Test");
                questionArrayList.add(emoji);
            }

            //6) Add a new matching question
            if (option.equals("6")){
                Question matching= new Matching();
                matching.insertQuestion("Test");
                questionArrayList.add(matching);
            }

            if (option.equals("7")){
                Question ranking = new Ranking();
                ranking.insertQuestion("Test");
                questionArrayList.add(ranking);
            }

            //8) Return to Menu 2
            if (option.equals("8")){
                return;
            }
        }
    }

    @Override
    public void Modify(Survey currentSurvey){
        //ioOutput.getOutput("What question do you wish to modify?");
        Integer questionArrayListLength = currentSurvey.questionArrayList.size();
        String done = "none";
        Integer questionNumber= null;

        //Work on this loop ,, now bye
        while (!done.toUpperCase().equals("DONE")){
            currentSurvey.Display();
            ioOutput.getOutput("Type the question number you want to modify. Type 'done' when you are done modifying");
            boolean ok = Boolean.FALSE;
            //Check if the number of choices is int and more than 2
            while(!ok){
                try {
                    String x = ioInput.checkEmpty();
                    if (x.toUpperCase().equals("DONE")) {
                        save.save(currentSurvey,"TEST");
                        return;
//                        ok = Boolean.TRUE;
//                        done = "DONE";
                    }
                    else{
                        questionNumber = Integer.parseInt(x);
                        if (questionNumber >0 & questionNumber < questionArrayListLength+1){
                            Question currentQuestion = questionArrayList.get(questionNumber-1);
                            String nameS = "TEST";
                            currentQuestion.modify(currentQuestion, nameS);
                            save.save(currentSurvey,"TEST");
                            ok = true;
                        }
                        else {
                            ioOutput.getOutput("Question number not in range");
                        }
                    }
                }
                catch (Exception e) {
                    ioOutput.getOutput("Type the question number you want to modify. Type 'done' when you are done modifying");
                }
            }
        }

    }

    @Override
    public void Take(Survey survey){
        ArrayList<ArrayList> allAnswers = Take.take(survey);
        userAnswers = allAnswers;
    }

    @Override
    public ArrayList<ArrayList> getUserAnswers() {
        return this.userAnswers;
    }

    @Override
    public void Grade(Survey survey){
        grade = Grade.grade(survey);
        ioOutput.getOutput(grade);
    }

    @Override
    public void Tabulate(Survey survey, String name){
        Tabulate tabulate = new Tabulate();
        tabulate.Tabulate(survey, name,"TESTTAKEN");
    }



}
