package com.shir.androidfinalproject.Activities;

import android.graphics.Bitmap;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.shir.androidfinalproject.Fragments.AddDatesFragment;
import com.shir.androidfinalproject.Fragments.AddEventDetailsFragment;
import com.shir.androidfinalproject.Fragments.AddLocationsFragment;
import com.shir.androidfinalproject.Fragments.InviteFriendsFragment;
import com.shir.androidfinalproject.R;

import java.util.ArrayList;
import java.util.Date;

public class CreateEventActivity extends AppCompatActivity implements AddDatesFragment.AddDatesListener,
        AddEventDetailsFragment.AddEventDetailsListener, AddLocationsFragment.AddLocationsFragmentListener
{

    ArrayList<String> locations;
    ArrayList<Bitmap> photosList;
    String title;
    String description;
    private static final String TAG = "CreateEventActivity";
    AddEventDetailsFragment addEventDetailsFragment;
    AddLocationsFragment addLocationsFragment;
    AddDatesFragment addDatesFragment;

    private void moveToLocationsFragment() {
        addLocationsFragment = new AddLocationsFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.fragment_container, addLocationsFragment);
        transaction.addToBackStack(null);

// Commit the transaction
        transaction.commit();
    }

    private void moveToAddDatesFragment() {
        addDatesFragment = new AddDatesFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.fragment_container, addDatesFragment);
        transaction.addToBackStack(null);

// Commit the transaction
        transaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        Log.d(TAG, "onCreate: ");

        if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create a new Fragment to be placed in the activity layout
            addEventDetailsFragment = new AddEventDetailsFragment();

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, addEventDetailsFragment).commit();
        }

    }

    @Override
    public void onNextClick(ArrayList<Bitmap> photosList, String title, String description) {
        this.title = title;
        this.description = description;
        this.photosList = photosList;
        moveToLocationsFragment();
    }

    @Override
    public void onAddDatesClicked(ArrayList<String> lstLocations) {
        this.locations = lstLocations;
        moveToAddDatesFragment();
    }

    @Override
    public void onInviteFriendsClick(Date lastUpdate, int duration) {

    }
}
