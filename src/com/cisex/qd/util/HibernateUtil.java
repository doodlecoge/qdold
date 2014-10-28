package com.cisex.qd.util;

/**
 * Created with IntelliJ IDEA.
 * User: huaiwang
 * Date: 12-9-5
 * Time: 上午9:38
 * To change this template use File | Settings | File Templates.
 */

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    static{
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }

    public static void shutDown(){
        //closes caches and connections
        getSessionFactory().close();
    }
}
