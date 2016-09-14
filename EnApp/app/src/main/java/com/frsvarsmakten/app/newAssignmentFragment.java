package com.frsvarsmakten.app;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by skogen_jonas on 2014-03-27.
 */
public class newAssignmentFragment extends android.app.Fragment {
    private View saveButton, photoButton;
    private EditText titleTxt, locationTxt, descriptionTxt, responsibleTxt, creatorTxt, next;
    ImageView imageView;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int SELECT_PICTURE = 1;
    static final int RESULT_OK = 1;
    //   private Spinner prioSpinner, categorySpinner;


    public newAssignmentFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_newassignment, container, false);

        saveButton = rootView.findViewById(R.id.save);
        titleTxt = (EditText) rootView.findViewById(R.id.title);
        locationTxt = (EditText) rootView.findViewById(R.id.location);
        descriptionTxt = (EditText) rootView.findViewById(R.id.description);
        responsibleTxt = (EditText) rootView.findViewById(R.id.responsible);
        creatorTxt = (EditText) rootView.findViewById(R.id.createdBy);
        photoButton = rootView.findViewById(R.id.photo);
        imageView = (ImageView) rootView.findViewById(R.id.imageView);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Uppdrag skapat", Toast.LENGTH_SHORT).show();

            }
        });

           /*
           Makes it possible to open the camera app and take a photo.

           More work left: save the image need to be done.

            */


         /*
         Listener for the edit text-fields. Activates the save-button when certain fields are field in.
          */

        titleTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                saveButton.setEnabled(readyToSave());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        locationTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                saveButton.setEnabled(readyToSave());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        creatorTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                saveButton.setEnabled(readyToSave());

            }

            @Override
            public void afterTextChanged(Editable s) {
            }

        });

        /*
        Make it possible to take photo or choose from library when hit the add photo-button
         */
        photoButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                pickPhoto();
            }

            public void pickPhoto(){

                Intent pickIntent = new Intent();
                pickIntent.setType("image/*");
                pickIntent.setAction(Intent.ACTION_PICK);

                Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                String pickTitle = "Välj från befintliga foton eller ta en ny";
                Intent chooseIntent = Intent.createChooser(pickIntent, pickTitle);
                chooseIntent.putExtra
                        (
                                Intent.EXTRA_INITIAL_INTENTS,
                                new Intent[] { takePhotoIntent });

                startActivityForResult(chooseIntent, SELECT_PICTURE);

            }

        });



        return rootView;

    }


    /*
    Check if enough data has been fill in to save the assignment
     */
    public boolean readyToSave(){
        if (!titleTxt.getText().toString().trim().isEmpty() &&
                !locationTxt.getText().toString().trim().isEmpty() && !creatorTxt.getText().toString().trim().isEmpty()){
            return true;
        } else {
            return false;
        }
    }

    // this is useless for now
    private void handleSmallCameraPhoto(Intent intent) {
        Bundle extras = intent.getExtras();
        Bitmap imageBitmap = (Bitmap) extras.get("data");
        imageView.setImageBitmap(imageBitmap);
    }



}