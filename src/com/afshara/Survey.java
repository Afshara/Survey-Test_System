package com.afshara;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

//Survey Class
public class Survey implements Serializable {
    private static final long serialVersionUID=1L;
    public Object getGrade;
    //Input & Output for the implementation (Bridge Pattern)
    AbsInput ioInput = new IOInput();
    AbsOutput ioOutput = new IOOutput();
    Save save = new Save();
    Take take = new Take();

    ArrayList userAnswers;

    protected ArrayList<Question> questionArrayList = new ArrayList();
    protected String option;
    protected String SurveyorTestname;

    public void setName(String name) {
        SurveyorTestname = name;
    }

    public String getName(){
        return SurveyorTestname;
    }

    public void setQuestion(){
        //Menu 3
        String createSurvey = String.join("\n"
                , "||=============================================||"
                , "1) Add a new T/F question"
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
            ioOutput.getOutput(createSurvey);
            option = ioInput.getInput();

            //1) Add a new T/F question
            if (option.equals("1")){
                Question trueFalse = new TrueFalse();
                trueFalse.insertQuestion("Survey");
                questionArrayList.add(trueFalse);
            }
            //2) Add a new multiple-choice question
            if (option.equals("2")){
                Question multipleChoice = new MultipleChoice();
                multipleChoice.insertQuestion("Survey");
                questionArrayList.add(multipleChoice);
            }
            //3) Add a new short answer question
            if (option.equals("3")){
                Question shortAnswer = new ShortAnswer();
                shortAnswer.insertQuestion("Survey");
                questionArrayList.add(shortAnswer);
            }
            //4) Add a new essay question
            if (option.equals("4")){
                Question essay = new Essay();
                essay.insertQuestion("Survey");
                questionArrayList.add(essay);
            }
            //5) Add a new emoji question
            if (option.equals("5")){
                Question emoji= new Emoji();
                emoji.insertQuestion("Survey");
                questionArrayList.add(emoji);
            }

            //6) Add a new matching question
            if (option.equals("6")){
                Question matching= new Matching();
                matching.insertQuestion("Survey");
                questionArrayList.add(matching);
            }

            //7) Return to Menu 2
            if (option.equals("7")){
                Question ranking = new Ranking();
                ranking.insertQuestion("Survey");
                questionArrayList.add(ranking);
            }

            //8) Return to Menu 2
            if (option.equals("8")){
                return;
            }
        }
    }

    public void Display(){
        ioOutput.getOutputQ(this.questionArrayList);
    }

    public void Modify(Survey currentSurvey){
        //ioOutput.getOutput("What question do you wish to modify?");
        currentSurvey.Display();
        Integer questionArrayListLength = currentSurvey.questionArrayList.size();
        String done = "none";
        Integer questionNumber= null;

            //Work on this loop ,, now bye
        while (!done.toUpperCase().equals("DONE")){
            ioOutput.getOutput("Type the question number you want to modify. Type 'done' when you are done modifying");
            boolean ok = Boolean.FALSE;
                //Check if the number of choices is int and more than 2
            while(!ok){
                try {
                    String x = ioInput.checkEmpty();
                    if (x.toUpperCase().equals("DONE")) {
                        ok = Boolean.TRUE;
                        save.save(currentSurvey,"SURVEY");
                        done = "DONE";
                    }
                    else{
                        questionNumber = Integer.parseInt(x);
                    if (questionNumber >0 & questionNumber < questionArrayListLength+1){
                        Question currentQuestion = questionArrayList.get(questionNumber-1);
                        String nameS = "SURVEY";
                        currentQuestion.modify(currentQuestion, nameS);
                        save.save(currentSurvey,"SURVEY");
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
            return;
            }

    }


    public void Take(Survey survey){
        userAnswers = Take.take(survey);

    }

    public ArrayList getUserAnswers() {
        return this.userAnswers;
    }

    public void Grade(Survey takeSurvey) {
        return;
    }

    public void Tabulate(Survey survey, String name){
        Tabulate tabulate = new Tabulate();
        tabulate.Tabulate(survey, name,"SURVEYTAKEN");
    }

    public String getGrade() {
        return null;
    }
}
