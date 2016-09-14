package com.frsvarsmakten.app;

import android.app.Activity;
import android.content.ContentResolver;
import android.os.Bundle;
import android.provider.Settings;
import android.provider.Settings.System;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;


/**
 * Created by Emmie on 2014-04-28.
 */
public class EnergySaving extends Activity {

    //Variable to store brightness value
    private int brightness;
    //Content resolver used as a handle to the system's settings
    private ContentResolver cResolver;
    //Window object, that will store a reference to the current window
    private Window window;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //Get the content resolver
        cResolver = getContentResolver();

//Get the current window
        window = getWindow();

        try
        {
            //Get the current system brightness
            brightness = System.getInt(cResolver, Settings.System.SCREEN_BRIGHTNESS);
        }
        catch (Settings.SettingNotFoundException e)
        {
            //Throw an error case it couldn't be retrieved
            Log.e("Error", "Cannot access system brightness");
            e.printStackTrace();
        }

        //Set the system brightness using the brightness variable value
        System.putInt(cResolver, System.SCREEN_BRIGHTNESS, brightness);
        //Get the current window attributes
        WindowManager.LayoutParams layoutpars = window.getAttributes();
        //Set the brightness of this window
        layoutpars.screenBrightness = brightness / (float)255;
        //Apply attribute changes to this window
        window.setAttributes(layoutpars);

            //checkBattery();

    }


    public void checkBattery()  {
        if (BatteryInformation.getLevel() < 101){


        }
    }



}
