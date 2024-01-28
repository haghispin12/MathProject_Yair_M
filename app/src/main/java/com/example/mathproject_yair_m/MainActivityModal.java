package com.example.mathproject_yair_m;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

public class MainActivityModal extends ViewModel {

    MainViewModal viewModalMain = new ViewModelProvider(this).get(MainViewModal.class);
    viewModalMain.vmNum1.observe(this, new Observer<Integer>{
        @Override
                public void onChn
    });

}
