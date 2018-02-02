package com.example.dront.testvk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dront.testvk.adapter.RecyclerViewAdapterUserVk;
import com.example.dront.testvk.response.City;
import com.example.dront.testvk.response.MainResponseVk;
import com.example.dront.testvk.response.Response;
import com.google.gson.Gson;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.util.VKUtil;

public class MainActivity extends AppCompatActivity {

    private static String[] sMyScope = new String[]{VKScope.FRIENDS, VKScope.WALL, VKScope.PHOTOS, VKScope.NOHTTPS};
    private ImageView imagePhoto;
    private TextView firstAndLastName;
    private RecyclerViewAdapterUserVk recyclerViewAdapterUserVk = new RecyclerViewAdapterUserVk();
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstAndLastName = findViewById(R.id.first_and_last_name);
        String[] fingerprints = VKUtil.getCertificateFingerprint(this, this.getPackageName());
        recyclerView = findViewById(R.id.rv);
        VKSdk.login(MainActivity.this, sMyScope);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                final VKRequest request = VKApi.users().get();
                VKRequest request2 = new VKRequest("friends.get", VKParameters.from(VKApiConst.FIELDS, "sex,bdate,city,photo"));
                request2.executeWithListener(new VKRequest.VKRequestListener() {
                    @Override
                    public void onComplete(VKResponse response) {
                        super.onComplete(response);
                        MainResponseVk mainResponseVk = new Gson().fromJson(response.responseString, MainResponseVk.class);
                        mainResponseVk.response.items.size();
                        recyclerViewAdapterUserVk.addView(mainResponseVk);
                        recyclerView.setAdapter(recyclerViewAdapterUserVk);

                    }
                });
            }

            @Override
            public void onError(VKError error) {

            }
        }))
            super.onActivityResult(requestCode, resultCode, data);
    }

}
