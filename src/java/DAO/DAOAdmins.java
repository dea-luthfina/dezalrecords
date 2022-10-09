/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.Admins;
import pojo.DezalRecordsUtil;

/**
 *
 * @author luthfina
 */
public class DAOAdmins {
    public List<Admins> getBy (String uEmail, String uPass){
        Admins a = new Admins();
        List<Admins> admin = new ArrayList();
        
        Transaction trans = null;
        Session session = DezalRecordsUtil.getSessionFactory().openSession();
        try{
            trans = session.beginTransaction();
            Query query = session.createQuery("from Admins where email= :uEmail AND password= :uPass");
            query.setString("uPass", uPass);
            query.setString("uEmail", uEmail);
            a = (Admins) query.uniqueResult();
            admin = query.list();
            trans.commit();
        } catch(Exception e){
            System.out.println("Error" + e);
        }
        
        return admin;
    }
    
    public String add_admin(Admins admin){
        Transaction trans = null;
        Session session = DezalRecordsUtil.getSessionFactory().openSession();
        try {
            trans = session.beginTransaction();
            session.save(admin);
            trans.commit();
            return "index";
        } catch (Exception e) {
            System.out.println(e);
        }
        return "error";
    }
}
