package com.barsoft.memepost.entities;


import com.barsoft.memepost.entities.User;

public class Comment {

        String text;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }
        User user;
        
        public Comment()
        {
        }
    }