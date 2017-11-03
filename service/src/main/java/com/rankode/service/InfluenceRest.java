/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rankode.service;

import com.google.gson.Gson;
import com.rankode.core.bc.InfluenceBc;
import com.rankode.core.model.Influence;
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
@Path("/influence")
@Stateless
public class InfluenceRest {
    
        private final Gson gson;
        
	private InfluenceBc influenceBc;

	public InfluenceRest() {
		this.gson = new Gson();
	}
        
        @POST
	@Path("/insert") // http://localhost:8080/service/api/influence/insert
	@Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
	public Response insert(Influence influence) {
		try{
                        influenceBc = new InfluenceBc();
			influenceBc.insert(influence);
			return Response.ok(gson.toJson("Influencia cadastrada!")).build();
		}catch(Exception e){
			return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
        
        @POST
	@Path("/update") // http://localhost:8080/service/api/influence/update
	@Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
	public Response update(Influence influence) {
		try{
                        influenceBc = new InfluenceBc();
			influenceBc.update(influence);
			return Response.ok(gson.toJson("Dados atualizados!")).build();
		}catch(Exception e){
			return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
        
        @POST
	@Path("/delete") // http://localhost:8080/service/api/influence/delete
	@Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
	public Response delete(Influence influence) {
		try{
                        influenceBc = new InfluenceBc();
			influenceBc.delete(influence);
			return Response.ok(gson.toJson("Influencia deletado")).build();
		}catch(Exception e){
			return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
        
        @GET
	@Path("/findById/{id}") // http://localhost:8080/service/api/influence/findById
	@Produces(MediaType.APPLICATION_JSON)
	public Response findById(@PathParam("id") int id) {
		try{
                        influenceBc = new InfluenceBc();
			Influence influence = influenceBc.findById(id);
                        if (influence != null)
				return Response.ok(gson.toJson(influence)).build();
                        
			return Response.ok(gson.toJson("Não há essa influencia cadastrada!")).build();
		}catch(Exception e){
			return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
        
        @GET
	@Path("/findAll") // http://localhost:8080/service/api/influence/findAll
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll() {
		try{
                    influenceBc = new InfluenceBc();
                    List<Influence> influences = influenceBc.findAll();
                    if (influences.size() > 0)
                            return Response.ok(gson.toJson(influences)).build();

                    return Response.ok(gson.toJson("Não há influencias cadastradas!")).build();
		}catch(Exception e){
                    return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
        
        @POST
	@Path("/findByFilter") // http://localhost:8080/service/api/influence/findByFilter
	@Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
	public Response findByFilter(Influence influence) {
		try{
                    influenceBc = new InfluenceBc();
                    List<Influence> influences = influenceBc.findByFilter(influence);
                    if (influences.size() > 0)
                        return Response.ok(gson.toJson(influences)).build();

                    return Response.ok(gson.toJson("Não foi encontrado influencias")).build();
		}catch(Exception e){
			return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
}
