/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rankode.core.bc;

import com.rankode.core.dao.CommitDao;
import com.rankode.core.model.Commit;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Matheus Pelegrini
 */
public class CommitBc extends PatternBc<Commit>{
    
    private final CommitDao commitDao;

    public CommitBc() {
        commitDao = new CommitDao();
    }

    @Override
    public void insert(Commit objeto) {
        validateObject(objeto);
        commitDao.insert(objeto);
    }

    @Override
    public void update(Commit objeto) {
        validateObject(objeto);
        commitDao.update(objeto);
    }

    @Override
    public void delete(Commit objeto) {
        commitDao.delete(objeto);
    }
    
    public Commit findById(String sha){
        return commitDao.findById(sha);
    }
    
    @Override
    public List<Commit> findAll() {
        return commitDao.findAll();
    }

    @Override
    public List<Commit> findByFilter(Commit filter) {
        if(!validateFilter(filter)){
            throw new RuntimeException("É necessario preencher ao menos um campo de busca");
        }
        return commitDao.findByFilter(filter);
    }

    @Override
    protected void validateObject(Commit object) {
        if(object == null){
            throw new RuntimeException("Target nulo");
        }
        if(StringUtils.isBlank(object.getSha())){
            throw new RuntimeException("Sha em branco");
        }
        if(object.getCollaborator() == null){
            throw new RuntimeException("Colaborador nulo");
        }
        if(StringUtils.isBlank(object.getCollaborator().getDeveloper().getLogin())){
            throw new RuntimeException("Login do colaborador em branco");
        }
        if(object.getDate() == null){
            throw new RuntimeException("Data nula");
        }
    }

    @Override
    protected boolean validateFilter(Commit object) {
        return object != null && (object.getCollaborator() != null ||
                StringUtils.isNotBlank(object.getCollaborator().getDeveloper().getLogin()) ||
                object.getDate() != null);
    }
    
}
