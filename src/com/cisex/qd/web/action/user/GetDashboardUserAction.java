package com.cisex.qd.web.action.user;

import com.cisex.qd.vo.Dashboard;
import com.cisex.qd.vo.User;
import com.cisex.qd.web.action.ApiAction;
import com.cisex.qd.web.api.ApiResult;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: huaiwang
 * Date: 12-9-11
 * Time: 上午8:23
 * To change this template use File | Settings | File Templates.
 */
public class GetDashboardUserAction extends ApiAction {
    @Override
    protected ApiResult executeApi() {
        HttpServletRequest req = ServletActionContext.getRequest();

        String strDbid = req.getParameter("id");
        String role = req.getParameter("r");

        int dbid = Integer.valueOf(strDbid);

        Dashboard d = this.dao.getDashboardDao().findDashboardById(dbid);
        PrintWriter out = null;
        try {
            out = ServletActionContext.getResponse().getWriter();
        } catch (IOException e) {}

        out.write("[");

        if("0".equalsIgnoreCase(role)) {
            Set<User> owners =  d.getOwners();

            Iterator<User> it =  owners.iterator();

            while (it.hasNext()) {
                User u = it.next();

                out.write("{");
                out.write("\"id\":" + u.getId() + ",");
                out.write("\"username\":" + "\"" + u.getUsername() + "\",");
                out.write("\"fullname\":" + "\"" + u.getFullname() + "\"");
                out.write("}");

                if (it.hasNext())
                    out.write(",");
            }
        } else if("1".equalsIgnoreCase(role)) {
            Set<User> users =  d.getUsers();

            Iterator<User> it =  users.iterator();

            while (it.hasNext()) {
                User u = it.next();

                out.write("{");
                out.write("\"id\":" + u.getId() + ",");
                out.write("\"username\":" + "\"" + u.getUsername() + "\",");
                out.write("\"fullname\":" + "\"" + u.getFullname() + "\"");
                out.write("}");

                if (it.hasNext())
                    out.write(",");
            }
        }

        out.write("]");
        out.close();
        return ApiResult.SUCCESS;
    }
}
