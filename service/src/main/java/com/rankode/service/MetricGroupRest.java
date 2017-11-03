/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rankode.service;

import com.google.gson.Gson;
import com.rankode.core.bc.MetricGroupBc;
import com.rankode.core.model.MetricGroup;
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
@Path("/group")
@Stateless
public class MetricGroupRest {
    
        private final Gson gson;
        
	private MetricGroupBc metricGroupBc;

	public MetricGroupRest() {
		this.gson = new Gson();
	}
        
        @POST
	@Path("/insert") // http://localhost:8080/service/api/metricGroup/insert
	@Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
	public Response insert(MetricGroup metricGroup) {
		try{
                        metricGroupBc = new MetricGroupBc();
			metricGroupBc.insert(metricGroup);
			return Response.ok(gson.toJson("Grupo cadastrado!")).build();
		}catch(Exception e){
			return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
        
        @POST
	@Path("/update") // http://localhost:8080/service/api/metricGroup/update
	@Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
	public Response update(MetricGroup metricGroup) {
		try{
                        metricGroupBc = new MetricGroupBc();
			metricGroupBc.update(metricGroup);
			return Response.ok(gson.toJson("Dados atualizados!")).build();
		}catch(Exception e){
			return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
        
        @POST
	@Path("/delete") // http://localhost:8080/service/api/metricGroup/delete
	@Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
	public Response delete(MetricGroup metricGroup) {
		try{
                        metricGroupBc = new MetricGroupBc();
			metricGroupBc.delete(metricGroup);
			return Response.ok(gson.toJson("Grupo deletado")).build();
		}catch(Exception e){
			return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
        
        @GET
	@Path("/findById/{id}") // http://localhost:8080/service/api/metricGroup/findById
	@Produces(MediaType.APPLICATION_JSON)
	public Response findById(@PathParam("id") int id) {
		try{
                        metricGroupBc = new MetricGroupBc();
			MetricGroup metricGroup = metricGroupBc.findById(id);
                        if (metricGroup != null)
				return Response.ok(gson.toJson(metricGroup)).build();
                        
			return Response.ok(gson.toJson("Não há esse grupo cadastrado!")).build();
		}catch(Exception e){
			return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
        
        @GET
	@Path("/findAll") // http://localhost:8080/service/api/metricGroup/findAll
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll() {
		try{
                    metricGroupBc = new MetricGroupBc();
                    List<MetricGroup> metricGroups = metricGroupBc.findAll();
                    if (metricGroups.size() > 0)
                            return Response.ok(gson.toJson(metricGroups)).build();

                    return Response.ok(gson.toJson("Não há desenvolvedores cadastrados!")).build();
		}catch(Exception e){
                    return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
        
        @POST
	@Path("/findByFilter") // http://localhost:8080/service/api/metricGroup/findByFilter
	@Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
	public Response findByFilter(MetricGroup metricGroup) {
		try{
                    metricGroupBc = new MetricGroupBc();
                    List<MetricGroup> metricGroups = metricGroupBc.findByFilter(metricGroup);
                    if (metricGroups.size() > 0)
                        return Response.ok(gson.toJson(metricGroups)).build();

                    return Response.ok(gson.toJson("Não foi encontrado grupos")).build();
		}catch(Exception e){
			return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
}
