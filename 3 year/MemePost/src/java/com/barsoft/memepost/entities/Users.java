/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barsoft.memepost.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 *
 * @author maks
 */
public class Users implements Serializable {

    public Users() {

    }

    private Map<User, List<Post>> users = new HashMap<User, List<Post>>();

    public Map<User, List<Post>> getUsers() {
        return users;
    }
}