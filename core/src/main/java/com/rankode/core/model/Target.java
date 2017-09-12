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
public class Target {
    
    private List<Metric> metrics;
    
    private Integer id;
    private String name;

    public Target() {
        this.metrics = new ArrayList<>();
    }
    
    public void addMetric(Metric obj){
        this.metrics.add(obj);
    }

    public List<Metric> getMetrics() {
        return metrics;
    }

    public void setMetrics(List<Metric> metrics) {
        this.metrics = metrics;
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
