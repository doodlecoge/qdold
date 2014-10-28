package com.cisex.qd.dao;

import com.cisex.qd.vo.Project;

/**
 * Created by vezhou.
 * Date: 2011-12-6
 * Time: 9:14:48
 */
public interface ProjectDao {
    public int getTotalProjectCount();

    public Project findProjectByName(String name);
}
