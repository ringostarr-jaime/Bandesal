/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jaimerodas.bandesal.controller;

import com.jaimerodas.bandesal.entity.Blogs;
import com.jaimerodas.bandesal.entity.BlogsReaders;
import com.jaimerodas.bandesal.entity.Readers;
import com.jaimerodas.bandesal.model.BlogsModel;
import com.jaimerodas.bandesal.model.BlogsReadersModel;
import com.jaimerodas.bandesal.service.BlogsInterface;
import com.jaimerodas.bandesal.service.impl.BlogsReadersImpl;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.Transient;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author jaime
 */
@Named("blogsReadersBean")
@ViewScoped
public class BlogsReadersBean implements Serializable{
    
    @Inject
    @Transient
    private BlogsReadersImpl blogsObject;
    
    private List<BlogsReaders> listaBlogs;
    
     private List<BlogsReadersModel> listarReadersBlogs;
    
    private BlogsReadersModel blogs = new BlogsReadersModel();
    
    private BlogsReaders blogsEntity;

    //<editor-fold defaultstate="collapsed" desc="GET SET">

    public List<BlogsReadersModel> getListarReadersBlogs() {
        return listarReadersBlogs;
    }

    public void setListarReadersBlogs(List<BlogsReadersModel> listarReadersBlogs) {
        this.listarReadersBlogs = listarReadersBlogs;
    }

    
    
    public List<BlogsReaders> getListaBlogs() {
        return listaBlogs;
    }

    public void setListaBlogs(List<BlogsReaders> listaBlogs) {
        this.listaBlogs = listaBlogs;
    }

    public BlogsReadersModel getBlogs() {
        return blogs;
    }

    public void setBlogs(BlogsReadersModel blogs) {
        this.blogs = blogs;
    }

    public BlogsReaders getBlogsEntity() {
        return blogsEntity;
    }

    public void setBlogsEntity(BlogsReaders blogsEntity) {
        this.blogsEntity = blogsEntity;
    }

   
 
    //</editor-fold>    
    
    public void eliminarObject(BlogsReaders object)
    {             
        int respuesta;
        try {            
            respuesta=blogsObject.eliminarObject(object);
            if (respuesta > 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "los registros fueron ingresados"));
                listarBlogs();
            }                       
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error de conexion"));           
        } 
    }
    
    public void modificarObject()
    {             
        int respuesta;
        if(blogsEntity!=null)
        {
        try {            
            respuesta=blogsObject.modificarObject(blogsEntity);
            if (respuesta > 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "los registros fueron ingresados"));
            }                       
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error de conexion"));           
        } 
        }              
        blogsEntity = null;      
    }
    
    public void selecionarReader(Readers dt)
    {   
        blogs.setrId(dt.getId());
        blogs.setUsuario(dt.getName());      
        //listarReadersBlogs();
       
    }
     
    public void selecionarBlog(Blogs dt)
    {
        blogs.setbId(dt.getId());
        blogs.setTitulo(dt.getTitle());
        blogs.setDescripcion(dt.getDescription());
        
    }
    
    public void listarBlogs()
    {
    listaBlogs = blogsObject.listarAll();    
    }
    
    
    public void listarReadersBlogs()
    {
      List<BlogsReadersModel> test = blogsObject.listarReadersBlogs(1L, 1L);    
    }
    
    public void guardarBlogsReaders() {
        int respuesta;
        BlogsReaders object = new BlogsReaders();
        object.setrId(blogs.getrId());
        object.setbId(blogs.getbId());
        try {
            if(blogs.getrId()==null || blogs.getbId()==null)
            {            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Los campos titulo y usuario son requeridos"));
            }else
            {
            respuesta=blogsObject.insertarObject(object);
            if (respuesta > 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "los registros fueron ingresados"));
            }
            }            
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error de conexion"));           
        }
    }
    
    
    public void borrarBlogsReaders() {
        int respuesta;
        BlogsReaders object = new BlogsReaders();
        object.setrId(blogs.getrId());
        object.setbId(blogs.getbId());
        try {
            if(blogs.getrId()==null || blogs.getbId()==null)
            {            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Los campos titulo y usuario son requeridos"));
            }else
            {
            respuesta=blogsObject.eliminarObject(object);
            if (respuesta > 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "los registros fueron ingresados"));
            }
            }            
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error de conexion"));           
        }
    }
    
}
