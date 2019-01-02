package com.tunabaranurut.budgetmanager_android.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tunabaranurut.budgetmanager_android.R;
import com.tunabaranurut.budgetmanager_android.commons.MainActivity;
import com.tunabaranurut.fragmentcontroller.FragmentController;
import com.tunabaranurut.fragmentcontroller.PageFragment;

public class SettingsFragment extends PageFragment<MainActivity> {

    private TextView textViewSettings;

    @Override
    public void onEnterPage() {

    }

    @Override
    public void onLeavePage() {

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater , @Nullable ViewGroup container, @Nullable Bundle saveInstanceState){
        View v = inflater.inflate(R.layout.fragment_settings_layout,container,false);

        textViewSettings =v.findViewById(R.id.textViewSettings);

      /*  navBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.home:
                        FragmentController.getInstance().setPage(HomeFragment.class, FragmentController.AnimationType.LeftToRight);
                        break;
                    case R.id.profile:
                        FragmentController.getInstance().setPage(ProfileFragment.class, FragmentController.AnimationType.LeftToRight);
                        break;
                }

                return false;
            }
        });
*/
        return v;
    }
}