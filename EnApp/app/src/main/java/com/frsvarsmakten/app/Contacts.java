package com.frsvarsmakten.app;

import android.app.ListFragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by julmustyo on 2014-04-02.
 */
public class Contacts extends ListFragment {

    int success = 0;
    Boolean notDone = false;

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_CONTACTS = "user_info";
    private static final String TAG_NUMBER = "Number";
    private static final String TAG_FIRSTNAME = "First name";
    private static final String TAG_LASTNAME = "Last name";
    private static final String TAG_USERID = "Userid";

    JSONArray contacts = null;
    ArrayList<HashMap<String, String>> contactsList = new ArrayList<HashMap<String, String>>();
    ArrayList<HashMap<String, String>> sqliteContactList = new ArrayList<HashMap<String, String>>();
    GetHttp contactsJSON = new GetHttp();

    public static boolean isLowEnergy() {
        return lowEnergy;
    }

    public static void setLowEnergy(boolean lowEnergy) {
        Contacts.lowEnergy = lowEnergy;
    }

    public static boolean lowEnergy = false;

    ListView lv = null;

    SQLiteHandler sqlitehandler;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.activity_contactsegment,container,false);
        Button getcontacts = (Button) rootView.findViewById(R.id.button);

        lv = (ListView) rootView.findViewById(android.R.id.list);
        getcontacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new GetContacts().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, null);
                Log.d("Success value", ""+ success);



            }
        });


        sqlitehandler = new SQLiteHandler(getActivity());


        sqliteContactList = sqlitehandler.getSqliteContactsList();

                ListAdapter adapter = new SimpleAdapter(
                getActivity(), sqliteContactList,
                R.layout.contactlist_item, new String[]{TAG_NUMBER,
                TAG_FIRSTNAME, TAG_LASTNAME, TAG_USERID},
                new int[]{R.id.number, R.id.firstname, R.id.lastname, R.id.userid}
        );


        // updating listview
        lv.setAdapter(adapter);

        return rootView;
    }

    class GetContacts extends AsyncTask<Void,Void,String>{

        public GetContacts(){
        }

        @Override
        protected String doInBackground(Void... voids) {
            success=0;
            String contacts_url = "http://itkand-4.ida.liu.se/phpfiler/get_all_contacts.php";
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            JSONObject json = contactsJSON.httpRequest(contacts_url,params);


            // Log.d("All contacts: ", json.toString());

            try {

                // Checking for SUCCESS TAG
                success = json.getInt(TAG_SUCCESS);
                Log.d("success",""+ success );
                if (success == 1) {


                    // products found
                    // Getting Array of Products
                    contacts = json.getJSONArray(TAG_CONTACTS);

                    contactsList.clear();
                    // looping through All Products
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);

                        // Storing each json item in variable
                        String num = c.getString(TAG_NUMBER);
                        String fn = c.getString(TAG_FIRSTNAME);
                        String ln = c.getString(TAG_LASTNAME);
                        String ui = c.getString(TAG_USERID);

                        //Kollar om sqlitedatabasen redan har en rad med detta id:et.
                        //if(!sqlitehandler.getAllContacts().contains(ui)) {
                        // creating new HashMap
                        HashMap<String, String> map = new HashMap<String, String>();




                            // adding each child node to HashMap key => value
                            map.put(TAG_NUMBER, num);
                            map.put(TAG_FIRSTNAME, fn);
                            map.put(TAG_LASTNAME, ln);
                            map.put(TAG_USERID, ui);




                            sqlitehandler.addSingleContact(num, fn, ln, ui);

                            // adding HashList to ArrayList
                            contactsList.add(map);

                        //}



                    }


                }
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("Won't add contacts:", e.toString());
            }
            notDone=false;
            return null;
        }
        protected void onPostExecute(String urls){
            if (success == 1) {
                sqliteContactList= sqlitehandler.getSqliteContactsList();
                ListAdapter adapter = new SimpleAdapter(
                        getActivity(), sqliteContactList,
                        R.layout.contactlist_item, new String[]{TAG_NUMBER,
                        TAG_FIRSTNAME, TAG_LASTNAME, TAG_USERID},
                        new int[]{R.id.number, R.id.firstname, R.id.lastname, R.id.userid}
                );
                lv.setAdapter(adapter);
            }
        }
    }
}
