package com.gkepage.demo.db.model;


public class User {
    
    String id;
    String from;
    int stars;
    String to;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "User [from=" + from + ", id=" + id + ", stars=" + stars + ", to=" + to + "]";
    }
    
}