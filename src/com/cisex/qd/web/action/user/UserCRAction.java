package com.cisex.qd.web.action.user;

import com.cisex.qd.web.action.ApiAction;
import com.cisex.qd.web.api.ApiResult;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: huaiwang
 * Date: 12-9-10
 * Time: 下午5:27
 * To change this template use File | Settings | File Templates.
 */
public class UserCRAction extends ApiAction {
    @Override
    protected ApiResult executeApi() {
        HttpServletRequest req = ServletActionContext.getRequest();

        String strUid = req.getParameter("uid");
        String strDbid = req.getParameter("dbid");
        String strCmd = req.getParameter("cmd");
        String strRole = req.getParameter("role");

        int uid = Integer.valueOf(strUid);
        int dbid = Integer.valueOf(strDbid);

        if("del".equalsIgnoreCase(strCmd)) {
            if("0".equalsIgnoreCase(strRole)) {
                this.dao.getUsersDashboardsDao().delAdmin(dbid, uid);
            } else if ("1".equalsIgnoreCase(strRole)) {
                this.dao.getUsersDashboardsDao().delMember(dbid, uid);
            } else {

            }
        } else if("add".equalsIgnoreCase(strCmd)) {
            if("0".equalsIgnoreCase(strRole)) {
                this.dao.getUsersDashboardsDao().addAdmin(dbid, uid);
            } else if ("1".equalsIgnoreCase(strRole)) {
                this.dao.getUsersDashboardsDao().addMember(dbid, uid);
            } else {

            }
        } else {

        }

        HttpServletResponse resp = ServletActionContext.getResponse();
        try {
            resp.getWriter().write("SUCCESS");
            resp.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ApiResult.SUCCESS;
    }
}
