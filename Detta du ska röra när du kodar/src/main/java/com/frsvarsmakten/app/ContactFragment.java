package com.frsvarsmakten.app;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Create a new contact fragment.
 */

public class ContactFragment extends Fragment {
    PostHttp post = new PostHttp();
    post cont = new post();
    private View button;
    private EditText firstname,lastname,number;
    TextView topic;
    Boolean notDone = false;
    public ContactFragment() {
    }
    class addContact extends AsyncTask<Void,Void,Void>{
        public addContact(){
        }

        @Override
        protected Void doInBackground(Void... voids) {
            notDone = true;
            Log.d("In background","It made it!");
            String add_contacts_url = "https://itkand-4.ida.liu.se/phpfiler/create_contact.php";
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("number",number.getText().toString()));
            params.add(new BasicNameValuePair("firstname",firstname.getText().toString()));
            params.add(new BasicNameValuePair("lastname",lastname.getText().toString()));
            //JSONObject json = post.makeHttpRequest(add_contacts_url,params);
            JSONObject json = cont.connectPost(add_contacts_url,params,1);
            Log.d("Made it past add", "true");
            notDone = false;
            return null;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_contactcreator, container, false);

        topic = (TextView) rootView.findViewById(R.id.ilbCreatorTitle);
        button = rootView.findViewById(R.id.button);
        firstname = (EditText) rootView.findViewById(R.id.firstName);
        lastname = (EditText) rootView.findViewById(R.id.lastName);
        number = (EditText) rootView.findViewById(R.id.number);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notDone = true;
                new addContact().execute();
                while(notDone){

                }
                topic.setText("Successfully added a contact to global database");
            }
        });

        /*
        nameTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                button.setEnabled(!nameTxt.getText().toString().trim().isEmpty() &&
                        !numberTxt.getText().toString().trim().isEmpty());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        numberTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                button.setEnabled(!numberTxt.getText().toString().trim().isEmpty() &&
                        !numberTxt.getText().toString().trim().isEmpty());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        */
        return rootView;
    }
}