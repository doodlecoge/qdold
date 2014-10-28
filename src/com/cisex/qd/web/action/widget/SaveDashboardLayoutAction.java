package com.cisex.qd.web.action.widget;

import com.cisex.qd.vo.Dashboard;
import com.cisex.qd.web.action.ApiAction;
import com.cisex.qd.web.api.ApiResult;
import com.cisex.qd.web.api.ErrorResult;
import com.cisex.qd.widget.Layout;
import net.sf.json.JSONException;

/**
 * Created by vezhou.
 * Date: 2012-8-10
 * Time: 13:40:09
 */
public class SaveDashboardLayoutAction extends ApiAction {
    private String layout;

    private int dashboardId;

    @Override
    protected ApiResult executeApi() {
        Layout layoutObj = null;
        try {
            layoutObj = Layout.fromJson(this.layout);
        } catch (JSONException e) {
            return new ErrorResult("Invalid format of layout");
        }

        if (layoutObj == null) {
            return new ErrorResult("Invalid layout");
        }

        Dashboard dashboard = this.dao.getDashboardDao().findDashboardById(dashboardId);
        if (dashboard == null) {
            return new ErrorResult("Unknown dashboard " + dashboardId);
        }

        dashboard.setLayout(this.layout);
        this.dao.getDashboardDao().saveDashboard(dashboard);
        return ApiResult.SUCCESS;
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public int getDashboardId() {
        return dashboardId;
    }

    public void setDashboardId(int dashboardId) {
        this.dashboardId = dashboardId;
    }
}
