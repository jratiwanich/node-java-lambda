package com.dolosplus.invoke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.amazonaws.services.lambda.runtime.events.SQSEvent.SQSMessage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import com.amazonaws.xray.AWSXRay;
import com.amazonaws.xray.AWSXRayRecorderBuilder;
import com.amazonaws.xray.strategy.sampling.NoSamplingStrategy;

import com.dolosplus.aws.handlers.PaymentHandler;
import com.dolosplus.aws.model.PaymentToken;
import com.dolosplus.aws.model.PaymentTokenRequest;

class InvokeTest {
  private static final Logger logger = LoggerFactory.getLogger(InvokeTest.class);
  Gson gson =  new GsonBuilder().setPrettyPrinting().create();
  // Gson gson = new GsonBuilder()
  //         .registerTypeAdapter(PaymentTokenRequest.class, new PaymentTokenRequest())
  //         .setPrettyPrinting()
  //         .create();

  public InvokeTest() {
    // AWSXRayRecorderBuilder builder = AWSXRayRecorderBuilder.standard();
    // builder.withSamplingStrategy(new NoSamplingStrategy());
    // AWSXRay.setGlobalRecorder(builder.build());
  }

  @Test
  void invokeTest() {
    //AWSXRay.beginSegment("java-test-lambda-test");
    String path = "src/test/resources/payment.json";
    String eventString = loadJsonFile(path);
    PaymentTokenRequest data = gson.fromJson(eventString, PaymentTokenRequest.class);
    Context context = new TestContext();
    //String requestId = context.getAwsRequestId();
    PaymentHandler handler = new PaymentHandler();
    String result = handler.handleRequest(data, context);
    assertTrue(result.contains("id"));
    //AWSXRay.endSegment();
  }

  private static String loadJsonFile(String path)
  {
      StringBuilder stringBuilder = new StringBuilder();
      try (Stream<String> stream = Files.lines( Paths.get(path), StandardCharsets.UTF_8))
      {
          stream.forEach(s -> stringBuilder.append(s));
      }
      catch (IOException e)
      {
          e.printStackTrace();
      }
      return stringBuilder.toString();
  }
}
