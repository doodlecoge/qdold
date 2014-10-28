package com.cisex.qd.dao;

import com.cisex.qd.util.UserDashboardCat;
import com.cisex.qd.vo.Dashboard;
import com.cisex.qd.vo.User;
import com.cisex.qd.vo.UsersDashboards;
import com.cisex.qd.vo.UsersDashboardsPK;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: huaiwang
 * Date: 12-9-5
 * Time: 上午8:52
 * To change this template use File | Settings | File Templates.
 */
public class UsersDashboardsDaoImpl extends HibernateDaoSupport implements UsersDashboardsDao {

//    public Dashboard getDefaultDashboard(String username) {
//        User user = (User)this.getHibernateTemplate().find("FROM Users where username=?", username).get(0);
//        int id = user.getId();
//
//        List lst = getHibernateTemplate().find("FROM UsersDashboards where usersDashboardsPK.userId=? and cat=?", id, UserDashboardCat.DEFAULT.cat);
//        if(lst == null || lst.size() == 0) return null;
//
//        int dbid = ((UsersDashboards)lst.get(0)).getUsersDashboardsPK().getDashboardId();
//
//        List dashboards = getHibernateTemplate().find("FROM Dashboard where id=?", dbid);
//
//        if(dashboards == null || dashboards.size() == 0) return null;
//        return (Dashboard)dashboards.get(0);
//    }

//    public void createDefaultDashboard(int uid, int dbid) {
//        UsersDashboards ud = new UsersDashboards();
//        ud.setUsersDashboardsPK(new UsersDashboardsPK(uid, dbid));
//        ud.setRole(UserDashboardCat.DEFAULT.cat);
//
//        this.getHibernateTemplate().save("UsersDashboards", ud);
//    }

    public boolean isOwnerOf(String username, int dbid) {
        User user = (User)this.getHibernateTemplate().find("FROM Users where username=?", username).get(0);
        int id = user.getId();

        List rst = getHibernateTemplate().find("FROM UsersDashboards where usersDashboardsPK.userId=? and usersDashboardsPK.dashboardId=? and role=?",
                id, dbid, UserDashboardCat.OWNER.cat);

        return rst.size() > 0;
    }

    public void addAdmin(int dbid, int uid) {
        List lst = getHibernateTemplate().find("FROM UsersDashboards where usersDashboardsPK.userId=? and usersDashboardsPK.dashboardId=?", uid, dbid);
        if(lst.size() > 0) {
            UsersDashboards ud = (UsersDashboards) lst.get(0);
            ud.setRole(0);
            getHibernateTemplate().update("UsersDashboards", ud);

        } else {
            UsersDashboards ud = new UsersDashboards();
            ud.setRole(0);
            ud.setUsersDashboardsPK(new UsersDashboardsPK(uid, dbid));
            getHibernateTemplate().save("UsersDashboards", ud);
        }
    }

    public void delAdmin(int dbid, int uid) {
        List lst = getHibernateTemplate().find("FROM UsersDashboards where role=0 and usersDashboardsPK.userId=? and usersDashboardsPK.dashboardId=?", uid, dbid);
        if(lst != null && lst.size() > 0) {
            getHibernateTemplate().delete("UsersDashboards", lst.get(0));
        }
    }

    public void addMember(int dbid, int uid) {
        List lst = getHibernateTemplate().find("FROM UsersDashboards where usersDashboardsPK.userId=? and usersDashboardsPK.dashboardId=?", uid, dbid);
        if(lst.size() == 0) {
            UsersDashboards ud = new UsersDashboards();
            ud.setRole(1);
            ud.setUsersDashboardsPK(new UsersDashboardsPK(uid, dbid));
            getHibernateTemplate().save("UsersDashboards", ud);
        }
    }

    public void delMember(int dbid, int uid) {
        List lst = getHibernateTemplate().find("FROM UsersDashboards where role=1 and usersDashboardsPK.userId=? and usersDashboardsPK.dashboardId=?", uid, dbid);
        if(lst != null && lst.size() > 0) {
            getHibernateTemplate().delete("UsersDashboards", lst.get(0));
        }
    }

    public List getAllMyDashboards(int uid) {
        List<UsersDashboards> lst = getHibernateTemplate().find("FROM UsersDashboards where usersDashboardsPK.userId=?", uid);

        if(lst == null || lst.size() == 0) return new ArrayList();

        List<Integer> ids = new ArrayList<Integer>();

        for(UsersDashboards udb : lst) {
            ids.add(udb.getUsersDashboardsPK().getDashboardId());
        }


        List dbs = getHibernateTemplate().findByNamedParam("select id, name, layout, type2, pub from Dashboard where id in (:ids)", "ids", ids);

        return dbs;
    }

    public List<Dashboard> getAllProjDashboards() {
        List<Dashboard> lst = getHibernateTemplate().find("FROM Dashboard where type2=0");

        return lst;
    }


}
