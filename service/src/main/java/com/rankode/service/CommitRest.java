/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rankode.service;

import com.google.gson.Gson;
import com.rankode.core.bc.CommitBc;
import com.rankode.core.model.Commit;
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
@Path("/commit")
@Stateless
public class CommitRest {
    
        private final Gson gson;
        
	private CommitBc commitBc;

	public CommitRest() {
		this.gson = new Gson();
	}
        
        @POST
	@Path("/insert") // http://localhost:8080/service/api/commit/insert
	@Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
	public Response insert(Commit commit) {
		try{
                        commitBc = new CommitBc();
			commitBc.insert(commit);
			return Response.ok(gson.toJson("Commit cadastrado!")).build();
		}catch(Exception e){
			return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
        
        @POST
	@Path("/update") // http://localhost:8080/service/api/commit/update
	@Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
	public Response update(Commit commit) {
		try{
                        commitBc = new CommitBc();
			commitBc.update(commit);
			return Response.ok(gson.toJson("Dados atualizados!")).build();
		}catch(Exception e){
			return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
        
        @POST
	@Path("/delete") // http://localhost:8080/service/api/commit/delete
	@Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
	public Response delete(Commit commit) {
		try{
                        commitBc = new CommitBc();
			commitBc.delete(commit);
			return Response.ok(gson.toJson("Commit deletado")).build();
		}catch(Exception e){
			return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
        
        @GET
	@Path("/findById/{id}") // http://localhost:8080/service/api/commit/findById
	@Produces(MediaType.APPLICATION_JSON)
	public Response findById(@PathParam("id") String id) {
		try{
                        commitBc = new CommitBc();
			Commit commit = commitBc.findById(id);
                        if (commit != null)
				return Response.ok(gson.toJson(commit)).build();
                        
			return Response.ok(gson.toJson("Não há esse commit cadastrado!")).build();
		}catch(Exception e){
			return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
        
        @GET
	@Path("/findAll") // http://localhost:8080/service/api/commit/findAll
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll() {
		try{
                    commitBc = new CommitBc();
                    List<Commit> commits = commitBc.findAll();
                    if (commits.size() > 0)
                            return Response.ok(gson.toJson(commits)).build();

                    return Response.ok(gson.toJson("Não há commits cadastrados!")).build();
		}catch(Exception e){
                    return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
        
        @POST
	@Path("/findByFilter") // http://localhost:8080/service/api/commit/findByFilter
	@Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
	public Response findByFilter(Commit commit) {
		try{
                    commitBc = new CommitBc();
                    List<Commit> commits = commitBc.findByFilter(commit);
                    if (commits.size() > 0)
                        return Response.ok(gson.toJson(commits)).build();

                    return Response.ok(gson.toJson("Não foi encontrado commits")).build();
		}catch(Exception e){
			return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
}
