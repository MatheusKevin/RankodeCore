/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rankode.core.bc;

import com.rankode.core.dao.MetricGroupDao;
import com.rankode.core.model.MetricGroup;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Matheus Pelegrini
 */
public class MetricGroupBc extends PatternBc<MetricGroup>{
    
    private final MetricGroupDao metricGroupDao;

    public MetricGroupBc() {
        metricGroupDao = new MetricGroupDao();
    }

    @Override
    public void insert(MetricGroup objeto) {
        validateObject(objeto);
        metricGroupDao.insert(objeto);
    }

    @Override
    public void update(MetricGroup objeto) {
        validateObject(objeto);
        metricGroupDao.update(objeto);
    }

    @Override
    public void delete(MetricGroup objeto) {
        metricGroupDao.delete(objeto);
    }
    
    public MetricGroup findById(int id){
        return metricGroupDao.findById(id);
    }
    
    @Override
    public List<MetricGroup> findAll() {
        return metricGroupDao.findAll();
    }

    @Override
    public List<MetricGroup> findByFilter(MetricGroup filter) {
        if(!validateFilter(filter)){
            throw new RuntimeException("É necessario preencher ao menos um campo de busca");
        }
        return metricGroupDao.findByFilter(filter);
    }

    @Override
    protected void validateObject(MetricGroup object) {
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
    protected boolean validateFilter(MetricGroup object) {
        return object != null && (StringUtils.isBlank(object.getName()) ||
                StringUtils.isBlank(object.getDescription()));
    }
    
}
