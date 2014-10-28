package com.cisex.qd.dao;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * Created by vezhou.
 * Date: 2012-8-13
 * Time: 12:30:08
 */
public class Dao extends HibernateDaoSupport {
    private DashboardDao dashboardDao;
    private ProjectDao projectDao;
    private UserDao userDao;
    private WidgetDao widgetDao;
    private UsersDashboardsDao usersDashboardsDao;
    private AdminDao adminDao;

    public DashboardDao getDashboardDao() {
        return dashboardDao;
    }

    public void setDashboardDao(DashboardDao dashboardDao) {
        this.dashboardDao = dashboardDao;
    }

    public ProjectDao getProjectDao() {
        return projectDao;
    }

    public void setProjectDao(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public WidgetDao getWidgetDao() {
        return widgetDao;
    }

    public void setWidgetDao(WidgetDao widgetDao) {
        this.widgetDao = widgetDao;
    }

    public UsersDashboardsDao getUsersDashboardsDao() {
        return usersDashboardsDao;
    }

    public void setUsersDashboardsDao(UsersDashboardsDao usersDashboardsDao) {
        this.usersDashboardsDao = usersDashboardsDao;
    }


    public AdminDao getAdminDao() {
        return adminDao;
    }

    public void setAdminDao(AdminDao adminDao) {
        this.adminDao = adminDao;
    }
}
