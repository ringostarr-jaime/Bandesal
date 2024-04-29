/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jaimerodas.bandesal.controller;

import com.jaimerodas.bandesal.entity.Login;


import com.jaimerodas.bandesal.model.Usuario;
import com.jaimerodas.bandesal.service.LoginInterface;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import jakarta.persistence.Transient;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;



/**
 *
 * @author jaime
 */
@Named("loginBean")
@ViewScoped
public class LoginBean implements Serializable {

    @Inject
    @Transient
    private LoginInterface loginObj;

    private Usuario usuario = new Usuario(); 

    private String errorConexion;
    
    private List<Login> listaUsuarios;
    
    private Login usuarioLogin;

    //<editor-fold defaultstate="collapsed" desc="GET SET">

    public Login getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(Login usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }
    
    
    
    public List<Login> getListaUsuarios() {
        return listaUsuarios;
    }
    public void setListaUsuarios(List<Login> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public String getErrorConexion() {
        return errorConexion;
    }

    public void setErrorConexion(String errorConexion) {
        this.errorConexion = errorConexion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    //</editor-fold>

    public void usuarioModificar(Login dt)
    {
        usuarioLogin=dt;
    }
    
    public void listarUsuarios()
    {
    listaUsuarios = loginObj.listarAll();
    }
    
    public void eliminarUsuarios(Login usuario)
    {             
        int respuesta;
        try {            
            respuesta=loginObj.eliminarUsuario(usuario);
            if (respuesta > 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "los registros fueron ingresados"));
                listarUsuarios();
            }                       
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error de conexion"));           
        } 
    }
    
    public void modificarUsuarios()
    {             
        int respuesta;
        Login usuarioTemp = new Login();
        if(usuarioLogin!=null)
        {
        try {   
            usuarioTemp.setId(usuarioLogin.getId());
            usuarioTemp.setUser(usuarioLogin.getUser());
            usuarioTemp.setPassword(encriptar(usuarioLogin.getPassword()));            
            respuesta=loginObj.modificarUsuario(usuarioTemp);
            if (respuesta > 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "los registros fueron ingresados"));
                listarUsuarios();
            }                       
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error de conexion"));           
        } 
        }              
        usuarioLogin=null;  
    }
    
    public void guardarUsuarios() {
        int respuesta;
        Login usuarioNuevo = new Login();
        usuarioNuevo.setUser(usuario.getUsuario());
        
        try {
            if(usuario.getUsuario().trim().equalsIgnoreCase("") || usuario.getClave().trim().equalsIgnoreCase(""))
            {            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Los campos usuario y password son requeridos"));
            }else
            {               
            usuarioNuevo.setPassword(encriptar(usuario.getClave()));
            respuesta=loginObj.insertarUsuario(usuarioNuevo);
            if (respuesta > 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "los registros fueron ingresados"));
                 usuario = new  Usuario();
            }
            }            
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error de conexion"));           
        }
       
    }
    
    public LoginBean() {
    }
    
    public String encriptar(String clave) 
    {       
        byte[] textoBytes = clave.trim().getBytes();       
        byte[] textoBase64Bytes = Base64.getEncoder().encode(textoBytes);        
        String textoBase64 = new String(textoBase64Bytes);        
        System.out.println(textoBase64);
      return textoBase64;
    }

   public String iniciarSession()
   {
        String redireccion = null;
        String user =usuario.getUsuario().trim();
        String pass =encriptar(usuario.getClave().trim());
        try {                        
            List<Login> login = loginObj.findByUserAndPassword(user, pass);                 
            if (login!=null && !login.isEmpty()) {                               
                for (int i = 0; i < login.size(); i++) {                                 
                if(login.get(i).getUser().trim().equalsIgnoreCase(user) && login.get(i).getPassword().trim().equalsIgnoreCase(pass))
                {
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", login.get(i));
                    errorConexion="EXITO";
                    redireccion= "/paginas/index?faces-redirect=true";
                    //redireccion= "/paginas/blogsReaders?faces-redirect=true";
                }else
                {
                errorConexion="COMUNIQUESE CON EL ADMINISTRADOR YA QUE HAY UN ERROR EN SU USUARIO";
                }
               }
            }else
            {
                errorConexion="USUARIO o CLAVE INCORRECTA";
            }        
        } catch (Exception e) {
            System.out.println("ERROR "+e);
            errorConexion="EL SERVIDOR SE ENCUENTRA EN MANTENIMIENTO";
        }
        
        return redireccion;
   }
   
    public void verificarSesion() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            Login us = (Login) context.getExternalContext().getSessionMap().get("usuario");
            if (us == null) {
                context.getExternalContext().redirect("./usuarios.xhtml");
            }
        } catch (Exception e) {
            System.out.println("[ERROR][LoginBean verificarSesion]"+e);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Error verificando sesion"));
        }
    }
   
    public void cerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }
           
}