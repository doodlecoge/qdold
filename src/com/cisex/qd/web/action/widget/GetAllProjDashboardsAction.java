package com.cisex.qd.web.action.widget;

import com.cisex.qd.vo.Dashboard;
import com.cisex.qd.web.action.ApiAction;
import com.cisex.qd.web.api.ApiResult;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: huaiwang
 * Date: 12-10-10
 * Time: 上午9:59
 * To change this template use File | Settings | File Templates.
 */
public class GetAllProjDashboardsAction extends ApiAction {
    @Override
    protected ApiResult executeApi() {
        HttpServletResponse resp = ServletActionContext.getResponse();
        PrintWriter out = null;




        try {
            out = resp.getWriter();

            if(isAdmin() == false) {
                out.write("[]");
                out.close();
                return new ApiResult(ApiResult.ERROR_CODE);
            }

            List<Dashboard> lst = this.dao.getUsersDashboardsDao().getAllProjDashboards();

            int len = lst.size();

            out.write("[");

            for(int i = 0; i < len; i++) {
                if(i != 0) out.write(",");
                Dashboard d = lst.get(i);
                out.write("{\"id\":" + d.getId() + ", \"name\": \"" + d.getName() + "\"}");
            }


            out.write("]");
            out.close();
        } catch (Exception e) {
            out.write("[]");
            out.close();
        }

        return new ApiResult(ApiResult.SUCCESS_CODE);
    }
}
