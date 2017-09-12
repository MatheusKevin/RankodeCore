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
public class Commit {
    
    private Collaborator collaborator;
    private List<MetricResult> results;
    
    private String sha;
    private Date date;

    public Commit(Collaborator collaborator) {
        this.collaborator = collaborator;
        this.results = new ArrayList<>();
    }

    public Commit() {
        this.results = new ArrayList<>();
    }

    public Collaborator getCollaborator() {
        return collaborator;
    }

    public void setCollaborator(Collaborator collaborator) {
        this.collaborator = collaborator;
    }

    public List<MetricResult> getResults() {
        return results;
    }

    public void setResults(List<MetricResult> results) {
        this.results = results;
    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
}
