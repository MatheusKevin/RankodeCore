/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rankode.core.bc.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Matheus Pelegrini
 */
public class Validations {
    
    static {
    }
    
    public synchronized static boolean validateEmail(String email){
        Pattern p = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$"); 
        Matcher m = p.matcher(email); 
        return m.find();
    }
}
