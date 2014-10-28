package com.cisex.qd.web.action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Created by vezhou.
 * Date: 2011-12-6
 * Time: 14:28:13
 */
public class LoginAction extends ActionSupport {
    private int loginError;

    public int getLoginError() {
        return loginError;
    }

    public void setLoginError(int loginError) {
        this.loginError = loginError;
    }
}
