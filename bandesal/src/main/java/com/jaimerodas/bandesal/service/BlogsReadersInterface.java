/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.jaimerodas.bandesal.service;

import com.jaimerodas.bandesal.entity.Blogs;
import com.jaimerodas.bandesal.entity.BlogsReaders;
import com.jaimerodas.bandesal.model.BlogsReadersModel;
import java.util.List;

/**
 *
 * @author jaime
 */
public interface BlogsReadersInterface {
        
    
    public List<BlogsReadersModel> listarReadersBlogs (Long rId, Long bId);
    
    public List<BlogsReaders> findByRidAndBid (Long rId, Long bId);
    
    public List<BlogsReaders> listarAll();
    
    public int insertarObject(BlogsReaders object);
    
    public int modificarObject(BlogsReaders object);
     
    public int eliminarObject(BlogsReaders object);
}
