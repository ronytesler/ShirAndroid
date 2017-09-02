package com.shir.androidfinalproject.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.shir.androidfinalproject.Fragments.AddDatesFragment;
import com.shir.androidfinalproject.Fragments.AddLocationsFragment;
import com.shir.androidfinalproject.Fragments.InviteFriendsFragment;
import com.shir.androidfinalproject.R;

public class CreateEventActivity extends AppCompatActivity
        implements AddDatesFragment.AddDatesListener,
        AddLocationsFragment.AddLocationsListener,
        InviteFriendsFragment.InviteFriendsListener {

    private static final String TAG = "CreateEventActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        Log.d(TAG, "onCreate: ");
    }
}
