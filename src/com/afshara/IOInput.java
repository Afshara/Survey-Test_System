package com.afshara;

import java.util.Scanner;

//Refined Abstraction from Abstract Input class
public class IOInput extends AbsInput {
    IOOutput ioOutput = new IOOutput();

    @Override
    public String getInput() {
        Scanner scanner = new Scanner(System.in);
        String oneLine = scanner.nextLine();
        return oneLine;
    }

    public String checkEmpty() {
        Scanner scanner = new Scanner(System.in);
        String oneLine = scanner.nextLine();
        while (oneLine.isEmpty() | oneLine.isBlank()){
            ioOutput.getOutput("Blanks and spaces cannot be accepted as a response");
            oneLine = scanner.nextLine();
        }
        return oneLine;
    }

    @Override
    public Integer checkNum() {
        while(true){
            try {
                Integer numberC = Integer.parseInt(checkEmpty());
                return numberC;
            }
            catch(Exception e){
                ioOutput.getOutput("Please Input an integer type");
            }
        }
    }


    public Scanner essayOrSA(){
        return new Scanner(System.in);
    }

}
