package com.cisex.qd.web.action;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by vezhou.
 * Date: 2011-12-5
 * Time: 14:38:43
 */
public class HomeAction extends ApplicationAction {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    private String greeting;

    @Override
    public String execute() throws Exception {
        greeting = sdf.format(new Date()); 
        return SUCCESS;
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }
}
