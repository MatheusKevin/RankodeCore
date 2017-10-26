/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rankode.core.model;

/*
 * @author Alexandre
 */
public class RepositoryAccount {
    
    private Developer developer;
    
    private String loginRepository;
    private String repository;

    public RepositoryAccount(Developer developer) {
        this.developer = developer;
    }

    public RepositoryAccount() {
    }
    
    public Developer getDeveloper() {
        return developer;
    }

    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }
    
    public String getLoginRepository() {
        return loginRepository;
    }

    public void setLoginRepository(String loginRepository) {
        this.loginRepository = loginRepository;
    }

    public String getRepository() {
        return repository;
    }

    public void setRepository(String repository) {
        this.repository = repository;
    }
    
}
