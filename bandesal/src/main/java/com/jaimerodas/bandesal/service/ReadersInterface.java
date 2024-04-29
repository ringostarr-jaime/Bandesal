/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.jaimerodas.bandesal.service;


import com.jaimerodas.bandesal.entity.Readers;
import java.util.List;

/**
 *
 * @author jaime
 */
public interface ReadersInterface {
    
    public List<Readers> listarAll();
    
    public int insertarObject(Readers object);
    
    public int modificarObject(Readers object);
     
    public int eliminarObject(Readers object);
}
