/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rankode.service;

import com.google.gson.Gson;
import com.rankode.core.bc.ProjectBc;
import com.rankode.core.model.Project;
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
@Path("/project")
@Stateless
public class ProjectRest {
    
    private final Gson gson;
    
    private ProjectBc projectBc;

    public ProjectRest() {
        this.gson = new Gson();
    }
    
    @POST
	@Path("/insert") // http://localhost:8080/service/api/project/insert
	@Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
	public Response insert(Project project) {
		try{
                        projectBc = new ProjectBc();
			projectBc.insert(project);
			return Response.ok(gson.toJson("Projeto cadastrado!")).build();
		}catch(Exception e){
			return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
        
        @POST
	@Path("/update") // http://localhost:8080/service/api/project/update
	@Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
	public Response update(Project project) {
		try{
                        projectBc = new ProjectBc();
			projectBc.update(project);
			return Response.ok(gson.toJson("Dados atualizados!")).build();
		}catch(Exception e){
			return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
        
        @POST
	@Path("/delete") // http://localhost:8080/service/api/project/delete
	@Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
	public Response delete(Project project) {
		try{
                        projectBc = new ProjectBc();
			projectBc.delete(project);
			return Response.ok(gson.toJson("Projeto deletado")).build();
		}catch(Exception e){
			return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
        
        @GET
	@Path("/findById/{id}") // http://localhost:8080/service/api/project/findById
	@Produces(MediaType.APPLICATION_JSON)
	public Response findById(@PathParam("id") int id) {
		try{
                        projectBc = new ProjectBc();
			Project project = projectBc.findById(id);
                        if (project != null)
				return Response.ok(gson.toJson(project)).build();
                        
			return Response.ok(gson.toJson("Não há esse projeto cadastrado!")).build();
		}catch(Exception e){
			return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
        
        @GET
	@Path("/findAll") // http://localhost:8080/service/api/project/findAll
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll() {
		try{
                    projectBc = new ProjectBc();
                    List<Project> projects = projectBc.findAll();
                    if (projects.size() > 0)
                            return Response.ok(gson.toJson(projects)).build();

                    return Response.ok(gson.toJson("Não há projetos cadastrados!")).build();
		}catch(Exception e){
                    return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
        
        @POST
	@Path("/findByFilter") // http://localhost:8080/service/api/project/findByFilter
	@Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
	public Response findByFilter(Project project) {
		try{
                    projectBc = new ProjectBc();
                    List<Project> projects = projectBc.findByFilter(project);
                    if (projects.size() > 0)
                        return Response.ok(gson.toJson(projects)).build();

                    return Response.ok(gson.toJson("Não foi encontrado desenvolvedores")).build();
		}catch(Exception e){
			return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
    
}
