package com.cisex.qd.web.action.widget;

import com.cisex.qd.vo.Dashboard;
import com.cisex.qd.vo.User;
import com.cisex.qd.web.action.DataAccessAction;
import org.apache.struts2.ServletActionContext;
import org.springframework.security.ldap.userdetails.InetOrgPerson;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by vezhou.
 * Date: 2012-8-7
 * Time: 13:19:52
 */
public class IndexAction extends DataAccessAction {

//    private List<Dashboard> myDashboards;
//    private Dashboard myHomeDashboard;
    private Dashboard dashboard;
    private List<Dashboard> publicDashboards;
    private List<Dashboard> dashboards;

    private boolean isLogged;
    private boolean isPublic;
    private boolean isOwner;



    @Override
    public String execute() throws Exception {
        this.publicDashboards = this.dao.getDashboardDao().getPublicDashboards();

        HttpServletRequest req = ServletActionContext.getRequest();
        String id = req.getParameter("id");


        int did = -1;
        try {
            did = Integer.valueOf(id);

            if (this.publicDashboards != null) {
                for (Dashboard d : this.publicDashboards) {
                    if (did == d.getId()) {
                        this.dashboard = d;
                        this.isPublic = true;
                        break;
                    }
                }
            }

            if (this.dashboard == null) {
                dashboard = this.dao.getDashboardDao().findDashboardById(did);
            }
        } catch (Exception e) {
            dashboard = null;
        }


        // dashboard not found,
        // use one of public
        if (this.dashboard ==null &&
                this.publicDashboards != null
                && this.publicDashboards.size() > 0) {
            this.dashboard = this.publicDashboards.get(0);
            this.isPublic = true;
        }

        isLogged = isLoggedIn();

        while(isLogged) {
            String un = getUsername();

            User u = this.dao.getUserDao().findByUserName(un);

            if(this.dashboard == null)
                break;

            isOwner = this.dao.getUsersDashboardsDao().isOwnerOf(un, this.dashboard.getId());


            this.dashboards = this.dao.getUsersDashboardsDao().getAllMyDashboards(u.getId());

            break;
        }




//        if (isLogged) {
//            String username = getUsername();
//
//            User user = this.dao.getUserDao().findByUserName(username);
//
//            this.myDashboards = this.dao.getDashboardDao().getAllDashboardsByUserId(user.getId());
//            this.myHomeDashboard = this.dao.getDashboardDao().getUserDashboardByUserId(user.getId());
//            this.publicDashboards = this.dao.getDashboardDao().getPublicDashboards();
//
//
//            this.isOwner = false;
//            if (dashboard != null) {
//                Set<User> set = dashboard.getOwners();
//                for (User u : set) {
//                    if (u.getId() == user.getId()) {
//                        this.isOwner = true;
//                        break;
//                    }
//                }
//            } else dashboard = this.myHomeDashboard;
//
//            if (myDashboards != null) {
//                List<Dashboard> lst = new ArrayList<Dashboard>();
//                for (Dashboard d : publicDashboards) {
//                    boolean exists = false;
//                    for (Dashboard d2 : myDashboards) {
//                        if (d2.getId() == d.getId()) exists = true;
//                    }
//                    if (!exists) lst.add(d);
//                }
//                this.publicDashboards = lst;
//            }
//
//        } else {
//            this.publicDashboards = this.dao.getDashboardDao().getPublicDashboards();
//            if (this.dashboard == null && this.publicDashboards.size() > 0)
//                this.dashboard = this.publicDashboards.get(0);
//        }


        return SUCCESS;
    }

    public Dashboard getDashboard() {
        return dashboard;
    }

    public void setDashboard(Dashboard dashboard) {
        this.dashboard = dashboard;
    }

//    public List getMyDashboards() {
//        return myDashboards;
//    }
//
//    public void setMyDashboards(List myDashboards) {
//        this.myDashboards = myDashboards;
//    }
//
//    public Dashboard getMyHomeDashboard() {
//        return myHomeDashboard;
//    }
//
//    public void setMyHomeDashboard(Dashboard myHomeDashboard) {
//        this.myHomeDashboard = myHomeDashboard;
//    }

    public boolean isOwner() {
        return isOwner;
    }

    public void setOwner(boolean owner) {
        isOwner = owner;
    }

    public List getPublicDashboards() {
        return publicDashboards;
    }

    public void setPublicDashboards(List publicDashboards) {
        this.publicDashboards = publicDashboards;
    }

    public boolean isLogged() {
        return isLogged;
    }

    public void setLogged(boolean logged) {
        isLogged = logged;
    }


    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public List<Dashboard> getDashboards() {
        return dashboards;
    }

    public void setDashboards(List<Dashboard> dashboards) {
        this.dashboards = dashboards;
    }

}
