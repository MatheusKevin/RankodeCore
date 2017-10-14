/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rankode.core.dao;

import com.rankode.core.dao.utils.Connect;
import com.rankode.core.model.Collaborator;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Matheus
 */
public class CollaboratorDao extends PatternDao<Collaborator>{
    
    private final Connect connection;

    private final StringBuilder insertSQL = new StringBuilder()
            .append("INSERT INTO COLLABORATORS ")
            .append("(DEVELOPERS_LOGIN, PROJECTS_ID, XP, ADMISSION_DATE) ")
            .append("VALUES ")
            .append("(?,?,?,?)");
    
    private final StringBuilder updateSQL = new StringBuilder()
            .append("UPDATE COLLABORATORS ")
            .append("XP=?, DEMISSION_DATE=? ")
            .append("WHERE DEVELOPERS_LOGIN=? AND PROJECTS_ID=? AND ADMISSION_DATE=?");
    
    private final StringBuilder deleteSQL = new StringBuilder()
            .append("DELETE FROM COLLABORATORS ")
            .append("WHERE DEVELOPERS_LOGIN=? AND PROJECTS_ID=? AND ADMISSION_DATE=? ");
    
    private final StringBuilder selectIdSQL =  new StringBuilder()
            .append("SELECT * ")
            .append("FROM COLLABORATORS ")
            .append("WHERE DEVELOPERS_LOGIN=? AND PROJECTS_ID=? AND ADMISSION_DATE=? ");
	
    private final StringBuilder selectAllSQL =  new StringBuilder()
            .append("SELECT * ")
            .append("FROM COLLABORATORS ");
    
    public CollaboratorDao(){
        connection = new Connect();
    }

    @Override
    public void insert(Collaborator object) {
        int cont = 1;
        PreparedStatement ps;
        try {
            ps = connection.getConnection().prepareStatement(insertSQL.toString());
            ps.setString(cont++, object.getDeveloper().getLogin());
            ps.setInt(cont++, object.getProject().getId());
            ps.setInt(cont++, object.getXp());
            ps.setDate(cont++, object.getAdmissionDate());

            connection.executeUpdate(ps);
            connection.close();
        } catch (SQLException e) {
                throw new RuntimeException("Problemas no sistema, por favor tente mais tarde "+ e.toString());
        }
    }

    @Override
    public void update(Collaborator object) {
        int cont = 1;
        PreparedStatement ps;
        try {
            ps = connection.getConnection().prepareStatement(updateSQL.toString());
            ps.setInt(cont++, object.getXp());
            ps.setDate(cont++, object.getDemissionDate());
            
            ps.setString(cont++, object.getDeveloper().getLogin());
            ps.setInt(cont++, object.getProject().getId());
            ps.setDate(cont++, object.getAdmissionDate());

            connection.executeUpdate(ps);
            connection.close();
        } catch (SQLException e) {
                throw new RuntimeException("Problemas no sistema, por favor tente mais tarde "+ e.toString());
        }
    }

    @Override
    public void delete(Collaborator object) {
        int cont = 1;
        PreparedStatement ps;
        try {
            ps = connection.getConnection().prepareStatement(deleteSQL.toString());
            ps.setString(cont++, object.getDeveloper().getLogin());
            ps.setInt(cont++, object.getProject().getId());
            ps.setDate(cont++, object.getAdmissionDate());

            connection.executeUpdate(ps);
            connection.close();
        } catch (SQLException e) {
                throw new RuntimeException("Problemas no sistema, por favor tente mais tarde "+ e.toString());
        }
    }
    
    public Collaborator findById(String login, int idProject, Date admissionDate) {
        int cont = 1;
        Collaborator obj = null;
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = connection.getConnection().prepareStatement(selectIdSQL.toString());
            ps.setString(cont++, login);
            ps.setInt(cont++, idProject);
            ps.setDate(cont++, admissionDate);
            rs = connection.executeQuery(ps);
            if(rs.next()){
                obj = populateObject(rs);
            }
            connection.close();
        } catch (SQLException e) {
                throw new RuntimeException("Problemas no sistema, por favor tente mais tarde" + e.toString());
        }
        return obj;
    }
    
    private String prepareStringSQLForFilter(Collaborator filter){
        StringBuilder sb = new StringBuilder(selectAllSQL.toString());
        sb.append(" WHERE ");

        boolean and = false;

        if(filter.getDeveloper().getLogin()!= null){
            if(!and){
                    and = true;
            }else{
                    sb.append(" AND ");
            }
            sb.append(" DEVELOPERS_LOGIN = ? ");
        }
        if(filter.getProject().getId()!= null){
            if(!and){
                    and = true;
            }else{
                    sb.append(" AND ");
            }
            sb.append(" PROJECTS_ID = ? ");
        }
        if(filter.getXp()!= null){
            if(!and){
                    and = true;
            }else{
                    sb.append(" AND ");
            }
            sb.append(" XP = ? ");    
        }
        if(filter.getAdmissionDate()!= null){
            if(!and){
                    and = true;
            }else{
                    sb.append(" AND ");
            }
            sb.append(" ADMISSION_DATE = ? ");    
        }
        if(filter.getAdmissionDate()!= null){
            if(!and){
                    and = true;
            }else{
                    sb.append(" AND ");
            }
            sb.append(" ADMISSION_DATE = ? ");    
        }
        if(filter.getDemissionDate()!= null){
            if(!and){
                    and = true;
            }else{
                    sb.append(" AND ");
            }
            sb.append(" DEMISSION_DATE = ? ");    
        }
        return sb.toString();
    }
    
    private PreparedStatement prepareStatementForFilter(Collaborator filter, PreparedStatement ps) throws SQLException{
        int cont = 1;
        if(filter.getDeveloper().getLogin() != null)
            ps.setString(cont++, filter.getDeveloper().getLogin());
        if(filter.getProject().getId()!= null)
            ps.setInt(cont++, filter.getProject().getId());
        if(filter.getXp()!= null)
            ps.setInt(cont++, filter.getXp());
        if(filter.getAdmissionDate()!= null)
            ps.setDate(cont++, filter.getAdmissionDate());
        if(filter.getDemissionDate()!= null)
            ps.setDate(cont++, filter.getDemissionDate());
        
        return ps;
    }
    
    @Override
    public List<Collaborator> findByFilter(Collaborator filter) {
        List<Collaborator> list = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = connection.getConnection().prepareStatement(prepareStringSQLForFilter(filter));
            rs = connection.executeQuery(prepareStatementForFilter(filter, ps));
            while(rs.next()){
                    list.add(populateObject(rs));
            }
            connection.close();
        } catch (SQLException e) {
                throw new RuntimeException("Problemas no sistema, por favor tente mais tarde" + e.toString());
        }
        return list;
    }

    @Override
    public List<Collaborator> findAll() {
        List<Collaborator> list = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = connection.getConnection().prepareStatement(selectAllSQL.toString());
            rs = connection.executeQuery(ps);
            while(rs.next()){
                    list.add(populateObject(rs));
            }
            connection.close();
        } catch (SQLException e) {
                throw new RuntimeException("Problemas no sistema, por favor tente mais tarde" + e.toString());
        }
        return list;
    }

    @Override
    public Collaborator populateObject(ResultSet rs) throws SQLException {
        Collaborator obj = new Collaborator();
        obj.setXp(rs.getInt("XP"));
        obj.setAdmissionDate(rs.getDate("ADMISSION_DATE"));
        obj.setDemissionDate(rs.getDate("DEMISSION_DATE"));
        
        return obj;
    }
    
}