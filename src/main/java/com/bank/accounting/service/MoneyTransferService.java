package com.bank.accounting.service;

import com.bank.accounting.model.ResponseModel;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.UUID;

@Path("/money")
public class MoneyTransferService {

    @POST
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseModel getTrackInJSON(ResponseModel request) {
        ResponseModel responseModel = new ResponseModel();
        responseModel.setTransactionCode(UUID.randomUUID().toString());
        responseModel.setComing(request.getComing());
        return responseModel;
    }

}
