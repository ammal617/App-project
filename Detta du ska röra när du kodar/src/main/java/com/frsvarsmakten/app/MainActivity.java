package com.frsvarsmakten.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import javax.net.ssl.SSLContext;


public class MainActivity extends Activity {

    Button b;
    EditText et,pass;
    TextView tv;
    HttpPost httppost;
    StringBuffer buffer;
    HttpResponse response;
    HttpClient httpclient;
    List<NameValuePair> nameValuePairs;
    ProgressDialog dialog = null;

    PostHttp post = new PostHttp();
    post po = new post();

    class check extends AsyncTask<Void,Void,Void>{
        public check(){

        }
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                String add_contacts_url = "https://itkand-4.ida.liu.se/phpfiler/check.php";

                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("use", et.getText().toString()));
                params.add(new BasicNameValuePair("pass", encrypt(pass.getText().toString())));
                //JSONObject json = post.makeHttpRequest(add_contacts_url, params);
                JSONObject json = po.connectPost(add_contacts_url,params,1);
                Log.d("Json values", json.toString());
                Integer success = json.getInt("success");
                if(success == 1){
                    startActivity(new Intent(MainActivity.this, map.class));
                }else{
                    /*runOnUiThread(new Runnable() {
                        public void run() {
                            et.setText(encrypt(pass.getText().toString()));
                        }
                    });*/
                }
                } catch (JSONException e) {
            }
            return null;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login);
        CertSSL.initiate();
        b = (Button)findViewById(R.id.Button01);
        et = (EditText)findViewById(R.id.username);
        pass= (EditText)findViewById(R.id.password);
        tv = (TextView)findViewById(R.id.tv);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new check().execute();

            }
        });
    }
    /*
    public void buttonClick(View v){


        startActivity(new Intent(MainActivity.this, map.class));

        //TODO
        //Add control off user,
    }
    */


    public void MainActivity(){
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
                        Toast.makeText(MainActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                    }
                });

                startActivity(new Intent(MainActivity.this, map.class));
            }else{
                showAlert();
                startActivity(new Intent(MainActivity.this, MainActivity.class));
            }

        }catch(Exception e){
            dialog.dismiss();
            System.out.println("Exception : " + e.getMessage());
        }
    }

    public void showAlert(){
        MainActivity.this.runOnUiThread(new Runnable() {
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
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
    public void onSectionAttached(int anInt) {
    }
    private String encrypt(String encry)
    {
        String output = "";
        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(encry.getBytes("UTF-8"));
            Formatter formatter = new Formatter();
            for(byte b : crypt.digest()){
                formatter.format("%02x", b);
            }
            output = formatter.toString();
            formatter.close();
        }
        catch(NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch(UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        return output;
    }

}

