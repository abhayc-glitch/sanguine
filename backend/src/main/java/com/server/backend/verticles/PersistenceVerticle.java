package com.server.backend.verticles;

import io.vertx.core.Promise;
import io.vertx.ext.auth.mongo.MongoAuthentication;
import io.vertx.ext.auth.mongo.MongoAuthenticationOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.ext.auth.mongo.MongoAuthorizationOptions;

public class PersistenceVerticle extends AbstractVerticle {
  // for DB access
  private MongoClient mongoClient;

  private Vertx vertx;

  // Logging in Client
  private MongoAuthenticationOptions loginAuthProvider;

  MongoAuthenticationOptions options = new MongoAuthenticationOptions();
  @Override
  public void start(Promise<Void> startPromise) {
    // Configure the MongoClient inline. This should be externalized into a config file
    mongoClient = MongoClient.createShared(vertx, new JsonObject()
      .put("db_name", config()
      .getString("db_name", "sanguinedb"))
      .put("connection_string", config()
      .getString("connection_string", "mongodb://localhost:27017")));
    // The point is to create a username and password field to input to.


    // Can Mongo auth have an new JSON Object???


    loginAuthProvider = MongoAuthentication.create(mongoClient, new JsonObject());
    loginAuthProvider.setUsernameField("email");
    loginAuthProvider.setUsernameCredentialField("email");

    JsonObject authProperties = new JsonObject();
    MongoAuthentication authProvider = MongoAuthentication.create(mongoClient, authProperties);

    EventBus eventBus = (EventBus) vertx.eventBus();

    //Creating a message consumer to send to a persistence address
    MessageConsumer<JsonObject> consumer = eventBus.consumer("persistence-address");

    consumer.handler(message -> {
      // In our message if it has an action which it should
      String action = message.body().getString("action");
      // Call a switch and if in case the action is "Register User" then go ahead and call the register user method
      switch (action) {
        case "register-user":
          registerUser(message);
          break;
      // Or else fail because we don't know what action that is.
        default:
          message.fail(1, "Unknown action: " + message.body());
      }
    });

    startPromise.complete();

  }

  /**
   * Receive Json in the following format:
   *
   {
   "username": "Jacob",
   "email": "jake@jake.jake",
   "password": "jakejake"
   }
   *
   * and return Json in the following format:
   *
   {
   "email": "jake@jake.jake",
   "token": "jwt.token.here",
   "username": "jake",
   "bio": "I work at statefarm",
   "image": null
   }
   *
   * @param message
   */
  private void registerUser(Message<JsonObject> message) {
    // Reply to the method call in the handler message.
    message.reply(new JsonObject()
      .put("email", "jake@jake.jake")
      .put("username", "Jacob"));
  }
}
