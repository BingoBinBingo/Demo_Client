package com.example.bingo.demo_client.activity;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.example.bingo.demo_client.R;

/**
 * Created by Bingo on 15/12/12.
 */
public class FlightPreferenceActivity extends PreferenceActivity {
    public static final String TAG = PreferenceMainActivity.class.getCanonicalName();
    public static final String TITLE = "FlightPreferenceActivity";
    public static final String ACTION_NAME = "com.bingo.demo.flightPreferenceActivity_action";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.flightoptions);
    }
}
