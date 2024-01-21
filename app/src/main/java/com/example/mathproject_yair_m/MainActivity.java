package com.example.mathproject_yair_m;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView numbText1;
    private TextView numbText2;
    private EditText answerText;
    private Button btnTimes20;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        createClickListener();
    }

    private void initViews(){
        numbText1 = findViewById(R.id.numText);
        numbText2 = findViewById(R.id.numText1);
        answerText = findViewById(R.id.answerText);
        btnTimes20 = findViewById(R.id.btnTimes20);
    }

    private void createClickListener(){
        btnTimes20.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view){
             Exercise exercise = new Exercise();
             exercise.setNum1(generateRandom(0,20));
             numbText1.setText(exercise.getNum1()+"");


             exercise.setNum2(generateRandom(0,20));
             numbText2.setText(exercise.getNum2()+"");
         }
        });
    }

    public int generateRandom(int min,int max){
        Random r = new Random();
        int random = r.nextInt(max)+min;
        return random;
    }

}