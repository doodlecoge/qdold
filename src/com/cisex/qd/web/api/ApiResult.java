package com.cisex.qd.web.api;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vezhou.
 * Date: 2012-8-15
 * Time: 9:29:19
 */
public class ApiResult {
    public static final int SUCCESS_CODE = 0;
    public static final int ERROR_CODE = -1;

    public static final ApiResult SUCCESS = new ApiResult(SUCCESS_CODE); 
    public static final ApiResult FAILURE = new ApiResult(ERROR_CODE);

    protected int statusCode;

    protected String statusMessage = "";

    protected Map<String, Object> ext = new HashMap<String, Object>();

    public ApiResult(int statusCode) {
        this(statusCode, "");
    }

    public ApiResult(int statusCode, String statusMessage) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }

    public boolean isSuccess() {
        return statusCode == SUCCESS_CODE;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public Map getExt() {
        return ext;
    }

    public void putExt(String key, Object value) {
        this.ext.put(key, value);
        
    }
}
