package com.cisex.qd.vo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by vezhou.
 * Date: 2011-12-6
 * Time: 13:56:41
 */
public class User  implements Serializable {
    private int id;
    private String username;
    private String fullname;
    private boolean enabled;
    private Set<Dashboard> dashboards = new HashSet<Dashboard>(0);

    private Set<UsersDashboards> usersDashboardsSet = new HashSet<UsersDashboards>(0);
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Set<Dashboard> getDashboards() {
        return dashboards;
    }

    public void setDashboards(Set<Dashboard> dashboards) {
        this.dashboards = dashboards;
    }


    public Set<UsersDashboards> getUsersDashboardsSet() {
        return usersDashboardsSet;
    }

    public void setUsersDashboardsSet(Set<UsersDashboards> usersDashboardsSet) {
        this.usersDashboardsSet = usersDashboardsSet;
    }
}
