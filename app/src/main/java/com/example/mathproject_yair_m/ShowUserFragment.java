package com.example.mathproject_yair_m;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class ShowUserFragment extends Fragment {

    MainViewModel mainViewModel;
    TextView usernameText;
    TextView ratingText;
    Button addPicBtn;
    ImageView img;
    Uri uri;

    ActivityResultLauncher<Intent> startCamera = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result){
                    if(result.getResultCode() == Activity.RESULT_OK) {
                        img.setImageURI(uri);
                    }
                }
            }

    );

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_showusers, container, false);
        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        initView(view);
        insertValues(mainViewModel);
        createClickListener();

        return  view;
    }

    private void initView(View view){
        usernameText= view.findViewById(R.id.usernameText);
        ratingText= view.findViewById(R.id.ratingText);
        addPicBtn=view.findViewById(R.id.addPicBtn);
        img=view.findViewById(R.id.img);
    }

    private void  insertValues(MainViewModel mainViewModel){
        usernameText.append(mainViewModel.user.getUsername());
        ratingText.append(mainViewModel.user.getRate()+"");
    }

    private void createClickListener(){
        addPicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.TITLE,"User Image");
                values.put(MediaStore.Images.Media.DESCRIPTION,"");
                uri = requireContext().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);
                Intent camIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                camIntent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
                startCamera.launch(camIntent);
            }
        });
    }
}