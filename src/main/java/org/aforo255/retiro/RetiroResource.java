package org.aforo255.retiro;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.aforo255.retiro.entity.Transaction;
import org.aforo255.retiro.service.ITransactionService;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;

import io.smallrye.reactive.messaging.annotations.Channel;
import io.smallrye.reactive.messaging.annotations.Emitter;

@Path("/retiro")
public class RetiroResource {
	
	@Inject
	ITransactionService service;
	
	@Inject
	@Channel("transactions")
	Emitter<Transaction> emitter;
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Counted(name="retiro", description = "Cantidad retiros")
	@Timed(name = "TimerRetiro", description = "Timer retiro", unit = MetricUnits.MILLISECONDS)
	@Transactional
	public Response saveTransaction(Transaction transaction) {
		Transaction trx = transaction; 
		Map<String , Object> response =  new HashMap<>();
		trx=service.saveTransaction(transaction);	
	    emitter.send(trx);		
		response.put("mensaje", "Transacción realizada !!");
		response.put("Transaccion", trx);		
		return Response.status(Status.CREATED).entity(response).build();
	}
}
