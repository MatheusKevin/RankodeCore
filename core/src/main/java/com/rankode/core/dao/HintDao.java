/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rankode.core.dao;

import com.rankode.core.dao.utils.Connect;
import com.rankode.core.model.Hint;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Matheus
 */
public class HintDao extends PatternDao<Hint>{
    
    private final Connect connection;

    private final StringBuilder insertSQL = new StringBuilder()
            .append("INSERT INTO HINTS ")
            .append("(TEXT, INDICATOR_ID) ")
            .append("VALUES ")
            .append("(?,?)");
    
    private final StringBuilder updateSQL = new StringBuilder()
            .append("UPDATE HINTS ")
            .append("TEXT=?, INDICATOR_ID=? ")
            .append("WHERE ID=?");
    
    private final StringBuilder deleteSQL = new StringBuilder()
            .append("DELETE FROM HINTS ")
            .append("WHERE ID=?");
    
    private final StringBuilder selectIdSQL =  new StringBuilder()
            .append("SELECT * ")
            .append("FROM HINTS ")
            .append("WHERE ID = ? ");
	
    private final StringBuilder selectAllSQL =  new StringBuilder()
            .append("SELECT * ")
            .append("FROM HINTS ");
    
    public HintDao(){
        connection = new Connect();
    }
    
    @Override
    public void insert(Hint object) {
        int cont = 1;
        PreparedStatement ps;
        try {
            ps = connection.getConnection().prepareStatement(insertSQL.toString());
            ps.setString(cont++, object.getText());
            ps.setInt(cont++, object.getIndicator().getId());
            
            connection.executeUpdate(ps);
            connection.close();
        } catch (SQLException e) {
                throw new RuntimeException("Problemas no sistema, por favor tente mais tarde "+ e.toString());
        }
    }

    @Override
    public void update(Hint object) {
        int cont = 1;
        PreparedStatement ps;
        try {
            ps = connection.getConnection().prepareStatement(updateSQL.toString());
            ps.setString(cont++, object.getText());
            ps.setInt(cont++, object.getIndicator().getId());
            
            ps.setInt(cont++, object.getId());
            
            connection.executeUpdate(ps);
            connection.close();
        } catch (SQLException e) {
                throw new RuntimeException("Problemas no sistema, por favor tente mais tarde "+ e.toString());
        }
    }

    @Override
    public void delete(Hint object) {
        int cont = 1;
        PreparedStatement ps;
        try {
            ps = connection.getConnection().prepareStatement(deleteSQL.toString());
            ps.setInt(cont++, object.getId());

            connection.executeUpdate(ps);
            connection.close();
        } catch (SQLException e) {
                throw new RuntimeException("Problemas no sistema, por favor tente mais tarde "+ e.toString());
        }
    }

    public Hint findById(int id) {
        int cont = 1;
        Hint obj = null;
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = connection.getConnection().prepareStatement(selectIdSQL.toString());
            ps.setInt(cont++, id);
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
    
    private String prepareStringSQLForFilter(Hint filter){
        StringBuilder sb = new StringBuilder(selectAllSQL.toString());
        sb.append(" WHERE ");

        boolean and = false;

        if(filter.getIndicator().getId()!= null){
            if(!and){
                    and = true;
            }else{
                    sb.append(" AND ");
            }
            sb.append(" INDICATOR_ID = ? ");
        }
        if(filter.getText()!= null){
            if(!and){
                    and = true;
            }else{
                    sb.append(" AND ");
            }
            sb.append(" TEXT LIKE '%?%' ");
        }
        
        return sb.toString();
    }
    
    private PreparedStatement prepareStatementForFilter(Hint filter, PreparedStatement ps) throws SQLException{
        int cont = 1;
        if(filter.getIndicator().getId() != null)
            ps.setInt(cont++, filter.getIndicator().getId());
        if(filter.getText()!= null)
            ps.setString(cont++, filter.getText());
        
        return ps;
    }
    
    @Override
    public List<Hint> findByFilter(Hint filter) {
       List<Hint> list = new ArrayList<>();
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
    public List<Hint> findAll() {
        List<Hint> list = new ArrayList<>();
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
    public Hint populateObject(ResultSet rs) throws SQLException {
        Hint obj = new Hint();
        obj.setId(rs.getInt("ID"));
        obj.setText(rs.getString("TEXT"));
        
        return obj;
    }
    
}
