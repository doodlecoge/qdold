package com.cisex.qd.web.action;

import com.opensymphony.xwork2.ActionSupport;
import com.cisex.qd.util.Authentication;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.velocity.tools.generic.MathTool;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

/**
 * Created by vezhou.
 * Date: 2011-12-5
 * Time: 14:37:58
 */
public class ApplicationAction extends ActionSupport implements SessionAware {
    private MathTool mathtool;

    public MathTool getMath() {
        if(mathtool == null)
            mathtool = new MathTool();

        return mathtool;
    }

    protected String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private Map session;


    public void setSession(Map<String, Object> stringObjectMap) {
        this.session = stringObjectMap;
    }

    public Map getSession() {
        return this.session;
    }

    private String username;

    public String getUsername() {
        return Authentication.getUsername();
    }

//    public void setUsername(String username) {
//        this.username = username;
//    }

    private String fullname;

    public String getFullname() {
        return Authentication.getFullname();
    }


    public boolean isLoggedIn() {
        org.springframework.security.core.Authentication auth =
                SecurityContextHolder.getContext().getAuthentication();

        if (auth == null) return false;
        else {
            Object p = auth.getPrincipal();
            boolean b = p instanceof UserDetails;
            return b;
        }
    }

}
