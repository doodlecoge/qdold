package com.cisex.qd.web.action.widget;

import com.cisex.qd.vo.Dashboard;
import com.cisex.qd.vo.User;
import com.cisex.qd.web.action.DataAccessAction;
import org.apache.struts2.ServletActionContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: huaiwang
 * Date: 12-9-4
 * Time: 上午8:51
 * To change this template use File | Settings | File Templates.
 */
public class DashboardsAction extends DataAccessAction {
    private List allDashboards;
    private List<Dashboard> myDashboards;
    private Object myHomeDashboard;
    private List<Dashboard> publicDashboards;

    private boolean isLogged;

    @Override
    public String execute() throws Exception {
        if (!isAdmin())
            ServletActionContext.getResponse().sendRedirect("widget/index");

        this.isLogged = isLoggedIn();

        if (!this.isLogged)
            ServletActionContext.getResponse().sendRedirect("widget/index");

        String username = getUsername();

        User user = this.dao.getUserDao().findByUserName(username);

        myHomeDashboard = this.dao.getDashboardDao().getUserDashboardByUserId(user.getId());
        myDashboards = this.dao.getDashboardDao().getAllDashboardsByUserId(user.getId());
        allDashboards = this.dao.getDashboardDao().getAllDashboards();

        publicDashboards = this.dao.getDashboardDao().getPublicDashboards();

        if (myDashboards != null) {
            List<Dashboard> lst = new ArrayList<Dashboard>();
            for (Dashboard d : publicDashboards) {
                boolean exists = false;
                for (Dashboard d2 : myDashboards) {
                    if (d2.getId() == d.getId()) {
                        exists = true;
                        break;
                    }
                }
                if (!exists) lst.add(d);
            }
            publicDashboards = lst;
        }

        return SUCCESS;
    }

    public List getAllDashboards() {
        return allDashboards;
    }

    public void setAllDashboards(List dashboards) {
        this.allDashboards = dashboards;
    }

    public List getMyDashboards() {
        return myDashboards;
    }

    public void setMyDashboards(List myDashboards) {
        this.myDashboards = myDashboards;
    }

    public Object getMyHomeDashboard() {
        return myHomeDashboard;
    }

    public void setMyHomeDashboard(Object myHomeDashboard) {
        this.myHomeDashboard = myHomeDashboard;
    }

    public boolean isLogged() {
        return isLogged;
    }

    public void setLogged(boolean logged) {
        isLogged = logged;
    }

    public List<Dashboard> getPublicDashboards() {
        return publicDashboards;
    }

    public void setPublicDashboards(List<Dashboard> publicDashboards) {
        this.publicDashboards = publicDashboards;
    }
}
