package de.landshut.pluto20_gkw.model;

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
}
