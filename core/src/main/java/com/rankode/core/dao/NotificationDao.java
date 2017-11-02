/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rankode.core.dao;

import com.rankode.core.dao.utils.Connect;
import com.rankode.core.model.Notification;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Matheus Pelegrini
 */
public class NotificationDao extends PatternDao<Notification>{
    
    private final Connect connection;
    
    private final StringBuilder insertSQL = new StringBuilder()
            .append("INSERT INTO NOTIFICATIONS ")
            .append("(DEVELOPERS_LOGIN, TITLE, CONTENT, DATE, SEEN) ")
            .append("VALUES ")
            .append("(?,?,?,?,?)");
    
    private final StringBuilder updateSQL = new StringBuilder()
            .append("UPDATE NOTIFICATIONS ")
            .append("DEVELOPERS_LOGIN=?, TITLE=?, CONTENT=?, DATE=?, SEEN=? ")
            .append("WHERE ID=?");
    
    private final StringBuilder deleteSQL = new StringBuilder()
            .append("DELETE FROM NOTIFICATIONS ")
            .append("WHERE ID=?");
    
    private final StringBuilder selectIdSQL =  new StringBuilder()
            .append("SELECT * ")
            .append("FROM NOTIFICATIONS ")
            .append("WHERE ID = ? ");
	
    private final StringBuilder selectAllSQL =  new StringBuilder()
            .append("SELECT * ")
            .append("FROM NOTIFICATIONS ");
    
    public NotificationDao(){
        connection = new Connect();
    }
    
    @Override
    public void insert(Notification object) {
        int cont = 1;
        PreparedStatement ps;
        try {
            ps = connection.getConnection().prepareStatement(insertSQL.toString());
            ps.setString(cont++, object.getDeveloper().getLogin());
            ps.setString(cont++, object.getTitle());
            ps.setString(cont++, object.getContent());
            ps.setDate(cont++, object.getDate());
            ps.setBoolean(cont++, object.isSeen());
            
            connection.executeUpdate(ps);
        }catch (SQLException e) {
                throw new RuntimeException("Problemas no sistema, por favor tente mais tarde "+ e.toString());
        }finally{
            connection.close();
        }
    }

    @Override
    public void update(Notification object) {
        int cont = 1;
        PreparedStatement ps;
        try {
            ps = connection.getConnection().prepareStatement(updateSQL.toString());
            ps.setString(cont++, object.getDeveloper().getLogin());
            ps.setString(cont++, object.getTitle());
            ps.setString(cont++, object.getContent());
            ps.setDate(cont++, object.getDate());
            ps.setBoolean(cont++, object.isSeen());
            
            ps.setInt(cont++, object.getId());
            connection.executeUpdate(ps);
        }catch (SQLException e) {
                throw new RuntimeException("Problemas no sistema, por favor tente mais tarde "+ e.toString());
        }finally{
            connection.close();
        }
    }

    @Override
    public void delete(Notification object) {
        int cont = 1;
        PreparedStatement ps;
        try {
            ps = connection.getConnection().prepareStatement(updateSQL.toString());
            
            ps.setInt(cont++, object.getId());
            connection.executeUpdate(ps);
        }catch (SQLException e) {
                throw new RuntimeException("Problemas no sistema, por favor tente mais tarde "+ e.toString());
        }finally{
            connection.close();
        }
    }
    
    public Notification findById(int id) {
        int cont = 1;
        Notification obj = null;
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
    
    private String prepareStringSQLForFilter(Notification filter){
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
        if(filter.getTitle()!= null){
            if(!and){
                    and = true;
            }else{
                    sb.append(" AND ");
            }
            sb.append(" TITLE = ? ");
        }
        if(filter.getContent()!= null){
            if(!and){
                    and = true;
            }else{
                    sb.append(" AND ");
            }
            sb.append(" CONTENT LIKE '%?%' ");
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
    
    private PreparedStatement prepareStatementForFilter(Notification filter, PreparedStatement ps) throws SQLException{
        int cont = 1;
        if(filter.getDeveloper().getLogin() != null)
            ps.setString(cont++, filter.getDeveloper().getLogin());
        if(filter.getTitle()!= null)
            ps.setString(cont++, filter.getTitle());
        if(filter.getContent()!= null)
            ps.setString(cont++, filter.getContent());
        if(filter.getDate()!= null)
            ps.setDate(cont++, filter.getDate());
        
        return ps;
    }

    @Override
    public List<Notification> findByFilter(Notification filter) {
        List<Notification> list = new ArrayList<>();
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
    public List<Notification> findAll() {
        List<Notification> list = new ArrayList<>();
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
    public Notification populateObject(ResultSet rs) throws SQLException {
        Notification obj = new Notification();
        obj.setId(rs.getInt("ID"));
        obj.setTitle(rs.getString("TITLE"));
        obj.setContent(rs.getString("CONTENT"));
        obj.setDate(rs.getDate("DATE"));
        obj.setSeen(rs.getBoolean("SEEN"));
        
        return obj;
    }
    
}
