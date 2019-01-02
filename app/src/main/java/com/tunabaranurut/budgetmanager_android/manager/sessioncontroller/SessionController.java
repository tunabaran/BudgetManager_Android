package com.tunabaranurut.budgetmanager_android.manager.sessioncontroller;

import android.util.Log;

import com.tunabaranurut.budgetmanager_android.commons.Constants;
import com.tunabaranurut.budgetmanager_android.model.LoginRequest;
import com.tunabaranurut.budgetmanager_android.model.LoginResponse;
import com.tunabaranurut.budgetmanager_android.model.RefreshTokenRequest;
import com.tunabaranurut.budgetmanager_android.model.RefreshTokenResponse;
import com.tunabaranurut.budgetmanager_android.model.SimpleUser;
import com.tunabaranurut.budgetmanager_android.model.route.CreateCategoryRequest;
import com.tunabaranurut.budgetmanager_android.model.route.CreateCategoryResponse;
import com.tunabaranurut.budgetmanager_android.network.RequestManager;
import com.tunabaranurut.microdb.base.MicroDB;
import com.tunabaranurut.restrequest.RestRequest;
import com.tunabaranurut.restrequest.callbacks.OnRequestFailedCallback;
import com.tunabaranurut.restrequest.callbacks.OnRequestSuccessCallback;
import com.tunabaranurut.restrequest.request.RequestType;
import com.tunabaranurut.restrequest.response.ApiResponse;

/**
 * Created by tunabaranurut on 27.12.2018.
 */

public class SessionController {

    private static String TAG = SessionController.class.getSimpleName();

    private static SessionController instance;

    public SimpleUser user;
    public String token;

//    public User realUser;


