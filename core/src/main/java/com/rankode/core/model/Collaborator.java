/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rankode.core.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Matheus
 */
public class Collaborator {
    
    private Developer developer;
    private Project project;
    private List<Commit> commits;
    
    
    private Integer xp;
    private Date admissionDate;
    private Date demissionDate;

    public Collaborator(Developer developer, Project project) {
        this.developer = developer;
        this.project = project;
        this.commits = new ArrayList<>();
    }

    public Collaborator() {
        this.commits = new ArrayList<>();
    }

    public void addCommit(Commit obj){
        this.commits.add(obj);
    }

    public Developer getDeveloper() {
        return developer;
    }

    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<Commit> getCommits() {
        return commits;
    }

    public void setCommits(List<Commit> commits) {
        this.commits = commits;
    }

    public Integer getXp() {
        return xp;
    }

    public void setXp(Integer xp) {
        this.xp = xp;
    }

    public Date getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(Date admissionDate) {
        this.admissionDate = admissionDate;
    }

    public Date getDemissionDate() {
        return demissionDate;
    }

    public void setDemissionDate(Date demissionDate) {
        this.demissionDate = demissionDate;
    }
    
}
