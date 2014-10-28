package com.cisex.qd.util;

/**
 * Created with IntelliJ IDEA.
 * User: huaiwang
 * Date: 12-9-4
 * Time: 下午4:59
 * To change this template use File | Settings | File Templates.
 */
public enum UserDashboardCat implements Comparable<UserDashboardCat> {
    OWNER(0),
    MEMBER(1);

    public int cat;

    UserDashboardCat(int cat) {
        this.cat = cat;
    }
}
