/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rankode.core.dao;

import com.rankode.core.dao.utils.Connect;
import com.rankode.core.model.Indicator;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Matheus
 */
public class IndicatorDao extends PatternDao<Indicator>{
    private final Connect connection;

    private final StringBuilder insertSQL = new StringBuilder()
            .append("INSERT INTO INDICATORS ")
            .append("(PROJECTS_ID, METRICS_INITIALS, NAME, MAX, MIN) ")
            .append("VALUES ")
            .append("(?,?,?,?,?)");
    
    private final StringBuilder updateSQL = new StringBuilder()
            .append("UPDATE INDICATORS ")
            .append("PROJECTS_ID=?, METRICS_INITIALS=?, NAME=?, MAX=?, MIN=?")
            .append("WHERE ID=?");
    
    private final StringBuilder deleteSQL = new StringBuilder()
            .append("DELETE FROM INDICATORS ")
            .append("WHERE ID=? ");
    
    private final StringBuilder selectIdSQL =  new StringBuilder()
            .append("SELECT * ")
            .append("FROM INDICATORS ")
            .append("WHERE ID=? ");
	
    private final StringBuilder selectAllSQL =  new StringBuilder()
            .append("SELECT * ")
            .append("FROM INDICATORS ");
    
    public IndicatorDao(){
        connection = new Connect();
    }
    
    @Override
    public void insert(Indicator object) {
        int cont = 1;
        PreparedStatement ps;
        try {
            ps = connection.getConnection().prepareStatement(insertSQL.toString());
            ps.setInt(cont++, object.getProjeto().getId());
            ps.setString(cont++, object.getMetric().getInitials());
            ps.setString(cont++, object.getName());
            ps.setInt(cont++, object.getMax());
            ps.setInt(cont++, object.getMin());
            
            connection.executeUpdate(ps);
        } catch (SQLException e) {
                throw new RuntimeException("Problemas no sistema, por favor tente mais tarde "+ e.toString());
        }
    }

    @Override
    public void update(Indicator object) {
        int cont = 1;
        PreparedStatement ps;
        try {
            ps = connection.getConnection().prepareStatement(updateSQL.toString());
            ps.setInt(cont++, object.getProjeto().getId());
            ps.setString(cont++, object.getMetric().getInitials());
            ps.setString(cont++, object.getName());
            ps.setInt(cont++, object.getMax());
            ps.setInt(cont++, object.getMin());
            
            ps.setInt(cont++, object.getId());
            
            connection.executeUpdate(ps);
        } catch (SQLException e) {
                throw new RuntimeException("Problemas no sistema, por favor tente mais tarde "+ e.toString());
        }
    }

    @Override
    public void delete(Indicator object) {
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

    public Indicator findById(int id) {
        int cont = 1;
        Indicator obj = null;
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
    
     private String prepareStringSQLForFilter(Indicator filter){
        StringBuilder sb = new StringBuilder(selectAllSQL.toString());
        sb.append(" WHERE ");

        boolean and = false;

        if(filter.getProjeto().getId()!= null){
            if(!and){
                    and = true;
            }else{
                    sb.append(" AND ");
            }
            sb.append(" PROJECTS_ID = ? ");
        }
        if(filter.getMetric().getInitials()!= null){
            if(!and){
                    and = true;
            }else{
                    sb.append(" AND ");
            }
            sb.append(" METRICS_INITIALS = ? ");
        }
        if(filter.getName()!= null){
            if(!and){
                    and = true;
            }else{
                    sb.append(" AND ");
            }
            sb.append(" NAME = ? ");
        }
        if(filter.getMax()!= null){
            if(!and){
                    and = true;
            }else{
                    sb.append(" AND ");
            }
            sb.append(" MAX = ? ");
        }
        if(filter.getMin()!= null){
            if(!and){
                    and = true;
            }else{
                    sb.append(" AND ");
            }
            sb.append(" MIN = ? ");
        }
        return sb.toString();
    }
     
    private PreparedStatement prepareStatementForFilter(Indicator filter, PreparedStatement ps) throws SQLException{
        int cont = 1;
        if(filter.getProjeto().getId() != null)
            ps.setInt(cont++, filter.getProjeto().getId());
        if(filter.getMetric().getInitials()!= null)
            ps.setString(cont++, filter.getMetric().getInitials());
        if(filter.getName()!= null)
            ps.setString(cont++, filter.getName());
        if(filter.getMax()!= null)
            ps.setInt(cont++, filter.getMax());
        if(filter.getMin()!= null)
            ps.setInt(cont++, filter.getMin());
        return ps;
    }

    @Override
    public List<Indicator> findByFilter(Indicator filter) {
        List<Indicator> list = new ArrayList<>();
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
    public List<Indicator> findAll() {
        List<Indicator> list = new ArrayList<>();
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
    public Indicator populateObject(ResultSet rs) throws SQLException {
        Indicator obj = new Indicator();
        obj.setId(rs.getInt("ID"));
        obj.setName(rs.getString("NAME"));
        obj.setMax(rs.getInt("MAX"));
        obj.setMin(rs.getInt("MIN"));
        
        return obj;
    }
    
    
}
