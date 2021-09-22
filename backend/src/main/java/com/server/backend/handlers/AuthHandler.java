package com.server.backend.handlers;


import com.server.backend.dto.RegisterRequest;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.reactivex.core.Vertx;

// Class to handle Auth Requests
public class AuthHandler extends BaseHandler{
  // Create a Logger for the CLI
  protected static Logger logger = LoggerFactory.getLogger(AuthHandler.class);

  public AuthHandler(Vertx vertx) {
    super(vertx);
  }

  public void signup(RegisterRequest registerRequest){

  }
}
