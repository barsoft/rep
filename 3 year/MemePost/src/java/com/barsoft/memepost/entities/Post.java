/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barsoft.memepost.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author maks
 */
public class Post implements Serializable {

    private String memUrl;
    private Date date;
    private int id;
    private String text;
    private Users likers = new Users();
    private Users dislikers = new Users();
    private ArrayList<Comment> comments = new ArrayList<Comment>();

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public Users getDislikers() {
        return dislikers;
    }

    public void setDislikers(Users dislikers) {
        this.dislikers = dislikers;
    }

    public Users getLikers() {
        return likers;
    }

    public void setLikers(Users likers) {
        this.likers = likers;
    }

    public String getText() {
        return text;
    }

    public void setText(String topic) {
        this.text = topic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMemUrl() {
        return memUrl;
    }

    public void setMemUrl(String memUrl) {
        this.memUrl = memUrl;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Post() {
        Random r=new Random();
        id = r.nextInt(1000000000);
    }

    public boolean userLikes(User u) {
        for (User liker : getLikers().getUsers().keySet()) {
            if (liker.equals(u)) {
                return true;
            }
        }
        return false;
    }

    public boolean userDislikes(User u) {
        for (User disliker : getDislikers().getUsers().keySet()) {
            if (disliker.equals(u)) {
                return true;
            }
        }
        return false;
    }
}
