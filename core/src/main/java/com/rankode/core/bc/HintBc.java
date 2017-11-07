/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rankode.core.bc;

import com.rankode.core.dao.HintDao;
import com.rankode.core.model.Hint;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Matheus Pelegrini
 */
public class HintBc extends PatternBc<Hint>{
    
    private final HintDao hintDao;

    public HintBc() {
        hintDao = new HintDao();
    }

    @Override
    public void insert(Hint objeto) {
        validateObject(objeto);
        hintDao.insert(objeto);
    }

    @Override
    public void update(Hint objeto) {
        validateObject(objeto);
        hintDao.update(objeto);
    }

    @Override
    public void delete(Hint objeto) {
        hintDao.delete(objeto);
    }
    
    public Hint findById(int id){
        return hintDao.findById(id);
    }
    
    @Override
    public List<Hint> findAll() {
        return hintDao.findAll();
    }

    @Override
    public List<Hint> findByFilter(Hint filter) {
        if(!validateFilter(filter)){
            throw new RuntimeException("É necessario preencher ao menos um campo de busca");
        }
        return hintDao.findByFilter(filter);
    }

    @Override
    protected void validateObject(Hint object) {
        if(object == null){
            throw new RuntimeException("Dica nula");
        }
        if(StringUtils.isBlank(object.getText())){
            throw new RuntimeException("Texto da dica em branco");
        }
        if(object.getIndicator() == null){
            throw new RuntimeException("Uma dica deve pertencer a um indicador");
        }
        if(object.getIndicator().getId() == null){
            throw new RuntimeException("O ID do indicador está em branco");
        }
    }

    @Override
    protected boolean validateFilter(Hint object) {
        return object != null && (StringUtils.isNotBlank(object.getText()) ||
                object.getIndicator() != null ||
                object.getIndicator().getId() != null);
    }
    
}
