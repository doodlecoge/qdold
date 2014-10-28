package com.cisex.qd.web.action.widget;

import com.cisex.qd.apiclient.ApiCaller;
import com.cisex.qd.vo.Widget;
import com.cisex.qd.web.action.DataAccessAction;
import com.cisex.qd.widget.IWidget;
import com.cisex.qd.widget.WidgetManager;
import org.apache.struts2.ServletActionContext;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by vezhou.
 * Date: 2012-8-7
 * Time: 10:34:44
 */
public class GetSettingAction extends DataAccessAction {

    private int widgetId;

    @Override
    public String execute() throws Exception {
        HttpServletResponse response = ServletActionContext.getResponse();

        PrintWriter out = response.getWriter();
        Widget w = this.dao.getWidgetDao().findWidgetById(widgetId);
        if (w != null) {
            IWidget widget = WidgetManager.getInstance().delegate(w);
            VelocityContext context = new VelocityContext();
            context.put("widget", widget);
            context.put("base", ServletActionContext.getRequest().getContextPath());
            context.put("config", widget.getConfiguration());

            Set<String> set = ApiCaller.GetProjectCodes();
            String pcs = "[";
            Iterator<String> it = set.iterator();
            while (it.hasNext()) {
                pcs += "\"" + it.next() + "\"";
                if(it.hasNext()) pcs += ",";
            }
            pcs += "]";

            context.put("project_codes", pcs);
            Template t = Velocity.getTemplate(WIDGET_PATH + w.getType() + "/setting.vm");

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
