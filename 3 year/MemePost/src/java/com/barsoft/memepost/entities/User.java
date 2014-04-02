/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barsoft.memepost.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author maks
 */
public class User implements Serializable {

    private String username = null;
    private String password = null;
    private String photoUrl = null;

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String user) {
        this.username = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User= " + username + " password= " + password;
    }

    @Override
    public int hashCode() {
        final int prime = 113;
        int hash = 1;
        hash = hash * prime + ((username == null) ? 0 : username.hashCode());
        return hash;
    }

    public boolean isRegisteredUser() {
        return (username != null) && (password != null);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != User.class) {
            return false;
        }
        final User temp = (User) obj;
        if (temp.username == null) {
            if (this.username != null) {
                return false;
            }
        } else {
            if (!temp.username.equals(this.username)) {
                return false;
            }
        }
        return true;
    }
}