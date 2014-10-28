package com.cisex.qd.web.action.project;

import com.cisex.qd.dao.ProjectDao;
import com.cisex.qd.web.action.ApplicationAction;
import org.apache.log4j.Logger;

/**
 * Created by vezhou.
 * Date: 2011-12-6
 * Time: 9:21:03
 */
public class ProjectAction extends ApplicationAction {
    private ProjectDao projectDao;

    private int projectCount;

    private static Logger log = Logger.getLogger(ProjectAction.class);

    @Override
    public String execute() throws Exception {
        projectCount = projectDao.getTotalProjectCount();
        return SUCCESS;
    }

    public String add() throws Exception {
        message = "Add project successfully";
        log.warn("THIS IS MY WARNING!");
        return SUCCESS;
    }

    public ProjectDao getProjectDao() {
        return projectDao;
    }

    public void setProjectDao(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    public int getProjectCount() {
        return projectCount;
    }

    public void setProjectCount(int projectCount) {
        this.projectCount = projectCount;
    }
}
