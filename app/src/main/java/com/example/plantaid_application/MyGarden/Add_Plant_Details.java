package com.example.plantaid_application.MyGarden;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.webkit.WebViewAssetLoader;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.plantaid_application.MainHome;
import com.example.plantaid_application.Models.User_Plants;
import com.example.plantaid_application.Module_Today;
import com.example.plantaid_application.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Add_Plant_Details extends AppCompatActivity {
    public String key;
    TextView commonNameTextView, sciNameTextView, descriptionTextView, txtWater, txtHarvest, txtCare, txtPestsDiseases;
    ImageView imgPlant;
    WebView webView;
    String comPlant, sciPlant, image;
    EditText editTxtPlantAge;

    Button addToGardenBtn;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    FirebaseDatabase database;


    //for Youtube View Embedding

    private String VideoEmbededAdress;
    private final String mimeType = "text/html";
    private final String encoding = "UTF-8";//"base64";

//    Plant_List_Adapter myAdapter;
//    ArrayList<PlantListModel> plantListModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plant_details);

        try {
            //Initialize firebase
            mAuth = FirebaseAuth.getInstance();
            currentUser = mAuth.getCurrentUser();
            database = FirebaseDatabase.getInstance();

            commonNameTextView = findViewById(R.id.com_plant);
            sciNameTextView = findViewById(R.id.sci_plant);
            descriptionTextView = findViewById(R.id.plant_desc);
            txtWater = findViewById(R.id.txtWater);
            txtHarvest = findViewById(R.id.txtHarvest);
            txtCare = findViewById(R.id.txtCare);
            txtPestsDiseases = findViewById(R.id.txtPestsDisease);
            imgPlant = findViewById(R.id.plant_image);
            webView = findViewById(R.id.ytLink);
            editTxtPlantAge = findViewById(R.id.plantAge);
            addToGardenBtn = findViewById(R.id.addToGardenBtn);

            Picasso.get().load(getIntent().getStringExtra("plant_image"))
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(imgPlant);

            commonNameTextView.setText(getIntent().getStringExtra("com_plant"));
            sciNameTextView.setText(getIntent().getStringExtra("sci_plant"));
            descriptionTextView.setText(getIntent().getStringExtra("plant_desc"));
            txtWater.setText(getIntent().getStringExtra("txtWater"));
            txtHarvest.setText(getIntent().getStringExtra("txtHarvest"));
            txtCare.setText(getIntent().getStringExtra("txtCare"));
            txtPestsDiseases.setText(getIntent().getStringExtra("txtPestsDisease"));

            key = getIntent().getStringExtra("key");
            comPlant = getIntent().getStringExtra("com_plant");
            sciPlant = getIntent().getStringExtra("sci_plant");
            image = getIntent().getStringExtra("plant_image");

            //youtube view

            String ytEmbedKey = getIntent().getStringExtra("ytLink");
            WebViewAssetLoader assetLoader = new WebViewAssetLoader.Builder()
                    .addPathHandler("/assets/", new WebViewAssetLoader.AssetsPathHandler(this))
                    .build();

            //webView.loadUrl(getIntent().getStringExtra("ytLink"));
            VideoEmbededAdress = "<iframe width=\"350\" height=\"225\" src=\"https://www.youtube.com/embed/"+ ytEmbedKey +"\" title=\"YouTube video player\"allow=\"autoplay;\" allowfullscreen></iframe>";

            webView.setWebViewClient(new WebViewClient() {
                private WebView view;
                private WebResourceRequest request;

                public WebResourceResponse shouldInterceptRequest(WebView view,
                                                                  WebResourceRequest request) {
                    Log.d("AddPlant", "shouldOverrideUrlLoading: Url = [" + request.getUrl()+"]");
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


            addToGardenBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    DatabaseReference userRef = database.getReference("Users").child(currentUser.getUid());
                    userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            //get date added
                            String plantID = "myGarden";
                            String userPlantKey = userRef.push().getKey();
                            User_Plants userPlants = new User_Plants(comPlant, sciPlant, image, key, userPlantKey);
                            userRef.child(plantID).child(userPlantKey).setValue(userPlants);
                            toast("Plant added successfully");

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                    Intent myGarden = new Intent(Add_Plant_Details.this, MainHome.class);
                    startActivity(myGarden);
                }
            });


//        commonNameTextView.setText(com_name);
//        sciNameTextView.setText(sci_name);
//        desciptionTextView.setText(description);
//        imgPlant.setImageResource(image);

    } catch (NullPointerException e) {
        Log.v("TEST", "Error: " + e.toString());
    }
    }

//    public void addPlantToGarden(String c_plantName, String s_plantName, String plantAge) {
//        User_Plants userPlants = new User_Plants(c_plantName, s_plantName, plantAge);
//        userRef.child(key).setValue(userPlants);
//    }

    private void toast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}