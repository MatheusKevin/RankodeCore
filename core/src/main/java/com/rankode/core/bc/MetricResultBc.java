/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rankode.core.bc;

import com.rankode.core.dao.MetricResultDao;
import com.rankode.core.model.MetricResult;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Matheus Pelegrini
 */
public class MetricResultBc extends PatternBc<MetricResult>{
    
    private final MetricResultDao metricResultDao;

    public MetricResultBc() {
        metricResultDao = new MetricResultDao();
    }
    
    @Override
    public void insert(MetricResult objeto) {
        validateObject(objeto);
        metricResultDao.insert(objeto);
    }

    @Override
    public void update(MetricResult objeto) {
        validateObject(objeto);
        metricResultDao.update(objeto);
    }

    @Override
    public void delete(MetricResult objeto) {
        metricResultDao.delete(objeto);
    }
    
    public MetricResult findById(String sha, String metricInitial){
        return metricResultDao.findById(sha, metricInitial);
    }
    
    @Override
    public List<MetricResult> findAll() {
        return metricResultDao.findAll();
    }

    @Override
    public List<MetricResult> findByFilter(MetricResult filter) {
        if(!validateFilter(filter)){
            throw new RuntimeException("É necessario preencher ao menos um campo de busca");
        }
        return metricResultDao.findByFilter(filter);
    }

    
    public List<MetricResult> findAllSources() {
        return metricResultDao.findAllSources();
    }
    
    @Override
    protected void validateObject(MetricResult object) {
        if(object == null){
            throw new RuntimeException("Metric result nulo");
        }
        if(object.getCommit() == null){
            throw new RuntimeException("Commit nulo");
        }
        if(StringUtils.isBlank(object.getCommit().getSha())){
            throw new RuntimeException("ID do commit está vazio");
        }
        if(object.getMetric() == null){
            throw new RuntimeException("Métrica nula");
        }
        if(StringUtils.isBlank(object.getMetric().getInitials())){
            throw new RuntimeException("ID da métrica está vazia");
        }
        if(object.getValue() == null){
            throw new RuntimeException("Valor vazio");
        }
        if(StringUtils.isBlank(object.getSource())){
            throw new RuntimeException("Source em branco");
        }
    }

    @Override
    protected boolean validateFilter(MetricResult object) {
        return object != null && (object.getCommit() == null ||
                StringUtils.isBlank(object.getCommit().getSha()) ||
                object.getMetric() == null ||
                StringUtils.isBlank(object.getMetric().getInitials()) ||
                object.getValue() == null ||
                object.getDeltaValue() == null ||
                StringUtils.isBlank(object.getSource()));
    }
    
}
