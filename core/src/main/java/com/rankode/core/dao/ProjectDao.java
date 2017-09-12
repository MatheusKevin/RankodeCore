/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rankode.core.dao;

import com.rankode.core.dao.utils.Connect;
import com.rankode.core.model.Project;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Matheus
 */
public class ProjectDao extends PatternDao<Project>{
    
    private final Connect connection;

    private final StringBuilder insertSQL = new StringBuilder()
            .append("INSERT INTO PROJECTS ")
            .append("(OWNER_LOGIN, PROJECT_NAME) ")
            .append("VALUES ")
            .append("(?,?)");
    
    private final StringBuilder updateSQL = new StringBuilder()
            .append("UPDATE PROJECTS ")
            .append("OWNER_LOGIN=?, PROJECT_NAME=? ")
            .append("WHERE ID=?");
    
    private final StringBuilder deleteSQL = new StringBuilder()
            .append("DELETE FROM PROJECTS ")
            .append("WHERE ID=?");
    
    private final StringBuilder selectIdSQL =  new StringBuilder()
            .append("SELECT * ")
            .append("FROM PROJECTS ")
            .append("WHERE ID = ? ");
	
    private final StringBuilder selectAllSQL =  new StringBuilder()
            .append("SELECT * ")
            .append("FROM PROJECTS ");
    
    public ProjectDao(){
        connection = new Connect();
    }
    
    @Override
    public void insert(Project object) {
        int cont = 1;
        PreparedStatement ps;
        try {
            ps = connection.getConnection().prepareStatement(insertSQL.toString());
            ps.setString(cont++, object.getOwner().getLogin());
            ps.setString(cont++, object.getName());
            
            connection.executeUpdate(ps);
        } catch (SQLException e) {
                throw new RuntimeException("Problemas no sistema, por favor tente mais tarde "+ e.toString());
        }
    }

    @Override
    public void update(Project object) {
        int cont = 1;
        PreparedStatement ps;
        try {
            ps = connection.getConnection().prepareStatement(updateSQL.toString());
            ps.setString(cont++, object.getOwner().getLogin());
            ps.setString(cont++, object.getName());
            
            ps.setInt(cont++, object.getId());
            
            connection.executeUpdate(ps);
        } catch (SQLException e) {
                throw new RuntimeException("Problemas no sistema, por favor tente mais tarde "+ e.toString());
        }
    }

    @Override
    public void delete(Project object) {
        int cont = 1;
        PreparedStatement ps;
        try {
            ps = connection.getConnection().prepareStatement(deleteSQL.toString());
            ps.setInt(cont++, object.getId());

            connection.executeUpdate(ps);
        } catch (SQLException e) {
                throw new RuntimeException("Problemas no sistema, por favor tente mais tarde "+ e.toString());
        }
    }

    public Project findById(int id) {
        int cont = 1;
        Project obj = null;
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = connection.getConnection().prepareStatement(selectIdSQL.toString());
            ps.setInt(cont++, id);
            rs = connection.executeQuery(ps);
            if(rs.next()){
                obj = populateObject(rs);
            }
        } catch (SQLException e) {
                throw new RuntimeException("Problemas no sistema, por favor tente mais tarde" + e.toString());
        }
        return obj;
    }


    private String prepareStringSQLForFilter(Project filter){
        StringBuilder sb = new StringBuilder(selectAllSQL.toString());
        sb.append(" WHERE ");

        boolean and = false;

        if(filter.getOwner().getLogin()!= null){
            if(!and){
                    and = true;
            }else{
                    sb.append(" AND ");
            }
            sb.append(" OWNER_LOGIN = ? ");
        }
        if(filter.getName()!= null){
            if(!and){
                    and = true;
            }else{
                    sb.append(" AND ");
            }
            sb.append(" PROJECT_NAME = ? ");
        }
        
        return sb.toString();
    }
    
    private PreparedStatement prepareStatementForFilter(Project filter, PreparedStatement ps) throws SQLException{
        int cont = 1;
        if(filter.getOwner().getLogin() != null)
            ps.setString(cont++, filter.getOwner().getLogin());
        if(filter.getName()!= null)
            ps.setString(cont++, filter.getName());
        
        return ps;
    }
    
    @Override
    public List<Project> findByFilter(Project filter) {
        List<Project> list = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = connection.getConnection().prepareStatement(prepareStringSQLForFilter(filter));
            rs = connection.executeQuery(prepareStatementForFilter(filter, ps));
            while(rs.next()){
                    list.add(populateObject(rs));
            }
        } catch (SQLException e) {
                throw new RuntimeException("Problemas no sistema, por favor tente mais tarde" + e.toString());
        }
        return list;
    }

    @Override
    public List<Project> findAll() {
        List<Project> list = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = connection.getConnection().prepareStatement(selectAllSQL.toString());
            rs = connection.executeQuery(ps);
            while(rs.next()){
                    list.add(populateObject(rs));
            }
        } catch (SQLException e) {
                throw new RuntimeException("Problemas no sistema, por favor tente mais tarde" + e.toString());
        }
        return list;
    }

    @Override
    public Project populateObject(ResultSet rs) throws SQLException {
        Project obj = new Project();
        obj.setId(rs.getInt("ID"));
        obj.setName(rs.getString("PROJECT_NAME"));
        
        return obj;
    }
    
    
}
