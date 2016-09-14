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
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by julmustyo on 2014-04-02.
 */
public class assignmentListFragment extends ListFragment {

    int success = 0;
    Boolean notDone = false;

    private static final String JTAG_SUCCESS = "success";
    private static final String JTAG_ASSIGNMENT = "deployments";
    private static final String JTAG_DEPID = "Deployment ID";
    private static final String JTAG_PRIO = "Priority";
    private static final String JTAG_DEPTYPE = "Deployment type";
    private static final String JTAG_DEPDESCRIP = "Deployment description";
    private static final String JTAG_LONG = "Longitude";
    private static final String JTAG_LAT = "Latitude";

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_ASSIGNMENT = "deployments";
    private static final String TAG_DEPID = "Deployment ID";
    private static final String TAG_PRIO = "Prioritet";
    private static final String TAG_DEPTYPE = "Deployment typ";
    private static final String TAG_DEPDESCRIP = "Deployment description";
    private static final String TAG_LONG = "Longitud";
    private static final String TAG_LAT = "Latitud";

    JSONArray assignments = null;
    ArrayList<HashMap<String, String>> assignmentList = new ArrayList<HashMap<String, String>>();
    ArrayList<HashMap<String, String>> sqliteassignmentList = new ArrayList<HashMap<String, String>>();
    GetHttp assignmentJSON = new GetHttp();
    post assignment = new post();

    ListView lv = null;

    SQLiteHandler sqlitehandler;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.activity_assignmentsegment,container,false);
        Button getassignment = (Button) rootView.findViewById(R.id.buttonassignment);

        lv = (ListView) rootView.findViewById(android.R.id.list);
        getassignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                notDone = true;
                new GetAssignments().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,null);
                while(notDone){

                }
                Toast.makeText(getActivity(), "Uppdrag skapat", Toast.LENGTH_SHORT).show();

            }
        });

        sqlitehandler = new SQLiteHandler(getActivity());

        sqliteassignmentList = sqlitehandler.getSqliteAssignmentList();

        ListAdapter adapter = new SimpleAdapter(
                getActivity(), sqliteassignmentList,
                R.layout.assignmentlist_item, new String[]{TAG_DEPID,
                TAG_PRIO, TAG_DEPTYPE, TAG_DEPDESCRIP, TAG_LONG, TAG_LAT},
                new int[]{R.id.deploymentid, R.id.prioritet, R.id.deploymentType, R.id.deploymentDescription, R.id.longitud, R.id.latitud
                }
        );


        // updating listview
        lv.setAdapter(adapter);

        return rootView;
    }

    class GetAssignments extends AsyncTask<Void,Void,String>{

        public GetAssignments(){
        }

        @Override
        protected String doInBackground(Void... voids) {
            success=0;
            String assignments_url = "https://itkand-4.ida.liu.se/phpfiler/get_all_missions.php";
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            //JSONObject json = assignmentJSON.httpRequest(assignments_url,params);
            JSONObject json = assignment.connectPost(assignments_url,params,0);

            // Log.d("All assignments: ", json.toString());

            try {

                // Checking for SUCCESS TAG
                success = json.getInt(TAG_SUCCESS);
                Log.d("success",""+ success );
                if (success == 1) {

                    // products found
                    // Getting Array of Products
                    assignments = json.getJSONArray(TAG_ASSIGNMENT);

                    assignmentList.clear();
                    // looping through All Products
                    for (int i = 0; i < assignments.length(); i++) {
                        JSONObject c = assignments.getJSONObject(i);

                        // Storing each json item in variable
                        String depid = c.getString(JTAG_DEPID);
                        String prio = c.getString(JTAG_PRIO);
                        String type = c.getString(JTAG_DEPTYPE);
                        String descript = c.getString(JTAG_DEPDESCRIP);
                        String longit = c.getString(JTAG_LONG);
                        String latit = c.getString(JTAG_LAT);

                        //Kollar om sqlitedatabasen redan har en rad med detta id:et.
                        //if(!sqlitehandler.getAllContacts().contains(ui)) {
                        // creating new HashMap
                        HashMap<String, String> map = new HashMap<String, String>();

                        // adding each child node to HashMap key => value

                        map.put(TAG_DEPID, depid);
                        map.put(TAG_PRIO, prio);
                        map.put(TAG_DEPTYPE, type);
                        map.put(TAG_DEPDESCRIP, descript);
                        map.put(TAG_LONG, longit);
                        map.put(TAG_LAT, latit);

                        Log.d("Values", depid + prio + type + descript + longit + latit);



                        sqlitehandler.addSingleAssignment(depid, prio, type, descript, longit, latit);

                        // adding HashList to ArrayList
                        assignmentList.add(map);

                        //}



                    }


                }
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("Won't add assignment:", e.toString());
            }
            notDone=false;
            return null;
        }
        protected void onPostExecute(String urls){
            if (success == 1) {
                sqliteassignmentList= sqlitehandler.getSqliteAssignmentList();
                ListAdapter adapter = new SimpleAdapter(
                        getActivity(), sqliteassignmentList,
                        R.layout.assignmentlist_item, new String[]{TAG_DEPID,
                        TAG_PRIO, TAG_DEPTYPE, TAG_DEPDESCRIP, TAG_LONG, TAG_LAT},
                        new int[]{R.id.deploymentid, R.id.prioritet, R.id.deploymentType, R.id.deploymentDescription, R.id.longitud, R.id.latitud}
                );
                lv.setAdapter(adapter);
            }
        }
    }
}
