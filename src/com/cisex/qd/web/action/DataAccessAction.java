package com.cisex.qd.web.action;

import com.cisex.qd.dao.Dao;

/**
 * Created by vezhou.
 * Date: 2012-8-9
 * Time: 16:33:16
 */
public abstract class DataAccessAction extends ApplicationAction {
    public static final String WIDGET_PATH = "/widget/";

    private int admin = -1;

    protected Dao dao;

    public Dao getDao() {
        return dao;
    }

    public void setDao(Dao dao) {
        this.dao = dao;
    }

    public boolean isAdmin() {
        String usernane = getUsername();

        if(admin == -1) {
            boolean b = this.dao.getAdminDao().isAdmin(usernane);
            if(b) admin = 1;
            else admin = 0;
        }

        return admin == 1;
    }
}
