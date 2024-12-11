package com.example.mathproject_yair_m;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;



public class MainActivity extends AppCompatActivity {

    private TextView numbText1;
    private TextView numbText2;
    private EditText answerText;
    private Button btnTimes20;
    private Button btnTimes;
    private Button btnChallenge;
    private Button checkBtn;
    private Button rateButton;
    private Button showUsers_btn;
    private MainViewModel mainViewModel;
    private FragmentTransaction trans;


    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    int rate = result.getData().getIntExtra("rate", -1);
                    mainViewModel.user.setRate(rate);
                    createToast(rate+"");
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        trans = getSupportFragmentManager().beginTransaction();


        createClickListener();
        Intent intent = getIntent();

        String username = intent.getStringExtra("username");
        mainViewModel.vUpdateUsername(username);



        createToast(username+"");
        setTitle(username);
    }

    private void initViews(){
        numbText1 = findViewById(R.id.numText);
        numbText2 = findViewById(R.id.numText1);
        answerText = findViewById(R.id.answerText);
        btnTimes20 = findViewById(R.id.btnTimes20);
        btnTimes = findViewById(R.id.btnTimes);
        checkBtn = findViewById(R.id.checkBtn);
        rateButton= findViewById(R.id.rateBtn);
        showUsers_btn=findViewById(R.id.showUsers_btn);
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

        rateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,RateActivity.class);
                activityResultLauncher.launch(intent);
            }
        });

        showUsers_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ShowAllUsers.class);
                startActivity(intent);
            }
        });

        mainViewModel.vNum1.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(
                    @Nullable Integer num1) {
                numbText1.setText(num1+"");
            }
        });

        mainViewModel.vNum2.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer num2) {
                numbText2.setText(num2+"");
            }
        });
    }

    public void createToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

}