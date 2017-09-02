package com.shir.androidfinalproject.Fragments;

import android.content.Context;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.shir.androidfinalproject.Models.EventDate;
import com.shir.androidfinalproject.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddDatesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddDatesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddDatesFragment extends Fragment {
    public static final String TAG = "AddDatesFragment";
    //private static final String STUDENT = "student";

    EditText lastEventUpdaeDate;
    NumberPicker eventTime;
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
        lastEventUpdaeDate = (EditText)view.findViewById(R.id.et_last_update_date);
        eventTime = (NumberPicker)view.findViewById(R.id.np_event_time);
        addEventStartDate = (EditText)view.findViewById(R.id.et_add_event_start_date);
        ivAddDate = (ImageView)view.findViewById(R.id.iv_add_date);
        datesList = (ListView)view.findViewById(R.id.lv_dates_list);
        btnInviteFriends = (FloatingActionButton)view.findViewById(R.id.btn_invite_friends);

        // listening to single list item on click
        eventTime.setOnClickListener(this);
        ivAddDate.setOnClickListener(this);
        btnInviteFriends.setOnClickListener(this);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof EditStudentListener) {
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.eventTime:
                if (mListener != null) {
                    mListener.onEditCancelClick();
                }
                break;
            case R.id.ivAddDate:
                onUpdateStudent();
                break;
            case R.id.btnInviteFriends:
                onDeleteStudent();
                break;
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface AddDatesListener {

    }
