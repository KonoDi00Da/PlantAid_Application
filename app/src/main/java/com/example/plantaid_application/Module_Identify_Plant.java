package com.example.plantaid_application;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Module_Identify_Plant#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Module_Identify_Plant extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1888;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button mButton;
    private ImageView pictureTaken;
    private TextView mtextView;
    private Uri imageUri;
    private final int REQUEST_IMAGE_CAPTURE = 1234;




    public Module_Identify_Plant() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Module_Identify_Plant.
     */
    // TODO: Rename and change types and number of parameters
    public static Module_Identify_Plant newInstance(String param1, String param2) {
        Module_Identify_Plant fragment = new Module_Identify_Plant();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Assign variables
        View myView = inflater.inflate(R.layout.fragment_module__identify__plant, container, false);
        pictureTaken = (ImageView) myView.findViewById(R.id.plantPicture);
        mButton = (Button)myView.findViewById(R.id.btnCamera);
        mButton.setOnClickListener(this);
        //mtextView = (TextView) myView.findViewById(R.id.testCam);



        // Inflate the layout for this fragment
        return myView;

    }

    @Override
    public void onClick(View view) {

        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE,"new image");
        values.put(MediaStore.Images.Media.DESCRIPTION,"From the camera");
        imageUri = getContext().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        Intent camIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        camIntent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
        startActivityForResult(camIntent, REQUEST_IMAGE_CAPTURE);

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            pictureTaken.setImageURI(imageUri);
//            Bundle extras = data.getExtras();
//            Bitmap imageBitmap = (Bitmap) extras.get("data");
//            pictureTaken.setImageBitmap(imageBitmap);
        }
    }
}