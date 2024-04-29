/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.jaimerodas.bandesal.service;

import com.jaimerodas.bandesal.entity.Login;
import java.util.List;

/**
 *
 * @author jaime
 */
public interface LoginInterface {
    
    public List<Login> findByUserAndPassword(String user, String password);
    
    public List<Login> listarAll();
    
    public Login findByUser(String user);
    
    public int insertarUsuario(Login usuario);
    
    public int modificarUsuario(Login usuario);
     
    public int eliminarUsuario(Login usuario);
    
}
