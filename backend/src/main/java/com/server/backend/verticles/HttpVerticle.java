package com.server.backend.verticles;

import com.server.backend.models.User;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.EventBus;
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

    // Have routing context for a certain action or cause for re-routing.
    baseRouter.route("/").handler(routingContext -> {
      HttpServerResponse response = routingContext.response();
      response.putHeader("content-type", "text/plain").end("Hello Vert.x!");
    });

    apiRouter.route("/user*").handler(BodyHandler.create());
    apiRouter.post("/users").handler(this::registerUser);
    // Mounting the router on top of base router for more
    baseRouter.mountSubRouter("/api", apiRouter);

    // Creating a HTTP Server
    HttpServer listen = vertx.createHttpServer().requestHandler(baseRouter).listen(8000, result -> {
      if (result.succeeded()) {
        startPromise.complete();
        System.out.println("HTTP server started on port 8000");
      } else {
        startPromise.fail(result.cause());
      }
    });
  }

  // Register User method to route efficiently. ...
  private void registerUser(RoutingContext routingContext) {
    // You get the request you pull the request off of the JSON...
    JsonObject message = new JsonObject()
      .put("action", "register-user")
      .put("user", routingContext.getBodyAsJson().getJsonObject("user"));
    // You send it to the eventBus
    vertx.<JsonObject>eventBus().send("persistence-address", message , res -> {
      if (res.succeeded()) {
        // If it succeeds in sending the address then we decode it back to a POJO (Java User Model)
        User returnedUser = Json.decodeValue(res.result().body().toString(), User.class);
        // And we set the JWT token
        returnedUser.setToken("jwt.token.here");
        // We get a response in JSON format
        routingContext.response()
          .setStatusCode(201)
          .putHeader("Content-Type", "application/json; charset=utf-8")
          //.putHeader("Content-Length", String.valueOf(userResult.toString().length()))
          // We get a "user" json object that is our response
          // We encode it again
          .end(Json.encodePrettily(returnedUser.toSanguineJson()));
      }
      else{
        routingContext.response()
          .setStatusCode(500)
          .putHeader("Content-Type", "application/json; charset=utf-8")
          //.putHeader("Content-Length", String.valueOf(userResult.toString().length()))
          // Else encode it in JSON with the cause and send that back to the client
          .end(Json.encodePrettily(res.cause().getMessage()));

      };
    });

  }
}
