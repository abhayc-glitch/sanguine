package com.server.backend;

import com.server.backend.models.User;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

public class HttpVerticle extends AbstractVerticle {
  @Override
  public void start(Promise<Void> startPromise) {

    // create a apiRouter to handle the API
    // create a base router to handle everything at the root level
    Router baseRouter = Router.router(vertx);
    Router apiRouter = Router.router(vertx);

    baseRouter.route("/").handler(routingContext -> {
      HttpServerResponse response = routingContext.response();
      response.putHeader("content-type", "text/plain").end("Hello Vert.x!");
    });

    apiRouter.route("/user*").handler(BodyHandler.create());
    apiRouter.post("/users").handler(this::registerUser);
    // We are going to build out the register method below
    baseRouter.mountSubRouter("/api", apiRouter);

    HttpServer listen = vertx.createHttpServer().requestHandler(baseRouter).listen(8080, result -> {
      if (result.succeeded()) {
        startPromise.complete();
      } else {
        startPromise.fail(result.cause());
      }
    });

  }

  private void registerUser(RoutingContext routingContext) {
    User retVal = new User("Jacob", "jakejake", null, "jake@jake.jake", "jwt.token.here");

    routingContext.response().setStatusCode(201).putHeader("Content-Type", "application/json")
        .end(Json.encodePrettily(retVal.toluminJson()));
  }

}
