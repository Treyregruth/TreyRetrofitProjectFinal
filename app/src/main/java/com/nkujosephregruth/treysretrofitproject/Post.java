package com.nkujosephregruth.treysretrofitproject;

public class Post {
    private int userID;
    private int id;
    private String title;
    private String body;

    public int getUserID() {
        return userID;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "Post{" +
                "title='" + title + '\'' +
                '}';
    }
}
