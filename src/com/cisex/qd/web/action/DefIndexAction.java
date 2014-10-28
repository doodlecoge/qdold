package com.cisex.qd.web.action;

import com.cisex.qd.vo.Dashboard;
import com.cisex.qd.web.api.ApiResult;
import org.apache.struts2.ServletActionContext;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.tools.generic.MathTool;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * User: huaiwang
 * Date: 12-9-11
 * Time: 上午9:26
 * To change this template use File | Settings | File Templates.
 */
public class DefIndexAction extends ApiAction {

    @Override
    protected ApiResult executeApi() {
        try {
            HttpServletResponse resp = ServletActionContext.getResponse();

            String username = getUsername();

//            Object o = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//            boolean b = o instanceof UserDetails;
//            if(username != null && !"anonymoususer".equalsIgnoreCase(username)) {
//                resp.sendRedirect("widget/index");
//                resp.getWriter().close();
//                return null;
//            }

//            User u = this.dao.getUserDao().findByUserName(username);

            PrintWriter out = resp.getWriter();

            Dashboard d = this.dao.getDashboardDao().findDashboardById(2);

            if (d != null) {
                VelocityContext context = new VelocityContext();
                context.put("dashboard", d);
                context.put("base", ServletActionContext.getRequest().getContextPath());
                context.put("math", new MathTool());
                boolean b = isLoggedIn();
                context.put("logged", b);
                context.put("username", username);
                context.put("fullname", getFullname());

                Template t = Velocity.getTemplate("/index.vm");
                try {
                    t.merge(context, out);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                out.close();
            }
        } catch (Exception e) {

        }

        return ApiResult.SUCCESS;
    }
}
