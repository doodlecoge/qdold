package com.cisex.qd.widget;

import com.cisex.qd.vo.Widget;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vezhou.
 * Date: 2012-8-8
 * Time: 15:18:30
 */
public class WidgetManager {
    private Map<String, Class> widgets = new HashMap<String, Class>();

    private static WidgetManager singleton;

    private WidgetManager() {
        widgets.put(WidgetTypes.PRODUCT, ProductWidget.class);
        widgets.put(WidgetTypes.NOT_FOUND, NotFoundWidget.class);
        widgets.put(WidgetTypes.PRODUCT_TRENDS, ProductTrendsWidget.class);
        widgets.put(WidgetTypes.PRODUCT_DIFFER, ProductDifferWidget.class);
        widgets.put(WidgetTypes.PRODUCT_W_PKG, ProductWPkgWidget.class);
        widgets.put(WidgetTypes.PRODUCT_DIFFER_W_PKG, ProductDifferWPkgWidget.class);
    }

    public static WidgetManager getInstance() {
        if (singleton == null)
            singleton = new WidgetManager();
        return singleton;
    }

    public IWidget delegate(Widget widget) {
        Class clazz = widgets.get(widget.getType());
        try {
            IWidget w = (IWidget)clazz.newInstance();
            w.copyFromWidget(widget);
            return w;
        } catch (InstantiationException e) {

        } catch (IllegalAccessException e) {

        }
        return null;
    }

    public boolean isValidType(String type) {
        return widgets.keySet().contains(type);
    }

//    public void register(String name, IWidget widget) {
//        widgets.put(name, widget);
//    }
//
//    public IWidget getWidget(String name) {
//        return widgets.get(name);
//    }
}