    public void login(LoginRequest loginRequest, final MicroDB microDB, final OnLoginSuccessListener onLoginSuccessListener, final OnLoginFailedListener onLoginFailedListener){
        RestRequest restRequest = new RestRequest(RequestManager.backendUrl + "/LoginController/login");

        restRequest.setOnRequestSuccessCallback(new OnRequestSuccessCallback() {
            @Override
            public void onSuccess(ApiResponse apiResponse) {
                LoginResponse loginResponse = (LoginResponse) apiResponse.getData();

                if(loginResponse.getResponse().getCode().equals("20")){
                    user = loginResponse.getSimpleUser();

                    token = loginResponse.getToken();
                    try {
                        microDB.save(Constants.SESSION_CACHE_KEY,loginResponse.getToken());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    onLoginSuccessListener.onSuccess(loginResponse.getSimpleUser());
                }else{
                    Log.e(TAG, "onSuccess: login failed, code : " + loginResponse.getResponse().getCode());
                    onLoginFailedListener.onFailed();
                }
            }
        });

        restRequest.setOnRequestFailedCallback(new OnRequestFailedCallback() {
            @Override
            public void onFailed() {
                Log.e(TAG, "onFailed: ");
                onLoginFailedListener.onFailed();
            }
        });

        restRequest.exchange(loginRequest, RequestType.POST, LoginResponse.class);
    }

//    public void createCategory(CreateCategoryRequest createCategoryRequest, final MicroDB microDB ,
//                               final CreateCategorySuccessListener createCategorySuccessListener,
//                               final CreateCategoryFailedListener createCategoryFailedListener ){
//
//        RestRequest restRequest = new RestRequest(RequestManager.backendUrl + "/CategoryController/createCategory");
//
//        restRequest.setOnRequestSuccessCallback(new OnRequestSuccessCallback() {
//            @Override
//            public void onSuccess(ApiResponse apiResponse) {
//                CreateCategoryResponse createCategoryResponse = (CreateCategoryResponse) apiResponse.getData();
//
//                if (createCategoryResponse.getResponse().getCode().equals("20")) {
//                    createCategorySuccessListener.createSuccess(createCategoryResponse.getCategory());
//                } else {
//                    Log.e(TAG, "onSuccess: Create Category failed, code : " + createCategoryResponse.getResponse().getCode());
//                    createCategoryFailedListener.createFailed();
//                }
//            }
//        });
//       restRequest.setOnRequestFailedCallback(new OnRequestFailedCallback() {
//           @Override
//           public void onFailed() {
//
//               Log.e(TAG, "onFailed: ");
//               createCategoryFailedListener.createFailed();
//           }
//       });
//    }


    public void logout(MicroDB microDB){
        user = null;
        token = null;

        try {
            microDB.delete(Constants.SESSION_CACHE_KEY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean refresh(final MicroDB microDB,final OnLoginSuccessListener onLoginSuccessListener, final OnLoginFailedListener onLoginFailedListener){

        String token = null;
        try {
            token = microDB.load(Constants.SESSION_CACHE_KEY,String.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(token == null){
            return false;
        }

        final RefreshTokenRequest refreshTokenRequest = new RefreshTokenRequest();
        refreshTokenRequest.setToken(token);

        RestRequest restRequest = new RestRequest(RequestManager.backendUrl + "/LoginController/refreshToken");

        restRequest.setOnRequestSuccessCallback(new OnRequestSuccessCallback() {
            @Override
            public void onSuccess(ApiResponse apiResponse) {
                RefreshTokenResponse refreshTokenResponse = (RefreshTokenResponse) apiResponse.getData();
                if(refreshTokenResponse.getResponse().getCode().equals("20")){
                    user = refreshTokenResponse.getSimpleUser();
                    SessionController.this.token = refreshTokenResponse.getToken();
                    try {
                        microDB.save(Constants.SESSION_CACHE_KEY,refreshTokenResponse.getToken());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    onLoginSuccessListener.onSuccess(refreshTokenResponse.getSimpleUser());
                }else{
                    Log.e(TAG, "onSuccess: refresh request failed, code = " + refreshTokenResponse.getResponse().getCode());
                    onLoginFailedListener.onFailed();
                }
            }
        });

        restRequest.setOnRequestFailedCallback(new OnRequestFailedCallback() {
            @Override
            public void onFailed() {
                Log.e(TAG, "onFailed: Refresh token failed");
                onLoginFailedListener.onFailed();
            }
        });

        restRequest.exchange(refreshTokenRequest,RequestType.POST, RefreshTokenResponse.class);

        return true;
    }

    public boolean isLoggedIn(){
        return user != null;
    }

    public static SessionController getInstance(){
        if(instance == null){
            instance = new SessionController();
        }
        return instance;
    }

    public void createCategory(CreateCategoryRequest createCategoryRequest  ,OnRequestSuccessCallback callback) {
        RestRequest restRequest = new RestRequest(RequestManager.backendUrl + "/CategoryController/createCategory");

        restRequest.setOnRequestSuccessCallback(callback);

        restRequest.exchange(createCategoryRequest,RequestType.POST,CreateCategoryResponse.class);

//        restRequest.setOnRequestSuccessCallback(new OnRequestSuccessCallback() {
//            @Override
//            public void onSuccess(ApiResponse apiResponse) {
//                CreateCategoryResponse createCategoryResponse = (CreateCategoryResponse) apiResponse.getData();
//
//                if (createCategoryResponse.getResponse().getCode().equals("20")) {
//                    createCategorySuccessListener.createSuccess(createCategoryResponse.getCategory());
//                } else {
//                    Log.e(TAG, "onSuccess: Create Category failed, code : " + createCategoryResponse.getResponse().getCode());
////                    createCategoryFailedListener.createFailed();
//                }
//            }
//        });
//        restRequest.setOnRequestFailedCallback(new OnRequestFailedCallback() {
//            @Override
//            public void onFailed() {
//
//                Log.e(TAG, "onFailed: ");
//                createCategoryFailedListener.createFailed();
//            }
//        });
    }

}
