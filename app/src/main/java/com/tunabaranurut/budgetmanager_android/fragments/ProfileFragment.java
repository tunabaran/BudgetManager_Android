package com.tunabaranurut.budgetmanager_android.fragments;

import android.content.ClipData;
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
import com.tunabaranurut.budgetmanager_android.manager.sessioncontroller.SessionController;
import com.tunabaranurut.fragmentcontroller.FragmentController;
import com.tunabaranurut.fragmentcontroller.PageFragment;


public class ProfileFragment extends PageFragment<MainActivity> {

    private TextView textViewProfile;
    @Override
    public void onEnterPage() {
        textViewProfile.setText(String.format("%s %s %s", "Name : " + SessionController.getInstance().user.getBasicInfo().getName(),
                "\nLast Name : " + SessionController.getInstance().user.getBasicInfo().getLastname(),
                "\nCountry : " + SessionController.getInstance().user.getBasicInfo().getCountry()));

    }

    @Override
    public void onLeavePage() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater , @Nullable ViewGroup container, @Nullable Bundle saveInstanceState){
        View v = inflater.inflate(R.layout.fragment_profile_layout,container,false);


        textViewProfile = v.findViewById(R.id.textViewProfile);



    /*    navBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.home:
                        FragmentController.getInstance().setPage(HomeFragment.class, FragmentController.AnimationType.RightToLeft);
                        break;
                    case R.id.settings:
                        FragmentController.getInstance().setPage(SettingsFragment.class, FragmentController.AnimationType.RightToLeft);
                        break;

                }
                return false;
            }
        });*/

//        User user = new User();
//        MicroDB microDB = new MicroDB(getContext());
//
//        String name = user.getBasicInfo().getName();
//
//        textViewProfile.setText(name);

        return v;
    }
}
