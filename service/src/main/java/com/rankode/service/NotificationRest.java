/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rankode.service;

import com.google.gson.Gson;
import com.rankode.core.bc.NotificationBc;
import com.rankode.core.model.Notification;
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
@Path("/notification")
@Stateless
public class NotificationRest {
    
        private final Gson gson;
        
	private NotificationBc notificationBc;

	public NotificationRest() {
		this.gson = new Gson();
	}
        
        @POST
	@Path("/insert") // http://localhost:8080/service/api/notification/insert
	@Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
	public Response insert(Notification notification) {
		try{
                        notificationBc = new NotificationBc();
			notificationBc.insert(notification);
			return Response.ok(gson.toJson("Notificação cadastrada!")).build();
		}catch(Exception e){
			return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
        
        @POST
	@Path("/update") // http://localhost:8080/service/api/notification/update
	@Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
	public Response update(Notification notification) {
		try{
                        notificationBc = new NotificationBc();
			notificationBc.update(notification);
			return Response.ok(gson.toJson("Dados atualizados!")).build();
		}catch(Exception e){
			return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
        
        @POST
	@Path("/delete") // http://localhost:8080/service/api/notification/delete
	@Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
	public Response delete(Notification notification) {
		try{
                        notificationBc = new NotificationBc();
			notificationBc.delete(notification);
			return Response.ok(gson.toJson("Notificação deletada")).build();
		}catch(Exception e){
			return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
        
        @GET
	@Path("/findById/{id}") // http://localhost:8080/service/api/notification/findById
	@Produces(MediaType.APPLICATION_JSON)
	public Response findById(@PathParam("id") int id) {
		try{
                        notificationBc = new NotificationBc();
			Notification notification = notificationBc.findById(id);
                        if (notification != null)
				return Response.ok(gson.toJson(notification)).build();
                        
			return Response.ok(gson.toJson("Não há essa notificação cadastrada!")).build();
		}catch(Exception e){
			return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
        
        @GET
	@Path("/findAll") // http://localhost:8080/service/api/notification/findAll
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll() {
		try{
                    notificationBc = new NotificationBc();
                    List<Notification> notifications = notificationBc.findAll();
                    if (notifications.size() > 0)
                            return Response.ok(gson.toJson(notifications)).build();

                    return Response.ok(gson.toJson("Não há notificações cadastradas!")).build();
		}catch(Exception e){
                    return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
        
        @POST
	@Path("/findByFilter") // http://localhost:8080/service/api/notification/findByFilter
	@Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
	public Response findByFilter(Notification notification) {
		try{
                    notificationBc = new NotificationBc();
                    List<Notification> notifications = notificationBc.findByFilter(notification);
                    if (notifications.size() > 0)
                        return Response.ok(gson.toJson(notifications)).build();

                    return Response.ok(gson.toJson("Não foi encontrado notificações")).build();
		}catch(Exception e){
			return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
}
