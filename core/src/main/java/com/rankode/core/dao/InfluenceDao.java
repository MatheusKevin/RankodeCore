/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rankode.core.dao;

import com.rankode.core.dao.utils.Connect;
import com.rankode.core.model.Influence;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Matheus Pelegrini
 */
public class InfluenceDao extends PatternDao<Influence>{
    
    private final Connect connection;
    
    private final StringBuilder insertSQL = new StringBuilder()
            .append("INSERT INTO INFLUENCES ")
            .append("(NAME, DESCRIPTION) ")
            .append("VALUES ")
            .append("(?,?)");
    
    private final StringBuilder updateSQL = new StringBuilder()
            .append("UPDATE INFLUENCES ")
            .append("NAME=?, DESCRIPTION=? ")
            .append("WHERE ID=?");
    
    private final StringBuilder deleteSQL = new StringBuilder()
            .append("DELETE FROM INFLUENCES ")
            .append("WHERE ID=?");
    
    private final StringBuilder selectIdSQL =  new StringBuilder()
            .append("SELECT * ")
            .append("FROM INFLUENCES ")
            .append("WHERE ID = ? ");
	
    private final StringBuilder selectAllSQL =  new StringBuilder()
            .append("SELECT * ")
            .append("FROM INFLUENCES ");
    
    public InfluenceDao(){
        connection = new Connect();
    }
    
    @Override
    public void insert(Influence object) {
        int cont = 1;
        PreparedStatement ps;
        try {
            ps = connection.getConnection().prepareStatement(insertSQL.toString());
            ps.setString(cont++, object.getName());
            ps.setString(cont++, object.getDescription());
            
            connection.executeUpdate(ps);
        }catch (SQLException e) {
                throw new RuntimeException("Problemas no sistema, por favor tente mais tarde "+ e.toString());
        }finally{
            connection.close();
        }
    }

    @Override
    public void update(Influence object) {
        int cont = 1;
        PreparedStatement ps;
        try {
            ps = connection.getConnection().prepareStatement(updateSQL.toString());
            ps.setString(cont++, object.getName());
            ps.setString(cont++, object.getDescription());
            ps.setInt(cont++, object.getId());
            
            connection.executeUpdate(ps);
        }catch (SQLException e) {
                throw new RuntimeException("Problemas no sistema, por favor tente mais tarde "+ e.toString());
        }finally{
            connection.close();
        }
    }

    @Override
    public void delete(Influence object) {
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
    
    public Influence findById(int id) {
        int cont = 1;
        Influence obj = null;
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
    
    private String prepareStringSQLForFilter(Influence filter){
        StringBuilder sb = new StringBuilder(selectAllSQL.toString());
        sb.append(" WHERE ");

        boolean and = false;

        if(filter.getName()!= null){
            if(!and){
                    and = true;
            }else{
                    sb.append(" AND ");
            }
            sb.append(" NAME = ? ");
        }
        if(filter.getDescription()!= null){
            if(!and){
                    and = true;
            }else{
                    sb.append(" AND ");
            }
            sb.append(" DESCRIPTION LIKE '%?%' ");
        }
        
        return sb.toString();
    }
    
    private PreparedStatement prepareStatementForFilter(Influence filter, PreparedStatement ps) throws SQLException{
        int cont = 1;
        if(filter.getName() != null)
            ps.setString(cont++, filter.getName());
        if(filter.getDescription()!= null)
            ps.setString(cont++, filter.getDescription());
        
        return ps;
    }
    
    @Override
    public List<Influence> findByFilter(Influence filter) {
        List<Influence> list = new ArrayList<>();
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
    public List<Influence> findAll() {
        List<Influence> list = new ArrayList<>();
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
    public Influence populateObject(ResultSet rs) throws SQLException {
        Influence obj = new Influence();
        obj.setId(rs.getInt("ID"));
        obj.setName(rs.getString("NAME"));
        obj.setDescription(rs.getString("DESCRIPTION"));
        
        return obj;
    }
    
}
