/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rankode.core.dao;

import com.rankode.core.dao.utils.Connect;
import com.rankode.core.model.Metric;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Matheus
 */
public class MetricDao extends PatternDao<Metric>{
    
    private final Connect connection;
    
    private final StringBuilder insertSQL = new StringBuilder()
            .append("INSERT INTO METRICS ")
            .append("(INITIALS, GROUP_ID, TARGET_ID, NAME, DESCRIPTION, WEIGHT_XP) ")
            .append("VALUES ")
            .append("(?,?,?,?,?,?)");
    
    private final StringBuilder updateSQL = new StringBuilder()
            .append("UPDATE METRICS SET ")
            .append("GROUP_ID=?, TARGET_ID=?, NAME=?, DESCRIPTION=?, WEIGHT_XP=? ")
            .append("WHERE INITIALS=?");
    
    private final StringBuilder deleteSQL = new StringBuilder()
            .append("DELETE FROM METRICS ")
            .append("WHERE INITIALS=?");
    
    private final StringBuilder selectIdSQL =  new StringBuilder()
            .append("SELECT * ")
            .append("FROM METRICS ")
            .append("WHERE INITIALS = ? ");
	
    private final StringBuilder selectAllSQL =  new StringBuilder()
            .append("SELECT * ")
            .append("FROM METRICS ");
    
    public MetricDao(){
        connection = new Connect();
    }

    @Override
    public void insert(Metric object) {
        int cont = 1;
        PreparedStatement ps;
        try {
            ps = connection.getConnection().prepareStatement(insertSQL.toString());
            ps.setString(cont++, object.getInitials());
            ps.setInt(cont++, object.getGroup().getId());
            ps.setInt(cont++, object.getTarget().getId());
            ps.setString(cont++, object.getName());
            ps.setString(cont++, object.getDescription());
            ps.setInt(cont++, object.getWeightXP());
            
            connection.executeUpdate(ps);
        }catch (SQLException e) {
                throw new RuntimeException("Problemas no sistema, por favor tente mais tarde "+ e.toString());
        }finally{
            connection.close();
        }
    }

    @Override
    public void update(Metric object) {
        int cont = 1;
        PreparedStatement ps;
        try {
            ps = connection.getConnection().prepareStatement(updateSQL.toString());
            ps.setInt(cont++, object.getGroup().getId());
            ps.setInt(cont++, object.getTarget().getId());
            ps.setString(cont++, object.getName());
            ps.setString(cont++, object.getDescription());
            ps.setInt(cont++, object.getWeightXP());
            
            ps.setString(cont++, object.getInitials());
            
            connection.executeUpdate(ps);
        }catch (SQLException e) {
                throw new RuntimeException("Problemas no sistema, por favor tente mais tarde "+ e.toString());
        }finally{
            connection.close();
        }
    }

    @Override
    public void delete(Metric object) {
        int cont = 1;
        PreparedStatement ps;
        try {
            ps = connection.getConnection().prepareStatement(deleteSQL.toString());
            
            ps.setString(cont++, object.getInitials());
            
            connection.executeUpdate(ps);
        }catch (SQLException e) {
                throw new RuntimeException("Problemas no sistema, por favor tente mais tarde "+ e.toString());
        }finally{
            connection.close();
        }
    }
    
    public Metric findById(String initials) {
        int cont = 1;
        Metric obj = null;
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = connection.getConnection().prepareStatement(selectIdSQL.toString());
            ps.setString(cont++, initials);
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
    
    private String prepareStringSQLForFilter(Metric filter){
        StringBuilder sb = new StringBuilder(selectAllSQL.toString());
        sb.append(" WHERE ");

        boolean and = false;

        if(filter.getGroup().getId()!= null){
            if(!and){
                    and = true;
            }else{
                    sb.append(" AND ");
            }
            sb.append(" GROUP_ID = ? ");
        }
        if(filter.getTarget().getId()!= null){
            if(!and){
                    and = true;
            }else{
                    sb.append(" AND ");
            }
            sb.append(" TARGET_ID = ? ");
        }
        if(filter.getName()!= null){
            if(!and){
                    and = true;
            }else{
                    sb.append(" AND ");
            }
            sb.append(" NAME LIKE '%?%' ");
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
    
    private PreparedStatement prepareStatementForFilter(Metric filter, PreparedStatement ps) throws SQLException{
        int cont = 1;
        if(filter.getGroup().getId() != null)
            ps.setInt(cont++, filter.getGroup().getId());
        if(filter.getTarget().getId() != null)
            ps.setInt(cont++, filter.getTarget().getId());
        if(filter.getName() != null)
            ps.setString(cont++, filter.getName());
        if(filter.getDescription() != null)
            ps.setString(cont++, filter.getDescription());
        
        return ps;
    }
    
    @Override
    public List<Metric> findByFilter(Metric filter) {
        List<Metric> list = new ArrayList<>();
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
    public List<Metric> findAll() {
        List<Metric> list = new ArrayList<>();
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
    public Metric populateObject(ResultSet rs) throws SQLException {
        Metric obj = new Metric();
        obj.setInitials(rs.getString("INITIALS"));
        obj.setName(rs.getString("NAME"));
        obj.setDescription(rs.getString("DESCRIPTION"));
        obj.setWeightXP(rs.getInt("WEIGHT_XP"));
        
        return obj;
    }
    
}
