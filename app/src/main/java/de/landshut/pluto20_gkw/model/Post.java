package de.landshut.pluto20_gkw.model;

import com.google.firebase.database.DataSnapshot;

public class Post {
    public String uid;
    public String author;
    public String title;
    public String text;
    public long timestamp;
    public String firebaseKey;

    public Post(String uid, String author, String title, String text, long timestamp, String firebaseKey) {
        this.uid = uid;
        this.author = author;
        this.title = title;
        this.text = text;
        this.timestamp = timestamp;
        this.firebaseKey = firebaseKey;
    }

    public Post(){}

    public static Post fromSnapshot(DataSnapshot dataSnapshot){
        Post p = new Post();

        p.uid = (String) dataSnapshot.child("uid").getValue();
        p.author = (String) dataSnapshot.child("author").getValue();
        p.text = (String) dataSnapshot.child("body").getValue(); // TODO fix naming for production
        p.title = (String) dataSnapshot.child("title").getValue();
        p.timestamp = (long) dataSnapshot.child("timestamp").getValue();
        p.firebaseKey = dataSnapshot.getKey();

        return p;
    }

}
