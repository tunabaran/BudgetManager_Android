package com.tunabaranurut.budgetmanager_android.network;

import com.tunabaranurut.budgetmanager_android.model.LoginRequest;
import com.tunabaranurut.budgetmanager_android.model.LoginResponse;
import com.tunabaranurut.restrequest.RestRequest;
import com.tunabaranurut.restrequest.callbacks.OnRequestSuccessCallback;
import com.tunabaranurut.restrequest.request.RequestType;

/**
 * Created by tunabaranurut on 26.12.2018.
 */

public class RequestManager {

    private static RequestManager instance;

//    public static String backendUrl = "http://10.0.2.2:8585";
    public static String backendUrl = "http://192.168.1.103:8585";

    private RestRequest loginRequest;

    public void login(LoginRequest request, OnRequestSuccessCallback callback){
        if(loginRequest == null){
            loginRequest = new RestRequest(backendUrl + "/LoginController/login");
        }

        loginRequest.setOnRequestSuccessCallback(callback);

        loginRequest.exchange(request, RequestType.POST, LoginResponse.class);
    }

    public static RequestManager getInstance(){
        if(instance == null){
            instance = new RequestManager();
        }
        return instance;
    }

    private RequestManager() {
    }
}
