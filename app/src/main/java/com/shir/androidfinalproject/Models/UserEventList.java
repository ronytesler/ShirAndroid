package com.shir.androidfinalproject.Models;

import com.shir.androidfinalproject.Enums.Status;

import java.util.ArrayList;

public class UserEventList<T> extends ArrayList<UserEvent> {

    public UserEvent getUser(String strUserID)
    {
        if (this.isEmpty()) {
            return null;
        }
        else {
            return this.stream().filter(ue -> ue.user.id == strUserID).findFirst().get();
        }
    }

    public UserEvent getHost()
    {
        if (this.isEmpty()) {
            return null;
        }
        else {
            return this.stream().filter(ue -> ue.status == Status.Host).findFirst().get();
        }
    }

    public boolean isUserInList(String strUserID)
    {
        if (this.isEmpty()) {
            return false;
        }
        else {
            return this.stream().anyMatch(ue -> ue.user.id == strUserID);
        }
    }
}
