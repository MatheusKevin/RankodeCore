/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rankode.core.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Alexandre
 * @param <T>
 */

public abstract class PatternDao<T> {
    
    public abstract void insert(T object);
    public abstract void update(T object);
    public abstract void delete(T object);

    public abstract T findById(int id);
    public abstract T findById(String id);
    public abstract List<T> findByFilter(T filter);	
    public abstract List<T> findAll();

    public abstract T populateObject(ResultSet rs) throws SQLException;
    
}
