package com.afshara;

import java.util.ArrayList;

//Refined Abstraction from Abstract Output class
public class IOOutput extends AbsOutput {
    @Override
    public void getOutput(String printThis) {
        System.out.println(printThis);
    }

    public void getOutputQ(ArrayList<Question> questions) {
        int arraySize = questions.size();
        for (int i = 0 ; i < arraySize; i++) {
            System.out.println(i+1+ ")");
            questions.get(i).outputQOnly();
            //System.out.println(questions.get(i));
            System.out.println("\n");
        }
    }

    @Override
    public void getOutputC(ArrayList<String> choices){
        int arraySize = choices.size();
        for (int i = 0 ; i < arraySize; i++) {
            System.out.println(i+1+ ")");
            System.out.println(choices.get(i));
            System.out.println("\n");
        }
    }

    @Override
    public void getOutputI(ArrayList<Integer> correctChoices) {
        int arraySize = correctChoices.size();
        for (int i = 0 ; i < arraySize; i++) {
            System.out.println(i+1+ ")");
            System.out.println(correctChoices.get(i));
            System.out.println("\n");
        }
    }

    char[] alphabet = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

    @Override
    public void getOutputA(ArrayList<String> choices) {
        int arraySize = choices.size();
        for (int i = 0 ; i < arraySize; i++) {
            System.out.println(alphabet[i] + ")");
            System.out.println(choices.get(i));
            System.out.println("\n");
        }
    }


}
