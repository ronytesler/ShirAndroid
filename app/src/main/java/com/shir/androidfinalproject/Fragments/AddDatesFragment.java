package com.shir.androidfinalproject.Fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;

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
        DatePickerDialog.OnDateSetListener {
    public static final String TAG = "AddDatesFragment";
    //private static final String STUDENT = "student";

    TextView tvLastEventUpdaeDate;
    ImageView ivAddLastUpdateDate;
    Date dtLastUpdate;

    NumberPicker eventTime;
    int nEventTime;

    EditText addEventStartDate;
    ImageView ivAddDate;
    ListView datesList;
    FloatingActionButton btnInviteFriends;

    ArrayList<EventDate> lstEventDates;

    //private Student mStudent;

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

        //last update date
        tvLastEventUpdaeDate = (TextView) view.findViewById(R.id.tv_last_update_date);
        ivAddLastUpdateDate = (ImageView) view.findViewById(R.id.iv_last_update_date);


        eventTime = (NumberPicker) view.findViewById(R.id.np_event_time);
        addEventStartDate = (EditText) view.findViewById(R.id.et_add_event_start_date);
        ivAddDate = (ImageView) view.findViewById(R.id.iv_add_date);
        datesList = (ListView) view.findViewById(R.id.lv_dates_list);
        btnInviteFriends = (FloatingActionButton) view.findViewById(R.id.btn_invite_friends);

        // listening to single list item on click
        eventTime.setOnClickListener(this);
        ivAddDate.setOnClickListener(this);
        btnInviteFriends.setOnClickListener(this);

        nEventTime = 1;
        lstEventDates = new ArrayList<>();

        eventTime.setMaxValue(8760);
        eventTime.setMinValue(1);
        eventTime.setWrapSelectorWheel(false);

        return view;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.np_event_time:
                nEventTime = eventTime.getValue();
                break;
            case R.id.iv_add_date:
//                TextView tvNewDate = new TextView();
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

    private void onInviteFriends() {
        String strLastUpdateDate = tvLastEventUpdaeDate.getText().toString();
        if (TextUtils.isEmpty(strLastUpdateDate)) {
            tvLastEventUpdaeDate.setError("Must to Enter a last update date");
            return;
        }

        if (mListener != null) {
            mListener.onInviteFriendsClick(dtLastUpdate);
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

    public void onDatePickerClick(View view) {
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.show(getFragmentManager(), "date");
    }

    private void setDate(final Calendar calendar) {
        final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
        dtLastUpdate = calendar.getTime();
        tvLastEventUpdaeDate.setText(dateFormat.format(dtLastUpdate));
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar cal = new GregorianCalendar(year, month, day);
        setDate(cal);
    }

    public static class DatePickerFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(),
                    (DatePickerDialog.OnDateSetListener)
                            getActivity(), year, month, day);
        }
    }

    // </editor-fold>

    public interface AddDatesListener {
        void onInviteFriendsClick(Date LastUpdate);
    }
}

