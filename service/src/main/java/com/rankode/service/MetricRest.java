/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rankode.service;

import com.google.gson.Gson;
import com.rankode.core.bc.MetricBc;
import com.rankode.core.model.Metric;
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
@Path("/metric")
@Stateless
public class MetricRest {
    
    private final Gson gson;
        
	private MetricBc metricBc;

	public MetricRest() {
		this.gson = new Gson();
	}
        
        @POST
	@Path("/insert") // http://localhost:8080/service/api/metric/insert
	@Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
	public Response insert(Metric metric) {
		try{
                        metricBc = new MetricBc();
			metricBc.insert(metric);
			return Response.ok(gson.toJson("Métrica cadastrada!")).build();
		}catch(Exception e){
			return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
        
        @POST
	@Path("/update") // http://localhost:8080/service/api/metric/update
	@Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
	public Response update(Metric metric) {
		try{
                        metricBc = new MetricBc();
			metricBc.update(metric);
			return Response.ok(gson.toJson("Dados atualizados!")).build();
		}catch(Exception e){
			return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
        
        @POST
	@Path("/delete") // http://localhost:8080/service/api/metric/delete
	@Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
	public Response delete(Metric metric) {
		try{
                        metricBc = new MetricBc();
			metricBc.delete(metric);
			return Response.ok(gson.toJson("Métrica deletada")).build();
		}catch(Exception e){
			return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
        
        @GET
	@Path("/findById/{id}") // http://localhost:8080/service/api/metric/findById
	@Produces(MediaType.APPLICATION_JSON)
	public Response findById(@PathParam("id") String id) {
		try{
                        metricBc = new MetricBc();
			Metric metric = metricBc.findById(id);
                        if (metric != null)
				return Response.ok(gson.toJson(metric)).build();
                        
			return Response.ok(gson.toJson("Não há essa métrica cadastrado!")).build();
		}catch(Exception e){
			return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
        
        @GET
	@Path("/findAll") // http://localhost:8080/service/api/metric/findAll
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll() {
		try{
                    metricBc = new MetricBc();
                    List<Metric> metrics = metricBc.findAll();
                    if (metrics.size() > 0)
                            return Response.ok(gson.toJson(metrics)).build();

                    return Response.ok(gson.toJson("Não há métricas cadastrados!")).build();
		}catch(Exception e){
                    return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
        
        @POST
	@Path("/findByFilter") // http://localhost:8080/service/api/metric/findByFilter
	@Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
	public Response findByFilter(Metric metric) {
		try{
                    metricBc = new MetricBc();
                    List<Metric> metrics = metricBc.findByFilter(metric);
                    if (metrics.size() > 0)
                        return Response.ok(gson.toJson(metrics)).build();

                    return Response.ok(gson.toJson("Não foi encontrado métricas")).build();
		}catch(Exception e){
			return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
    
}
