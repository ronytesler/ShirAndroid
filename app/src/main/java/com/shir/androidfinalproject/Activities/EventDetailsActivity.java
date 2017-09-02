package com.shir.androidfinalproject.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.shir.androidfinalproject.Models.Event;
import com.shir.androidfinalproject.R;

public class EventDetailsActivity extends Activity implements View.OnClickListener{

    public static final String TAG = "EventDetailsActivity";
    public static final String CURR_EVENT = "CURR_EVENT";
    private Event currEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        Spinner staticSpinner = (Spinner) findViewById(R.id.event_status);

        // Create an ArrayAdapter using the string array and a default spinner
//        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
//                .createFromResource(this, R.array.brew_array,
//                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
//        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
//        staticSpinner.setAdapter(staticAdapter);

        setEventDetails(null);

        //findViewById(R.id.btnEditStudent).setOnClickListener(this);
    }

    private void setEventDetails(Intent data) {
//        TextView txtID = (TextView) findViewById(R.id.student_id);
//        TextView txtName = (TextView) findViewById(R.id.student_name);
//        TextView txtPhone = (TextView) findViewById(R.id.student_phone);
//        TextView txtAddress = (TextView) findViewById(R.id.student_address);

        if (data == null)
            data = getIntent();
        currEvent = (Event) data.getSerializableExtra(this.CURR_EVENT);

        // displaying selected product name
//        txtID.setText(currStudent.ID);
//        txtName.setText(currStudent.Name);
//        txtPhone.setText(currStudent.Phone);
//        txtAddress.setText(currStudent.Address);
    }

    @Override
    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.btnEditStudent:
//                onEditStudent();
//                break;
//        }
    }
}
