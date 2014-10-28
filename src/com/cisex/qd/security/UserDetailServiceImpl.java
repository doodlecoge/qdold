package com.cisex.qd.security;

import com.cisex.qd.dao.UserDao;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Created by vezhou.
 * Date: 2011-12-6
 * Time: 13:52:47
 */
public class UserDetailServiceImpl implements UserDetailsService {
    private UserDao userDao;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.cisex.qd.vo.User user = userDao.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User " + username + " not found!");
        }

        Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
        auths.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
//        return new User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, auths);
        return null;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
