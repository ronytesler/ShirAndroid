package com.shir.androidfinalproject.Models;

import com.shir.androidfinalproject.Enums.Status;

import java.util.Date;

/**
 * Created by shir on 23-Jul-17.
 */

public class UserEvent {
    public int eventID;
    public User user;
    public Status status;
    public EventLocation selectedEventLocation;
    public Date selectedDateRange;

    public  UserEvent(int nEventID, User uUser, Status sStatus)
    {
        this.eventID = nEventID;
        this.user = uUser;
        this.status = sStatus;
    }
}

