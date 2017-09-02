package com.shir.androidfinalproject.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.shir.androidfinalproject.Fragments.EventsListFragment;
import com.shir.androidfinalproject.Models.Event;
import com.shir.androidfinalproject.data.DataManager;

import com.shir.androidfinalproject.R;

public class MainActivity extends AppCompatActivity
        implements EventsListFragment.EventsListListener, View.OnClickListener {

    public static final int EVENT_DETAILS_REQUEST = 101;
    private Event mEvent;
    private Menu mMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.fab).setOnClickListener(this);

        getFragmentManager()
                .beginTransaction()
                .add(R.id.main_container, EventsListFragment.newInstance(), EventsListFragment.TAG)
                .commit();

        //setTitle();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        switch (requestCode) {
//            case ADD_STUDENT_REQUEST:
//                if (resultCode == RESULT_OK) {
//                    ((StudentListAdapter) mStudentList.getAdapter()).notifyDataSetChanged();
//                }
//                break;
//            case STUDENT_DETAILS:
//                if (resultCode == RESULT_OK) {
//                    ((StudentListAdapter) mStudentList.getAdapter()).notifyDataSetChanged();
//                }
//                else if (resultCode == EditStudentActivity.RESULT_DELETE) {
//                    ((StudentListAdapter) mStudentList.getAdapter()).notifyDataSetChanged();
//                }
//                break;
//        }
    }

    @Override
    public void onEventClick(Event event) {
        mEvent = event;
        openEventDetails(mEvent);

        Toast.makeText(getApplicationContext(),
                "A event in list was clicked = " + event.title,
                Toast.LENGTH_LONG).show();
    }

    private void openEventDetails(Event event) {
        // Launching new Activity on selecting single List Item
        Intent i = new Intent(getApplicationContext(), EventDetailsActivity.class);

        Bundle bundle = new Bundle();
        bundle.putSerializable(EventDetailsActivity.CURR_EVENT, event);
        i.putExtras(bundle);

        startActivityForResult(i, this.EVENT_DETAILS_REQUEST);
    }

    @Override
    public void onClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main, menu);

        mMenu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        item.setVisible(false);

        int itemId = item.getItemId();

        switch (itemId){
            case R.id.main_logout:
                DataManager.getInstance(this).setLoginWithFacebook(false);
                LoginManager.getInstance().logOut();
                goToLoginActivity();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private void goToLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
