package com.example.mathproject_yair_m;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mathproject_yair_m.modals.Fruit;

import java.util.ArrayList;

public class ShowAllUsers extends AppCompatActivity {
    private RecyclerView rcShowUsers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_users);

        rcShowUsers = findViewById(R.id.rcShowUsers);
        ArrayList<Fruit> fruits = new ArrayList<Fruit>();
        fruits.add(new Fruit("orange",R.drawable.img));
        fruits.add(new Fruit("fdsfds",R.drawable.img_1));
        fruits.add(new Fruit("sdfdsdsfds",R.drawable.img_2));
        fruits.add(new Fruit("sdfdsfds",R.drawable.img_3));
        fruits.add(new Fruit("12312",R.drawable.img_3));
        fruits.add(new Fruit("sdfdsfds",R.drawable.img_3));
        fruits.add(new Fruit("sdfdsfds",R.drawable.img_3));

        FruitAdapter fa = new FruitAdapter(fruits, new FruitAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Fruit item) {
                android.widget.Toast.makeText(ShowAllUsers.this, item.getName(), Toast.LENGTH_SHORT).show();

            }
        });
        rcShowUsers.setLayoutManager(new LinearLayoutManager(this));
        rcShowUsers.setAdapter(fa);
        rcShowUsers.setHasFixedSize(true);
    }
}