package com.cisex.qd.vo;

/**
 * Created with IntelliJ IDEA.
 * User: huaiwang
 * Date: 12-9-3
 * Time: 下午2:12
 * To change this template use File | Settings | File Templates.
 */
public class UsersDashboards {
    UsersDashboardsPK usersDashboardsPK;

    private int role;

    private User user;


    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public UsersDashboardsPK getUsersDashboardsPK() {
        return usersDashboardsPK;
    }

    public void setUsersDashboardsPK(UsersDashboardsPK usersDashboardsPK) {
        this.usersDashboardsPK = usersDashboardsPK;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
