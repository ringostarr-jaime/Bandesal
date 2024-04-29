/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jaimerodas.bandesal.service.impl;

import com.jaimerodas.bandesal.conexion.conexion;

import com.jaimerodas.bandesal.entity.Readers;
import com.jaimerodas.bandesal.service.ReadersInterface;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jaime
 */
public class ReadersImpl implements ReadersInterface, Serializable{
    
     @Override
    public List<Readers> listarAll() {
       List<Readers> readers = new ArrayList<>();
       EntityManager em=null;
       try {            
            em = conexion.manager();
            Query query = em.createQuery("SELECT l FROM Readers l", Readers.class);                       
            readers = query.getResultList();
        } catch (Exception e) {
            System.out.println("[ERROR][BlogsImpl listarAll]= "+e);          
        }       
        return readers;
    }
    

    @Override
    public int insertarObject(Readers object) {
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
            System.out.println("[ERROR][LoginImpl insertarObject]= "+e);  
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
    public int modificarObject(Readers object) {
        int respuesta =0;
        EntityManager em =null;
       try {            
            em = conexion.manager();
            Readers objeto = em.find(Readers.class, object.getId());
            em.getTransaction().begin();
            if(em.contains(objeto))
            {
            em.merge(objeto);
            }                  
            em.getTransaction().commit();
            respuesta=1;
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("[ERROR][LoginImpl modificarObject]= "+e);  
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
    public int eliminarObject(Readers object) {
       int respuesta =0;
        EntityManager em =null;
       try {            
            em = conexion.manager();
            Readers objeto = em.find(Readers.class, object.getId());
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
