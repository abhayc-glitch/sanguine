package com.server.backend;

import com.server.backend.models.User;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
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

    HttpServer listen = vertx.createHttpServer().requestHandler(baseRouter).listen(8000, result -> {
      if (result.succeeded()) {
        startPromise.complete();
        System.out.println("HTTP server started on port 8000");
      } else {
        startPromise.fail(result.cause());
      }
    });
  }
  private void registerUser(RoutingContext routingContext) {
    JsonObject message = new JsonObject()
      .put("action", "register-user")
      .put("user", routingContext.getBodyAsJson().getJsonObject("user"));

    vertx.eventBus().send("persistence-address", message, res -> {
      if (res.succeeded()) {
        User returnedUser = Json.decodeValue(res.result().body().toString(), User.class);
        returnedUser.setToken("jwt.token.here");
        routingContext.response()
          .setStatusCode(201)
          .putHeader("Content-Type", "application/json; charset=utf-8")
          //.putHeader("Content-Length", String.valueOf(userResult.toString().length()))
          .end(Json.encodePrettily(returnedUser.toSanguineJson()));
      }else{
        routingContext.response()
          .setStatusCode(500)
          .putHeader("Content-Type", "application/json; charset=utf-8")
          //.putHeader("Content-Length", String.valueOf(userResult.toString().length()))
          .end(Json.encodePrettily(res.cause().getMessage()));

      }
    });
  }
}
