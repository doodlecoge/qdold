package com.cisex.qd.dao;

import com.cisex.qd.vo.Widget;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * Created by vezhou.
 * Date: 2012-8-9
 * Time: 16:25:59
 */
public class WidgetDaoImpl extends HibernateDaoSupport implements WidgetDao {
    public Widget findWidgetById(int id) {
        List results = this.getHibernateTemplate().find("FROM Widget WHERE id=?", id);
        if (results.isEmpty())
            return null;
        else
            return (Widget) results.get(0);
    }

    public void saveWidget(Widget widget) {
        getHibernateTemplate().update("Widget", widget);
    }

    public Widget createWidget(int dashboardId, String widgetType, String name) {
        final Widget widget = new Widget();
        widget.setDashboardId(dashboardId);
        widget.setType(widgetType);
        widget.setSetting("{}");
        widget.setName(name);
        this.getHibernateTemplate().save("Widget", widget);
        return widget;
    }

    public void deleteWidget(Widget widget) {
        this.getHibernateTemplate().delete("Widget", widget);
    }
}
