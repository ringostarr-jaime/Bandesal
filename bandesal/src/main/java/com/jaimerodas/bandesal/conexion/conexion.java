/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jaimerodas.bandesal.conexion;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 *
 * @author jaime
 */
public class conexion {
    
    private static EntityManagerFactory emf = null;
    
    public static EntityManager manager()
    {
    
        try {
            if(emf ==null)
            {
                emf = Persistence.createEntityManagerFactory("com.jaimerodas_bandesal_war_1.0-SNAPSHOTPU");
            }
        } catch (Exception e) {
             System.out.println("[ERROR] [CONEXION] = "+e);
        }        
        return emf.createEntityManager();
    }
}
