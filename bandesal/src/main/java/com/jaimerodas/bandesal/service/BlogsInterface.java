/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.jaimerodas.bandesal.service;


import com.jaimerodas.bandesal.entity.Blogs;

import java.util.List;

/**
 *
 * @author jaime
 */
public interface BlogsInterface {
    
    public List<Blogs> listarAll();
    
    public int insertarObject(Blogs object);
    
    public int modificarObject(Blogs object);
     
    public int eliminarObject(Blogs object);
}
