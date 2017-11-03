/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rankode.service;

import com.google.gson.Gson;
import com.rankode.core.bc.TargetBc;
import com.rankode.core.model.Target;
import java.util.List;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Matheus Pelegrini
 */
@Path("/target")
@Stateless
public class TargetRest {
        
        private final Gson gson;
        
	private TargetBc targetBc;

	public TargetRest() {
		this.gson = new Gson();
	}
        
        @POST
	@Path("/insert") // http://localhost:8080/service/api/target/insert
	@Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
	public Response insert(Target target) {
		try{
                        targetBc = new TargetBc();
			targetBc.insert(target);
			return Response.ok(gson.toJson("Target cadastrado!")).build();
		}catch(Exception e){
			return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
        
        @POST
	@Path("/update") // http://localhost:8080/service/api/target/update
	@Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
	public Response update(Target target) {
		try{
                        targetBc = new TargetBc();
			targetBc.update(target);
			return Response.ok(gson.toJson("Dados atualizados!")).build();
		}catch(Exception e){
			return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
        
        @POST
	@Path("/delete") // http://localhost:8080/service/api/target/delete
	@Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
	public Response delete(Target target) {
		try{
                        targetBc = new TargetBc();
			targetBc.delete(target);
			return Response.ok(gson.toJson("Target deletado")).build();
		}catch(Exception e){
			return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
        
        @GET
	@Path("/findById/{id}") // http://localhost:8080/service/api/target/findById
	@Produces(MediaType.APPLICATION_JSON)
	public Response findById(@PathParam("id") int id) {
		try{
                        targetBc = new TargetBc();
			Target target = targetBc.findById(id);
                        if (target != null)
				return Response.ok(gson.toJson(target)).build();
                        
			return Response.ok(gson.toJson("Não há esse target cadastrado!")).build();
		}catch(Exception e){
			return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
        
        @GET
	@Path("/findAll") // http://localhost:8080/service/api/target/findAll
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll() {
		try{
                    targetBc = new TargetBc();
                    List<Target> targets = targetBc.findAll();
                    if (targets.size() > 0)
                            return Response.ok(gson.toJson(targets)).build();

                    return Response.ok(gson.toJson("Não há targets cadastrados!")).build();
		}catch(Exception e){
                    return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
        
        @POST
	@Path("/findByFilter") // http://localhost:8080/service/api/target/findByFilter
	@Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
	public Response findByFilter(Target target) {
		try{
                    targetBc = new TargetBc();
                    List<Target> targets = targetBc.findByFilter(target);
                    if (targets.size() > 0)
                        return Response.ok(gson.toJson(targets)).build();

                    return Response.ok(gson.toJson("Não foi encontrado targets")).build();
		}catch(Exception e){
			return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
}
