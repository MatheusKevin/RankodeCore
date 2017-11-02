/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rankode.core.bc;

import com.rankode.core.dao.MetricDao;
import com.rankode.core.model.Metric;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Matheus Pelegrini
 */
public class MetricBc extends PatternBc<Metric>{
    
    private final MetricDao metricDao;

    public MetricBc() {
        metricDao = new MetricDao();
    }
    
    @Override
    public void insert(Metric objeto) {
        validateObject(objeto);
        metricDao.insert(objeto);
    }

    @Override
    public void update(Metric objeto) {
        validateObject(objeto);
        metricDao.update(objeto);
    }

    @Override
    public void delete(Metric objeto) {
        metricDao.delete(objeto);
    }
    
    public Metric findById(String initials){
        return metricDao.findById(initials);
    }

    @Override
    public List<Metric> findAll() {
        return metricDao.findAll();
    }

    @Override
    public List<Metric> findByFilter(Metric filter) {
        if(!validateFilter(filter)){
            throw new RuntimeException("É necessario preencher ao menos um campo de busca");
        }
        return metricDao.findByFilter(filter);
    }

    @Override
    protected void validateObject(Metric object) {
        if(object == null){
            throw new RuntimeException("Métrica nula");
        }
        if(object.getGroup() == null){
            throw new RuntimeException("Grupo nulo");
        }
        if(object.getTarget() == null){
            throw new RuntimeException("Target nulo");
        }
        if(StringUtils.isBlank(object.getName())){
            throw new RuntimeException("Nome em branco");
        }
        if(StringUtils.isBlank(object.getDescription())){
            throw new RuntimeException("Descrição em branco");
        }
    }

    @Override
    protected boolean validateFilter(Metric object) {
        return object != null && (object.getGroup() == null ||
                object.getGroup().getId() == null||
                object.getTarget()== null ||
                object.getTarget().getId() == null||
                StringUtils.isBlank(object.getName()) ||
                StringUtils.isBlank(object.getDescription()));
    }
    
}
