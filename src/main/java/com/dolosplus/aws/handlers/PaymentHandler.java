package com.dolosplus.aws.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.amazonaws.services.lambda.runtime.events.SQSEvent.SQSMessage;

import software.amazon.awssdk.services.lambda.model.GetAccountSettingsRequest;
import software.amazon.awssdk.services.lambda.model.GetAccountSettingsResponse;
import software.amazon.awssdk.services.lambda.model.ServiceException;
import software.amazon.awssdk.services.lambda.LambdaAsyncClient;
import software.amazon.awssdk.services.lambda.model.AccountUsage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.StringBuilder;
import java.util.Map;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.dolosplus.aws.model.*;

// Handler value: com.dolosplus.aws.handlers.PaymentHandler
public class PaymentHandler implements RequestHandler<PaymentTokenRequest, String>{
  private static final Logger logger = LoggerFactory.getLogger(PaymentHandler.class);
  private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
  private static final LambdaAsyncClient lambdaClient = LambdaAsyncClient.create();

  public PaymentHandler(){
    CompletableFuture<GetAccountSettingsResponse> accountSettings = lambdaClient.getAccountSettings(GetAccountSettingsRequest.builder().build());
    try {
      logger.debug("STARTING PaymentHandler()");
      GetAccountSettingsResponse settings = accountSettings.get();
      logger.info("ACCOUNT SETTINGS: {}", gson.toJson(settings));
    } catch(Exception e) {
      e.getStackTrace();
    }
  }
  @Override
  public String handleRequest(PaymentTokenRequest request, Context context)
  {
    String response = new String();
    // call Lambda API
    logger.info("Getting account settings");
    // CompletableFuture<GetAccountSettingsResponse> accountSettings = 
    //     lambdaClient.getAccountSettings(GetAccountSettingsRequest.builder().build());
    // log execution details
    logger.info("ENVIRONMENT VARIABLES: {}", gson.toJson(System.getenv()));
    logger.info("CONTEXT: {}", gson.toJson(context));
    logger.info("REQUEST: {}", gson.toJson(request));
    // process event
    // for(SQSMessage msg : event.getRecords()){
    //   logger.info(msg.getBody());
    // }
    // process Lambda API response
    try {
      //GetAccountSettingsResponse settings = accountSettings.get();
      //response = gson.toJson(settings.accountUsage());
      PaymentToken payment = new PaymentToken(request.getId(),"ME TOKEN: XXXXX");
      response = gson.toJson(payment);
      logger.info("FROM JAK TEST: Account usage: {}", response);
    } catch(Exception e) {
      e.getStackTrace();
    }
    return response;
  }
}