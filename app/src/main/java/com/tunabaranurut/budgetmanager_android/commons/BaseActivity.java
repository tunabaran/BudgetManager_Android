package com.tunabaranurut.budgetmanager_android.commons;

import android.support.v7.app.AppCompatActivity;

import com.tunabaranurut.fragmentcontroller.FragmentController;

/**
 * Created by tunabaranurut on 26.12.2018.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        FragmentController.getInstance().navigateBackFromStack();
    }
}
