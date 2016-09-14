package com.frsvarsmakten.app;

/**
 * Created by T420S on 2014-05-05.
 */

import android.app.Activity;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.TextView;

/**
 * Created by T420S on 2014-05-02.
 */
public class SignalStrengthActivity  extends Activity {

    SignalStrengthListener signalStrengthListener;
    TextView signalStrengthTextView;

    static boolean enoughSignal;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        signalStrengthListener = new SignalStrengthListener();
        ((TelephonyManager) getSystemService(TELEPHONY_SERVICE)).listen(signalStrengthListener, SignalStrengthListener.LISTEN_SIGNAL_STRENGTHS);
        finish();
    }


    private class SignalStrengthListener extends PhoneStateListener {
        @Override
        public void onSignalStrengthsChanged(android.telephony.SignalStrength signalStrength) {

            //  (a value between 0 and 31)
            int strengthAmplitude = signalStrength.getGsmSignalStrength();

            if(strengthAmplitude < 6 ){

               Contacts.setLowSignal(true);
            }
            else{Contacts.setLowSignal(false);}


            super.onSignalStrengthsChanged(signalStrength);
        }
    }




}