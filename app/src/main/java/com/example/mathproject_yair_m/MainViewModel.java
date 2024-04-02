package com.example.mathproject_yair_m;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    Exercise exercise;
    User user;
    MutableLiveData<Integer> vNum1;
    MutableLiveData<Integer> vNum2;


    public MainViewModel(){
        exercise = new Exercise();
        user = new User();
        vNum1 = new MutableLiveData<>();
        vNum2 = new MutableLiveData<>();
    }

    public void vTimes10(){
        exercise.times10();
        vNum1.setValue(exercise.getNum1());
        vNum2.setValue(exercise.getNum2());
    }

    public void vTimes20(){
        exercise.times20();
        vNum1.setValue(exercise.getNum1());
        vNum2.setValue(exercise.getNum2());
    }

    public void vChallenge(){
        exercise.challenge();
        vNum1.setValue(exercise.getNum1());
        vNum2.setValue(exercise.getNum2());
    }

    public void vUpdateUsername(String name){
        user.setUsername(name);
    }

    public Boolean vIsCorrect(String answer){
        return  exercise.checkCorrect(answer);
    }
}
