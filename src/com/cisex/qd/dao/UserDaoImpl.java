package com.cisex.qd.dao;

import com.cisex.qd.vo.User;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * Created by vezhou.
 * Date: 2011-12-6
 * Time: 13:48:29
 */
public class UserDaoImpl extends HibernateDaoSupport implements UserDao {
    public User findByUserName(String username) {
//        return (User) this.getSession().createQuery("FROM Users WHERE username=?").setString(0, username).uniqueResult();

        List<User> lst = getHibernateTemplate().find("FROM Users where username=?", username);
        if(lst == null || lst.size() == 0) return null;

        return lst.get(0);
    }

    public User addUser(String username, String full_name) {
        User u = new User();
        u.setUsername(username);
        u.setFullname(full_name);
        u.setEnabled(true);


        try{
            this.getHibernateTemplate().save("Users", u);
            return u;
        }catch (Exception e) {
            return null;
        }
    }

    public List<User> searchUser(String ptn) {
        List users = getHibernateTemplate().find("FROM Users where fullname like ?", "%" + ptn + "%");
        return users;
    }
}
