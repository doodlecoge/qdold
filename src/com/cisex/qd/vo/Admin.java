package com.cisex.qd.vo;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: huaiwang
 * Date: 12-9-13
 * Time: 下午4:28
 * To change this template use File | Settings | File Templates.
 */
public class Admin implements Serializable {
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
