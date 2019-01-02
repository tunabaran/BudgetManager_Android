package com.tunabaranurut.budgetmanager_android.commons;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import com.tunabaranurut.budgetmanager_android.R;
import com.tunabaranurut.budgetmanager_android.fragments.HomeFragment;
import com.tunabaranurut.budgetmanager_android.fragments.LoginFragment;
import com.tunabaranurut.budgetmanager_android.fragments.ProfileFragment;
import com.tunabaranurut.budgetmanager_android.fragments.SettingsFragment;
import com.tunabaranurut.budgetmanager_android.manager.sessioncontroller.OnLoginFailedListener;
import com.tunabaranurut.budgetmanager_android.manager.sessioncontroller.OnLoginSuccessListener;
import com.tunabaranurut.budgetmanager_android.manager.sessioncontroller.SessionController;
import com.tunabaranurut.budgetmanager_android.model.SimpleUser;
import com.tunabaranurut.fragmentcontroller.FragmentController;
import com.tunabaranurut.fragmentcontroller.PageFragment;
import com.tunabaranurut.microdb.base.MicroDB;

public class MainActivity extends BaseActivity {

    private static String TAG = MainActivity.class.getSimpleName();

    public MicroDB microDB;

    public BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        microDB = new MicroDB(this);

        bottomNavigationView = findViewById(R.id.navBar);
        FragmentController.getInstance().initalize(R.id.main_fragment_container,this);
//        FragmentController.getInstance().setPage(LoginFragment.class);

        initBottomNavBar();
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

    private void initBottomNavBar(){
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int weigth = 0;

                if(FragmentController.getInstance().getCurrentFragment() instanceof ProfileFragment){
                    weigth = 0;
                }else if(FragmentController.getInstance().getCurrentFragment() instanceof SettingsFragment){
                    weigth = 2;
                }else if(FragmentController.getInstance().getCurrentFragment() instanceof HomeFragment){
                    weigth = 1;
                }else{
                    throw new RuntimeException("UnExpected navigation " + FragmentController.getInstance().getCurrentFragment().getClass());
                }

                Class targetFragment = null;
                int targetWeight = 0;
                switch (item.getItemId()){
                    case R.id.profile:
                        targetWeight = 0;
                        targetFragment = ProfileFragment.class;
//                        FragmentController.getInstance().setPage(ProfileFragment.class, FragmentController.AnimationType.LeftToRight);
                        break;

                    case R.id.settings:
                        targetWeight = 2;
                        targetFragment = SettingsFragment.class;
//                        FragmentController.getInstance().setPage(SettingsFragment.class, FragmentController.AnimationType.RightToLeft);
                        break;

                    case R.id.home:
                        targetWeight = 1;
                        targetFragment = HomeFragment.class;
//                        FragmentController.getInstance().setPage(HomeFragment.class,FragmentController.AnimationType.LeftToRight);
                }

                FragmentController.AnimationType animationType = null;
                if(targetWeight > weigth){
                    animationType = FragmentController.AnimationType.RightToLeft;
                }else if(targetWeight < weigth){
                    animationType = FragmentController.AnimationType.LeftToRight;
                }

                FragmentController.getInstance().setPage(targetFragment,animationType);


                return false;
            }
        });

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
