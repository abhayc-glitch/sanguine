package com.server.backend;


import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class MainVerticle extends AbstractVerticle {
  @Override
  public void start(Promise<Void> startPromise) {
      deployVerticle(HttpVerticle.class.getName());
      deployVerticle(PersistenceVerticle.class.getName());
      // Dont need an onComplete because we already do that in the deployverticle?
  }

  Promise<Void> deployVerticle (String verticleName){
    Promise<Void> retVal = Promise.promise();
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

