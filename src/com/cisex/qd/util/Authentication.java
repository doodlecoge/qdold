package com.cisex.qd.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.ldap.userdetails.InetOrgPerson;

/**
 * Created with IntelliJ IDEA.
 * User: huaiwang
 * Date: 12-9-3
 * Time: 下午1:34
 * To change this template use File | Settings | File Templates.
 */
public class Authentication {
    public static final String QD_ROLE_ADMIN = "ROLE_ADMIN";

    public static String getUsername() {
        try {
            return SecurityContextHolder.getContext().getAuthentication().getName();
        } catch (Exception e) {
        return null
                ;
        }
    }

    public static String getFullname() {
        try{
        return ((InetOrgPerson)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getDisplayName();
        }catch (Exception e) {
            return  null;
        }
    }

//    public static boolean isAdmin() {
//        try{
//            return SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(QD_ROLE_ADMIN);
//        } catch (Exception e) {
//            return false;
//        }
//    }
}
