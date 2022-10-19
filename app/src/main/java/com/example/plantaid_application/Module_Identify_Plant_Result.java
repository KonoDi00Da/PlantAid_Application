package com.example.plantaid_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.plantaid_application.Adapter.PlantIdResultsAdapter;
import com.example.plantaid_application.Models.FetchModel;
import com.example.plantaid_application.Models.PlantIdentifyModel;
import com.example.plantaid_application.Models.User_MyGarden_Adapter;
import com.example.plantaid_application.Models.User_Plants;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Module_Identify_Plant_Result extends AppCompatActivity {
    private TextView txtPlantID;
    LoadingDialog loadingDialog;
    ArrayList<PlantIdentifyModel> list;
    private PlantIdResultsAdapter cAdapter;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    //if not plant then edi ende

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_identify_plant_result);
        list = new ArrayList<>();

        loadingDialog = new LoadingDialog(this);

        recyclerView = findViewById(R.id.identifyResults);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        cAdapter = new PlantIdResultsAdapter(Module_Identify_Plant_Result.this, list);
        recyclerView.setAdapter(cAdapter);

        txtPlantID = findViewById(R.id.txtPlantID);

        showResults();


    }


    private void showResults() {
        try {
//            loadingDialog.startLoading("Fetching Results...");
            String imgUrl = getIntent().getStringExtra("serviceUrl");
            loadingDialog = new LoadingDialog(this);

            String afterEncode = URLEncoder.encode(imgUrl, "UTF-8");
            String api_key = "2b10UhsM38YnFCRm7pHO8zK";
            String organ = getIntent().getStringExtra("plantOrgan");
            String JSON_URL = "https://my-api.plantnet.org/v2/identify/all?api-key="+ api_key +"&images="+ afterEncode +"&organs="+ organ+"&include-related-images=true";
            //txtPlantID.setText(afterEncode);

            RequestQueue requestQueue = Volley.newRequestQueue(Module_Identify_Plant_Result.this);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
//                    Log.d("response", "Response Check :" + response);
//                    loadingDialog.stopLoading();
                    try {
                        JSONArray results = response.getJSONArray("results");
                        for(int i = 0; i < results.length(); i++){
                            PlantIdentifyModel model = new PlantIdentifyModel();
                            JSONObject resultObject = results.getJSONObject(i);
                            String score = String.valueOf(resultObject.getDouble("score"));

                            // scientific name of plant
                            JSONObject species = resultObject.getJSONObject("species");
                            JSONObject genus = species.getJSONObject("genus");
                            String scientificName = genus.getString("scientificName");

                            // family of plant
                            JSONObject familyObj = species.getJSONObject("family");
                            String family = familyObj.getString("scientificName");

//                            // common names of plant
                            JSONArray commonName = species.getJSONArray("commonNames");

                            List<String> commonNameList = new ArrayList<String>();
                            for (int j = 0; j < commonName.length(); j++) {
                                commonNameList.add(commonName.getString(j));
                            }

                            int size = commonNameList.size();
                            String[] stringArray
                                    = commonNameList.toArray(new String[size]);
                            model.setComName(TextUtils.join(", ",stringArray));



                            // images of plants
                            JSONArray images = resultObject.getJSONArray("images");

                            List<String> plantImages = new ArrayList<String>();
                            for (int j = 0; j < images.length(); j++) {
                                JSONObject imagesObj = images.getJSONObject(j);
                                JSONObject imgURL = imagesObj.getJSONObject("url");
                                plantImages.add(imgURL.getString("s"));
                            }

                            int len = plantImages.size();
                            String[] stringImages
                                    = plantImages.toArray(new String[size]);

//                            txtPlantID.setText(String.valueOf(len));

                            if (len >= 1){
                                model.setImg1(stringImages[0]);
                                if(len >= 2 ){
                                    model.setImg2(stringImages[1]);
                                    if(len >= 3){
                                        model.setImg3(stringImages[2]);
                                        if(len >= 4){
                                            model.setImg4(stringImages[3]);
                                            if(len >= 5){
                                                model.setImg5(stringImages[4]);
                                            }
                                        }
                                    }
                                }
                            }

                            model.setSciName(scientificName);

                            model.setFamily(family);
                            model.setScore(score);
                            list.add(model);

                            cAdapter.notifyDataSetChanged();
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
//                    loadingDialog.stopLoading();
                    if(error.getMessage() != null){
                        toast("Failed to get data...");
                    }
                }
            });

            requestQueue.add(jsonObjectRequest);

        } catch (Exception e){
            Log.e("Shop", "exception", e);
        }
    }

    private void toast(String message){
        Toast.makeText(Module_Identify_Plant_Result.this, message, Toast.LENGTH_SHORT).show();
    }



}