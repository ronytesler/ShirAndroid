package com.shir.androidfinalproject.Fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.shir.androidfinalproject.R;

import java.util.ArrayList;

public class AddEventDetailsFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "AddEventDetailsFragment";
    private AddEventDetailsListener mListener;

    Button btnAddPhotos;
    LinearLayout llEventImages;
    EditText etEventTitle;
    EditText etEventDescription;
    FloatingActionButton btnAddLocation;

    ArrayList<Bitmap> photosList;
    String strTitle;
    String strDescription;

    public AddEventDetailsFragment() {
    }

    public static AddEventDetailsFragment newInstance() {
        AddEventDetailsFragment fragment = new AddEventDetailsFragment();
//        Bundle args = new Bundle();
//        args.putSerializable(STUDENT, student);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {}
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_event_details, container, false);

        btnAddPhotos = (Button)view.findViewById(R.id.btn_add_photos);
        llEventImages = (LinearLayout)view.findViewById(R.id.ll_event_images);
        etEventTitle = (EditText)view.findViewById(R.id.et_event_title);
        etEventDescription= (EditText)view.findViewById(R.id.et_event_description);
        btnAddLocation= (FloatingActionButton)view.findViewById(R.id.btn_add_location);

        btnAddPhotos.setOnClickListener(this);
        btnAddLocation.setOnClickListener(this);

        initDataMembers();

        // Inflate the layout for this fragment
        return view;
    }

    private void initDataMembers(){
        photosList = new ArrayList<>();
        strTitle = "";
        strDescription = "";
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof AddEventDetailsListener) {
            mListener = (AddEventDetailsListener) context;
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add_photos:
                //TODO SHIR - HOW GET PHOTOS FROM PHONE
                break;
            case R.id.btn_add_location:

                String title = etEventTitle.getText().toString();
                String description = etEventDescription.getText().toString();
                if (TextUtils.isEmpty(title)) {
                    etEventTitle.setError("Must to Enter a title");
                    return;
                }
                else if (TextUtils.isEmpty(description)) {
                    etEventDescription.setError("Must to Enter a description");
                    return;
                }
                else {
                    this.strTitle = title;
                    this.strDescription = description;

                    if (mListener != null)
                        mListener.onAddLocationsClick
                                (this.photosList, this.strTitle, this.strDescription);
                }

                break;
        }
    }
    public interface AddEventDetailsListener {
        void onAddLocationsClick(ArrayList<Bitmap> photosList, String Title, String Description);
    }
}
