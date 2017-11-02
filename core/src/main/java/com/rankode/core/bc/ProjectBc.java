/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rankode.core.bc;

import com.rankode.core.dao.ProjectDao;
import com.rankode.core.model.Project;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Matheus Pelegrini
 */
public class ProjectBc extends PatternBc<Project>{
    
    private final ProjectDao projectDao;

    public ProjectBc() {
        projectDao = new ProjectDao();
    }

    @Override
    public void insert(Project objeto) {
        validateObject(objeto);
        projectDao.insert(objeto);
    }

    @Override
    public void update(Project objeto) {
        validateObject(objeto);
        projectDao.update(objeto);
    }

    @Override
    public void delete(Project objeto) {
        projectDao.delete(objeto);
    }
    
    public Project findById(int id){
        return projectDao.findById(id);
    }
    
    @Override
    public List<Project> findAll() {
        return projectDao.findAll();
    }

    @Override
    public List<Project> findByFilter(Project filter) {
        if(!validateFilter(filter)){
            throw new RuntimeException("É necessario preencher ao menos um campo de busca");
        }
        return projectDao.findByFilter(filter);
    }

    @Override
    protected void validateObject(Project object) {
        if(object == null){
            throw new RuntimeException("Project nulo");
        }
        if(StringUtils.isBlank(object.getName())){
            throw new RuntimeException("Nome em branco");
        }
        if(object.getOwner() == null){
            throw new RuntimeException("Owner nulo");
        }
        if(object.getOwner().getLogin() == null){
            throw new RuntimeException("Login do Owner nulo");
        }
    }

    @Override
    protected boolean validateFilter(Project object) {
        return object != null && (object.getOwner() == null ||
                StringUtils.isBlank(object.getOwner().getLogin()) ||
                StringUtils.isBlank(object.getName()));
    }
    
}
