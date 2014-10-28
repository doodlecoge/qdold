package com.cisex.qd.web.action.widget;

import com.cisex.qd.vo.Dashboard;
import com.cisex.qd.vo.User;
import com.cisex.qd.web.action.ApiAction;
import com.cisex.qd.web.api.ApiResult;
import com.cisex.qd.web.api.ErrorResult;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: huaiwang
 * Date: 12-8-29
 * Time: 上午9:16
 * To change this template use File | Settings | File Templates.
 */
public class AddDashboard extends ApiAction {
    private int columns;
    private String name;
    private int typea;
    private int pub;

    @Override
    protected ApiResult executeApi() {
        ApiResult result = ApiResult.FAILURE;
        try{
            User user = dao.getUserDao().findByUserName(getUsername());
            int uid = user.getId();

            Set<Dashboard> dbs = user.getDashboards();

            int cnt = 0;

            for(Dashboard db : dbs) {
                if(db.getType2() == 1) cnt++;
            }

            if(cnt >= 3) {
                ApiResult rst = new ApiResult(ApiResult.ERROR_CODE);
                rst.putExt("errMsg", "Reach limitation, you are permitted to create at most 3 dashboards.");
                return rst;
            }

            Dashboard dashboard = dao.getDashboardDao().findDashboardByName(name);

            if (dashboard != null)
                return new ErrorResult("Dashboard already exist!");

            if(!(columns == 2 || columns == 3))
                return new ErrorResult("Dashboard allows only 2 ~ 3 columns!");

            Dashboard db = dao.getDashboardDao().createDashboard(name, columns, typea, pub);

            if(typea == 1) {
                dao.getUsersDashboardsDao().addAdmin(db.getId(), uid);
            }

            result = new ApiResult(ApiResult.SUCCESS_CODE);

            result.putExt("dbid", db.getId());
        }catch (Exception e) {
            result.putExt("errMsg", e.getMessage());
        }

        return result;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTypea() {
        return typea;
    }

    public void setTypea(int typea) {
        this.typea = typea;
    }

    public int getPub() {
        return pub;
    }

    public void setPub(int pub) {
        this.pub = pub;
    }
}
