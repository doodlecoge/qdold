package com.cisex.qd.vo;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: huaiwang
 * Date: 12-9-4
 * Time: 下午4:24
 * To change this template use File | Settings | File Templates.
 */
public class UsersDashboardsPK implements Serializable {
    private int userId;
    private int dashboardId;

    public UsersDashboardsPK() {

    }

    public UsersDashboardsPK(int userId, int dashboardId) {
        this.userId = userId;
        this.dashboardId = dashboardId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDashboardId() {
        return dashboardId;
    }

    public void setDashboardId(int dashboardId) {
        this.dashboardId = dashboardId;
    }
}
