package com.shir.androidfinalproject.Models;

import android.media.Image;
import android.provider.MediaStore;

import java.util.Date;
import java.util.List;

/**
 * Created by shir on 24-Jul-17.
 */

public class Post {
    private Event event;
    private User user;
    private String text;
    private Date date;
    private int likes;
    private List<Post> commentsList;
}
