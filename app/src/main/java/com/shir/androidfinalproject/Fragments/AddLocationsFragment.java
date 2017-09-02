package com.shir.androidfinalproject.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shir.androidfinalproject.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddLocationsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddLocationsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddLocationsFragment extends Fragment {
    private static final String TAG = "AddLocationsFragment";

    private OnFragmentInteractionListener mListener;

    public AddLocationsFragment() {
        // Required empty public constructor
    }

    public static AddLocationsFragment newInstance() {

        AddLocationsFragment fragment = new AddLocationsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_locations, container, false);

//        alEvents = common.Instance().getAllEvents();
//        ListAdapter adapter = new EventsListAdapter(getActivity(), alEvents);
//
//        lvEvents = (ListView) view.findViewById(R.id.lv_events);
//
//        // Binding resources Array to ListAdapter
//        lvEvents.setAdapter(adapter);
//
//        // listening to single list item on click
//        lvEvents.setOnItemClickListener(this);

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
