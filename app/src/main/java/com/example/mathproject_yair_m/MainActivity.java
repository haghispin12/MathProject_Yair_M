package com.example.mathproject_yair_m;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView numbText1;
    private TextView numbText2;
    private EditText answerText;
    private Button btnTimes20;
    private Button btnTimes;
    private Button btnChallenge;
    private Button checkBtn;
    private Exercise exercise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        exercise = new Exercise();

        initViews();
        createClickListener();
    }

    private void initViews(){
        numbText1 = findViewById(R.id.numText);
        numbText2 = findViewById(R.id.numText1);
        answerText = findViewById(R.id.answerText);
        btnTimes20 = findViewById(R.id.btnTimes20);
        btnTimes = findViewById(R.id.btnTimes);
        checkBtn = findViewById(R.id.checkBtn);
        btnChallenge = findViewById(R.id.btnChallenge);
    }

    private void createClickListener(){
        btnTimes20.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view){
             exercise.setNum1(exercise.generateRandom(0,20));
             exercise.setNum2(exercise.generateRandom(0,20));

             updateViews();
         }
        });

        btnTimes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                exercise.setNum1(exercise.generateRandom(0,10));
                exercise.setNum2(exercise.generateRandom(0,10));

                updateViews();
            }
        });

        checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                Boolean isCorrect = exercise.checkCorrect(answerText.getText().toString());
               if(isCorrect){
                   createToast("true");
               }else{
                   createToast("false");
               }
            }
        });
    }


    public void updateViews(){
        numbText1.setText(exercise.getNum1()+"");
        numbText2.setText(exercise.getNum2()+"");

    }

    public void createToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

}