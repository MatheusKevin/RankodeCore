/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rankode.core.dao;

import com.rankode.core.dao.utils.Connect;
import com.rankode.core.model.Target;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Matheus Pelegrini
 */
public class TargetDao extends PatternDao<Target>{
    
    private final Connect connection;
    
    private final StringBuilder insertSQL = new StringBuilder()
            .append("INSERT INTO TARGETS ")
            .append("(NAME) ")
            .append("VALUES ")
            .append("(?)");
    
    private final StringBuilder updateSQL = new StringBuilder()
            .append("UPDATE TARGETS ")
            .append("NAME=? ")
            .append("WHERE ID=?");
    
    private final StringBuilder deleteSQL = new StringBuilder()
            .append("DELETE FROM TARGETS ")
            .append("WHERE ID=?");
    
    private final StringBuilder selectIdSQL =  new StringBuilder()
            .append("SELECT * ")
            .append("FROM TARGETS ")
            .append("WHERE ID = ? ");
	
    private final StringBuilder selectAllSQL =  new StringBuilder()
            .append("SELECT * ")
            .append("FROM TARGETS ");
    
    public TargetDao(){
        connection = new Connect();
    }

    @Override
    public void insert(Target object) {
        int cont = 1;
        PreparedStatement ps;
        try {
            ps = connection.getConnection().prepareStatement(insertSQL.toString());
            ps.setString(cont++, object.getName());
            
            connection.executeUpdate(ps);
        }catch (SQLException e) {
                throw new RuntimeException("Problemas no sistema, por favor tente mais tarde "+ e.toString());
        }finally{
            connection.close();
        }
    }

    @Override
    public void update(Target object) {
        int cont = 1;
        PreparedStatement ps;
        try {
            ps = connection.getConnection().prepareStatement(updateSQL.toString());
            ps.setString(cont++, object.getName());
            ps.setInt(cont++, object.getId());
            
            connection.executeUpdate(ps);
        }catch (SQLException e) {
                throw new RuntimeException("Problemas no sistema, por favor tente mais tarde "+ e.toString());
        }finally{
            connection.close();
        }
    }

    @Override
    public void delete(Target object) {
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
    
    public Target findById(int id) {
        int cont = 1;
        Target obj = null;
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
    
    private String prepareStringSQLForFilter(Target filter){
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
        
        return sb.toString();
    }
    
    private PreparedStatement prepareStatementForFilter(Target filter, PreparedStatement ps) throws SQLException{
        int cont = 1;
        if(filter.getName() != null)
            ps.setString(cont++, filter.getName());
        
        return ps;
    }
    
    @Override
    public List<Target> findByFilter(Target filter) {
        List<Target> list = new ArrayList<>();
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
    public List<Target> findAll() {
        List<Target> list = new ArrayList<>();
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
    public Target populateObject(ResultSet rs) throws SQLException {
        Target obj = new Target();
        obj.setId(rs.getInt("ID"));
        obj.setName(rs.getString("NAME"));
        
        return obj;
    }
    
}
