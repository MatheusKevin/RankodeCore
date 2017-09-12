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
public class Hint {
    
    private Indicator indicator;
    
    private Integer id;
    private String text;

    public Hint(Indicator indicator) {
        this.indicator = indicator;
    }

    public Hint() {
    }

    public Indicator getIndicator() {
        return indicator;
    }

    public void setIndicator(Indicator indicator) {
        this.indicator = indicator;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
