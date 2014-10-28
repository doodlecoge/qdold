package com.cisex.qd.security;

import com.cisex.qd.dao.UserDao;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: huaiwang
 * Date: 12-8-31
 * Time: 下午1:21
 * To change this template use File | Settings | File Templates.
 */
public class LoginSuccessHanderImpl implements AuthenticationSuccessHandler {
    private UserDao userDao;

    public void onAuthenticationSuccess(HttpServletRequest req,
                                        HttpServletResponse resp,
                                        Authentication auth) throws IOException, ServletException {





//        Object p = auth.getPrincipal();
//        Object d = auth.getDetails();
//
//        Object aa = auth
//                .getCredentials();


//        String name = auth.getName();
//
//
//        //((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes())
//                //.getRequest().getSession().setAttribute("uid", name);
//
//        User u = userDao.findByUserName(name);
//
//        if(u == null) {
//            userDao.addUser(name);
//        }
//
//        resp.sendRedirect("widget/index");
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
