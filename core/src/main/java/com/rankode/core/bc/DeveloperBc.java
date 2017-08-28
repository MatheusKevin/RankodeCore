/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rankode.core.bc;

import com.rankode.core.dao.DeveloperDao;
import com.rankode.core.model.Developer;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Alexandre
 */
public class DeveloperBc extends PatternBC<Developer>{

    DeveloperDao developerDao;

    public DeveloperBc() {
        developerDao = new DeveloperDao();
    }
    
    @Override
    public void insert(Developer objeto) {
        validateObject(objeto);
        developerDao.insert(objeto);
    }

    @Override
    public void update(Developer objeto) {
        validateObject(objeto);
        developerDao.update(objeto);
    }

    @Override
    public void delete(Developer objeto) {
        if(developerDao.findById(objeto.getLogin())!=null){
            developerDao.delete(objeto);
        }
    }

    @Override
    public List<Developer> findAll() {
        return developerDao.findAll();
    }

    @Override
    public Developer findById(String id) {
        return developerDao.findById(id);
    }

    @Override
    public List<Developer> findByFilter(Developer filter) {
        if(!validateFilter(filter)){
            throw new RuntimeException("É necessario preencher ao menos um campo de busca");
        }
        return developerDao.findByFilter(filter);
    }

    @Override
    protected void validateObject(Developer object) {
        if(StringUtils.isBlank(object.getLogin())){
            throw new RuntimeException("Login nulo");
        }
        if(StringUtils.isBlank(object.getFirstName())){
            throw new RuntimeException("Nome nulo");
        }
        if(StringUtils.isBlank(object.getLastName())){
            throw new RuntimeException("Sobrenome nulo");
        }
        if(StringUtils.isBlank(object.getPassword())){
            throw new RuntimeException("Senha nula");
        }
        if(StringUtils.isBlank(object.getEmail())){
            throw new RuntimeException("Email nulo");
        }
    }

    @Override
    protected boolean validateFilter(Developer object) {
            return object != null && (StringUtils.isNotBlank(object.getLogin()) ||
            StringUtils.isNotBlank(object.getFirstName()) ||
            StringUtils.isNotBlank(object.getLastName()) ||
            StringUtils.isNotBlank(object.getPassword()) ||
            StringUtils.isNotBlank(object.getEmail())
        );
    }

    @Override
    public Developer findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
