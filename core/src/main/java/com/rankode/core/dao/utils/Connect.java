/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rankode.core.dao.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Alexandre
 */
public class Connect {
    
    private Connection conn;
    
    private final String DB = "rankode";
    private final String CLASS = "com.mysql.jdbc.Driver";
    private final String USER = "root";
    private final String PW = "root";
    private final String URL = "jdbc:mysql://localhost:3306/"+DB;

    
    public Connect(){   
    }

    public Connection getConnection(){
        open();
        return conn;
    }
    
    private void open() {
        try {
            Class.forName(CLASS);
            conn = DriverManager.getConnection(URL, USER, PW);
            conn.setAutoCommit(false);
        } catch (ClassNotFoundException  e) {
            throw new RuntimeException("Classe não encontrada.  - " + e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException("Erro SQL",e);
        }
    }
    
    private void close() {
        try{
            conn.close();
        }catch(SQLException e){
            throw new RuntimeException("Erro ao fechar a conexão - " + e.toString());
        }
    }
    
    public synchronized void executeUpdate(PreparedStatement ps){
        try{
            ps.executeUpdate();
            confirm();
        }catch(SQLException e){
            abort();
            throw new RuntimeException("Erro de ao realizar atualizacao - " + e.toString());
        }finally{
            close();
        }
        
    }
    public synchronized ResultSet executeQuery(PreparedStatement ps){
        ResultSet rs = null;
        try{
            rs = ps.executeQuery();
        }catch(SQLException e){
            throw new RuntimeException("Erro de ao realizar busca - " + e.toString());
        }finally{
            close();
        }
        return rs;
    }
    

    private void confirm() {
        try{
            conn.commit();            
        }catch(SQLException e){
            throw new RuntimeException("Erro de commit - " + e.toString());
        }
    }

    private void abort() {
        try{
            conn.rollback();
        }catch(SQLException e){
            throw new RuntimeException("Erro de rollback - " + e.toString());
        }
    }
}
