/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rankode.core.bc;

import com.rankode.core.dao.TargetDao;
import com.rankode.core.model.Target;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Matheus Pelegrini
 */
public class TargetBc extends PatternBc<Target>{
    
    private final TargetDao targetDao;

    public TargetBc() {
        targetDao = new TargetDao();
    }
    
    @Override
    public void insert(Target objeto) {
        validateObject(objeto);
        targetDao.insert(objeto);
    }

    @Override
    public void update(Target objeto) {
        validateObject(objeto);
        targetDao.update(objeto);
    }

    @Override
    public void delete(Target objeto) {
        targetDao.delete(objeto);
    }
    
    public Target findById(int id){
        return targetDao.findById(id);
    }

    @Override
    public List<Target> findAll() {
        return targetDao.findAll();
    }

    @Override
    public List<Target> findByFilter(Target filter) {
        if(!validateFilter(filter)){
            throw new RuntimeException("É necessario preencher ao menos um campo de busca");
        }
        return targetDao.findByFilter(filter);
    }

    @Override
    protected void validateObject(Target object) {
        if(object == null){
            throw new RuntimeException("Target nulo");
        }
        if(StringUtils.isBlank(object.getName())){
            throw new RuntimeException("Nome em branco");
        }
    }

    @Override
    protected boolean validateFilter(Target object) {
        return object != null && StringUtils.isNotBlank(object.getName());
    }
    
}
