package com.cisex.qd.dao;

import com.cisex.qd.vo.Project;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * Created by vezhou.
 * Date: 2011-12-6
 * Time: 9:15:55
 */
public class ProjectDaoImpl extends HibernateDaoSupport implements ProjectDao {
    public int getTotalProjectCount() {
        Number count = (Number)this.getHibernateTemplate().find("SELECT COUNT(*) FROM Project").get(0);
        return count.intValue();
//        try {
//            return ((Number)(this.getSession().createQuery("SELECT COUNT(*) FROM Project").uniqueResult())).intValue();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return -1;
//        }
    }

    public Project findProjectByName(String name) {
        List results = getHibernateTemplate().find("FROM Project WHERE name = ?", name);
        if (results == null || results.size() == 0)
            return null;
        else
            return (Project)results.get(0);
    }
}
