package com.frsvarsmakten.app;

/**
 * Created by Emmie on 2014-04-28.
 */

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class BatteryInformation extends Fragment {
    private TextView batteryInfo;
    private ImageView imageBatteryState;
    public static final float BRIGHTNESS_OVERRIDE_FULL = 1;
    public static final float BRIGHTNESS_OVERRIDE_OFF = 0;


    public static int level;
    /** Called when the activity is first created. */


    public static int getLevel() {
        return level;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.battery_information, container, false);
        batteryInfo=(TextView) rootView.findViewById(R.id.textViewBatteryInfo);
        imageBatteryState=(ImageView) rootView.findViewById(R.id.imageViewBatteryState);

        getActivity().registerReceiver(this.batteryInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        return rootView;
    }

    private BroadcastReceiver batteryInfoReceiver;

    {
        batteryInfoReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                int health = intent.getIntExtra(BatteryManager.EXTRA_HEALTH, 0);
                int icon_small = intent.getIntExtra(BatteryManager.EXTRA_ICON_SMALL, 0);
                level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
                int plugged = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0);
                boolean present = intent.getExtras().getBoolean(BatteryManager.EXTRA_PRESENT);
                int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, 0);
                int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, 0);
                String technology = intent.getExtras().getString(BatteryManager.EXTRA_TECHNOLOGY);
                int temperature = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0);
                int voltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, 0);


                batteryInfo.setText(
                        "Health: " + health + "\n" +
                                "Icon Small:" + icon_small + "\n" +
                                "Level: " + level + "\n" +
                                "Plugged: " + plugged + "\n" +
                                "Present: " + present + "\n" +
                                "Scale: " + scale + "\n" +
                                "Status: " + status + "\n" +
                                "Technology: " + technology + "\n" +
                                "Temperature: " + temperature + "\n" +
                                "Voltage: " + voltage + "\n"
                );
                imageBatteryState.setImageResource(icon_small);

            }
        };
    }


}
