package com.example.mathproject_yair_m;


import android.content.Context;
import android.net.Uri;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mathproject_yair_m.DB.DBHelper;
import com.example.mathproject_yair_m.modals.Exercise;
import com.example.mathproject_yair_m.modals.User;

import java.util.ArrayList;

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

    public void vUpdateScore(int type){
        if(type==0){
            user.setScore(10);
        }
        else if(type==1){
            user.setScore(15);
        }
        else{
            user.setScore(20);
        }
    }

    public void vUpdateUsername(String name){
        user.setUsername(name);
    }
public void vUpdateUri(Uri uri){user.setUri(uri);}
    public Boolean vIsCorrect(String answer){
        return  exercise.checkCorrect(answer);
    }

    public long dbAddUser(Context context){
        DBHelper db = new DBHelper(context);
        Log.d("user",user.getUri()+"");
        long id = db.insert(user,context);
        Log.d("yair1",id+"");
        return id;
    }


}
