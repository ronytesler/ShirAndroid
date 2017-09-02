package com.shir.androidfinalproject.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;

import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
//import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shir.androidfinalproject.Models.User;
import com.shir.androidfinalproject.Models.common;
import com.shir.androidfinalproject.R;
import com.shir.androidfinalproject.data.DataManager;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.Arrays;

import static com.facebook.FacebookSdk.getApplicationContext;

public class LoginActivity extends AppCompatActivity
        implements View.OnClickListener {
    private static final String TAG = "LoginActivity";
    LoginButton loginBtn;
    TextView tvText;
    CallbackManager callbackManager;
    ImageView image;
    AccessToken accessToken;
    FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener firebaseAuthListener;

    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mCondiotionRef = mRootRef.child("condition");

    DatabaseReference mUsersRef = mRootRef.child("users");

    @Override
    protected void onStart(){
        super.onStart();

        mCondiotionRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue(String.class);
                tvText.setText(text);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//
//        firebaseAuth = FirebaseAuth.getInstance();
//        startActivityForResult(AuthUI.getInstance()
//                .createSignInIntentBuilder()
//                .setProviders(
//                        AuthUI.FACEBOOK_PROVIDER,
//                        AuthUI.EMAIL_PROVIDER)
//                .build(), 1);

        initControls();
        logInWithFacebook();
        // HASHKEY cSoxPH00SgxvFfCVOExPXUv23QE=

        if (accessToken != null){
            accessToken = com.facebook.AccessToken.getCurrentAccessToken();
        }

        LoginManager.getInstance().logOut();
        DataManager.getInstance(this).setLoginWithFacebook(false);

        if (!DataManager.getInstance(this).isLoggedIn()) {
            logInWithFacebook();
        } else {
            // the user is logged in :)
            goToMainActivity();
        }
    }

    private void initControls(){
        callbackManager = CallbackManager.Factory.create();
        tvText = (TextView)findViewById(R.id.tvFaceBookStatus);
        image = (ImageView)findViewById(R.id.fbImage);
        loginBtn = (LoginButton)findViewById(R.id.fb_login_btn);
        loginBtn.setReadPermissions(Arrays.asList("email"));
        //loginBtn.setPublishPermissions(Arrays.asList("public_profile, user_friends, email"));

    }

    private void logInWithFacebook() {
        loginBtn.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                accessToken = loginResult.getAccessToken();
                GraphRequest req = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback(){
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response){
                        DataManager.getInstance(getApplicationContext()).setLoginWithFacebook(true);
                        displayUserInfo(object);
                    }
                });

                Bundle bundle = new Bundle();
                bundle.putString("fields", "id, first_name, last_name, email");
                req.setParameters(bundle);
                req.executeAsync();

//                mCondiotionRef.setValue(common.Instance().getUserID());
                tvText.setText("Login Success");
            }

            @Override
            public void onCancel() {
                tvText.setText("Login canceled");
            }

            @Override
            public void onError(FacebookException error) {
                tvText.setText("network error " +  error.toString());
            }
        });

        loginBtn.setOnClickListener(this);
    }

    private void displayUserInfo(JSONObject object) {

        try {
//            String strUserDetails = response.getRawResponse();
//            JSONObject jsonObject = new JSONObject(strUserDetails);

            String strFacebbokID = object.getString("id");
            String strFirstName = object.getString("first_name");
            String strLastName = object.getString("last_name");
            String strEmail = object.getString("email");
            // tyep can be normal, large
            String strImage = "http://graph.facebook.com/" + strFacebbokID + "/picture?type=large";
            Glide.with(getApplicationContext()).load(strImage).into(image);
            image.buildDrawingCache();
            Bitmap bmpImage = image.getDrawingCache();

            User connectedUser = new User(strFacebbokID, strFirstName, strLastName, strEmail, bmpImage);
            common.Instance().setCurrentUser(connectedUser);
            mCondiotionRef.setValue(common.Instance().getUserID());

            mUsersRef.child(connectedUser.id).setValue(connectedUser);

         //   Log.d(TAG, "onCompleted: jsonObject::" + object);
            //goToMainActivity();
//            String struser_friends = jsonObject.getString("user_friends");
//            Log.d(TAG, "onCompleted: user_friends::" + struser_friends);

        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View view) {
        int viewID = view.getId();

        switch (viewID){
            case R.id.fb_login_btn:
                LoginManager.getInstance().logInWithReadPermissions
                        (this, Arrays.asList("public_profile, email"));

//                loginBtn.setReadPermissions("friends");
                break;
        }
    }

    private void goToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
//        Bundle bundle = new Bundle();
//        bundle.putSerializable(EventDetailsActivity.CURR_EVENT, event);
//        i.putExtras(bundle);
        startActivity(intent);
        finish();
    }
}
