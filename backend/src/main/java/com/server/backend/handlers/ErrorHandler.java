package com.server.backend.handlers;

import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.reactivex.core.Vertx;

public class ErrorHandler extends BaseHandler{

  private static Logger logger = LoggerFactory.getLogger(ErrorHandler.class);

  public ErrorHandler(Vertx vertx) {
    super(vertx);
  }
}
