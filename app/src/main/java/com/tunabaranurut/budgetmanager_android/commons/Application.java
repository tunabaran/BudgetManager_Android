package com.tunabaranurut.budgetmanager_android.commons;

import android.support.multidex.MultiDexApplication;

import com.tunabaranurut.restrequest.GlobalRestConfig;
import com.tunabaranurut.restrequest.RestRequest;

/**
 * Created by tunabaranurut on 26.12.2018.
 */

public class Application extends MultiDexApplication{



    @Override
    public void onCreate() {
        super.onCreate();

        GlobalRestConfig.DEBUG = true;

    }
}
