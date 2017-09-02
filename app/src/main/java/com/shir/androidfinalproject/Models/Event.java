package com.shir.androidfinalproject.Models;

import android.graphics.Bitmap;
import android.media.Image;

import com.shir.androidfinalproject.Enums.Status;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by shir on 22-Jul-17.
 */
public class Event implements Serializable {
    public int id;
    public String title;
    public String description;
    public ArrayList<Bitmap> photosList;
    public Date lastUpdateDate;
    public int duration;
    public VotesList<EventDate> dateList;
    public VotesList<EventLocation> locationsList;
    public UserEventList<UserEvent> usersList;

    public Event(int nEventID, User uHost) {
        this.id = nEventID;
        this.usersList = new UserEventList<UserEvent>();
        this.usersList.add(new UserEvent(this.id, uHost, Status.Host));
    }

    public boolean addUserEvent(User user)
    {
        boolean bUserAdded = false;

        if (!this.usersList.isUserInList(user.id))
        {
            this.usersList.add(new UserEvent(this.id, user, Status.Invited));
            bUserAdded = true;
        }

        return bUserAdded;
    }
}