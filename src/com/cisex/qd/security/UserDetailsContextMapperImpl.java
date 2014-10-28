package com.cisex.qd.security;

import com.cisex.qd.dao.DashboardDao;
import com.cisex.qd.dao.UserDao;
import com.cisex.qd.dao.UsersDashboardsDao;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.InetOrgPerson;
import org.springframework.security.ldap.userdetails.InetOrgPersonContextMapper;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.DirContextAdapter;


import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: huaiwang
 * Date: 12-9-3
 * Time: 上午9:03
 * To change this template use File | Settings | File Templates.
 */
public class UserDetailsContextMapperImpl implements UserDetailsContextMapper {
    private UserDao userDao;
    private DashboardDao dashboardDao;
    private UsersDashboardsDao usersDashboardsDao;

//    @Override
//    public UserDetails mapUserFromContext(DirContextOperations ctx, String username, Collection<GrantedAuthority> authorities) {
//        return super.mapUserFromContext(ctx, username, authorities);    //To change body of overridden methods use File | Settings | File Templates.
//    }
//
//    @Override
//    public void mapUserToContext(UserDetails user, DirContextAdapter ctx) {
//        super.mapUserToContext(user, ctx);    //To change body of overridden methods use File | Settings | File Templates.
//    }


        public UserDetails mapUserFromContext(
                DirContextOperations ctx,
                String username,
                Collection<GrantedAuthority> auths) {


            //((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes())
            //.getRequest().getSession().setAttribute("uid", name);

            com.cisex.qd.vo.User u = userDao.findByUserName(username);
            String full_name = ctx.getStringAttribute("cn");

            // add user to db if not exists
            if(u == null) {
                u = userDao.addUser(username, full_name);
            }

            u = userDao.findByUserName(username);

            if(u == null) return null;

//            Dashboard d = dashboardDao.getUserDashboardByUserId(u.getId());
//
////            Dashboard ud = usersDashboardsDao.getDefaultDashboard(username);
//
//            // create home dashborad for user in a transaction, if,
//            // not found in users_dashboards
//            if(d == null) {
////                Trans.addDefaultDashboard4(u,dashboardDao,usersDashboardsDao);
//                d = dashboardDao.createDashboard(username, 2, u.getId(), 0);
//            }
//
//            if(d == null) return null;

            InetOrgPerson.Essence p = new InetOrgPerson.Essence(ctx);

            p.setUsername(username);
            p.setDisplayName(full_name);

            // root user
//            if(Authentication.isAdmin())
//                auths.add(new GrantedAuthorityImpl("QD_ROOT_USER"));

            p.setAuthorities(auths);

            return  p.createUserDetails();
    }

    public void mapUserToContext(UserDetails user,
                                 DirContextAdapter ctx) {
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public DashboardDao getDashboardDao() {
        return dashboardDao;
    }

    public void setDashboardDao(DashboardDao dashboardDao) {
        this.dashboardDao = dashboardDao;
    }

    public UsersDashboardsDao getUsersDashboardsDao() {
        return usersDashboardsDao;
    }

    public void setUsersDashboardsDao(UsersDashboardsDao usersDashboardsDao) {
        this.usersDashboardsDao = usersDashboardsDao;
    }
}
