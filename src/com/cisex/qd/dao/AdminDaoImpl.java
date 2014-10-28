package com.cisex.qd.dao;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: huaiwang
 * Date: 12-9-13
 * Time: 下午4:33
 * To change this template use File | Settings | File Templates.
 */
public class AdminDaoImpl extends HibernateDaoSupport implements AdminDao {

    public boolean isAdmin(String username) {
        List lst = getHibernateTemplate().find("FROM AdminUsers where username=?", username);

        if(lst == null || lst.size() < 1) return false;
        else return true;
    }
}
