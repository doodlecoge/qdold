package com.cisex.qd.web.action.widget;

import com.cisex.qd.vo.Widget;
import com.cisex.qd.web.action.ApiAction;
import com.cisex.qd.web.api.ApiResult;

/**
 * Created by vezhou.
 * Date: 2012-8-15
 * Time: 14:29:42
 */
public class DeleteWidgetAction extends ApiAction {
    private int widgetId;

    @Override
    protected ApiResult executeApi() {
        Widget widget = dao.getWidgetDao().findWidgetById(widgetId);
        if (widget !=null) {
            dao.getWidgetDao().deleteWidget(widget);
        }
        return ApiResult.SUCCESS;
    }

    public int getWidgetId() {
        return widgetId;
    }

    public void setWidgetId(int widgetId) {
        this.widgetId = widgetId;
    }
}
