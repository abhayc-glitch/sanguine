package com.server.backend;


import io.vertx.core.AbstractVerticle;
import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.Promise;

public class MainVerticle extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) {
    CompositeFuture.all(
      deployVerticle(HttpVerticle.class.getName()),
      deployVerticle(PersistenceVerticle.class.getName())
    ).onComplete(f ->{
      if (f.succeeded()) {
        startPromise.complete();
        System.out.println("Http Server Successfully started on Port 8000");
      }else{
        startPromise.fail(f.cause());
      }
    });
  }

  Future<Void> deployVerticle(String verticleName) {
    Future<Void> retVal = Future.future();
    vertx.deployVerticle(verticleName, event -> {
      if (event.succeeded()) {
        retVal.complete();
      }else{
        retVal.fail(event.cause());
      }
    });
    return retVal;
  }
}

