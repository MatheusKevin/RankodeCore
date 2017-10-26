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
public class MetricResult {
    
    private Commit commit;
    private Metric metric;
    
    private Integer value;
    private String source;
    private Integer deltaValue;

    public MetricResult(Commit commit, Metric metric) {
        this.commit = commit;
        this.metric = metric;
    }

    public MetricResult() {
        
    }
    
    public Commit getCommit() {
        return commit;
    }

    public void setCommit(Commit commit) {
        this.commit = commit;
    }

    public Metric getMetric() {
        return metric;
    }

    public void setMetric(Metric metric) {
        this.metric = metric;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Integer getDeltaValue() {
        return deltaValue;
    }

    public void setDeltaValue(Integer deltaValue) {
        this.deltaValue = deltaValue;
    }
    
}
