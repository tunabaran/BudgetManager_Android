package com.tunabaranurut.budgetmanager_android.commons;

import android.os.Bundle;

import com.tunabaranurut.budgetmanager_android.R;
import com.tunabaranurut.budgetmanager_android.fragments.HomeFragment;
import com.tunabaranurut.budgetmanager_android.fragments.LoginFragment;
import com.tunabaranurut.budgetmanager_android.manager.sessioncontroller.OnLoginFailedListener;
import com.tunabaranurut.budgetmanager_android.manager.sessioncontroller.OnLoginSuccessListener;
import com.tunabaranurut.budgetmanager_android.manager.sessioncontroller.SessionController;
import com.tunabaranurut.budgetmanager_android.model.SimpleUser;
import com.tunabaranurut.fragmentcontroller.FragmentController;
import com.tunabaranurut.microdb.base.MicroDB;

public class MainActivity extends BaseActivity {

    private static String TAG = MainActivity.class.getSimpleName();

    public MicroDB microDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        microDB = new MicroDB(this);

        FragmentController.getInstance().initalize(R.id.main_fragment_container,this);
//        FragmentController.getInstance().setPage(LoginFragment.class);

        checkSession();
        //     LoginRequest loginRequest = new LoginRequest();


//        RequestManager.getInstance().login(loginRequest, new OnRequestSuccessCallback() {
//            @Override
//            public void onSuccess(ApiResponse apiResponse) {
//                LoginResponse loginResponse = (LoginResponse) apiResponse.getData();
//                Toast.makeText(MainActivity.this,loginResponse.getUser().getBasicInfo().getName(),Toast.LENGTH_LONG).show();
//                Log.d(TAG, "onSuccess: ");
//            }
//        });

    }

    private void checkSession(){
        boolean isTokenAvailable = SessionController.getInstance().refresh(microDB, new OnLoginSuccessListener() {
            @Override
            public void onSuccess(SimpleUser simpleUser) {
                FragmentController.getInstance().setPage(HomeFragment.class);
            }
        }, new OnLoginFailedListener() {
            @Override
            public void onFailed() {
                FragmentController.getInstance().setPage(LoginFragment.class);
            }
        });

        if(!isTokenAvailable){
            FragmentController.getInstance().setPage(LoginFragment.class);
        }
    }
}
