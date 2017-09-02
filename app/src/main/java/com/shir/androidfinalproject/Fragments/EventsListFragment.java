package com.shir.androidfinalproject.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.shir.androidfinalproject.Models.common;
import com.shir.androidfinalproject.Models.Event;
import com.shir.androidfinalproject.Models.EventsListAdapter;
import com.shir.androidfinalproject.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EventsListListener} interface
 * to handle interaction events.
 * Use the {@link EventsListListener#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EventsListFragment extends Fragment implements AdapterView.OnItemClickListener {

    private EventsListListener mListener;
    private ListView lvEvents;
    private ArrayList<Event> alEvents;
    public static final String TAG = "EventsListFragment";

    public EventsListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment EventsListFragment.
     */
    public static EventsListFragment newInstance() {
        EventsListFragment fragment = new EventsListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_events_list, container, false);

        alEvents = common.Instance().getAllEvents();
        ListAdapter adapter = new EventsListAdapter(getActivity(), alEvents);

        lvEvents = (ListView) view.findViewById(R.id.lv_events);

        // Binding resources Array to ListAdapter
        lvEvents.setAdapter(adapter);

        // listening to single list item on click
        lvEvents.setOnItemClickListener(this);

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onAttach(Context context) {

        super.onAttach(context);
        if (context instanceof EventsListListener) {
            mListener = (EventsListListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement StudentsListListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        lvEvents = null;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        switch (adapterView.getId()) {
            case R.id.lv_events:
                onShowStudentDetails(position);
                break;
        }
    }

    private void onShowStudentDetails(int position) {
        Event event = alEvents.get(position);
        if (mListener != null) {
            mListener.onEventClick(event);
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
    public interface EventsListListener {
        void onEventClick(Event event);
    }
}
