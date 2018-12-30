package com.tunabaranurut.budgetmanager_android.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.tunabaranurut.budgetmanager_android.R;
import com.tunabaranurut.budgetmanager_android.commons.MainActivity;
import com.tunabaranurut.budgetmanager_android.manager.sessioncontroller.CreateCategorySuccessListener;
import com.tunabaranurut.budgetmanager_android.manager.sessioncontroller.SessionController;
import com.tunabaranurut.budgetmanager_android.model.Category;
import com.tunabaranurut.budgetmanager_android.model.route.CreateCategoryRequest;
import com.tunabaranurut.budgetmanager_android.view.compound.shared.TitleBar;
import com.tunabaranurut.fragmentcontroller.FragmentController;
import com.tunabaranurut.fragmentcontroller.PageFragment;

/**
 * Created by tunabaranurut on 26.12.2018.
 */

public class HomeFragment extends PageFragment<MainActivity> {

    private Button btnCreateCategory;
    private TitleBar titleBar;
    private TextView testTv,textViewCategory;
    private BottomNavigationView navBar;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("ResourceType")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home_layout, container, false);

        btnCreateCategory = v.findViewById(R.id.btnCreateCategory);
        titleBar = v.findViewById(R.id.titlebar);
        navBar = v.findViewById(R.id.navBar);
        textViewCategory = v.findViewById(R.id.textViewCategory);

        navBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.profile:
                        FragmentController.getInstance().setPage(ProfileFragment.class, FragmentController.AnimationType.LeftToRight);
                        break;

                    case R.id.settings:
                        FragmentController.getInstance().setPage(SettingsFragment.class, FragmentController.AnimationType.RightToLeft);
                        break;
                }
                return false;
            }
        });


        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setOnClicklisteners();

        onEnterPage();
    }

    @Override
    public void onEnterPage() {

    }

    @Override
    public void onLeavePage() {

    }

    private void setOnClicklisteners(){
        titleBar.setBackBtnOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnCreateCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String categoryName = textViewCategory.getText().toString();

                CreateCategoryRequest createCategoryRequest = new CreateCategoryRequest();
                createCategoryRequest.setUserId(SessionController.getInstance().realUser.getId());
                createCategoryRequest.setName(categoryName);

                SessionController.getInstance().createCategory(createCategoryRequest, getMainActivity().microDB, new CreateCategorySuccessListener() {
                    @Override
                    public void createSuccess(Category category) {

                    }
                });

//                SessionController.getInstance().login(loginRequest, getMainActivity().microDB, new OnLoginSuccessListener() {
//                    @Override
//                    public void onSuccess(SimpleUser simpleUser) {
//                        FragmentController.getInstance().setPage(HomeFragment.class, FragmentController.AnimationType.RightToLeft);
//                    }
//                }, new OnLoginFailedListener() {
//                    @Override
//                    public void onFailed() {
//                        Toast.makeText(getContext(),"Wrong id or Password",Toast.LENGTH_SHORT).show();
//                    }
//                });




            }
        });
    }
}
