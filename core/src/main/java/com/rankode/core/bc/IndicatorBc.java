/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rankode.core.bc;

import com.rankode.core.dao.IndicatorDao;
import com.rankode.core.model.Indicator;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Matheus Pelegrini
 */
public class IndicatorBc extends PatternBc<Indicator>{
    
    private final IndicatorDao indicatorDao;

    public IndicatorBc() {
        indicatorDao = new IndicatorDao();
    }

    @Override
    public void insert(Indicator objeto) {
        validateObject(objeto);
        indicatorDao.insert(objeto);
    }

    @Override
    public void update(Indicator objeto) {
        validateObject(objeto);
        indicatorDao.update(objeto);
    }

    @Override
    public void delete(Indicator objeto) {
        indicatorDao.delete(objeto);
    }
    
    public Indicator findById(int id){
        return indicatorDao.findById(id);
    }
    
    @Override
    public List<Indicator> findAll() {
        return indicatorDao.findAll();
    }

    @Override
    public List<Indicator> findByFilter(Indicator filter) {
        if(!validateFilter(filter)){
            throw new RuntimeException("É necessario preencher ao menos um campo de busca");
        }
        return indicatorDao.findByFilter(filter);
    }

    @Override
    protected void validateObject(Indicator object) {
        if(object == null){
            throw new RuntimeException("Target nulo");
        }
        if(StringUtils.isBlank(object.getName())){
            throw new RuntimeException("Nome em branco");
        }
        if(object.getMax() == null){
            throw new RuntimeException("Valor máximo vazio");
        }
        if(object.getMin() == null){
            throw new RuntimeException("Valor mínimo vazio");
        }
        if(object.getMax()<=object.getMin()){
            throw new RuntimeException("Valor mínimo não pode ser maior ou igual ao valor máximo");
        }
        if(object.getMetric() == null){
            throw new RuntimeException("Métrica nula");
        }
        if(StringUtils.isBlank(object.getMetric().getInitials())){
            throw new RuntimeException("A Sigla da métrica está em branco");
        }
        if(object.getProject()== null){
            throw new RuntimeException("Projeto nulo");
        }
        if(StringUtils.isBlank(object.getMetric().getInitials())){
            throw new RuntimeException("A Sigla da métrica está em branco");
        }
    }

    @Override
    protected boolean validateFilter(Indicator object) {
        return object != null && (StringUtils.isBlank(object.getName()) ||
                object.getMax() == null ||
                object.getMin() == null ||
                object.getMetric()== null ||
                object.getMetric().getInitials() == null ||
                object.getProject()== null ||
                object.getProject().getId() == null);
    }
    
}
