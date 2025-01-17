package com.example.mathproject_yair_m;

import static android.widget.Toast.*;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mathproject_yair_m.modals.User;

import java.util.ArrayList;


public class ShowUserFragment extends Fragment implements MenuProvider {

    MainViewModel mainViewModel;
    EditText usernameText;
    TextView ratingText;
    TextView scoreText;
    Button addPicBtn;
    ImageView img;
    Uri uri;
    Button addUserBtn;
    RecyclerView rcShowUsers;
    MenuItem itemDelete;
    MenuItem itemEdit;


    ActivityResultLauncher<Intent> startCamera = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result){
                    if(result.getResultCode() == Activity.RESULT_OK) {
                        img.setImageURI(uri);
                        mainViewModel.vUpdateUri(uri);
                    }
                }
            }

    );

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
         menuInflater.inflate(R.menu.menu,menu);
        itemDelete = menu.findItem(R.id.action_delete);
        itemEdit = menu.findItem(R.id.action_edit);

        setMenuItems(false);
    }

    @Override
    public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        if(id == R.id.action_edit) {
            mainViewModel.currentUser.setUsername(usernameText.getText()+"");
           mainViewModel.dbUpdate(getActivity());
            setMenuItems(false);
           return true;
        }else if(id == R.id.action_delete){
            openDialog();
            setMenuItems(false);
            return true;
        }
       return true;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_showusers, container, false);
        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        rcShowUsers = view.findViewById(R.id.rcShowAllUsers);
        initView(view);
        insertValues(mainViewModel);
        createClickListener();

        mainViewModel.users.observe(requireActivity(), new Observer<ArrayList<User>>() {
            @Override
            public void onChanged(ArrayList<User> users) {
                UserAdapter fa = new UserAdapter(users, new UserAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(User item) {
                        mainViewModel.currentUser = item;
                        insertValues(mainViewModel);
                        img.setImageBitmap(item.getBitmap());
                        setMenuItems(true);
                    }

                });
                rcShowUsers.setLayoutManager(new LinearLayoutManager(requireActivity()));
                rcShowUsers.setAdapter(fa);
                rcShowUsers.setHasFixedSize(true);
            }
        });
        mainViewModel.dbSelectAll(getActivity());

        requireActivity().addMenuProvider(this);

        return  view;
    }


    private void initView(View view){
        itemDelete = view.findViewById(R.id.action_delete);
        itemEdit = view.findViewById(R.id.action_edit);
        usernameText= view.findViewById(R.id.usernameText);
        ratingText= view.findViewById(R.id.ratingText);
        addPicBtn=view.findViewById(R.id.addPicBtn);
        img=view.findViewById(R.id.img);
        scoreText=view.findViewById(R.id.scoreText);
        addUserBtn=view.findViewById(R.id.addUserBtn);
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
        addUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long id = mainViewModel.dbAddUser(getActivity());
                if(id==-1){
                    makeText(getActivity(),"user exsit",LENGTH_SHORT).show();
                }else {
                    makeText(getActivity(), "id " + id, LENGTH_SHORT).show();
                }
            }
        });
    }
    public void openDialog(){
        AlertDialog.Builder alertDialog =new AlertDialog.Builder(requireActivity());
        alertDialog.setTitle("Dialog");
        alertDialog.setMessage("do you want to delete");

        alertDialog.setCancelable(false);
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mainViewModel.dbDeleteByID(requireActivity());
                usernameText.setText("user:");
                ratingText.setText("rate:");
                img.setImageBitmap(null);
                scoreText.setText("score:");
                dialogInterface.dismiss();
            }
        });
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alertDialog.show();
    }
    public void setMenuItems(Boolean option){
        itemDelete.setVisible(option);
        itemEdit.setVisible(option);
    }

    @SuppressLint("SetTextI18n")
    private void  insertValues(MainViewModel mainViewModel){
        if(mainViewModel.getCurrentUser() != null){
            usernameText.setText(mainViewModel.currentUser.getUsername());
            ratingText.setText("rate" + " " +mainViewModel.currentUser.getRate()+"");
            scoreText.setText("score" + " " +mainViewModel.currentUser.getScore()+"");
        }else {
            usernameText.append(mainViewModel.user.getUsername());
            ratingText.append(mainViewModel.user.getRate() + "");
            scoreText.append(mainViewModel.user.getScore() + "");
        }
    }


}