package com.shir.androidfinalproject.Enums;

public enum Status
{
    Host("Host"),
    Going("Going"),
    NotGoing("Not Going"),
    Invited("Invited");

    private String friendlyName;

    Status(String strFriendlyName){
        this.friendlyName = strFriendlyName;
    }

    @Override public String toString(){
        return friendlyName;
    }
}
