/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rankode.core.dao;

import com.rankode.core.dao.utils.Connect;
import com.rankode.core.model.MetricResult;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Matheus Pelegrini
 */
public class MetricResultDao extends PatternDao<MetricResult>{
    
    private final Connect connection;
    
    private final StringBuilder insertSQL = new StringBuilder()
            .append("INSERT INTO METRICS_RESULTS ")
            .append("(COMMITS_SHA, METRICS_INITIALS, VALUE, DELTA_VALUE, SOURCE) ")
            .append("VALUES ")
            .append("(?,?,?,?,?)");
    
    private final StringBuilder updateSQL = new StringBuilder()
            .append("UPDATE METRICS_RESULTS SET ")
            .append("VALUE=?, DELTA_VALUE=?, SOURCE=? ")
            .append("WHERE COMMITS_SHA=? AND METRICS_INITIALS=?");
    
    private final StringBuilder deleteSQL = new StringBuilder()
            .append("DELETE FROM METRICS_RESULTS ")
            .append("WHERE COMMITS_SHA=? AND METRICS_INITIALS=?");
    
    private final StringBuilder selectIdSQL =  new StringBuilder()
            .append("SELECT * ")
            .append("FROM METRICS_RESULTS ")
            .append("WHERE COMMITS_SHA=? AND METRICS_INITIALS=? ");
	
    private final StringBuilder selectAllSQL =  new StringBuilder()
            .append("SELECT * ")
            .append("FROM METRICS_RESULTS ");
    
    private final StringBuilder selectAllSourcesSQL =  new StringBuilder()
            .append("SELECT DISTINCT R.SOURCE, M.TARGET_ID, T.NAME ")
            .append("FROM RANKODE.METRICS_RESULTS R, RANKODE.METRICS M, RANKODE.TARGETS T ")
            .append("WHERE R.METRICS_INITIALS = M.INITIALS ")
            .append("AND M.TARGET_ID = T.ID ");
    
    public MetricResultDao(){
        connection = new Connect();
    }

    @Override
    public void insert(MetricResult object) {
        int cont = 1;
        PreparedStatement ps;
        try {
            ps = connection.getConnection().prepareStatement(insertSQL.toString());
            ps.setString(cont++, object.getCommit().getSha());
            ps.setString(cont++, object.getMetric().getInitials());
            ps.setInt(cont++, object.getValue());
            ps.setInt(cont++, object.getDeltaValue());
            ps.setString(cont++, object.getSource());
            
            connection.executeUpdate(ps);
        }catch (SQLException e) {
                throw new RuntimeException("Problemas no sistema, por favor tente mais tarde "+ e.toString());
        }finally{
            connection.close();
        }
    }

    @Override
    public void update(MetricResult object) {
        int cont = 1;
        PreparedStatement ps;
        try {
            ps = connection.getConnection().prepareStatement(updateSQL.toString());
            ps.setInt(cont++, object.getValue());
            ps.setInt(cont++, object.getDeltaValue());
            ps.setString(cont++, object.getSource());
            
            ps.setString(cont++, object.getCommit().getSha());
            ps.setString(cont++, object.getMetric().getInitials());
            
            connection.executeUpdate(ps);
        }catch (SQLException e) {
                throw new RuntimeException("Problemas no sistema, por favor tente mais tarde "+ e.toString());
        }finally{
            connection.close();
        }
    }

    @Override
    public void delete(MetricResult object) {
        int cont = 1;
        PreparedStatement ps;
        try {
            ps = connection.getConnection().prepareStatement(deleteSQL.toString());
            ps.setString(cont++, object.getCommit().getSha());
            ps.setString(cont++, object.getMetric().getInitials());
            
            connection.executeUpdate(ps);
        }catch (SQLException e) {
                throw new RuntimeException("Problemas no sistema, por favor tente mais tarde "+ e.toString());
        }finally{
            connection.close();
        }
    }
    
    public MetricResult findById(String sha, String metricInitials) {
        int cont = 1;
        MetricResult obj = null;
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = connection.getConnection().prepareStatement(selectIdSQL.toString());
            ps.setString(cont++, sha);
            ps.setString(cont++, metricInitials);
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
    
    private String prepareStringSQLForFilter(MetricResult filter){
        StringBuilder sb = new StringBuilder(selectAllSQL.toString());
        sb.append(" WHERE ");

        boolean and = false;

        if(filter.getCommit().getSha()!= null){
            if(!and){
                    and = true;
            }else{
                    sb.append(" AND ");
            }
            sb.append(" COMMITS_SHA = ? ");
        }
        if(filter.getMetric().getInitials()!= null){
            if(!and){
                    and = true;
            }else{
                    sb.append(" AND ");
            }
            sb.append(" METRICS_INITIALS = ? ");
        }
        if(filter.getValue()!= null){
            if(!and){
                    and = true;
            }else{
                    sb.append(" AND ");
            }
            sb.append(" VALUE = ? ");
        }
        if(filter.getDeltaValue()!= null){
            if(!and){
                    and = true;
            }else{
                    sb.append(" AND ");
            }
            sb.append(" DELTA_VALUE = ? ");
        }
        if(filter.getSource()!= null){
            if(!and){
                    and = true;
            }else{
                    sb.append(" AND ");
            }
            sb.append(" SOURCE = ? ");
        }
        
        return sb.toString();
    }
    
    private PreparedStatement prepareStatementForFilter(MetricResult filter, PreparedStatement ps) throws SQLException{
        int cont = 1;
        if(filter.getCommit().getSha() != null)
            ps.setString(cont++, filter.getCommit().getSha());
        if(filter.getMetric().getInitials()!= null)
            ps.setString(cont++, filter.getMetric().getInitials());
        if(filter.getValue()!= null)
            ps.setInt(cont++, filter.getValue());
        if(filter.getDeltaValue()!= null)
            ps.setInt(cont++, filter.getDeltaValue());
        if(filter.getSource()!= null)
            ps.setString(cont++, filter.getSource());
        
        return ps;
    }
    
    @Override
    public List<MetricResult> findByFilter(MetricResult filter) {
        List<MetricResult> list = new ArrayList<>();
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
    public List<MetricResult> findAll() {
        List<MetricResult> list = new ArrayList<>();
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
    /*
     * @return list
     */

    public List<MetricResult> findAllSources() {
        List<MetricResult> list = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = connection.getConnection().prepareStatement(selectAllSourcesSQL.toString());
            rs = connection.executeQuery(ps);
            while(rs.next()){
                    list.add(populateObjectSources(rs));
            }

        } catch (SQLException e) {
                throw new RuntimeException("Problemas no sistema, por favor tente mais tarde" + e.toString());
        }finally{
            connection.close();
        }
        return list;
    }
    
    @Override
    public MetricResult populateObject(ResultSet rs) throws SQLException {
        MetricResult obj = new MetricResult();
        obj.getCommit().setSha(rs.getString("COMMITS_SHA"));
        obj.getMetric().setInitials(rs.getString("METRICS_INITIALS"));
        obj.setValue(rs.getInt("VALUE"));
        obj.setDeltaValue(rs.getInt("DELTA_VALUE"));
        obj.setSource(rs.getString("SOURCE"));
        
        return obj;
    }
    
    public MetricResult populateObjectSources(ResultSet rs) throws SQLException {
        MetricResult obj = new MetricResult();
        obj.setSource(rs.getString("SOURCE"));
        obj.getMetric().getTarget().setId(rs.getInt("TARGET_ID"));
        obj.getMetric().getTarget().setName(rs.getString("NAME"));
        return obj;
    }
    
}
