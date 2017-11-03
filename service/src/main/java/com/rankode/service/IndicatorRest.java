/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rankode.service;

import com.google.gson.Gson;
import com.rankode.core.bc.IndicatorBc;
import com.rankode.core.model.Indicator;
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
@Path("/indicator")
@Stateless
public class IndicatorRest {
        
        private final Gson gson;
        
	private IndicatorBc indicatorBc;

	public IndicatorRest() {
		this.gson = new Gson();
	}
        
        @POST
	@Path("/insert") // http://localhost:8080/service/api/indicator/insert
	@Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
	public Response insert(Indicator indicator) {
		try{
                        indicatorBc = new IndicatorBc();
			indicatorBc.insert(indicator);
			return Response.ok(gson.toJson("Indicador cadastrado!")).build();
		}catch(Exception e){
			return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
        
        @POST
	@Path("/update") // http://localhost:8080/service/api/indicator/update
	@Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
	public Response update(Indicator indicator) {
		try{
                        indicatorBc = new IndicatorBc();
			indicatorBc.update(indicator);
			return Response.ok(gson.toJson("Dados atualizados!")).build();
		}catch(Exception e){
			return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
        
        @POST
	@Path("/delete") // http://localhost:8080/service/api/indicator/delete
	@Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
	public Response delete(Indicator indicator) {
		try{
                        indicatorBc = new IndicatorBc();
			indicatorBc.delete(indicator);
			return Response.ok(gson.toJson("Indicador deletado")).build();
		}catch(Exception e){
			return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
        
        @GET
	@Path("/findById/{id}") // http://localhost:8080/service/api/indicator/findById
	@Produces(MediaType.APPLICATION_JSON)
	public Response findById(@PathParam("id") int id) {
		try{
                        indicatorBc = new IndicatorBc();
			Indicator indicator = indicatorBc.findById(id);
                        if (indicator != null)
				return Response.ok(gson.toJson(indicator)).build();
                        
			return Response.ok(gson.toJson("Não há esse Indicador cadastrado!")).build();
		}catch(Exception e){
			return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
        
        @GET
	@Path("/findAll") // http://localhost:8080/service/api/indicator/findAll
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll() {
		try{
                    indicatorBc = new IndicatorBc();
                    List<Indicator> indicators = indicatorBc.findAll();
                    if (indicators.size() > 0)
                            return Response.ok(gson.toJson(indicators)).build();

                    return Response.ok(gson.toJson("Não há indicadores cadastrados!")).build();
		}catch(Exception e){
                    return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
        
        @POST
	@Path("/findByFilter") // http://localhost:8080/service/api/indicator/findByFilter
	@Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
	public Response findByFilter(Indicator indicator) {
		try{
                    indicatorBc = new IndicatorBc();
                    List<Indicator> indicators = indicatorBc.findByFilter(indicator);
                    if (indicators.size() > 0)
                        return Response.ok(gson.toJson(indicators)).build();

                    return Response.ok(gson.toJson("Não foi encontrado indicadores")).build();
		}catch(Exception e){
			return Response.status(500).entity(gson.toJson("Erro: " + e.getMessage())).build();
		}
		
	}
}
