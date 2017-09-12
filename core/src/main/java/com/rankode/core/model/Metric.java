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
public class Metric {
    
    private Target target;
    private MetricGroup group;
    private List<Influence> influences;
    
    private String initials;
    private String name;
    private String description;
    private Integer weightXP;

    public Metric() {
        this.influences = new ArrayList<>();
    }

    public Target getTarget() {
        return target;
    }

    public void setTarget(Target target) {
        this.target = target;
    }

    public MetricGroup getGroup() {
        return group;
    }

    public void setGroup(MetricGroup group) {
        this.group = group;
    }

    public List<Influence> getInfluences() {
        return influences;
    }

    public void setInfluences(List<Influence> influences) {
        this.influences = influences;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getWeightXP() {
        return weightXP;
    }

    public void setWeightXP(Integer weightXP) {
        this.weightXP = weightXP;
    }
    
}
