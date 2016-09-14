package com.frsvarsmakten.app;

/**
 * Created by Emmie on 2014-04-29.
 */
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.provider.Settings.System;
import android.view.Window;
import android.view.WindowManager.LayoutParams;

public class AndroidBrightnessActivity extends Activity {

    //Content resolver used as a handle to the system's settings
    private ContentResolver cResolver;
    //Window object, that will store a reference to the current window
    private Window window;
    private boolean brightnessLevel = true;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        cResolver = getContentResolver();
        window = getWindow();

        registerReceiver(batteryStatusReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        finish();
    }

    private BroadcastReceiver batteryStatusReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int level = intent.getIntExtra("level", 0);
            if(level < 30) {
                changeBrightness(20);
                Contacts.setLowEnergy(true);
            } else {
                Contacts.setLowEnergy(false);
            }
        }

    };

    private void changeBrightness(int brightness) {

        //Set the system brightness using the brightness variable value
        System.putInt(cResolver, System.SCREEN_BRIGHTNESS, brightness);
        //Get the current window attributes
        LayoutParams layoutpars = window.getAttributes();
        //Set the brightness of this window
        layoutpars.screenBrightness = brightness / (float) 255;
        //Apply attribute changes to this window
        window.setAttributes(layoutpars);
    }
}