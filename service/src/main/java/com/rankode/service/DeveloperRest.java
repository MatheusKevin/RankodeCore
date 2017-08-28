/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rankode.service;

import java.util.Date;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.google.gson.Gson;
import com.rankode.core.bc.DeveloperBc;
import com.rankode.core.model.Developer;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;

/**
 *
 * @author Alexandre
 */
@Path("/developer")
@Stateless
public class DeveloperRest {
    
    	private Gson gson;
        
	private DeveloperBc developerBc;

	public DeveloperRest() {
		super();
		this.gson = new Gson();
	}
        
        @GET
	@Produces("text/plain") // http://localhost:8080/service/api/developer
	public String hello(){
		return "OK";
	}

	@POST
	@Path("/insert") // http://localhost:8080/service/api/developer/insert
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insert(Developer developer) {
		try{
                        developerBc = new DeveloperBc();
			developerBc.insert(developer);
			return Response.ok(gson.toJson("Desenvolvedor cadastrado!")).build();
		}catch(Exception e){
			e.printStackTrace();
			return Response.status(500).entity(gson.toJson("Erro ao acessar servidor")).build();
		}
		
	}
        
        @GET
	@Path("/findById/{id}") // http://localhost:8080/service/api/developer/insert
	@Produces(MediaType.APPLICATION_JSON)
	public Response findById(@PathParam("id") String id) {
		try{
                        developerBc = new DeveloperBc();
			Developer developer = developerBc.findById(id);
                        if (developer != null)
				return Response.ok(gson.toJson(developer)).build();
                        
			return Response.ok(gson.toJson("Não há esse desenvolvedor cadastrado!")).build();
		}catch(Exception e){
			e.printStackTrace();
			return Response.status(500).entity(gson.toJson("Erro ao acessar servidor")).build();
		}
		
	}
    
}
