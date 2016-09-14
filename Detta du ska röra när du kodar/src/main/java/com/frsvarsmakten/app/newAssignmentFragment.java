package com.frsvarsmakten.app;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Result;

/**
 * Created by skogen_jonas on 2014-03-27.
 */
public class newAssignmentFragment extends Fragment {
    private Button saveButton, photoButton;
    private EditText titleTxt, longitude, latitude, descriptionTxt, responsibleTxt, creatorTxt, next;
    ImageView imageView;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int SELECT_PICTURE = 1;
    static final int RESULT_OK = 1;
    private Spinner prioSpinner, categorySpinner;

    PostHttp post = new PostHttp();
    post newAssignment = new post();


    public newAssignmentFragment() {
    }

    class createAssignment extends AsyncTask<Void,Void,Void>{
        public createAssignment(){

        }
        @Override
        protected Void doInBackground(Void... voids) {
            Log.d("In background", "It made it!");
            String add_contacts_url = "https://itkand-4.ida.liu.se/phpfiler/create_mission.php";
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("longitude",longitude.getText().toString()));
            params.add(new BasicNameValuePair("latitude",latitude.getText().toString()));
            params.add(new BasicNameValuePair("priority",prioSpinner.getSelectedItem().toString()));
            params.add(new BasicNameValuePair("type",categorySpinner.getSelectedItem().toString()));
            params.add(new BasicNameValuePair("description",descriptionTxt.getText().toString()));
            //JSONObject json = post.makeHttpRequest(add_contacts_url,params);
            JSONObject json = newAssignment.connectPost(add_contacts_url,params,1);
            Log.d("Made it past add", "true");
            return null;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_newassignment, container, false);

        saveButton = (Button) rootView.findViewById(R.id.save);
        titleTxt = (EditText) rootView.findViewById(R.id.title);
        longitude= (EditText) rootView.findViewById(R.id.longitude);
        latitude = (EditText) rootView.findViewById(R.id.latitude);
        descriptionTxt = (EditText) rootView.findViewById(R.id.description);
        responsibleTxt = (EditText) rootView.findViewById(R.id.responsible);
        creatorTxt = (EditText) rootView.findViewById(R.id.createdBy);
        photoButton = (Button) rootView.findViewById(R.id.photo);
        imageView = (ImageView) rootView.findViewById(R.id.imageView);

        prioSpinner = (Spinner) rootView.findViewById(R.id.prioSpinner);
        ArrayAdapter<CharSequence> prioAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.priority_array, android.R.layout.simple_spinner_item);
        prioAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        prioSpinner.setAdapter(prioAdapter);

        categorySpinner = (Spinner) rootView.findViewById(R.id.categorySpinner);
        ArrayAdapter<CharSequence> cateAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.category_array, android.R.layout.simple_spinner_item);
        prioAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(cateAdapter);


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new createAssignment().execute();
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
        longitude.addTextChangedListener(new TextWatcher() {
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
        latitude.addTextChangedListener(new TextWatcher() {
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
                !longitude.getText().toString().trim().isEmpty() && !latitude.getText().toString().trim().isEmpty() && !creatorTxt.getText().toString().trim().isEmpty()){
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