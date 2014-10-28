package com.cisex.qd.dao;

import com.cisex.qd.vo.Dashboard;
import com.cisex.qd.vo.User;
import com.cisex.qd.vo.UsersDashboards;
import com.cisex.qd.vo.Widget;
import net.sf.json.JSONArray;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vezhou.
 * Date: 2012-8-9
 * Time: 17:01:38
 */
public class DashboardDaoImpl extends HibernateDaoSupport implements DashboardDao {
    public Dashboard findDashboardById(int id) {
        List results = this.getHibernateTemplate().find("FROM Dashboard WHERE id=?", id);
        if (results.isEmpty())
            return null;
        else
            return (Dashboard) results.get(0);
//        return (Dashboard) this.getSession().createQuery("FROM Dashboard WHERE id=?").setInteger(0, id).uniqueResult();
    }

    public Dashboard findDashboardByName(String name) {
        List results = this.getHibernateTemplate().find("FROM Dashboard WHERE name=?", name);

        if (results.isEmpty())
            return null;
        else
            return (Dashboard) results.get(0);
    }

    public void saveDashboard(Dashboard dashboard) {
        this.getHibernateTemplate().update("Dashboard", dashboard);
    }

    public List getAllDashboardsByUserName(String username) {
        List<User> users = this.getHibernateTemplate().find("FROM Users where username=?", username);

        if (users == null || users.size() < 1) return null;

        User user = users.get(0);

        return getAllDashboardsByUserId(user.getId());
    }

    public List<Dashboard> getAllDashboardsByUserId(int uid) {
        List dbs = this.getHibernateTemplate().find("FROM UsersDashboards where usersDashboardsPK.userId=?",
                Integer.valueOf(uid));


        if (dbs == null || dbs.size() < 1) return null;

        List dbids = new ArrayList();

        for (int i = 0; i < dbs.size(); i++) {
            UsersDashboards db = (UsersDashboards) dbs.get(i);
            dbids.add(db.getUsersDashboardsPK().getDashboardId());
        }

        List results = this.getHibernateTemplate().findByNamedParam("FROM Dashboard where id in (:dbids)", "dbids", dbids);

//        List results = this.getHibernateTemplate().find("FROM Dashboard");

        if (results.isEmpty())
            return null;
        else
            return results;
    }

    public List getAllDashboards() {

//        this.getSession().enableFilter("adminUser").setParameter("cat", 1);
//
//        this.getHibernateTemplate().enableFilter("adminUser");

//        String[] sss = this.getHibernateTemplate().getFilterNames();

//        this.getSession().createQuery("")

//        List<Dashboard> results = this.getSession().createQuery("FROM Dashboard where type2=0").list();
        List<Dashboard> results = getHibernateTemplate().find("FROM Dashboard where type2=2");
//        List results = this.getHibernateTemplate().find("FROM Dashboard");

        for (Dashboard db : results) {
            db.getUsers();
        }

        if (results.isEmpty())
            return null;
        else
            return results;
    }

    public Dashboard createDashboard(String name, int columns, int type, int pub) {
        JSONArray jarr = new JSONArray();
        for (int i = 0; i < columns; i++) {
            jarr.add(new JSONArray());
        }

        Dashboard db = new Dashboard();

        db.setName(name);
        db.setLayout(jarr.toString());
        db.setType2(type);
        db.setPub(pub);

        Integer id = (Integer) this.getHibernateTemplate().save("Dashboard", db);

        db.setId(id);

        return db;
    }

    public Dashboard getUserDashboardByUserName(String username) {
        User user = (User) this.getHibernateTemplate().find("FROM Users where username=?", username).get(0);

        return getUserDashboardByUserId(user.getId());

    }

    public Dashboard getUserDashboardByUserId(int id) {
        List lst = getHibernateTemplate().find("FROM Dashboard where type2=?", id);

        if (lst == null || lst.size() < 1)
            return null;

        return (Dashboard) lst.get(0);
    }

    public List<Dashboard> getPublicDashboards() {
        List<Dashboard> lst = getHibernateTemplate().find("FROM Dashboard where pub=? order by rank asc", 1);

        return lst;
    }

    public void deleteDashboard(int dbid) throws Exception {
        SessionFactory sf = getSessionFactory();

        List lst = getHibernateTemplate().find("FROM Dashboard where id=?", dbid);

        if (lst == null || lst.size() == 0) throw new Exception("Failed to find dashboard.");

        Dashboard db = (Dashboard) lst.get(0);

        List<UsersDashboards> uds = getHibernateTemplate().find("FROM UsersDashboards where usersDashboardsPK.dashboardId=?", dbid);

        List<Widget> widgets = getHibernateTemplate().find("FROM Widget where dashboardId=?", dbid);


        Session s = sf.openSession();
        Transaction tx = null;

        try {
            tx = s.beginTransaction();


            for (Widget w : widgets) {
                s.delete("Widget", w);
            }

            for(UsersDashboards ud : uds) {
                s.delete("UsersDashboards", ud);
            }

            s.delete("Dashboard", db);


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw e;
        } finally {
            s.close();
        }
    }
}
