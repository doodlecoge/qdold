package com.cisex.qd.validate;

import com.cisex.qd.dao.Dao;
import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Created by vezhou.
 * Date: 2012-8-15
 * Time: 8:00:45
 */
public class ValidationUtil {

    private static ValidationUtil singleton;

    private Dao dao;

    private ValidationUtil() {
        WebApplicationContext springContext = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
        dao = (Dao) springContext.getBean("theDao");
    }

    public static ValidationUtil getInstance() {
        if (singleton == null) {
            singleton = new ValidationUtil();
        }
        return singleton;
    }

    public boolean isValidProject(String project) {
        return dao.getProjectDao().findProjectByName(project) != null;
    }
}
