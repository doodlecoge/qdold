package com.cisex.qd.web.action.widget;

import com.cisex.qd.web.action.ApiAction;
import com.cisex.qd.web.api.ApiResult;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: huaiwang
 * Date: 12-9-27
 * Time: 下午4:45
 * To change this template use File | Settings | File Templates.
 */
public class GetMyDashboardsAction extends ApiAction {
    @Override
    protected ApiResult executeApi() {
        HttpServletResponse resp = ServletActionContext.getResponse();
        PrintWriter out = null;

        try {
            out = resp.getWriter();

            int uid = this.dao.getUserDao().findByUserName(getUsername()).getId();
            List lst = this.dao.getUsersDashboardsDao().getAllMyDashboards(uid);

            int len = lst.size();

            out.write("[");

            for (int i = 0; i < len; i++) {
                Object[] db = (Object[])lst.get(i);
                if (i != 0) out.write(",");
                int id = Integer.valueOf(db[0].toString());
                String name = db[1].toString();
                out.write("{\"id\":" + id + ", \"name\": \"" + name + "\"}");
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
