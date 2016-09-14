package com.frsvarsmakten.app;

import android.app.Fragment;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import org.osmdroid.bonuspack.overlays.Marker;
import org.osmdroid.bonuspack.overlays.Polyline;
import org.osmdroid.bonuspack.routing.OSRMRoadManager;
import org.osmdroid.bonuspack.routing.Road;
import org.osmdroid.bonuspack.routing.RoadManager;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;

import java.util.ArrayList;

import idv.hondadai.offlinemap.views.OfflineMapView;

/**
 * Created by skogen_jonas on 2014-04-02.
 */
public class MapFragment extends Fragment {
    private MapView mMapView;
    private RelativeLayout mMapLayout;
    private MapController mMapController;
    public RoadManager roadManager;
    public ArrayList<GeoPoint> wayPoints;
    public Road mRoad = null;


    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static MapFragment newInstance(int sectionNumber) {
        MapFragment fragment = new MapFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public void mapFragment() {
    }


    @Override
    public  View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);


        mMapLayout = (RelativeLayout) rootView.findViewById(R.id.kartan);

        // init Offline Map
        mMapView = new OfflineMapView(getActivity(), "OSM.sqlitedb");
        mMapController = (MapController) mMapView.getController();

        // set Zoom Countrol
        // zoom to 9
        // set Touch Control
        mMapView.setBuiltInZoomControls(true);
        mMapController.setZoom(7);

        mMapView.setMultiTouchControls(true);

        //add mapview
        mMapLayout.addView(mMapView, new RelativeLayout.LayoutParams(
                android.view.ViewGroup.LayoutParams.FILL_PARENT,
                android.view.ViewGroup.LayoutParams.FILL_PARENT));

        GeoPoint startPoint = new GeoPoint(58.4126943, 15.570251);
        GeoPoint endPoint = new GeoPoint(58.4107928, 15.6499137);
        mMapController.setCenter(startPoint);

        wayPoints = new ArrayList<GeoPoint>();

        wayPoints.add(startPoint);
        wayPoints.add(endPoint);
        getRoad getRoad = (MapFragment.getRoad) new getRoad().execute();


        Drawable nodeIcon = getResources().getDrawable(R.drawable.small);
        Marker nodeMark = new Marker(mMapView);
        Marker nodeMark1 = new Marker(mMapView);
        nodeMark.setTitle("Start");
        nodeMark.setPosition(startPoint);
        nodeMark.setIcon(nodeIcon);

        nodeMark1.setIcon(nodeIcon);
        nodeMark1.setPosition(endPoint);

        mMapView.getOverlays().add(nodeMark);
        mMapView.getOverlays().add(nodeMark1);

        mMapView.invalidate();


        getRoad.cancel(true);



        return rootView;
    }


  class getRoad extends AsyncTask<Void, Road, Polyline> {


        public getRoad() {
        }
         @Override
         protected Polyline doInBackground(Void... params) {
             RoadManager rM = new OSRMRoadManager();
             Road road = rM.getRoad(wayPoints);
             Polyline roadOverlay = roadManager.buildRoadOverlay(road, getActivity());
             roadOverlay.setColor(Color.BLUE);
             mMapView.getOverlays().add(roadOverlay);
             return roadOverlay;
         }

        protected void onPostExecute(Road result) {
            mRoad=result;
        }


  }
}
