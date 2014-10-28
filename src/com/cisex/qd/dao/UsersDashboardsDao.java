package com.cisex.qd.dao;

import com.cisex.qd.vo.Dashboard;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: huaiwang
 * Date: 12-9-5
 * Time: 上午8:54
 * To change this template use File | Settings | File Templates.
 */
public interface UsersDashboardsDao {
//    public Dashboard getDefaultDashboard(String username);

//    public void createDefaultDashboard(int uid, int dbid);

    public boolean isOwnerOf(String username, int dbid);

    public void addAdmin(int dbid, int uid);

    public void delAdmin(int dbid, int uid);

    public void addMember(int dbid, int uid);

    public void delMember(int dbid, int uid);

    public List getAllMyDashboards(int uid);

    public List<Dashboard> getAllProjDashboards();
}
