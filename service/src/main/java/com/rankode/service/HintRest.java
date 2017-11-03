/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rankode.service;

import com.google.gson.Gson;
import com.rankode.core.bc.HintBc;
import com.rankode.core.model.Hint;
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
@Path("/hint")
@Stateless
public class HintRest {
    
        private final Gson gson;
        
	private HintBc hintBc;

	public HintRest() {
		this.gson = new Gson();
	}
        
        @POST
	@Path("/insert") // http://localhost:8080/service/api/hint/insert
	@Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
	public Response insert(Hint hint) {
		try{
                        hintBc = new HintBc();
			hintBc.insert(hint);
			return Response.ok(gson.toJson("Dica cadastrada!")).build();
		}catch(Exception e){
			return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
        
        @POST
	@Path("/update") // http://localhost:8080/service/api/hint/update
	@Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
	public Response update(Hint hint) {
		try{
                        hintBc = new HintBc();
			hintBc.update(hint);
			return Response.ok(gson.toJson("Dados atualizados!")).build();
		}catch(Exception e){
			return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
        
        @POST
	@Path("/delete") // http://localhost:8080/service/api/hint/delete
	@Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
	public Response delete(Hint hint) {
		try{
                        hintBc = new HintBc();
			hintBc.delete(hint);
			return Response.ok(gson.toJson("Dica deletada")).build();
		}catch(Exception e){
			return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
        
        @GET
	@Path("/findById/{id}") // http://localhost:8080/service/api/hint/findById
	@Produces(MediaType.APPLICATION_JSON)
	public Response findById(@PathParam("id") int id) {
		try{
                        hintBc = new HintBc();
			Hint hint = hintBc.findById(id);
                        if (hint != null)
				return Response.ok(gson.toJson(hint)).build();
                        
			return Response.ok(gson.toJson("Não há essa dica cadastrado!")).build();
		}catch(Exception e){
			return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
        
        @GET
	@Path("/findAll") // http://localhost:8080/service/api/hint/findAll
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll() {
		try{
                    hintBc = new HintBc();
                    List<Hint> hints = hintBc.findAll();
                    if (hints.size() > 0)
                            return Response.ok(gson.toJson(hints)).build();

                    return Response.ok(gson.toJson("Não há dicas cadastrados!")).build();
		}catch(Exception e){
                    return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
        
        @POST
	@Path("/findByFilter") // http://localhost:8080/service/api/hint/findByFilter
	@Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
	public Response findByFilter(Hint hint) {
		try{
                    hintBc = new HintBc();
                    List<Hint> hints = hintBc.findByFilter(hint);
                    if (hints.size() > 0)
                        return Response.ok(gson.toJson(hints)).build();

                    return Response.ok(gson.toJson("Não foi encontrado desenvolvedores")).build();
		}catch(Exception e){
			return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
}
