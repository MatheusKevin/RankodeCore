/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rankode.core.model;

/**
 *
 * @author Matheus
 */
public class Invite extends Notification{
    
    private Project project;
    
    private boolean response;
    
    public Invite(Developer developer, Project project) {
        super(developer);
        this.project = project;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public boolean isResponse() {
        return response;
    }

    public void setResponse(boolean response) {
        this.response = response;
    }
    
}
