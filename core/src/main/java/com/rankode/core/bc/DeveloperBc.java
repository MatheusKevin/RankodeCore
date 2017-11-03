/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rankode.core.bc;

import com.rankode.core.bc.utils.Validations;
import com.rankode.core.dao.DeveloperDao;
import com.rankode.core.model.Developer;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Alexandre
 */
public class DeveloperBc extends PatternBc<Developer>{

    private final DeveloperDao developerDao;

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
        developerDao.delete(objeto);
    }

    @Override
    public List<Developer> findAll() {
        return developerDao.findAll();
    }

    public Developer findById(String login) {
        return developerDao.findById(login);
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
        if(object == null){
            throw new RuntimeException("Desenvolvedor nulo");
        }
        if(StringUtils.isBlank(object.getLogin())){
            throw new RuntimeException("Login em branco");
        }
        if(findById(object.getLogin()) != null){
            throw new RuntimeException("Login já utilizado");
        }
        if(StringUtils.isBlank(object.getFirstName())){
            throw new RuntimeException("Nome em branco");
        }
        if(StringUtils.isBlank(object.getLastName())){
            throw new RuntimeException("Sobrenome em branco");
        }
        if(StringUtils.isBlank(object.getPassword())){
            throw new RuntimeException("Senha em branco");
        }
        if(StringUtils.isBlank(object.getEmail())){
            throw new RuntimeException("Email em branco");
        }
        if(!Validations.validateEmail(object.getEmail())){
            throw new RuntimeException("Email inválido");
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
    
}
