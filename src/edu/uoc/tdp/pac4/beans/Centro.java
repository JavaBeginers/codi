/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uoc.tdp.pac4.beans;

import java.io.Serializable;


/**
 *
 * @author Ivan
 */
public class Centro implements Serializable {
    
    private static final long serialVersionUID = 1L;

    
    private int id;
    private String nom;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the descrpcion
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the descrpcion to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
}
