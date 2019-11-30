package com.afshara;

import java.io.Serializable;
import java.util.Scanner;

//Abstract Input Class
public abstract class AbsInput implements Serializable {
    public abstract String getInput();
    public abstract Scanner essayOrSA();
    public abstract String checkEmpty();
    public abstract Integer checkNum();
}
