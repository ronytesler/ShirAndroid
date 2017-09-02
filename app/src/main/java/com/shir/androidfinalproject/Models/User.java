package com.shir.androidfinalproject.Models;

import android.graphics.Bitmap;
import android.media.Image;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shir on 23-Jul-17.
 */

public class User {

    public String id;
    public String firstName;
    public String lastName;
    public String emailAddress;
    private Bitmap picture;
    public String pictureUrl;
    public ArrayList<Event> eventsList = new ArrayList<>();

    public ArrayList<User> friendsList = new ArrayList<>();

    public Bitmap getBitmap()
    {
        return picture;
    }
    public User()
    {

    }
    public User(String strID, String strFirstName, String strLastName, String strEmail, Bitmap bmpPic, String picUrl)
    {
        this.id = strID;
        this.firstName = strFirstName;
        this.lastName = strLastName;
        this.emailAddress = strEmail;
        this.picture = bmpPic;
        this.eventsList = new ArrayList<Event>();
        this.friendsList = new ArrayList<User>();
        this.pictureUrl = picUrl;
    }

    public String getFullName()
    {
        return this.firstName + " " + this.lastName;

    }

    public boolean isEventInList(int nEventID)
    {
        if (this.eventsList.isEmpty()) {
            return false;
        }
        else {
            return this.eventsList.stream().anyMatch(e -> e.id == nEventID);
        }
    }

    public int getIndexOfEvent(int nEventID)
    {
        return this.eventsList.indexOf(
                this.eventsList.stream().filter(x-> x.id == nEventID).findFirst());

    }
}
