package org.aforo255.retiro.service;

import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.aforo255.retiro.entity.Currency;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("ticker")
@RegisterRestClient(configKey = "config.api.crypto")
public interface ICurrentService {
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	Collection<Currency> getCurrency(@QueryParam("id") String id);
}
