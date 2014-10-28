package com.cisex.qd.dao;

import com.cisex.qd.vo.Widget;

/**
 * Created by vezhou.
 * Date: 2012-8-9
 * Time: 16:25:15
 */
public interface WidgetDao {
    public Widget findWidgetById(int id);

    public void saveWidget(Widget widget);

    public Widget createWidget(int dashboardId, String widgetType, String name);

    public void deleteWidget(Widget widget);
}
