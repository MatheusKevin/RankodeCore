/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rankode.service;

import com.google.gson.Gson;
import com.rankode.core.bc.RepositoryAccountBc;
import com.rankode.core.model.RepositoryAccount;
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
@Path("/repository")
@Stateless
public class RepositoryAccountRest {
    
    private final Gson gson;
        
	private RepositoryAccountBc repositoryAccountBc;

	public RepositoryAccountRest() {
		this.gson = new Gson();
	}
        
    @POST
	@Path("/insert") // http://localhost:8080/service/api/repositoryAccount/insert
	@Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
	public Response insert(RepositoryAccount repositoryAccount) {
		try{
                        repositoryAccountBc = new RepositoryAccountBc();
			repositoryAccountBc.insert(repositoryAccount);
			return Response.ok(gson.toJson("Repositório cadastrado!")).build();
		}catch(Exception e){
			return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
        
        @POST
	@Path("/update") // http://localhost:8080/service/api/repositoryAccount/update
	@Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
	public Response update(RepositoryAccount repositoryAccount) {
		try{
                        repositoryAccountBc = new RepositoryAccountBc();
			repositoryAccountBc.update(repositoryAccount);
			return Response.ok(gson.toJson("Dados atualizados!")).build();
		}catch(Exception e){
			return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
        
        @POST
	@Path("/delete") // http://localhost:8080/service/api/repositoryAccount/delete
	@Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
	public Response delete(RepositoryAccount repositoryAccount) {
		try{
                        repositoryAccountBc = new RepositoryAccountBc();
			repositoryAccountBc.delete(repositoryAccount);
			return Response.ok(gson.toJson("Repositório deletado")).build();
		}catch(Exception e){
			return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
        
        @GET
	@Path("/findById/{id}") // http://localhost:8080/service/api/repositoryAccount/findById
	@Produces(MediaType.APPLICATION_JSON)
	public Response findById(@PathParam("id") String id) {
		try{
                        repositoryAccountBc = new RepositoryAccountBc();
			RepositoryAccount repositoryAccount = repositoryAccountBc.findById(id);
                        if (repositoryAccount != null)
				return Response.ok(gson.toJson(repositoryAccount)).build();
                        
			return Response.ok(gson.toJson("Não há esse repositório cadastrado!")).build();
		}catch(Exception e){
			return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
        
        @GET
	@Path("/findAll") // http://localhost:8080/service/api/repositoryAccount/findAll
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll() {
		try{
                    repositoryAccountBc = new RepositoryAccountBc();
                    List<RepositoryAccount> repositoryAccounts = repositoryAccountBc.findAll();
                    if (repositoryAccounts.size() > 0)
                            return Response.ok(gson.toJson(repositoryAccounts)).build();

                    return Response.ok(gson.toJson("Não há repositórios cadastrados!")).build();
		}catch(Exception e){
                    return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
        
        @POST
	@Path("/findByFilter") // http://localhost:8080/service/api/repositoryAccount/findByFilter
	@Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
	public Response findByFilter(RepositoryAccount repositoryAccount) {
		try{
                    repositoryAccountBc = new RepositoryAccountBc();
                    List<RepositoryAccount> repositoryAccounts = repositoryAccountBc.findByFilter(repositoryAccount);
                    if (repositoryAccounts.size() > 0)
                        return Response.ok(gson.toJson(repositoryAccounts)).build();

                    return Response.ok(gson.toJson("Não foi encontrado repositórios")).build();
		}catch(Exception e){
			return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
}
