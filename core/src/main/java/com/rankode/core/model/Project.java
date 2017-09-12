/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rankode.core.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Matheus
 */
public class Project {
    
    private Developer owner;
    private List<Collaborator> collaborators;
    private List<Indicator> indicators;
    
    private Integer id;
    private String name;

    public Project(Developer owner) {
        this.owner = owner;
        this.collaborators = new ArrayList<>();
        this.indicators = new ArrayList<>();
    }
    
    public Project() {
        this.collaborators = new ArrayList<>();
        this.indicators = new ArrayList<>();
    }
    
    public void addCollaborator(Collaborator obj){
        this.collaborators.add(obj);
    }
    
    public void addIndicator(Indicator obj){
        this.indicators.add(obj);
    }

    public Developer getOwner() {
        return owner;
    }

    public void setOwner(Developer owner) {
        this.owner = owner;
    }

    public List<Collaborator> getCollaborators() {
        return collaborators;
    }

    public void setCollaborators(List<Collaborator> collaborators) {
        this.collaborators = collaborators;
    }

    public List<Indicator> getIndicators() {
        return indicators;
    }

    public void setIndicators(List<Indicator> indicators) {
        this.indicators = indicators;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
