/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rankode.service;

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
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;

/**
 *
 * @author Alexandre
 */
@Path("/developer")
@Stateless
public class DeveloperRest {
    
    	private final Gson gson;
        
	private DeveloperBc developerBc;

	public DeveloperRest() {
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
        @Produces(MediaType.APPLICATION_JSON)
	public Response insert(Developer developer) {
		try{
                        developerBc = new DeveloperBc();
			developerBc.insert(developer);
			return Response.ok(gson.toJson("Desenvolvedor cadastrado!")).build();
		}catch(Exception e){
			return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
        
        @POST
	@Path("/update") // http://localhost:8080/service/api/developer/update
	@Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
	public Response update(Developer developer) {
		try{
                        developerBc = new DeveloperBc();
			developerBc.update(developer);
			return Response.ok(gson.toJson("Dados atualizados!")).build();
		}catch(Exception e){
			return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
        
        @POST
	@Path("/delete") // http://localhost:8080/service/api/developer/delete
	@Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
	public Response delete(Developer developer) {
		try{
                        developerBc = new DeveloperBc();
			developerBc.delete(developer);
			return Response.ok(gson.toJson("Desenvolvedor deletado")).build();
		}catch(Exception e){
			return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
        
        @GET
	@Path("/findById/{id}") // http://localhost:8080/service/api/developer/findById
	@Produces(MediaType.APPLICATION_JSON)
	public Response findById(@PathParam("id") String id) {
		try{
                        developerBc = new DeveloperBc();
			Developer developer = developerBc.findById(id);
                        if (developer != null)
				return Response.ok(gson.toJson(developer)).build();
                        
			return Response.ok(gson.toJson("Não há esse desenvolvedor cadastrado!")).build();
		}catch(Exception e){
			return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
        
        @GET
	@Path("/findAll") // http://localhost:8080/service/api/developer/findAll
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll() {
		try{
                    developerBc = new DeveloperBc();
                    List<Developer> developers = developerBc.findAll();
                    if (developers.size() > 0)
                            return Response.ok(gson.toJson(developers)).build();

                    return Response.ok(gson.toJson("Não há desenvolvedores cadastrados!")).build();
		}catch(Exception e){
                    return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
        
        @POST
	@Path("/findByFilter") // http://localhost:8080/service/api/developer/findByFilter
	@Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
	public Response findByFilter(Developer developer) {
		try{
                    developerBc = new DeveloperBc();
                    List<Developer> developers = developerBc.findByFilter(developer);
                    if (developers.size() > 0)
                        return Response.ok(gson.toJson(developers)).build();

                    return Response.ok(gson.toJson("Não foi encontrado desenvolvedores")).build();
		}catch(Exception e){
			return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
        
        
        @POST
	@Path("/login") // http://localhost:8080/service/api/developer/findByFilter
	@Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
	public Response login(Developer developer) {
		try{
                    developerBc = new DeveloperBc();
                    Developer developerLogin = developerBc.login(developer);
                    if(developerLogin != null)
                        return Response.ok(gson.toJson(developerBc.login(developer))).build();
                    
                    return Response.ok(gson.toJson("Não foi encontrado desenvolvedores")).build();
		}catch(Exception e){
			return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
}
