/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jaimerodas.bandesal.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;

/**
 *
 * @author jaime
 */
@Entity
@Table(name = "blogs_readers")
public class BlogsReaders {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private Long id;    
    @Column(name = "r_id")
    private Long rId;
    @Column(name = "b_id")
    private Long bId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getrId() {
        return rId;
    }

    public void setrId(Long rId) {
        this.rId = rId;
    }

    public Long getbId() {
        return bId;
    }

    public void setbId(Long bId) {
        this.bId = bId;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + Objects.hashCode(this.id);
        hash = 61 * hash + Objects.hashCode(this.rId);
        hash = 61 * hash + Objects.hashCode(this.bId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BlogsReaders other = (BlogsReaders) obj;
        return true;
    }

    @Override
    public String toString() {
        return "BlogsReaders{" + "id=" + id + ", rId=" + rId + ", bId=" + bId + '}';
    }
    
    
    
    
}
