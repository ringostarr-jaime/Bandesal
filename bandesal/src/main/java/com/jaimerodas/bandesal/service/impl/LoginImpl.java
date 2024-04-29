/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jaimerodas.bandesal.service.impl;

import com.jaimerodas.bandesal.conexion.conexion;
import com.jaimerodas.bandesal.entity.Login;
import com.jaimerodas.bandesal.service.LoginInterface;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jaime
 */
public class LoginImpl implements LoginInterface, Serializable {

    @Override
    public List<Login> findByUserAndPassword(String user, String password) {
         List<Login> login = new ArrayList<>();
         EntityManager em=null;
       try {            
            em = conexion.manager();
            Query query = em.createQuery("SELECT l FROM Login l WHERE l.user = :user and l.password = :password", Login.class);
            query.setParameter("user", user);
            query.setParameter("password", password);
            login = query.getResultList();           
        } catch (Exception e) {
            System.out.println("[ERROR][LoginImpl]= "+e);          
        }       
       return login;
    }

    @Override
    public int insertarUsuario(Login usuario) {
         int respuesta =0;
         EntityManager em =null;
       try {            
            em = conexion.manager();
            em.getTransaction().begin();
            em.persist(usuario);            
            em.getTransaction().commit();
             respuesta=1;
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("[ERROR][LoginImpl]= "+e);  
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
    public int modificarUsuario(Login usuario) {    
        int respuesta =0;
        EntityManager em =null;
       try {            
            em = conexion.manager();
            Login login = em.find(Login.class, usuario.getId());
            em.getTransaction().begin();
            if(em.contains(login))
            {
            em.merge(usuario);
            }                  
            em.getTransaction().commit();
            respuesta=1;
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("[ERROR][LoginImpl]= "+e);  
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
    public int eliminarUsuario(Login usuario) {
        int respuesta =0;
        EntityManager em =null;
       try {            
            em = conexion.manager();
            Login login = em.find(Login.class, usuario.getId());
            em.getTransaction().begin();
            if(em.contains(login))
            {
                em.merge(login);
                em.remove(login);
            }                  
            em.getTransaction().commit();
            respuesta=1;
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("[ERROR][LoginImpl]= "+e);  
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
    public Login findByUser(String user) {
        Login login = new Login();
         EntityManager em=null;
       try {            
            em = conexion.manager();
            Query query = em.createQuery("SELECT l FROM Login l WHERE l.user = :user", Login.class);
            query.setParameter("user", user);            
            login = (Login) query.getSingleResult();
        } catch (Exception e) {
            System.out.println("[ERROR][LoginImpl]= "+e);          
        }       
       return login;
    }

    @Override
    public List<Login> listarAll() {
        List<Login> login = new ArrayList<>();
         EntityManager em=null;
       try {            
            em = conexion.manager();
            Query query = em.createQuery("SELECT l FROM Login l", Login.class);                       
            login = query.getResultList();
        } catch (Exception e) {
            System.out.println("[ERROR][LoginImpl]= "+e);          
        }       
       return login;
    }
    
}
