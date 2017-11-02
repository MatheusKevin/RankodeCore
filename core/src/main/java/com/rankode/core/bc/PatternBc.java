/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rankode.core.bc;

import java.util.List;

/**
 *
 * @author Alexandre
 */
public abstract class PatternBc<T> {
    
    public abstract void insert(T objeto);
    public abstract void update(T objeto);
    public abstract void delete(T objeto);
    
    public abstract List<T> findAll();
    public abstract List<T> findByFilter(T filter);
    
    protected abstract void validateObject(T object);
    protected abstract boolean validateFilter(T object);
}
