package com.cisex.qd.web.action.widget;

import com.cisex.qd.web.action.ApiAction;
import com.cisex.qd.web.api.ApiResult;

/**
 * Created with IntelliJ IDEA.
 * User: huaiwang
 * Date: 12-9-29
 * Time: 下午2:27
 * To change this template use File | Settings | File Templates.
 */
public class DeleteDashboardAction extends ApiAction {
    int dbid;

    @Override
    protected ApiResult executeApi() {
        ApiResult rst = null;

        try {
            dao.getDashboardDao().deleteDashboard(dbid);
            rst = new ApiResult(ApiResult.SUCCESS_CODE);
        } catch (Exception e) {
            rst = new ApiResult(ApiResult.ERROR_CODE);
            rst.putExt("errMsg", e.getMessage());
        }

        return rst;
    }

    public int getDbid() {
        return dbid;
    }

    public void setDbid(int dbid) {
        this.dbid = dbid;
    }
}
