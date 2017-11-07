/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rankode.core.bc;

import com.rankode.core.dao.RepositoryAccountDao;
import com.rankode.core.model.RepositoryAccount;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Matheus Pelegrini
 */
public class RepositoryAccountBc extends PatternBc<RepositoryAccount>{
    
    private final RepositoryAccountDao repositoryAccountDao;

    public RepositoryAccountBc() {
        repositoryAccountDao = new RepositoryAccountDao();
    }

    @Override
    public void insert(RepositoryAccount objeto) {
        validateObject(objeto);
        repositoryAccountDao.insert(objeto);
    }

    @Override
    public void update(RepositoryAccount objeto) {
        validateObject(objeto);
        repositoryAccountDao.update(objeto);
    }

    @Override
    public void delete(RepositoryAccount objeto) {
        repositoryAccountDao.delete(objeto);
    }
    
    public RepositoryAccount findById(String login) {
        return repositoryAccountDao.findById(login);
    }
    
    @Override
    public List<RepositoryAccount> findAll() {
        return repositoryAccountDao.findAll();
    }

    @Override
    public List<RepositoryAccount> findByFilter(RepositoryAccount filter) {
        if(!validateFilter(filter)){
            throw new RuntimeException("É necessario preencher ao menos um campo de busca");
        }
        return repositoryAccountDao.findByFilter(filter);
    }

    @Override
    protected void validateObject(RepositoryAccount object) {
        if(object == null)
            throw new RuntimeException("Repositório Nulo");
        if(StringUtils.isBlank(object.getLoginRepository()))
            throw new RuntimeException("Login do Repositório em branco");
        if(StringUtils.isBlank(object.getRepository()))
            throw new RuntimeException("Repositório em branco");
        if(object.getDeveloper() == null)
            throw new RuntimeException("Desenvolvedor nulo");
        if(StringUtils.isBlank(object.getDeveloper().getLogin()))
            throw new RuntimeException("Login do desenvolvedor em branco");
    }

    @Override
    protected boolean validateFilter(RepositoryAccount object) {
        return object != null && (StringUtils.isNotBlank(object.getRepository()) ||
                object.getDeveloper() != null ||
                StringUtils.isNotBlank(object.getDeveloper().getLogin()));
    }
    
}
