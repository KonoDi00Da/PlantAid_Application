package com.example.plantaid_application.MyGarden;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.webkit.WebViewAssetLoader;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.example.plantaid_application.Models.PlantListModel;
import com.example.plantaid_application.R;
import com.example.plantaid_application.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PlantInfoFragment extends Fragment {
    public String key;
    TextView commonNameTextView, sciNameTextView, descriptionTextView, txtWater, txtHarvest, txtCare, txtPestsDiseases;
    WebView webView;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    FirebaseDatabase database;
    //for Youtube View Embedding

    private String VideoEmbededAdress;
    private final String mimeType = "text/html";
    private final String encoding = "UTF-8";//"base64";

    // TODO: Rename and change types of parameters
    private String userKey;
    private String plantKey;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_plant_info, container, false);
        Module_MyGarden_Details activity = (Module_MyGarden_Details) getActivity();
        plantKey = activity.getPlantKey();
        userKey = activity.getUserKey();
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        try {

            commonNameTextView = view.findViewById(R.id.txtCom_plant);
            sciNameTextView = view.findViewById(R.id.txtSci_plant);
            descriptionTextView = view.findViewById(R.id.plant_desc_);
            txtWater = view.findViewById(R.id.txtWater_);
            txtHarvest = view.findViewById(R.id.txtHarvest_);
            txtCare = view.findViewById(R.id.txtCare_);
            txtPestsDiseases = view.findViewById(R.id.txtPestsDisease_);
            webView = view.findViewById(R.id.ytLink_);

            //Initialize firebase
            mAuth = FirebaseAuth.getInstance();
            currentUser = mAuth.getCurrentUser();
            database = FirebaseDatabase.getInstance();

            DatabaseReference reference = database.getReference("Plants").child(plantKey);
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    PlantListModel model = snapshot.getValue(PlantListModel.class);
                    if (model != null){
                        commonNameTextView.setText(model.getCommonName());
                        sciNameTextView.setText(model.getSciName());
                        descriptionTextView.setText(model.getDescription());
                        txtWater.setText(model.getWater());
                        txtHarvest.setText(model.getHarvest());
                        txtCare.setText(model.getCare());
                        txtPestsDiseases.setText(model.getPestsDiseases());
                        youtubeView(model.getYtLink());

                    }
                    else{
//                        userGreeting.setText("Hi there!");
                    }
                }

                @Override
                   public void onCancelled(@NonNull DatabaseError error) {

                }
            });




        } catch (Exception e) {
            Log.v("TEST", "Error: " + e.toString());
        }
    }
    private void youtubeView(String s){
        WebViewAssetLoader assetLoader = new WebViewAssetLoader.Builder()
                .addPathHandler("/assets/", new WebViewAssetLoader.AssetsPathHandler(getActivity()))
                .build();

        String ytEmbedKey = s;
        VideoEmbededAdress = "<iframe width=\"350\" height=\"225\" src=\"https://www.youtube.com/embed/"+ ytEmbedKey +"\" title=\"YouTube video player\"allow=\"autoplay;\" allowfullscreen></iframe>";

        webView.setWebViewClient(new WebViewClient() {
            private WebView view;
            private WebResourceRequest request;

            public WebResourceResponse shouldInterceptRequest(WebView view,
                                                              WebResourceRequest request) {
                this.view = view;
                this.request = request;
                return assetLoader.shouldInterceptRequest(request.getUrl());
            }
        });

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAllowContentAccess(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setMediaPlaybackRequiresUserGesture(false);
//            webView.getSettings().setUserAgentString(USERAGENT);//Important to auto play video
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(VideoEmbededAdress);
        webView.loadDataWithBaseURL("", VideoEmbededAdress, mimeType, encoding, "");

    }
    private void toast(String message){
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
