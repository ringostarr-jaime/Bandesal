/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jaimerodas.bandesal.model;

import java.io.Serializable;

/**
 *
 * @author jaime
 */
public class BlogsModel implements Serializable{
    
    private int idBlogs; 
    private String title;
    private String description;

    public int getIdBlogs() {
        return idBlogs;
    }

    public void setIdBlogs(int idBlogs) {
        this.idBlogs = idBlogs;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
    
}
