package com.afshara;

import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.ArrayList;

//Abstract Question Class
public abstract class Question implements Serializable {
    private static final long serialVersionUID=1L;
    protected Save save = new Save();

    protected int numberOfCChoice;
    public String questionType;
    public AbstractCollection qColumn;
    //Input & Output for the implementation (Bridge Pattern)
    protected AbsOutput ioOutput = new IOOutput();
    protected AbsInput ioInput = new IOInput();
    protected String prompt;
    protected ArrayList correctAnswers = new ArrayList();

    public abstract void insertQuestion(String SurveyOrTest);
    public abstract void outputQuestions();
    public abstract void modify(Question question, String nameS );
    public abstract int getNumberOfCChoices();
    public abstract String getquestionType();
    public abstract Integer getnumberOfChoice();

    public ArrayList<String> getaColumn() {
        return null;
    }
    public ArrayList<String> getqColumn() {
        return null;
    }
    public ArrayList<String> getChoices(){return null;}
    public ArrayList<String> getLetters(){return null;}

    public abstract void outputQOnly();
    public abstract ArrayList getCorrectAnswers();
}
