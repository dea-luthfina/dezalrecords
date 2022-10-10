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
import pojo.Albums;
import pojo.DezalRecordsUtil;

/**
 *
 * @author luthfina
 */
public class DAOAlbums {
    public String addAlbum(Albums album){
        Transaction trans = null;
        Session session = DezalRecordsUtil.getSessionFactory().openSession();
        try {
            trans = session.beginTransaction();
            session.save(album);
            trans.commit();
            return "album_list";
        } catch (Exception e) {
            System.out.println(e);
        }
        return "error";
    }
        
    public void deleteAlbum (String idA){
        Transaction trans = null;
        Session session = DezalRecordsUtil.getSessionFactory().openSession();
        try{
            trans = session.beginTransaction();
            Albums album = (Albums) session.load(Albums.class, new String(idA));
            session.delete(album);
            trans.commit();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public List<Albums> getbyID(String idA){
        Albums album = new Albums();
        List<Albums> lAlbum = new ArrayList();
        
        Transaction trans = null;
        Session session = DezalRecordsUtil.getSessionFactory().openSession();
        try {
            trans = session.beginTransaction();
            Query query = session.createSQLQuery("from Albums where id_album= :id");
            query.setString("id", idA);
            album = (Albums) query.uniqueResult();
            lAlbum = query.list();
            trans.commit();     
        } catch(Exception e){
            
        }
        return lAlbum;
    }
    
    public List<Albums> retrieveAlbums(){
        List stud = new ArrayList();
        Albums stud1 = new Albums();
        Transaction trans = null;
        Session session = DezalRecordsUtil.getSessionFactory().openSession();
        try{
            trans = session.beginTransaction();
            Query query = session.createQuery("from Albums");
            stud = query.list();
            stud.add(stud1.getAlbumName());
            stud.add(stud1.getArtist());
            stud.add(stud1.getYears());
            stud.add(stud1.getType());
            stud.add(stud1.getPrice());
            stud.add(stud1.getStock());
        } catch(Exception e){
    
        }
    return stud;
    } 
    
    public void updateAlbum(Albums album){
        Transaction trans = null;
        Session session = DezalRecordsUtil.getSessionFactory().openSession();
        try {
            trans = session.beginTransaction();
            session.update(album);
            trans.commit();
        } catch (Exception e){
            
        }
    }
}
