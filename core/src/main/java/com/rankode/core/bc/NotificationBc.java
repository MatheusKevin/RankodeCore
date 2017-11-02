/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rankode.core.bc;

import com.rankode.core.dao.NotificationDao;
import com.rankode.core.model.Notification;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Matheus Pelegrini
 */
public class NotificationBc extends PatternBc<Notification>{
    
    private final NotificationDao notificationDao;

    public NotificationBc() {
        notificationDao = new NotificationDao();
    }

    @Override
    public void insert(Notification objeto) {
        validateObject(objeto);
        notificationDao.insert(objeto);
    }

    @Override
    public void update(Notification objeto) {
        validateObject(objeto);
        notificationDao.update(objeto);
    }

    @Override
    public void delete(Notification objeto) {
        notificationDao.delete(objeto);
    }
    
    public Notification findById(int id){
        return notificationDao.findById(id);
    }

    @Override
    public List<Notification> findAll() {
        return notificationDao.findAll();
    }

    @Override
    public List<Notification> findByFilter(Notification filter) {
        if(!validateFilter(filter)){
            throw new RuntimeException("É necessario preencher ao menos um campo de busca");
        }
        return notificationDao.findByFilter(filter);
    }

    @Override
    protected void validateObject(Notification object) {
        if(object == null){
            throw new RuntimeException("Notificação nula");
        }
        if(object.getDeveloper() == null){
            throw new RuntimeException("Desenvolvedor nulo");
        }
        if(StringUtils.isBlank(object.getDeveloper().getLogin())){
            throw new RuntimeException("Login do Desenvolvedor vazio");
        }
        if(StringUtils.isBlank(object.getTitle())){
            throw new RuntimeException("Título em branco");
        }
        if(StringUtils.isBlank(object.getContent())){
            throw new RuntimeException("Conteúdo em branco");
        }
        if(object.getDate() == null){
            throw new RuntimeException("Data nula");
        }
    }

    @Override
    protected boolean validateFilter(Notification object) {
        return object != null && (object.getDeveloper() == null ||
                StringUtils.isBlank(object.getDeveloper().getLogin()) ||
                StringUtils.isBlank(object.getTitle()) ||
                StringUtils.isBlank(object.getContent()) ||
                object.getDate() == null);
    }
    
}
