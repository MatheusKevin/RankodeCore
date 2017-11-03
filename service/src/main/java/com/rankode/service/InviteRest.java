/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rankode.service;

import com.google.gson.Gson;
import com.rankode.core.bc.InviteBc;
import com.rankode.core.model.Invite;
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
@Path("/invite")
@Stateless
public class InviteRest {
    
        private final Gson gson;
        
	private InviteBc inviteBc;

	public InviteRest() {
		this.gson = new Gson();
	}
        
        @POST
	@Path("/insert") // http://localhost:8080/service/api/invite/insert
	@Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
	public Response insert(Invite invite) {
		try{
                        inviteBc = new InviteBc();
			inviteBc.insert(invite);
			return Response.ok(gson.toJson("Convite cadastrado!")).build();
		}catch(Exception e){
			return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
        
        @POST
	@Path("/update") // http://localhost:8080/service/api/invite/update
	@Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
	public Response update(Invite invite) {
		try{
                        inviteBc = new InviteBc();
			inviteBc.update(invite);
			return Response.ok(gson.toJson("Dados atualizados!")).build();
		}catch(Exception e){
			return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
        
        @POST
	@Path("/delete") // http://localhost:8080/service/api/invite/delete
	@Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
	public Response delete(Invite invite) {
		try{
                        inviteBc = new InviteBc();
			inviteBc.delete(invite);
			return Response.ok(gson.toJson("Convite deletado")).build();
		}catch(Exception e){
			return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
        
        @GET
	@Path("/findById/{id}") // http://localhost:8080/service/api/invite/findById
	@Produces(MediaType.APPLICATION_JSON)
	public Response findById(@PathParam("id") int id) {
		try{
                        inviteBc = new InviteBc();
			Invite invite = inviteBc.findById(id);
                        if (invite != null)
				return Response.ok(gson.toJson(invite)).build();
                        
			return Response.ok(gson.toJson("Não há esse convite cadastrado!")).build();
		}catch(Exception e){
			return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
        
        @GET
	@Path("/findAll") // http://localhost:8080/service/api/invite/findAll
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll() {
		try{
                    inviteBc = new InviteBc();
                    List<Invite> invites = inviteBc.findAll();
                    if (invites.size() > 0)
                            return Response.ok(gson.toJson(invites)).build();

                    return Response.ok(gson.toJson("Não há convites cadastrados!")).build();
		}catch(Exception e){
                    return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
        
        @POST
	@Path("/findByFilter") // http://localhost:8080/service/api/invite/findByFilter
	@Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
	public Response findByFilter(Invite invite) {
		try{
                    inviteBc = new InviteBc();
                    List<Invite> invites = inviteBc.findByFilter(invite);
                    if (invites.size() > 0)
                        return Response.ok(gson.toJson(invites)).build();

                    return Response.ok(gson.toJson("Não foi encontrado convites")).build();
		}catch(Exception e){
			return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
}
