package com.rankode.core.test;

import com.rankode.core.bc.DeveloperBc;
import com.rankode.core.model.Developer;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import static org.junit.Assert.assertEquals;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Alexandre
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DeveloperTest {
    
    
    private String LOGIN = "Johndoe";
    private String FIRST_NAME = "John";
    private String LAST_NAME = "Doe";
    private String PASSWORD = "Johndoe123";
    private String EMAIL = "Johndoe@doe.com";


    @Test
    public void A_NewDevelloper() {
        DeveloperBc developerBc = new DeveloperBc(); // MyClass is tested
        Developer developer = new Developer();
        
        developer.setLogin("Johndoe");
        developer.setFirstName("John");
        developer.setLastName("Doe");
        developer.setPassword("Johndoe123");
        developer.setEmail("Johndoe@doe.com");
        
        developerBc.insert(developer);
    }
    
    @Test
    public void B_SelectDevelloper() {
        DeveloperBc developerBc = new DeveloperBc(); // MyClass is tested
        
        Developer developer; 
        developer = developerBc.findById("Johndoe");
        
        assertEquals("Login Developer", LOGIN, developer.getLogin());
        assertEquals("First name Developer", FIRST_NAME, developer.getFirstName());
        assertEquals("Last name Developer", LAST_NAME, developer.getLastName());
        assertEquals("Password Developer", PASSWORD, developer.getPassword());
        assertEquals("Email Developer", EMAIL, developer.getEmail());
        
    }
    
    @Test
    public void C_DeleteDevelloper() {
        DeveloperBc developerBc = new DeveloperBc(); // MyClass is tested
        
        Developer developer = developerBc.findById("Johndoe");
        developerBc.delete(developer);
        
    }
}
