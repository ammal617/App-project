package com.frsvarsmakten.app;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Ammar on 2014-03-25.
 */
public class login extends Activity {;


        Button b;
        EditText et,pass;
        TextView tv;
        HttpPost httppost;
        StringBuffer buffer;
        HttpResponse response;
        HttpClient httpclient;
        List<NameValuePair> nameValuePairs;
        ProgressDialog dialog = null;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.fragment_login);

            b = (Button)findViewById(R.id.Button01);
            et = (EditText)findViewById(R.id.username);
            pass= (EditText)findViewById(R.id.password);
            tv = (TextView)findViewById(R.id.tv);

            b.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog = ProgressDialog.show(login.this, "",
                            "Validating user...", true);
                    new Thread(new Runnable() {
                        public void run() {
                            login();
                        }
                    }).start();
                }
            });
        }

        void login(){
            try{

                httpclient=new DefaultHttpClient();
                httppost= new HttpPost("http://10.0.2.2/my_folder_inside_htdocs/check.php"); // make sure the url is correct.
                //add your data
                nameValuePairs = new ArrayList<NameValuePair>(2);
                // Always use the same variable name for posting i.e the android side variable name and php side variable name should be similar,
                nameValuePairs.add(new BasicNameValuePair("username",et.getText().toString().trim()));  // $Edittext_value = $_POST['Edittext_value'];
                nameValuePairs.add(new BasicNameValuePair("password",pass.getText().toString().trim()));
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                //Execute HTTP Post Request
                response=httpclient.execute(httppost);
                // edited by James from coderzheaven.. from here....
                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                final String response = httpclient.execute(httppost, responseHandler);
                System.out.println("Response : " + response);
                runOnUiThread(new Runnable() {
                    public void run() {
                        tv.setText("Response from PHP : " + response);
                        dialog.dismiss();
                    }
                });

                if(response.equalsIgnoreCase("User Found")){
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(login.this,"Login Success", Toast.LENGTH_SHORT).show();
                        }
                    });

                    startActivity(new Intent(login.this, UserPage.class));
                }else{
                    showAlert();
                }

            }catch(Exception e){
                dialog.dismiss();
                System.out.println("Exception : " + e.getMessage());
            }
        }
        public void showAlert(){
            login.this.runOnUiThread(new Runnable() {
                public void run() {
                    AlertDialog.Builder builder = new AlertDialog.Builder(login.this);
                    builder.setTitle("Login Error.");
                    builder.setMessage("User not Found.")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            });
        }
    }

