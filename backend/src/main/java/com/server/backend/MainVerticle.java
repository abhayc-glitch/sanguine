package com.server.backend;


import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class MainVerticle extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) {

    vertx.deployVerticle(HttpVerticle.class.getName());
    startPromise.complete();
  }

}
