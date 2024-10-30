package com.example;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Biglietti {
    private int numOro;
    private int numArg;
    private int numBase;
    public Biglietti(){
        numOro = 100;
        numArg = 100;
        numBase = 100;
    }
    

    public int getBigliettiOro(){
        return numOro;
        
    }
    public int setBigliettiOro(int num){
        return this.numOro=num;
    }
    public int getBigliettiArg(){
        return numArg;
        
    }
    public int setBigliettiArg(int num){
        return this.numArg=num;
    }
    public int getBigliettiBase(){
        return numBase;
        
    }
    public int setBigliettiBase(int num){
        return this.numBase=num;
    }
}
