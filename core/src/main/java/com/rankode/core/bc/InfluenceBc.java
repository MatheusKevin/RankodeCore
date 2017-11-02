/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rankode.core.bc;

import com.rankode.core.dao.InfluenceDao;
import com.rankode.core.model.Influence;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Matheus Pelegrini
 */
public class InfluenceBc extends PatternBc<Influence>{
    
    private final InfluenceDao influenceDao;

    public InfluenceBc() {
        influenceDao = new InfluenceDao();
    }   
    
    @Override
    public void insert(Influence objeto) {
        validateObject(objeto);
        influenceDao.insert(objeto);
    }

    @Override
    public void update(Influence objeto) {
        validateObject(objeto);
        influenceDao.update(objeto);
    }

    @Override
    public void delete(Influence objeto) {
        influenceDao.delete(objeto);
    }
    
    public Influence findById(int id){
        return influenceDao.findById(id);
    }
    
    @Override
    public List<Influence> findAll() {
        return influenceDao.findAll();
    }

    @Override
    public List<Influence> findByFilter(Influence filter) {
        if(!validateFilter(filter)){
            throw new RuntimeException("É necessario preencher ao menos um campo de busca");
        }
        return influenceDao.findByFilter(filter);
    }

    @Override
    protected void validateObject(Influence object) {
        if(object == null){
            throw new RuntimeException("MetricGroup nulo");
        }
        if(StringUtils.isBlank(object.getName())){
            throw new RuntimeException("Nome em branco");
        }
        if(StringUtils.isBlank(object.getDescription())){
            throw new RuntimeException("Descrição em branco");
        }
    }

    @Override
    protected boolean validateFilter(Influence object) {
        return object != null && (StringUtils.isBlank(object.getName()) ||
                StringUtils.isBlank(object.getDescription()));
    }
    
}
