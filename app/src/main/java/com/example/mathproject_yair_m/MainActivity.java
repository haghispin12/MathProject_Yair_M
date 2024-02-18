package com.example.mathproject_yair_m;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.support.annotation.Nullable;
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
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        mainViewModel = new ViewModelProvider(this,new ViewModelProvider.NewInstanceFactory()).get(MainViewModel.class);

        createClickListener();
        Intent intent = new Intent();
        String username = intent.getStringExtra("username");

        createToast(username);
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
           mainViewModel.vTimes20();
         }
        });

        btnTimes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                mainViewModel.vTimes10();
            }
        });

        btnChallenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                mainViewModel.vChallenge();
            }
        });

        checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Boolean isCorrect = mainViewModel.vIsCorrect(answerText.getText().toString());
               if(isCorrect){
                   createToast("true");
               }else{
                   createToast("false");
               }
            }
        });

//        mainViewModel.vNum1.observe(this, new Observer<Integer>() {
//            @Override
//            public void onChanged(
//                    @Nullable Integer num1) {
//                numbText1.setText(num1+"");
//            }
//        });
//
//        mainViewModel.vNum2.observe(this, new Observer<Integer>() {
//            @Override
//            public void onChanged(@Nullable Integer num2) {
//                numbText2.setText(num2+"");
//            }
//        });
    }

    public void createToast(String msg){

        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

}