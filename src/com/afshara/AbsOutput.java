package com.afshara;

import java.io.Serializable;
import java.util.ArrayList;

//Abstract Output Class
public abstract class AbsOutput implements Serializable {
    public abstract void getOutput(String printThis);
    public abstract void getOutputQ(ArrayList<Question> questionArrayList);
    public abstract void getOutputC(ArrayList<String> questionArrayList);
    public abstract void getOutputI(ArrayList<Integer> questionArrayList);
    public abstract void getOutputA(ArrayList<String> questionArrayList);
}
