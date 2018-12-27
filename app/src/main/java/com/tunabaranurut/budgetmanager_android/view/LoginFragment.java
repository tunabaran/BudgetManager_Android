package com.tunabaranurut.budgetmanager_android.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tunabaranurut.budgetmanager_android.R;
import com.tunabaranurut.budgetmanager_android.commons.MainActivity;
import com.tunabaranurut.budgetmanager_android.model.LoginRequest;
import com.tunabaranurut.budgetmanager_android.model.LoginResponse;
import com.tunabaranurut.budgetmanager_android.network.RequestManager;
import com.tunabaranurut.fragmentcontroller.FragmentController;
import com.tunabaranurut.fragmentcontroller.PageFragment;
import com.tunabaranurut.restrequest.callbacks.OnRequestSuccessCallback;
import com.tunabaranurut.restrequest.response.ApiResponse;

/**
 * Created by tunabaranurut on 26.12.2018.
 */

public class LoginFragment extends PageFragment<MainActivity> {

    private Button btn;
    private EditText editTextId,editTextPassword;
    private TextView textViewUserInfo;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login_layout, container, false);

        editTextId=v.findViewById(R.id.editTextId);
        editTextPassword=v.findViewById(R.id.editTextPassword);
        btn = v.findViewById(R.id.btn);
        textViewUserInfo=v.findViewById(R.id.textViewUserInfo);


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
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id       = editTextId.getText().toString();
                String password = editTextPassword.getText().toString();

                LoginRequest loginRequest = new LoginRequest();
                loginRequest.setId(id);
                loginRequest.setPassword(password);

                RequestManager.getInstance().login(loginRequest, new OnRequestSuccessCallback() {
                    @Override
                    public void onSuccess(ApiResponse apiResponse) {
                        LoginResponse loginResponse = (LoginResponse) apiResponse.getData();
                        String nameInfo = null;
                        try {
                            nameInfo = loginResponse.getUser().getBasicInfo().getName();
                        } catch (Exception e) {
                            Toast.makeText(getContext(),"Wrong id or Password",Toast.LENGTH_SHORT).show();
                        }
                        boolean Success= (nameInfo!=null);
                       if (Success){
                           textViewUserInfo.setText(nameInfo);
                           FragmentController.getInstance().setPage(HomeFragment.class, FragmentController.AnimationType.RightToLeft);
                       }
     //                   Toast.makeText(MainActivity.this,loginResponse.getUser().getBasicInfo().getName(),Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}
