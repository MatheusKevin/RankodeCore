/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rankode.core.dao;

import com.rankode.core.dao.utils.Connect;
import com.rankode.core.model.Developer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alexandre
 */
public class DeveloperDao extends PatternDao<Developer>{
    
private final Connect connection;

private final StringBuilder insertSQL = new StringBuilder()
            .append("INSERT INTO DEVELOPERS ")
            .append("(LOGIN, FIRST_NAME, LAST_NAME, PASSWORD, EMAIL, PROFILE_PICTURE) ")
            .append("VALUES ")
            .append("(?,?,?,?,?,?)");
    
    private final StringBuilder updateSQL = new StringBuilder()
            .append("UPDATE DEVELOPERS ")
            .append("SET LOGIN=?, FIRST_NAME=?, LAST_NAME=?, PASSWORD=?, EMAIL=?, PROFILE_PICTURE=? ")
            .append("WHERE LOGIN=?");
    
    private final StringBuilder deleteSQL = new StringBuilder()
            .append("DELETE FROM DEVELOPERS ")
            .append("WHERE LOGIN=?");
    
    private final StringBuilder selectIdSQL =  new StringBuilder()
            .append("SELECT * ")
            .append("FROM DEVELOPERS ")
            .append("WHERE LOGIN = ? ");
	
    private final StringBuilder selectAllSQL =  new StringBuilder()
            .append("SELECT * ")
            .append("FROM DEVELOPERS ");
    
    public DeveloperDao(){
        connection = new Connect();
    }
    
    @Override
    public void insert(Developer object) {
        int cont = 1;
        PreparedStatement ps;
        try {
            ps = connection.getConnection().prepareStatement(insertSQL.toString());
            ps.setString(cont++, object.getLogin());
            ps.setString(cont++, object.getFirstName());
            ps.setString(cont++, object.getLastName());
            ps.setString(cont++, object.getPassword());
            ps.setString(cont++, object.getEmail());
            ps.setString(cont++, object.getProfilePicture());
            
            connection.executeUpdate(ps);
        } catch (SQLException e) {
                throw new RuntimeException("Problemas no sistema, por favor tente mais tarde "+ e.toString());
        }
    }

    @Override
    public void update(Developer object) {  
        int cont = 1;
        PreparedStatement ps;
        try {
            ps = connection.getConnection().prepareStatement(updateSQL.toString());
            ps.setString(cont++, object.getLogin());
            ps.setString(cont++, object.getFirstName());
            ps.setString(cont++, object.getLastName());
            ps.setString(cont++, object.getPassword());
            ps.setString(cont++, object.getEmail());
            ps.setString(cont++, object.getProfilePicture());
            
            connection.executeUpdate(ps);
        } catch (SQLException e) {
                throw new RuntimeException("Problemas no sistema, por favor tente mais tarde "+ e.toString());
        }
    }

    @Override
    public void delete(Developer object) { 
        int cont = 1;
        PreparedStatement ps;
        try {
            ps = connection.getConnection().prepareStatement(deleteSQL.toString());
            ps.setString(cont++, object.getLogin());

            connection.executeUpdate(ps);
        } catch (SQLException e) {
                throw new RuntimeException("Problemas no sistema, por favor tente mais tarde "+ e.toString());
        }
    }

    @Override
    public Developer findById(String id) {
        int cont = 1;
        Developer concurso = null;
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = connection.getConnection().prepareStatement(selectIdSQL.toString());
            ps.setString(cont++, id);
            rs = connection.executeQuery(ps);
            if(rs.next()){
                    concurso = populateObject(rs);
            }
        } catch (SQLException e) {
                throw new RuntimeException("Problemas no sistema, por favor tente mais tarde" + e.toString());
        }
        return concurso;
    }
    
    private String prepareStringSQLForFilter(Developer filtro){
        StringBuilder sb = new StringBuilder(selectAllSQL.toString());
        sb.append(" WHERE ");

        boolean and = false;

        if(filtro.getLogin() != null){
            if(!and){
                    and = true;
            }else{
                    sb.append(" AND ");
            }
            sb.append(" LOGIN = ? ");
        }
        if(filtro.getFirstName()!= null){
            if(!and){
                    and = true;
            }else{
                    sb.append(" AND ");
            }
            sb.append(" FIRST_NAME = ? ");
        }
        if(filtro.getLastName()!= null){
            if(!and){
                    and = true;
            }else{
                    sb.append(" AND ");
            }
            sb.append(" LAST_NAME = ? ");
        }
        if(filtro.getEmail()!= null){
            if(!and){
                    and = true;
            }else{
                    sb.append(" AND ");
            }
            sb.append(" EMAIL = ? ");
        }
        return sb.toString();
    }
    
    private PreparedStatement prepareStatementForFilter(Developer filtro, PreparedStatement ps) throws SQLException{
        int cont = 1;
        if(filtro.getLogin() != null)
            ps.setString(cont++, filtro.getLogin());
        if(filtro.getFirstName()!= null)
            ps.setString(cont++, filtro.getFirstName());
        if(filtro.getLastName()!= null)
            ps.setString(cont++, filtro.getLastName());
        if(filtro.getEmail()!= null)
            ps.setString(cont++, filtro.getEmail());
        
        return ps;
    }

    public List<Developer> findByFilter(Developer filter) {
        List<Developer> concursos = new ArrayList<Developer>();
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = connection.getConnection().prepareStatement(prepareStringSQLForFilter(filter));
            rs = connection.executeQuery(prepareStatementForFilter(filter, ps));
            while(rs.next()){
                    concursos.add(populateObject(rs));
            }
        } catch (SQLException e) {
                throw new RuntimeException("Problemas no sistema, por favor tente mais tarde" + e.toString());
        }
        return concursos;
    }

    @Override
    public List<Developer> findAll() {
        List<Developer> concursos = new ArrayList<Developer>();
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = connection.getConnection().prepareStatement(selectAllSQL.toString());
            rs = connection.executeQuery(ps);
            while(rs.next()){
                    concursos.add(populateObject(rs));
            }
        } catch (SQLException e) {
                throw new RuntimeException("Problemas no sistema, por favor tente mais tarde" + e.toString());
        }
        return concursos;
    }

    @Override
    public Developer populateObject(ResultSet rs) throws SQLException {
        Developer obj = new Developer();
        obj.setLogin(rs.getString("LOGIN"));
        obj.setFirstName(rs.getString("FIRST_NAME"));
        obj.setLastName(rs.getString("LAST_NAME"));
        obj.setPassword(rs.getString("PASSWORD"));
        obj.setEmail(rs.getString("EMAIL"));
        obj.setProfilePicture(rs.getString("PROFILE_PICTURE"));
        
        return obj;
    }   

    @Override
    public Developer findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
