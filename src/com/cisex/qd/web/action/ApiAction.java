package com.cisex.qd.web.action;

import com.cisex.qd.web.api.ApiResult;
import com.cisex.qd.web.api.ApiResultWriter;
import org.apache.struts2.ServletActionContext;

/**
 * Created by vezhou.
 * Date: 2012-8-15
 * Time: 10:07:34
 */
public abstract class ApiAction extends DataAccessAction {

    @Override
    public String execute() throws Exception {
        ApiResult result = executeApi();
        ApiResultWriter.write( ServletActionContext.getResponse(), result);
        return SUCCESS;
    }

    protected abstract ApiResult executeApi();
}
