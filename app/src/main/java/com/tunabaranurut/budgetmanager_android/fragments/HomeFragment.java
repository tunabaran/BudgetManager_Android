package com.tunabaranurut.budgetmanager_android.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.tunabaranurut.budgetmanager_android.R;
import com.tunabaranurut.budgetmanager_android.commons.MainActivity;
import com.tunabaranurut.budgetmanager_android.manager.sessioncontroller.SessionController;
import com.tunabaranurut.budgetmanager_android.view.compound.shared.TitleBar;
import com.tunabaranurut.fragmentcontroller.FragmentController;
import com.tunabaranurut.fragmentcontroller.PageFragment;

/**
 * Created by tunabaranurut on 26.12.2018.
 */

public class HomeFragment extends PageFragment<MainActivity> {

    private Button btn;
    private TitleBar titleBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home_layout, container, false);

        btn = v.findViewById(R.id.btn);
        titleBar = v.findViewById(R.id.titlebar);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setOnClicklisteners();
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

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SessionController.getInstance().logout(getMainActivity().microDB);
                FragmentController.getInstance().setPage(LoginFragment.class, FragmentController.AnimationType.RightToLeft);
            }
        });
    }
}
