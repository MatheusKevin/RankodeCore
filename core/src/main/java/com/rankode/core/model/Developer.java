/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rankode.core.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alexandre
 */
public class Developer {
    
    private List<Project> projectsAsOwner;
    private List<Collaborator> projectsAsCollaborator;
    private List<RepositoryAccount> accounts;
    private List<Notification> notifications;
    
    private String login;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Integer level;
    private String profilePicture;

    public Developer(RepositoryAccount account) {
        accounts = new ArrayList<RepositoryAccount>();
        accounts.add(account);
    }
    
    public Developer() {
        accounts = new ArrayList<RepositoryAccount>();
    }
    
    public void addProjectAsOwner(Project obj){
        this.projectsAsOwner.add(obj);
    }
    
    public void addProjectAsCollaborator(Collaborator obj){
        this.projectsAsCollaborator.add(obj);
    }
    
    public void addRepositoryAccount(RepositoryAccount obj){
        this.accounts.add(obj);
    }
    
    public void addNotification(Notification obj){
        this.notifications.add(obj);
    }

    public List<Project> getProjectsAsOwner() {
        return projectsAsOwner;
    }

    public void setProjectsAsOwner(List<Project> projectsAsOwner) {
        this.projectsAsOwner = projectsAsOwner;
    }

    public List<Collaborator> getProjectsAsCollaborator() {
        return projectsAsCollaborator;
    }

    public void setProjectsAsCollaborator(List<Collaborator> projectsAsCollaborator) {
        this.projectsAsCollaborator = projectsAsCollaborator;
    }

    public List<RepositoryAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<RepositoryAccount> accounts) {
        this.accounts = accounts;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
    
    
}
