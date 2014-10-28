package com.cisex.qd.dao;

import com.cisex.qd.vo.Dashboard;

import java.util.List;

/**
 * Created by vezhou.
 * Date: 2012-8-9
 * Time: 17:00:23
 */
public interface DashboardDao {
    public Dashboard findDashboardById(int id);

    public Dashboard findDashboardByName(String name);

    public void saveDashboard(Dashboard dashboard);

    public List getAllDashboardsByUserName(String username);

    public List<Dashboard> getAllDashboardsByUserId(int uid);

    public List getAllDashboards();

    public Dashboard createDashboard(String name, int columns, int type, int pub);

    public Dashboard getUserDashboardByUserName(String username);

    public Dashboard getUserDashboardByUserId(int id);

    public List<Dashboard> getPublicDashboards();


    // delete dashboard, and
    // it's widgets
    public void deleteDashboard(int dbid) throws Exception;



}
