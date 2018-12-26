package com.tunabaranurut.budgetmanager_android.commons;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.tunabaranurut.budgetmanager_android.R;
import com.tunabaranurut.budgetmanager_android.model.LoginRequest;
import com.tunabaranurut.budgetmanager_android.model.LoginResponse;
import com.tunabaranurut.budgetmanager_android.network.RequestManager;
import com.tunabaranurut.budgetmanager_android.view.LoginFragment;
import com.tunabaranurut.fragmentcontroller.FragmentController;
import com.tunabaranurut.restrequest.callbacks.OnRequestSuccessCallback;
import com.tunabaranurut.restrequest.response.ApiResponse;

public class MainActivity extends BaseActivity {

    private static String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentController.getInstance().initalize(R.id.main_fragment_container,this);
        FragmentController.getInstance().setPage(LoginFragment.class);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setId("1");
        loginRequest.setPassword("123456");

        RequestManager.getInstance().login(loginRequest, new OnRequestSuccessCallback() {
            @Override
            public void onSuccess(ApiResponse apiResponse) {
                LoginResponse loginResponse = (LoginResponse) apiResponse.getData();
                Toast.makeText(MainActivity.this,loginResponse.getUser().getBasicInfo().getName(),Toast.LENGTH_LONG).show();
                Log.d(TAG, "onSuccess: ");
            }
        });

    }
}
