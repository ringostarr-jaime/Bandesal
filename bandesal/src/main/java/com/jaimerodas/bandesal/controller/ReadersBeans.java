/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jaimerodas.bandesal.controller;

import com.jaimerodas.bandesal.entity.Readers;
import com.jaimerodas.bandesal.model.ReadersModel;
import com.jaimerodas.bandesal.service.ReadersInterface;
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
@Named("readersBean")
@ViewScoped
public class ReadersBeans implements Serializable{
    
    @Inject
    @Transient
    private ReadersInterface readersObject;
    
    private List<Readers> listaReaders;
    
    private ReadersModel readers = new ReadersModel();
    
    private Readers readersEntity;
    
    //<editor-fold defaultstate="collapsed" desc="GET SET">
    
    public List<Readers> getListaReaders() {
        return listaReaders;
    }

    public void setListaReaders(List<Readers> listaReaders) {
        this.listaReaders = listaReaders;
    }

    public ReadersModel getReaders() {
        return readers;
    }

    public void setReaders(ReadersModel readers) {
        this.readers = readers;
    }

    public Readers getReadersEntity() {
        return readersEntity;
    }

    public void setReadersEntity(Readers readersEntity) {
        this.readersEntity = readersEntity;
    }
    
    
        
//</editor-fold>
    
     public void eliminarObject(Readers object)
    {             
        int respuesta;
        try {            
            respuesta=readersObject.eliminarObject(object);
            if (respuesta > 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "los registros fueron ingresados"));
                listarReaders();
            }                       
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error de conexion"));           
        } 
    }
    
    public void modificarObject()
    {             
        int respuesta;
        if(readersEntity!=null)
        {
        try {            
            respuesta=readersObject.modificarObject(readersEntity);
            if (respuesta > 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "los registros fueron ingresados"));
            }                       
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error de conexion"));           
        } 
        }              
        readersEntity = null;      
    }
    
    public void objectModificar(Readers dt)
    {
        readersEntity=dt;
    }
    
    public void listarReaders()
    {
    listaReaders = readersObject.listarAll();    
    }
    
    public void guardarReaders() {
        int respuesta;
        Readers object = new Readers();
        object.setName(readers.getName());
       
        try {
            if(readers.getName().trim().equalsIgnoreCase(""))
            {            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Los campos titulo y descripcion son requeridos"));
            }else
            {
            respuesta=readersObject.insertarObject(object);
            if (respuesta > 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "los registros fueron ingresados"));
            }
            }            
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error de conexion"));           
        }
    }
    
}
