/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rankode.service;

import com.google.gson.Gson;
import com.rankode.core.bc.MetricResultBc;
import com.rankode.core.model.MetricResult;
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
@Path("/result")
@Stateless
public class MetricResultRest {
        
        private final Gson gson;
        
	private MetricResultBc metricResultBc;

	public MetricResultRest() {
		this.gson = new Gson();
	}
        
        @POST
	@Path("/insert") // http://localhost:8080/service/api/result/insert
	@Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
	public Response insert(MetricResult metricResult) {
		try{
                        metricResultBc = new MetricResultBc();
			metricResultBc.insert(metricResult);
			return Response.ok(gson.toJson("Resultado cadastrado!")).build();
		}catch(Exception e){
			return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
        
        @POST
	@Path("/update") // http://localhost:8080/service/api/result/update
	@Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
	public Response update(MetricResult metricResult) {
		try{
                        metricResultBc = new MetricResultBc();
			metricResultBc.update(metricResult);
			return Response.ok(gson.toJson("Dados atualizados!")).build();
		}catch(Exception e){
			return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
        
        @POST
	@Path("/delete") // http://localhost:8080/service/api/result/delete
	@Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
	public Response delete(MetricResult metricResult) {
		try{
                        metricResultBc = new MetricResultBc();
			metricResultBc.delete(metricResult);
			return Response.ok(gson.toJson("Resultado deletado")).build();
		}catch(Exception e){
			return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
        
        @GET
	@Path("/findById/{sha}/{initials}") // http://localhost:8080/service/api/result/findById
	@Produces(MediaType.APPLICATION_JSON)
	public Response findById(@PathParam("sha") String sha, @PathParam("initials") String initials) {
		try{
                        metricResultBc = new MetricResultBc();
			MetricResult metricResult = metricResultBc.findById(sha, initials);
                        if (metricResult != null)
				return Response.ok(gson.toJson(metricResult)).build();
                        
			return Response.ok(gson.toJson("Não há esse resultado cadastrado!")).build();
		}catch(Exception e){
			return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
        
        @GET
	@Path("/findAll") // http://localhost:8080/service/api/result/findAll
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll() {
		try{
                    metricResultBc = new MetricResultBc();
                    List<MetricResult> metricResults = metricResultBc.findAll();
                    if (metricResults.size() > 0)
                            return Response.ok(gson.toJson(metricResults)).build();

                    return Response.ok(gson.toJson("Não há resultados cadastrados!")).build();
		}catch(Exception e){
                    return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
        
        @GET
	@Path("/findAllSources") // http://localhost:8080/service/api/result/findAllSources
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAllSources() {
		try{
                    metricResultBc = new MetricResultBc();
                    List<MetricResult> metricResults = metricResultBc.findAllSources();
                    if (metricResults.size() > 0)
                            return Response.ok(gson.toJson(metricResults)).build();

                    return Response.ok(gson.toJson("Não há resultados cadastrados!")).build();
		}catch(Exception e){
                    return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
        
        @POST
	@Path("/findByFilter") // http://localhost:8080/service/api/result/findByFilter
	@Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
	public Response findByFilter(MetricResult metricResult) {
		try{
                    metricResultBc = new MetricResultBc();
                    List<MetricResult> metricResults = metricResultBc.findByFilter(metricResult);
                    if (metricResults.size() > 0)
                        return Response.ok(gson.toJson(metricResults)).build();

                    return Response.ok(gson.toJson("Não foram encontrados resultados")).build();
		}catch(Exception e){
			return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
}
