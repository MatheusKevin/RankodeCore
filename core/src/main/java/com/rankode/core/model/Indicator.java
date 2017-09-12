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
    
    private Project projeto;
    private Metric metric;
    private List<Hint> hints;
    
    private Integer id;
    private String name;
    private Integer max;
    private Integer min;

    public Indicator(Project projeto, Metric metric) {
        this.projeto = projeto;
        this.metric = metric;
        this.hints = new ArrayList<>();
    }

    public Indicator() {
        this.hints = new ArrayList<>();
    }
    
    public void addInterval (Hint obj){
        this.hints.add(obj);
    }

    public Project getProjeto() {
        return projeto;
    }

    public void setProjeto(Project projeto) {
        this.projeto = projeto;
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
