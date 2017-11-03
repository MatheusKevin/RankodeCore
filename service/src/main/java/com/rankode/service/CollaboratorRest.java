/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rankode.service;

import com.google.gson.Gson;
import com.rankode.core.bc.CollaboratorBc;
import com.rankode.core.model.Collaborator;
import java.sql.Date;
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
@Path("/collaborator")
@Stateless
public class CollaboratorRest {
    
        private final Gson gson;
        
	private CollaboratorBc collaboratorBc;

	public CollaboratorRest() {
		this.gson = new Gson();
	}
        
        @POST
	@Path("/insert") // http://localhost:8080/service/api/collaborator/insert
	@Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
	public Response insert(Collaborator collaborator) {
		try{
                        collaboratorBc = new CollaboratorBc();
			collaboratorBc.insert(collaborator);
			return Response.ok(gson.toJson("Colaborador adicionado!")).build();
		}catch(Exception e){
			return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
        
        @POST
	@Path("/update") // http://localhost:8080/service/api/collaborator/update
	@Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
	public Response update(Collaborator collaborator) {
		try{
                        collaboratorBc = new CollaboratorBc();
			collaboratorBc.update(collaborator);
			return Response.ok(gson.toJson("Dados atualizados!")).build();
		}catch(Exception e){
			return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
        
        @POST
	@Path("/delete") // http://localhost:8080/service/api/collaborator/delete
	@Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
	public Response delete(Collaborator collaborator) {
		try{
                        collaboratorBc = new CollaboratorBc();
			collaboratorBc.delete(collaborator);
			return Response.ok(gson.toJson("Colaborador removido")).build();
		}catch(Exception e){
			return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
        
        @GET
	@Path("/findById/{login}/{idProject}/{date}") // http://localhost:8080/service/api/collaborator/findById
	@Produces(MediaType.APPLICATION_JSON)
	public Response findById(@PathParam("login") String login, @PathParam("idProject") int idProject,
        @PathParam("date") Date date) {
		try{
                        collaboratorBc = new CollaboratorBc();
			Collaborator collaborator = collaboratorBc.findById(login, idProject, date);
                        if (collaborator != null)
				return Response.ok(gson.toJson(collaborator)).build();
                        
			return Response.ok(gson.toJson("Não há esse colaborador cadastrado!")).build();
		}catch(Exception e){
			return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
        
        @GET
	@Path("/findAll") // http://localhost:8080/service/api/collaborator/findAll
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll() {
		try{
                    collaboratorBc = new CollaboratorBc();
                    List<Collaborator> collaborators = collaboratorBc.findAll();
                    if (collaborators.size() > 0)
                            return Response.ok(gson.toJson(collaborators)).build();

                    return Response.ok(gson.toJson("Não há colaboradores cadastrados!")).build();
		}catch(Exception e){
                    return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
        
        @POST
	@Path("/findByFilter") // http://localhost:8080/service/api/collaborator/findByFilter
	@Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
	public Response findByFilter(Collaborator collaborator) {
		try{
                    collaboratorBc = new CollaboratorBc();
                    List<Collaborator> collaborators = collaboratorBc.findByFilter(collaborator);
                    if (collaborators.size() > 0)
                        return Response.ok(gson.toJson(collaborators)).build();

                    return Response.ok(gson.toJson("Não foi encontrado colaboradores")).build();
		}catch(Exception e){
			return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
}
