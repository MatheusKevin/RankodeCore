/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rankode.core.bc;

import com.rankode.core.dao.CollaboratorDao;
import com.rankode.core.model.Collaborator;
import java.sql.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Matheus
 */
public class CollaboratorBc extends PatternBc<Collaborator>{
    
    private final CollaboratorDao collaboratorDao;
    
    public CollaboratorBc(){
        collaboratorDao = new CollaboratorDao();
    }
    
    @Override
    public void insert(Collaborator objeto) {
        validateObject(objeto);
        collaboratorDao.insert(objeto);
    }

    @Override
    public void update(Collaborator objeto) {
        validateObject(objeto);
        collaboratorDao.update(objeto);
    }

    @Override
    public void delete(Collaborator objeto) {
        collaboratorDao.delete(objeto);
    }

    @Override
    public List<Collaborator> findAll() {
        return collaboratorDao.findAll();
    }


    public Collaborator findById(String login, int idProject, Date admissionDate) {
        return collaboratorDao.findById(login, idProject, admissionDate);
    }

    @Override
    public List<Collaborator> findByFilter(Collaborator filter) {
        if(!validateFilter(filter)){
            throw new RuntimeException("É necessario preencher ao menos um campo de busca");
        }
        return collaboratorDao.findByFilter(filter);
    }

    @Override
    protected void validateObject(Collaborator object) {
        if(object == null){
            throw new RuntimeException("Colaborador nulo");
        }
        if(object.getDeveloper() == null){
            throw new RuntimeException("Desenvolvedor nulo");
        }
        if(object.getProject() == null){
            throw new RuntimeException("Projeto nulo");
        }
        if(StringUtils.isBlank(object.getDeveloper().getLogin())){
            throw new RuntimeException("Login do desenvolvedor em branco");
        }
        if(object.getProject().getId() == null){
            throw new RuntimeException("Id do projeto em branco");
        }
        if(object.getAdmissionDate() == null){
            throw new RuntimeException("Data de admissão em branco");
        }
        if(object.getDemissionDate() != null && object.getDemissionDate().before(object.getAdmissionDate())){
            throw new RuntimeException("Data de demissão inválida");
        }
    }

    @Override
    protected boolean validateFilter(Collaborator object) {
        return object != null && (object.getDeveloper() != null ||
                StringUtils.isNotBlank(object.getDeveloper().getLogin()) ||
                object.getProject() != null ||
                object.getProject().getId() != null ||
                object.getXp() != null||
                object.getAdmissionDate() != null||
                object.getDemissionDate() != null);
    }
    
}
