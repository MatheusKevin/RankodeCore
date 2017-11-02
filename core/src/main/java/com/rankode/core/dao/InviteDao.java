/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rankode.core.dao;

import com.rankode.core.dao.utils.Connect;
import com.rankode.core.model.Invite;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Matheus Pelegrini
 */
public class InviteDao extends PatternDao<Invite>{
    
    private final Connect connection;
    
    private final StringBuilder insertSQL = new StringBuilder()
            .append("INSERT INTO INVITES ")
            .append("(NOTIFICATION_ID, PROJECTS_ID, RESPONSE) ")
            .append("VALUES ")
            .append("(?,?,?)");
    
    private final StringBuilder updateSQL = new StringBuilder()
            .append("UPDATE INVITES ")
            .append("PROJECTS_ID=?, RESPONSE=? ")
            .append("WHERE NOTIFICATION_ID=?");
    
    private final StringBuilder deleteSQL = new StringBuilder()
            .append("DELETE FROM INVITES ")
            .append("WHERE NOTIFICATION_ID=?");
    
    private final StringBuilder selectIdSQL =  new StringBuilder()
            .append("SELECT * ")
            .append("FROM INVITES ")
            .append("WHERE NOTIFICATION_ID = ? ");
	
    private final StringBuilder selectAllSQL =  new StringBuilder()
            .append("SELECT * ")
            .append("FROM INVITES ");
    
    public InviteDao(){
        connection = new Connect();
    }

    @Override
    public void insert(Invite object) {
        int cont = 1;
        PreparedStatement ps;
        try {
            ps = connection.getConnection().prepareStatement(insertSQL.toString());
            ps.setInt(cont++, object.getId());
            ps.setInt(cont++, object.getProject().getId());
            ps.setBoolean(cont++, object.isResponse());
            
            connection.executeUpdate(ps);
        }catch (SQLException e) {
                throw new RuntimeException("Problemas no sistema, por favor tente mais tarde "+ e.toString());
        }finally{
            connection.close();
        }
    }

    @Override
    public void update(Invite object) {
        int cont = 1;
        PreparedStatement ps;
        try {
            ps = connection.getConnection().prepareStatement(updateSQL.toString());
            ps.setInt(cont++, object.getProject().getId());
            ps.setBoolean(cont++, object.isResponse());
            
            ps.setInt(cont++, object.getId());
            
            connection.executeUpdate(ps);
        }catch (SQLException e) {
                throw new RuntimeException("Problemas no sistema, por favor tente mais tarde "+ e.toString());
        }finally{
            connection.close();
        }
    }

    @Override
    public void delete(Invite object) {
        int cont = 1;
        PreparedStatement ps;
        try {
            ps = connection.getConnection().prepareStatement(deleteSQL.toString());
            ps.setInt(cont++, object.getId());
            
            connection.executeUpdate(ps);
        }catch (SQLException e) {
                throw new RuntimeException("Problemas no sistema, por favor tente mais tarde "+ e.toString());
        }finally{
            connection.close();
        }
    }
    
    public Invite findById(int id) {
        int cont = 1;
        Invite obj = null;
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
        }finally{
            connection.close();
        }
        return obj;
    }
    
    private String prepareStringSQLForFilter(Invite filter){
        StringBuilder sb = new StringBuilder(selectAllSQL.toString());
        sb.append(" WHERE ");

        boolean and = false;

        if(filter.getProject().getId()!= null){
            if(!and){
                    and = true;
            }else{
                    sb.append(" AND ");
            }
            sb.append(" PROJECTS_ID = ? ");
        }
        
        return sb.toString();
    }
    
    private PreparedStatement prepareStatementForFilter(Invite filter, PreparedStatement ps) throws SQLException{
        int cont = 1;
        if(filter.getProject().getId() != null)
            ps.setInt(cont++, filter.getProject().getId());
        
        return ps;
    }

    @Override
    public List<Invite> findByFilter(Invite filter) {
        List<Invite> list = new ArrayList<>();
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
    public List<Invite> findAll() {
        List<Invite> list = new ArrayList<>();
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
    public Invite populateObject(ResultSet rs) throws SQLException {
        Invite obj = new Invite();
        obj.setId(rs.getInt("NOTIFICATION_ID"));
        obj.setResponse(rs.getBoolean("RESPONSE"));
        
        return obj;
    }
    
}
