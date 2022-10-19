package com.example.plantaid_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;


import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Module_Identify_Plant_Result extends AppCompatActivity {
    private TextView txtPlantID;
    LoadingDialog loadingDialog;

    //if not plant then edi ende

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_identify_plant_result);
        OkHttpClient client = new OkHttpClient();
        String imgUrl = getIntent().getStringExtra("serviceUrl");
        loadingDialog = new LoadingDialog(this);

        //PlantIdentifyModel model = new PlantIdentifyModel();
        //String imgPath = getIntent().getStringExtra("userPic");
//        String encodedQueryString = URLEncoder.encode(queryString,"UTF-8");
//        String url = URLEncoder.encode(imgUrl, StandardCharsets.UTF_8);

        txtPlantID = findViewById(R.id.txtPlantID);
        try {
            //https://my-api.plantnet.org/v2/identify/all?api-key=2b10UhsM38YnFCRm7pHO8zK&images=imgPath&organs=fruit&include-related-images=true
            String afterEncode = URLEncoder.encode(imgUrl, "UTF-8");
            String api_key = "2b10UhsM38YnFCRm7pHO8zK";
            String organ = getIntent().getStringExtra("plantOrgan");
            String serviceUrl = "https://my-api.plantnet.org/v2/identify/all?api-key="+ api_key +"&images="+ afterEncode +"&organs="+ organ+"&include-related-images=true";
            //txtPlantID.setText(afterEncode);

            Request request = new Request.Builder()
                    .url(serviceUrl)
                    .build();
            loadingDialog.startLoading("Fetching data...");

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    if (response.isSuccessful()){
                        String myResponse = response.body().string();

                        Module_Identify_Plant_Result.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                loadingDialog.stopLoading();
                                txtPlantID.setText(myResponse);
                            }
                        });
                    }
                }
            });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }




    }
}