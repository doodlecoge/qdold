package com.cisex.qd.web.api;

/**
 * Created by vezhou.
 * Date: 2012-8-15
 * Time: 10:20:28
 */
public class ErrorResult extends ApiResult {
    
    public ErrorResult() {
        this("");
    }

    public ErrorResult(String message) {
        super(ApiResult.ERROR_CODE, message);
    }
}
