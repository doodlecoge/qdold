package com.cisex.qd.web.action.widget;

import com.cisex.qd.dao.Dao;
import com.cisex.qd.vo.Widget;
import com.cisex.qd.web.action.DataAccessAction;
import com.cisex.qd.widget.IWidget;
import com.cisex.qd.widget.WidgetManager;
import org.apache.struts2.ServletActionContext;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.tools.generic.MathTool;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by vezhou.
 * Date: 2012-8-13
 * Time: 10:41:40
 */
public class GetViewAction extends DataAccessAction {
    private int widgetId;

    @Override
    public String execute() throws Exception {
        HttpServletResponse response = ServletActionContext.getResponse();

        PrintWriter out = response.getWriter();
        Widget w = this.dao.getWidgetDao().findWidgetById(widgetId);
        if (w != null) {
            WebApplicationContext springContext = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
            Dao dao = (Dao)springContext.getBean("theDao");
            IWidget widget = WidgetManager.getInstance().delegate(w);
            widget.setDao(dao);
            widget.loadData();
            VelocityContext context = new VelocityContext();
            context.put("widget", widget);
            context.put("data", widget.getData());
            context.put("setting", widget.getConfiguration());
            context.put("base", ServletActionContext.getRequest().getContextPath());
            context.put("math", new MathTool());
            context.put("rptBaseUrl", "http://ta.cisex.com.cn");
            Template t = Velocity.getTemplate(WIDGET_PATH + w.getType() + "/view.vm");
            t.merge(context, out);
            out.close();
        } else {
            out.print("Sorry widget " + widgetId + " NOT FOUND!!!");
            out.close();
        }
        return SUCCESS;
    }

    public void setWidgetId(int widgetId) {
        this.widgetId = widgetId;
    }
}
