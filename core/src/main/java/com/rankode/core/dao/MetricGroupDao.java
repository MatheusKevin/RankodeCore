/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rankode.core.dao;

import com.rankode.core.dao.utils.Connect;
import com.rankode.core.model.MetricGroup;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Matheus Pelegrini
 */
public class MetricGroupDao extends PatternDao<MetricGroup>{
    
    private final Connect connection;
    
    private final StringBuilder insertSQL = new StringBuilder()
            .append("INSERT INTO METRIC_GROUPS ")
            .append("(NAME, DESCRIPTION) ")
            .append("VALUES ")
            .append("(?,?)");
    
    private final StringBuilder updateSQL = new StringBuilder()
            .append("UPDATE METRIC_GROUPS ")
            .append("NAME=?, DESCRIPTION=? ")
            .append("WHERE ID=?");
    
    private final StringBuilder deleteSQL = new StringBuilder()
            .append("DELETE FROM METRIC_GROUPS ")
            .append("WHERE ID=?");
    
    private final StringBuilder selectIdSQL =  new StringBuilder()
            .append("SELECT * ")
            .append("FROM METRIC_GROUPS ")
            .append("WHERE ID = ? ");
	
    private final StringBuilder selectAllSQL =  new StringBuilder()
            .append("SELECT * ")
            .append("FROM METRIC_GROUPS ");
    
    public MetricGroupDao(){
        connection = new Connect();
    }
    
    @Override
    public void insert(MetricGroup object) {
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
    public void update(MetricGroup object) {
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
    public void delete(MetricGroup object) {
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
    
    public MetricGroup findById(int id) {
        int cont = 1;
        MetricGroup obj = null;
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
    
    private String prepareStringSQLForFilter(MetricGroup filter){
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
    
    private PreparedStatement prepareStatementForFilter(MetricGroup filter, PreparedStatement ps) throws SQLException{
        int cont = 1;
        if(filter.getName() != null)
            ps.setString(cont++, filter.getName());
        if(filter.getDescription()!= null)
            ps.setString(cont++, filter.getDescription());
        
        return ps;
    }
    
    @Override
    public List<MetricGroup> findByFilter(MetricGroup filter) {
        List<MetricGroup> list = new ArrayList<>();
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
    public List<MetricGroup> findAll() {
        List<MetricGroup> list = new ArrayList<>();
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
    public MetricGroup populateObject(ResultSet rs) throws SQLException {
        MetricGroup obj = new MetricGroup();
        obj.setId(rs.getInt("ID"));
        obj.setName(rs.getString("NAME"));
        obj.setDescription(rs.getString("DESCRIPTION"));
        
        return obj;
    }
    
}
