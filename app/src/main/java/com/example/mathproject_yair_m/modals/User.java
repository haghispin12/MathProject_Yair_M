package com.example.mathproject_yair_m.modals;

import android.net.Uri;

public class User {
    private String username;
    private  int score;
    private  int rate;
    private long id;
    private Uri uri;

    public User(){}

    public User(String username, int score, int rate, long id, Uri uri) {
        this.username = username;
        this.score = score;
        this.rate = rate;
        this.id = id;
        this.uri = uri;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }
}
