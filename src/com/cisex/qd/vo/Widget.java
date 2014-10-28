package com.cisex.qd.vo;


/**
 * Created by vezhou.
 * Date: 2012-8-9
 * Time: 16:15:45
 */
public class Widget {
    private int id;
    private String setting;
    private int dashboardId;
    private String type;
    private String name;

    public int getId() { 
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSetting() {
        return setting;
    }

    public void setSetting(String setting) {
        this.setting = setting;
    }

    public int getDashboardId() {
        return dashboardId;
    }

    public void setDashboardId(int dashboardId) {
        this.dashboardId = dashboardId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
