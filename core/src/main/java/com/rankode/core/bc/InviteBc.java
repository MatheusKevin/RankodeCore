/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rankode.core.bc;

import com.rankode.core.dao.InviteDao;
import com.rankode.core.model.Invite;
import java.util.List;

/**
 *
 * @author Matheus Pelegrini
 */
public class InviteBc extends PatternBc<Invite>{
    
    private final InviteDao inviteDao;

    public InviteBc() {
        inviteDao = new InviteDao();
    }

    @Override
    public void insert(Invite objeto) {
        validateObject(objeto);
        inviteDao.insert(objeto);
    }

    @Override
    public void update(Invite objeto) {
        validateObject(objeto);
        inviteDao.update(objeto);
    }

    @Override
    public void delete(Invite objeto) {
        inviteDao.delete(objeto);
    }
    
    public Invite findById(int id){
        return inviteDao.findById(id);
    }

    @Override
    public List<Invite> findAll() {
        return inviteDao.findAll();
    }

    @Override
    public List<Invite> findByFilter(Invite filter) {
        if(!validateFilter(filter)){
            throw new RuntimeException("É necessario preencher ao menos um campo de busca");
        }
        return inviteDao.findByFilter(filter);
    }

    @Override
    protected void validateObject(Invite object) {
        if(object == null){
            throw new RuntimeException("Convite nulo");
        }
        if(object.getProject() == null){
            throw new RuntimeException("Projeto nulo");
        }
        if(object.getProject().getId() == null){
            throw new RuntimeException("Id do projeto nulo");
        }
    }

    @Override
    protected boolean validateFilter(Invite object) {
        return object != null && (object.getProject() == null ||
                object.getProject().getId() == null);
    }
    
}
