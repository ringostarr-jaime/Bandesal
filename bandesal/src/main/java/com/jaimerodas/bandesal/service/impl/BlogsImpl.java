/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jaimerodas.bandesal.service.impl;

import com.jaimerodas.bandesal.conexion.conexion;
import com.jaimerodas.bandesal.entity.Blogs;
import com.jaimerodas.bandesal.service.BlogsInterface;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jaime
 */
public class BlogsImpl implements BlogsInterface, Serializable{

    @Override
    public List<Blogs> listarAll() {
       List<Blogs> blogs = new ArrayList<>();
       EntityManager em=null;
       try {            
            em = conexion.manager();
            Query query = em.createQuery("SELECT l FROM Blogs l", Blogs.class);                       
            blogs = query.getResultList();
        } catch (Exception e) {
            System.out.println("[ERROR][BlogsImpl listarAll]= "+e);          
        }       
        return blogs;
    }
    

    @Override
    public int insertarObject(Blogs object) {
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
            System.out.println("[ERROR][BlogsImpl insertarObject]= "+e);  
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
    public int modificarObject(Blogs object) {
        int respuesta =0;
        EntityManager em =null;
       try {            
            em = conexion.manager();
            Blogs objeto = em.find(Blogs.class, object.getId());
            em.getTransaction().begin();
            if(em.contains(objeto))
            {
            em.merge(objeto);
            }                  
            em.getTransaction().commit();
            respuesta=1;
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("[ERROR][BlogsImpl modificarObject]= "+e);  
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
    public int eliminarObject(Blogs object) {
       int respuesta =0;
        EntityManager em =null;
       try {            
            em = conexion.manager();
            Blogs objeto = em.find(Blogs.class, object.getId());
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
            System.out.println("[ERROR][BlogsImpl eliminarObject]= "+e);  
             respuesta=0;
        }finally
        {
            if (em!=null) {
                em.close();
            }
        }
       return respuesta;
    }
    
}
