/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jaimerodas.bandesal.service.impl;

import com.jaimerodas.bandesal.conexion.conexion;
import com.jaimerodas.bandesal.entity.BlogsReaders;
import com.jaimerodas.bandesal.model.BlogsReadersModel;
import com.jaimerodas.bandesal.service.BlogsReadersInterface;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jaime
 */
public class BlogsReadersImpl implements BlogsReadersInterface, Serializable{

    @Override
    public List<BlogsReadersModel> listarReadersBlogs(Long rId, Long bId) {
       List<BlogsReadersModel> blogs = new ArrayList<>();
       EntityManager em=null;
       try {            
            em = conexion.manager();
            Query query = em.createQuery("SELECT b FROM Blogs b JOIN b.blogs_readers a JOIN b.readers c WHERE c.r_id = :rId AND a.b_id = :bId", BlogsReadersModel.class);                       
            query.setParameter("rId", rId);
            query.setParameter("bId", bId);
            blogs = query.getResultList();
        } catch (Exception e) {
            System.out.println("[ERROR][BlogsReadersImpl listarReadersBlogs]= "+e);          
        }       
        return blogs;
    }
    
    @Override
    public List<BlogsReaders> listarAll() {
       List<BlogsReaders> blogs = new ArrayList<>();
       EntityManager em=null;
       try {            
            em = conexion.manager();
            Query query = em.createQuery("SELECT l FROM Blogs l", BlogsReaders.class);                       
            blogs = query.getResultList();
        } catch (Exception e) {
            System.out.println("[ERROR][BlogsReadersImpl listarAll]= "+e);          
        }       
        return blogs;
    }
    

    @Override
    public int insertarObject(BlogsReaders object) {
        int respuesta =0;
         EntityManager em =null;
       try {            
            em = conexion.manager();
            em.getTransaction().begin();
            em.persist(object);            
            em.getTransaction().commit();
             respuesta=1;
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("[ERROR][BlogsReadersImpl insertarObject]= "+e);  
             respuesta=0;
        }finally
        {
            if (em!=null) {
                em.close();
            }
        }
       
       return respuesta;
    }

    @Override
    public int modificarObject(BlogsReaders object) {
        int respuesta =0;
        EntityManager em =null;
       try {            
            em = conexion.manager();
            BlogsReaders objeto = findByRidAndBid(object.getrId(),object.getbId()).get(0);
            em.getTransaction().begin();
            if(em.contains(objeto))
            {
            em.merge(objeto);
            }                  
            em.getTransaction().commit();
            respuesta=1;
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("[ERROR][BlogsReadersImpl modificarObject]= "+e);  
             respuesta=0;
        }finally
        {
            if (em!=null) {
                em.close();
            }
        }
       return respuesta;
    }

    @Override
    public int eliminarObject(BlogsReaders object) {
       int respuesta =0;
        EntityManager em =null;
       try {            
            em = conexion.manager();
            BlogsReaders objeto = findByRidAndBid(object.getrId(),object.getbId()).get(0);
            em.getTransaction().begin();
            if(em.contains(objeto))
            {
                em.merge(objeto);
                em.remove(objeto);
            }                  
            em.getTransaction().commit();
            respuesta=1;
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("[ERROR][BlogsReadersImpl eliminarObject]= "+e);  
             respuesta=0;
        }finally
        {
            if (em!=null) {
                em.close();
            }
        }
       return respuesta;
    }

    @Override
    public List<BlogsReaders> findByRidAndBid(Long rId, Long bId) {
        List<BlogsReaders> login = new ArrayList<>();
         EntityManager em=null;
       try {            
            em = conexion.manager();
            Query query = em.createQuery("SELECT l FROM BlogsReaders l WHERE l.r_Id = :r_Id  and l.b_Id  = :b_Id ", BlogsReaders.class);
            query.setParameter("r_Id", rId);            
            query.setParameter("b_Id ", bId);
            login = query.getResultList();           
        } catch (Exception e) {
            System.out.println("[ERROR][BlogsReadersImpl findByRidAndBid]= "+e);          
        }       
       return login;
    }
    
}
