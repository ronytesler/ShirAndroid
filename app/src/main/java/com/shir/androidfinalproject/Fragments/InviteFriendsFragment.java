package com.shir.androidfinalproject.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shir.androidfinalproject.Models.User;
import com.shir.androidfinalproject.R;

import java.util.ArrayList;

public class InviteFriendsFragment extends Fragment {
    public static final String TAG = "InviteFriendsFragment";
    private static final String FRIENDS_LIST = "FRIENDS_LIST";
    private InviteFriendsListener mListener;
    private ArrayList<User> lstFriends;

    public InviteFriendsFragment() {
        // Required empty public constructor
    }

    public static InviteFriendsFragment newInstance(ArrayList<User> friendsList) {
        InviteFriendsFragment fragment = new InviteFriendsFragment();
        Bundle args = new Bundle();
        args.putSerializable(FRIENDS_LIST, friendsList);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            lstFriends = (ArrayList<User>)getArguments().getSerializable(FRIENDS_LIST);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_invite_friends, container, false);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof InviteFriendsListener) {
            mListener = (InviteFriendsListener) context;
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
    public interface InviteFriendsListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}