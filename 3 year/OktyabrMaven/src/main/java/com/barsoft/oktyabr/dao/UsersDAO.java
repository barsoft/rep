/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barsoft.oktyabr.dao;

import com.barsoft.oktyabr.entities.Users;
import java.util.List;

/**
 *
 * @author maks
 */
public interface UsersDAO {
    UsersDAO INSTANCE_USERS= new UsersDAOImpl();
    List<Users> getUsers();
    void addUser(Users seedType);
    //и метод с которым мы будем работать
}