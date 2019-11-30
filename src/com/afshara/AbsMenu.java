package com.afshara;

import java.io.IOException;
import java.io.Serializable;

//Abstract Menu Class
public abstract class AbsMenu implements Serializable {
    public abstract void createMenu() throws IOException, ClassNotFoundException;
}
