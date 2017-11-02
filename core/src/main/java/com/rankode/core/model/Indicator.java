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
public class Indicator {
    
    private Project project;
    private Metric metric;
    private List<Hint> hints;
    
    private Integer id;
    private String name;
    private Integer max;
    private Integer min;

    public Indicator(Project project, Metric metric) {
        this.project = project;
        this.metric = metric;
        this.hints = new ArrayList<>();
    }

    public Indicator() {
        this.hints = new ArrayList<>();
    }
    
    public void addInterval (Hint obj){
        this.hints.add(obj);
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Metric getMetric() {
        return metric;
    }

    public void setMetric(Metric metric) {
        this.metric = metric;
    }

    public List<Hint> getHints() {
        return hints;
    }

    public void setHints(List<Hint> hints) {
        this.hints = hints;
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

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }
    
}
