package com.example.mathproject_yair_m.modals;

import java.util.Random;

public class Exercise {
    private int num1;
    private int num2;

    public  Exercise(){

    }

    public Boolean checkCorrect(String answer){
        if((num1*num2) == Integer.valueOf(answer))
            return  true;
        return  false;
    }

    public int getNum1(){
        return  num1;
    }

    public int getNum2(){
        return  num2;
    }

    public void setNum1(int num){
       num1 = num;
    }

    public void setNum2(int num){
        num2 = num;
    }

    public int generateRandom(int min,int max){
        Random r = new Random();
        int sum = max - min;
        int random = r.nextInt(sum)+min;
        return random;
    }

    public void times10(){
       setNum1(generateRandom(0,10));
        setNum2(generateRandom(0,10));
    }

    public void times20(){
        setNum1(generateRandom(0,20));
        setNum2(generateRandom(0,20));

    }

    public void challenge(){
        setNum1(generateRandom(0,10));
        setNum2(generateRandom(10,100));
    }
}

