/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jaimerodas.bandesal.test;

import com.jaimerodas.bandesal.conexion.conexion;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.Iterator;


/**
 *
 * @author jaime
 */
public class test {
    
    public void listar()
    {
        EntityManager em;
        try {
            em = conexion.manager();
            Query query = em.createQuery("SELECT user, host FROM mysql.user WHERE user = 'root' ");
            for (Object object : query.getResultList()) {
                System.out.println(" "+object);
            }
            
        } catch (Exception e) {
        }
        
    }
    
}
