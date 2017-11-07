/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rankode.core.bc.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Matheus Pelegrini
 */
public class Validations {
    
    private static final int HTTP_SUCCESS_CODE = 200;
    private static final String GITHUB_URL = "https://api.github.com/";
    
    static {
    }
    
    public synchronized static boolean validateEmail(String email){
        Pattern p = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$"); 
        Matcher m = p.matcher(email); 
        return m.find();
    }
    
    public synchronized static boolean validateGitProject(String uri) throws MalformedURLException, IOException{
        String parts[] = uri.split("/");
        URL url = new URL(GITHUB_URL+"repos/"+parts[3]+"/"+parts[4]);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        return connection.getResponseCode() == HTTP_SUCCESS_CODE;
    }
}
