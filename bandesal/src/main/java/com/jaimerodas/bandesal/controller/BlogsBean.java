/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jaimerodas.bandesal.controller;

import com.jaimerodas.bandesal.entity.Blogs;
import com.jaimerodas.bandesal.model.BlogsModel;
import com.jaimerodas.bandesal.service.BlogsInterface;
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
@Named("blogsBean")
@ViewScoped
public class BlogsBean implements Serializable{
    
    @Inject
    @Transient
    private BlogsInterface blogsObject;
    
    private List<Blogs> listaBlogs;
    
    private BlogsModel blogs = new BlogsModel();
    
    private Blogs blogsEntity;

    //<editor-fold defaultstate="collapsed" desc="GET SET">

    public Blogs getBlogsEntity() {
        return blogsEntity;
    }

    public void setBlogsEntity(Blogs blogsEntity) {
        this.blogsEntity = blogsEntity;
    }

    
    
    public BlogsInterface getBlogsObject() {
        return blogsObject;
    }

    public void setBlogsObject(BlogsInterface blogsObject) {
        this.blogsObject = blogsObject;
    }

    public List<Blogs> getListaBlogs() {
        return listaBlogs;
    }

    public void setListaBlogs(List<Blogs> listaBlogs) {
        this.listaBlogs = listaBlogs;
    }

    public BlogsModel getBlogs() {
        return blogs;
    }

    public void setBlogs(BlogsModel blogs) {
        this.blogs = blogs;
    }
 
    //</editor-fold>    
    
    public void eliminarObject(Blogs object)
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
    
    public void objectModificar(Blogs dt)
    {
        blogsEntity=dt;
    }
    
    public void listarBlogs()
    {
    listaBlogs = blogsObject.listarAll();    
    }
    
    public void guardarBlogs() {
        int respuesta;
        Blogs object = new Blogs();
        object.setTitle(blogs.getTitle());
        object.setDescription(blogs.getDescription());
        try {
            if(blogs.getTitle().trim().equalsIgnoreCase("") || blogs.getDescription().trim().equalsIgnoreCase(""))
            {            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Los campos titulo y descripcion son requeridos"));
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
    
}
