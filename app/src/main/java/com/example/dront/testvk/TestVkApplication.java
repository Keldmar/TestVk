package com.example.dront.testvk;

import android.app.Application;

import com.vk.sdk.VKSdk;

public class TestVkApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        VKSdk.initialize(this);
    }
}
