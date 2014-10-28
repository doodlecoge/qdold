package com.cisex.qd.dao;

import com.cisex.qd.vo.User;

import java.util.List;

/**
 * Created by vezhou.
 * Date: 2011-12-6
 * Time: 13:48:17
 */
public interface UserDao {
    public User findByUserName(String username);

    public User addUser(String username, String full_name);

    public List<User> searchUser(String ptn);
}
