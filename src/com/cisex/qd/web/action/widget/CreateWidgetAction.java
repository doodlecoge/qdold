package com.cisex.qd.web.action.widget;

import com.cisex.qd.vo.Dashboard;
import com.cisex.qd.vo.Widget;
import com.cisex.qd.web.action.ApiAction;
import com.cisex.qd.web.api.ApiResult;
import com.cisex.qd.web.api.ErrorResult;
import com.cisex.qd.widget.WidgetManager;

/**
 * Created by vezhou.
 * Date: 2012-8-15
 * Time: 8:30:40
 */
public class CreateWidgetAction extends ApiAction {

    private String name;

    private String type;

    private int dashboardId;

    @Override
    protected ApiResult executeApi() {
        Dashboard dashboard = dao.getDashboardDao().findDashboardById(dashboardId);
        if (dashboard == null)
            return new ErrorResult("Dashboard doesn't exist");

        if (!WidgetManager.getInstance().isValidType(type))
            return new ErrorResult("Unknown widget type " + type);

        Widget widget = dao.getWidgetDao().createWidget(dashboardId, type, name);
        ApiResult result = new ApiResult(ApiResult.SUCCESS_CODE);
        result.putExt("widgetId", widget.getId());
        return result;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDashboardId() {
        return dashboardId;
    }

    public void setDashboardId(int dashboardId) {
        this.dashboardId = dashboardId;
    }


    public String getName() {
        return name;
    }

    public void setName(String title) {
        this.name = title;
    }
}
