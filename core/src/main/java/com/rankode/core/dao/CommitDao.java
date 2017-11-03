/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rankode.core.dao;

import com.rankode.core.dao.utils.Connect;
import com.rankode.core.model.Commit;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Matheus Pelegrini
 */
public class CommitDao extends PatternDao<Commit>{
    
    private final Connect connection;
    
    private final StringBuilder insertSQL = new StringBuilder()
            .append("INSERT INTO COMMITS ")
            .append("(SHA, COLLABORATORS_LOGIN, DATE) ")
            .append("VALUES ")
            .append("(?,?,?)");
    
    private final StringBuilder updateSQL = new StringBuilder()
            .append("UPDATE COMMITS SET ")
            .append("COLLABORATORS_LOGIN=?, DATE=? ")
            .append("WHERE SHA=?");
    
    private final StringBuilder deleteSQL = new StringBuilder()
            .append("DELETE FROM COMMITS ")
            .append("WHERE SHA=?");
    
    private final StringBuilder selectIdSQL =  new StringBuilder()
            .append("SELECT * ")
            .append("FROM COMMITS ")
            .append("WHERE SHA= ? ");
	
    private final StringBuilder selectAllSQL =  new StringBuilder()
            .append("SELECT * ")
            .append("FROM COMMITS ");
    
    public CommitDao(){
        connection = new Connect();
    }
    
    @Override
    public void insert(Commit object) {
        int cont = 1;
        PreparedStatement ps;
        try {
            ps = connection.getConnection().prepareStatement(insertSQL.toString());
            ps.setString(cont++, object.getSha());
            ps.setString(cont++, object.getCollaborator().getDeveloper().getLogin());
            ps.setDate(cont++, object.getDate());
            
            connection.executeUpdate(ps);
        }catch (SQLException e) {
                throw new RuntimeException("Problemas no sistema, por favor tente mais tarde "+ e.toString());
        }finally{
            connection.close();
        }
    }

    @Override
    public void update(Commit object) {
        int cont = 1;
        PreparedStatement ps;
        try {
            ps = connection.getConnection().prepareStatement(updateSQL.toString());
            ps.setString(cont++, object.getCollaborator().getDeveloper().getLogin());
            ps.setDate(cont++, object.getDate());
            
            ps.setString(cont++, object.getSha());
            
            connection.executeUpdate(ps);
        }catch (SQLException e) {
                throw new RuntimeException("Problemas no sistema, por favor tente mais tarde "+ e.toString());
        }finally{
            connection.close();
        }
    }

    @Override
    public void delete(Commit object) {
        int cont = 1;
        PreparedStatement ps;
        try {
            ps = connection.getConnection().prepareStatement(deleteSQL.toString());            
            ps.setString(cont++, object.getSha());
            
            connection.executeUpdate(ps);
        }catch (SQLException e) {
                throw new RuntimeException("Problemas no sistema, por favor tente mais tarde "+ e.toString());
        }finally{
            connection.close();
        }
    }
    
    public Commit findById(String sha) {
        int cont = 1;
        Commit obj = null;
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = connection.getConnection().prepareStatement(selectIdSQL.toString());
            ps.setString(cont++, sha);
            rs = connection.executeQuery(ps);
            if(rs.next()){
                obj = populateObject(rs);
            }
        } catch (SQLException e) {
                throw new RuntimeException("Problemas no sistema, por favor tente mais tarde" + e.toString());
        }finally{
            connection.close();
        }
        return obj;
    }
    
    private String prepareStringSQLForFilter(Commit filter){
        StringBuilder sb = new StringBuilder(selectAllSQL.toString());
        sb.append(" WHERE ");

        boolean and = false;

        if(filter.getCollaborator().getDeveloper().getLogin()!= null){
            if(!and){
                    and = true;
            }else{
                    sb.append(" AND ");
            }
            sb.append(" COLLABORATORS_LOGIN = ? ");
        }
        if(filter.getDate()!= null){
            if(!and){
                    and = true;
            }else{
                    sb.append(" AND ");
            }
            sb.append(" DATE = ? ");
        }
        
        return sb.toString();
    }
    
    private PreparedStatement prepareStatementForFilter(Commit filter, PreparedStatement ps) throws SQLException{
        int cont = 1;
        if(filter.getCollaborator().getDeveloper().getLogin() != null)
            ps.setString(cont++, filter.getCollaborator().getDeveloper().getLogin());
        if(filter.getDate()!= null)
            ps.setDate(cont++, filter.getDate());
        
        return ps;
    }
    
    @Override
    public List<Commit> findByFilter(Commit filter) {
        List<Commit> list = new ArrayList<>();
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
        }finally{
            connection.close();
        }
        return list;
    }

    @Override
    public List<Commit> findAll() {
        List<Commit> list = new ArrayList<>();
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
        }finally{
            connection.close();
        }
        return list;
    }

    @Override
    public Commit populateObject(ResultSet rs) throws SQLException {
        Commit obj = new Commit();
        obj.setSha(rs.getString("SHA"));
        obj.setDate(rs.getDate("DATE"));
        
        return obj;
    }
    
}
