package com.cisex.qd.util;


/**
 * Created with IntelliJ IDEA.
 * User: huaiwang
 * Date: 12-9-5
 * Time: 上午9:25
 * To change this template use File | Settings | File Templates.
 */
public class Trans {
//    public static void addDefaultDashboard4(User user, DashboardDao dd, UsersDashboardsDao udd) {
//        Transaction tx = null;
//        Session session = null;
//
//        try {
//            session = HibernateUtil.getSessionFactory().openSession();
//
//            tx = session.beginTransaction();
//            tx.setTimeout(5);
//
//            Dashboard d = dd.createDashboard(user.getUsername(), 2);
//            udd.createDefaultDashboard(user.getId(), d.getId());
//
//            tx.commit();
//        } catch (RuntimeException e) {
//            try {
//                tx.rollback();
//            } catch (RuntimeException e2) {
//            }
//        } finally {
//            if(session != null)
//                session.close();
//        }
//    }
}
