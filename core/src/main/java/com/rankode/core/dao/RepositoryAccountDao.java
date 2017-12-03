/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rankode.core.dao;

import com.rankode.core.dao.utils.Connect;
import com.rankode.core.model.RepositoryAccount;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Matheus
 */
public class RepositoryAccountDao extends PatternDao<RepositoryAccount>{
    
    private final Connect connection;

    private final StringBuilder insertSQL = new StringBuilder()
            .append("INSERT INTO REPOSITORY_ACCOUNTS ")
            .append("(LOGIN_REPOSITORY, REPOSITORY, DEVELOPERS_LOGIN) ")
            .append("VALUES ")
            .append("(?,?,?)");
    
    private final StringBuilder updateSQL = new StringBuilder()
            .append("UPDATE REPOSITORY_ACCOUNTS SET ")
            .append("REPOSITORY=?, DEVELOPERS_LOGIN=? ")
            .append("WHERE LOGIN_REPOSITORY=?");
    
    private final StringBuilder deleteSQL = new StringBuilder()
            .append("DELETE FROM REPOSITORY_ACCOUNTS ")
            .append("WHERE LOGIN_REPOSITORY=?");
    
    private final StringBuilder selectIdSQL =  new StringBuilder()
            .append("SELECT * ")
            .append("FROM REPOSITORY_ACCOUNTS ")
            .append("WHERE LOGIN_REPOSITORY = ? ");
	
    private final StringBuilder selectAllSQL =  new StringBuilder()
            .append("SELECT * ")
            .append("FROM REPOSITORY_ACCOUNTS ");
    
    public RepositoryAccountDao(){
        connection = new Connect();
    }
    
    @Override
    public void insert(RepositoryAccount object) {
        int cont = 1;
        PreparedStatement ps;
        try {
            ps = connection.getConnection().prepareStatement(insertSQL.toString());
            ps.setString(cont++, object.getLoginRepository());
            ps.setString(cont++, object.getRepository());            
            ps.setString(cont++, object.getDeveloper().getLogin());
            
            connection.executeUpdate(ps);
        } catch (SQLException e) {
                throw new RuntimeException("Problemas no sistema, por favor tente mais tarde "+ e.toString());
        }finally{
            connection.close();
        }
    }

    @Override
    public void update(RepositoryAccount object) {
        int cont = 1;
        PreparedStatement ps;
        try {
            ps = connection.getConnection().prepareStatement(updateSQL.toString());
            ps.setString(cont++, object.getRepository());
            ps.setString(cont++, object.getDeveloper().getLogin());           
            ps.setString(cont++, object.getLoginRepository());
                       
            connection.executeUpdate(ps);
        } catch (SQLException e) {
                throw new RuntimeException("Problemas no sistema, por favor tente mais tarde "+ e.toString());
        }finally{
            connection.close();
        }
    }

    @Override
    public void delete(RepositoryAccount object) {
        int cont = 1;
        PreparedStatement ps;
        try {
            ps = connection.getConnection().prepareStatement(deleteSQL.toString());
            ps.setString(cont++, object.getLoginRepository());

            connection.executeUpdate(ps);
        } catch (SQLException e) {
                throw new RuntimeException("Problemas no sistema, por favor tente mais tarde "+ e.toString());
        }finally{
            connection.close();
        }
    }

    public RepositoryAccount findById(String id) {
        int cont = 1;
        RepositoryAccount obj = null;
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = connection.getConnection().prepareStatement(selectIdSQL.toString());
            ps.setString(cont++, id);
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
    
    private String prepareStringSQLForFilter(RepositoryAccount filter){
        StringBuilder sb = new StringBuilder(selectAllSQL.toString());
        sb.append(" WHERE ");

        boolean and = false;

        if(filter.getRepository()!= null){
            if(!and){
                    and = true;
            }else{
                    sb.append(" AND ");
            }
            sb.append(" REPOSITORY = ? ");
        }
        if(filter.getDeveloper().getLogin()!= null){
            if(!and){
                    and = true;
            }else{
                    sb.append(" AND ");
            }
            sb.append(" DEVELOPERS_LOGIN = ? ");
        }
        
        return sb.toString();
    }
    
    private PreparedStatement prepareStatementForFilter(RepositoryAccount filter, PreparedStatement ps) throws SQLException{
        int cont = 1;
        if(filter.getRepository() != null)
            ps.setString(cont++, filter.getRepository());
        if(filter.getDeveloper().getLogin()!= null)
            ps.setString(cont++, filter.getDeveloper().getLogin());
        
        return ps;
    }
    
    @Override
    public List<RepositoryAccount> findByFilter(RepositoryAccount filter) {
        List<RepositoryAccount> list = new ArrayList<>();
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
    public List<RepositoryAccount> findAll() {
        List<RepositoryAccount> list = new ArrayList<>();
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
    public RepositoryAccount populateObject(ResultSet rs) throws SQLException {
        RepositoryAccount obj = new RepositoryAccount();
        obj.setLoginRepository(rs.getString("LOGIN_REPOSITORY"));
        obj.setRepository(rs.getString("REPOSITORY"));
        
        return obj;
    }
    
}
