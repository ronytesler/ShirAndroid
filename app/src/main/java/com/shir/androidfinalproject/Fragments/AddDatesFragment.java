package com.shir.androidfinalproject.Fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.shir.androidfinalproject.Models.EventDate;
import com.shir.androidfinalproject.R;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddDatesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddDatesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddDatesFragment extends Fragment
        implements View.OnClickListener,
        DatePickerDialog.OnDateSetListener,
        TimePickerDialog.OnTimeSetListener,
        AdapterView.OnItemClickListener{
    public static final String TAG = "AddDatesFragment";

    boolean bIsAddDate;
    TextView tvLastEventUpdaeDate;
    ImageView ivAddLastUpdateDate;
    Date dtLastUpdate;

    NumberPicker npDuration;
    int nDuration;

    TextView tvAddDate;
    TextView tvAddTime;
    ImageView ivAddDate;
    Date dtCurrAddDate;
    int year,  month, dayOfMonth, hourOfDay, minute;

    ListView lvDatesList;
    ArrayList<EventDate> lstEventDates;

    FloatingActionButton btnInviteFriends;

    private AddDatesListener mListener;

    public AddDatesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment EditStudentFragment.
     */
    public static AddDatesFragment newInstance() {
        AddDatesFragment fragment = new AddDatesFragment();
        //Bundle args = new Bundle();
//        args.putSerializable(STUDENT, student);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // mStudent = (Student) getArguments().getSerializable(STUDENT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_dates, container, false);


        tvLastEventUpdaeDate = (TextView) view.findViewById(R.id.tv_last_update_date);
        ivAddLastUpdateDate = (ImageView) view.findViewById(R.id.iv_last_update_date);
        npDuration = (NumberPicker) view.findViewById(R.id.np_duration);
        tvAddDate = (TextView) view.findViewById(R.id.tv_add_date);
        tvAddTime = (TextView) view.findViewById(R.id.tv_add_time);
        ivAddDate = (ImageView) view.findViewById(R.id.iv_add_date);

        lvDatesList = (ListView) view.findViewById(R.id.lv_dates_list);
        btnInviteFriends = (FloatingActionButton) view.findViewById(R.id.btn_invite_friends);

        // listening to single list item on click
        npDuration.setOnClickListener(this);
        ivAddDate.setOnClickListener(this);
        btnInviteFriends.setOnClickListener(this);

        npDuration.setMaxValue(8760);
        npDuration.setMinValue(1);
        npDuration.setWrapSelectorWheel(false);

        initDataMembers();

        ListAdapter adapter = new VotesListAdapter(getActivity(), lstEventDates);

//        //Fill data into listview.............
//        if(arrListItems.size()>0)
//        {
//            adapteritem=new ItemAdapter(MainActivity.this, arrListItems);
//            listviewitems.setAdapter(adapteritem);
//        }
//        else
//        {
//            txtNoitemfound.setVisibility(View.VISIBLE);
//            listviewitems.setVisibility(View.GONE);
//            btnEdit.setVisibility(View.INVISIBLE);
//            btnClearAll.setVisibility(View.INVISIBLE);
//
//        }

        // Binding resources Array to ListAdapter
        lvDatesList.setAdapter(adapter);

        // listening to single list item on click
        lvDatesList.setOnItemClickListener(this);


//
//        Students = Model.Instance.getAllStudets();
//        ListAdapter adapter = new StudentListAdapter(getActivity(), Students);
//
//        lvStudents = (ListView) view.findViewById(R.id.lv_students);
//
//        // Binding resources Array to ListAdapter
//        lvStudents.setAdapter(adapter);
//
//        // listening to single list item on click
//        lvStudents.setOnItemClickListener(this);

        return view;
    }

    private void initDataMembers(){
        nDuration = 1;
        lstEventDates = new ArrayList<EventDate>();
        year = 0;
        month = 0;
        dayOfMonth = 0;
        hourOfDay = 0;
        minute = 0;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_last_update_date:
                bIsAddDate = false;
                DialogFragments.DatePickerFragment fragment1 = new DialogFragments.DatePickerFragment();
                fragment1.show(getFragmentManager(), "date");
                break;
            case R.id.np_duration:
                nDuration = npDuration.getValue();
                break;
            case R.id.iv_add_date:
                bIsAddDate = true;

                DialogFragments.DatePickerFragment fragment2 = new DialogFragments.DatePickerFragment();
                fragment2.show(getFragmentManager(), "date");

                String strDate = tvAddDate.getText().toString();
                String strTime = tvAddTime.getText().toString();
                if (TextUtils.isEmpty(strDate)) {
                    tvAddDate.setError("Must to Enter a start date");
                    return;
                }
                else if (TextUtils.isEmpty(strTime)) {
                    tvAddTime.setError("Must to Enter a start time");
                    return;
                }
                else{
                    EventDate ed = new EventDate(dtCurrAddDate);
                    lstEventDates.add(ed);

                    //lvDatesList.addView();
                }
                //TextView tvNewDate = new TextView();
//                datesList.addView(tvNewDate);
//                EventDate ed = new EventDate();
                //ed.
//                        lstEventDates.add();
//                onUpdateStudent();
                break;
            case R.id.btn_invite_friends:
                onInviteFriends();

                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        switch (adapterView.getId()) {
//            case R.id.lv_students:
//                onShowStudentDetails(position);
//                break;
//        }
    }

    private void onInviteFriends() {
        String strLastUpdateDate = tvLastEventUpdaeDate.getText().toString();
        if (TextUtils.isEmpty(strLastUpdateDate)) {
            tvLastEventUpdaeDate.setError("Must to Enter a last update date");
            return;
        }

        if (mListener != null) {
            mListener.onInviteFriendsClick(dtLastUpdate, nDuration);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof AddDatesListener) {
            mListener = (AddDatesListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement AddDatesListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    // <editor-fold defaultstate="collapsed" desc="date time picker">

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar cal = new GregorianCalendar(year, month, day, this.hourOfDay, this.minute);

        final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
        if (bIsAddDate){
            this.year = year;
            this.month = month;
            this.dayOfMonth = day;

            setCurrAddDate();

            DialogFragments.TimePickerFragment frag = new DialogFragments.TimePickerFragment();
            frag.show(getFragmentManager(), "time");
        }
        else{
            dtLastUpdate = cal.getTime();
            tvLastEventUpdaeDate.setText(dateFormat.format(dtLastUpdate));
        }
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
       this.hourOfDay = hourOfDay;
        this.minute = minute;

        setCurrAddDate();
    }

    private void setCurrAddDate(){
        final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
        Calendar cal = new GregorianCalendar(this.year, this.month, this.dayOfMonth, this.hourOfDay, this.minute);
        dtCurrAddDate = cal.getTime();

        tvAddDate.setText(dateFormat.format(dtCurrAddDate));
        tvAddTime.setText(hourOfDay + ":" + minute);
    }

    // </editor-fold>

    public interface AddDatesListener {
        void onInviteFriendsClick(Date lastUpdate, int duration);
    }
}

