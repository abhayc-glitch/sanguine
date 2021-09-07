package com.server.backend;

import io.vertx.core.Promise;
import io.vertx.reactivex.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.reactivex.ext.mongo.MongoClient;
import io.vertx.reactivex.ext.auth.mongo.MongoAuth;

public class PersistenceVerticle extends AbstractVerticle {
  // for DB access
  private MongoClient mongoClient;

  private Vertx vertx;

  private MongoAuth loginAuthProvider;

  @Override
  public void start(Promise<Void> startPromise) {
    // Configure the MongoClient inline.  This should be externalized into a config file
    mongoClient = MongoClient.createShared(vertx, new JsonObject()
      .put("db_name", config()
      .getString("db_name", "conduit"))
      .put("connection_string", config()
      .getString("connection_string", "mongodb://localhost:27017")));

    loginAuthProvider = MongoAuth.create(mongoClient, new JsonObject());
    loginAuthProvider.setUsernameField("email");
    loginAuthProvider.setUsernameCredentialField("email");

    EventBus eventBus = (EventBus) vertx.eventBus();
    MessageConsumer<JsonObject> consumer = eventBus.consumer("persistence-address");

    consumer.handler(message -> {

      String action = message.body().getString("action");

      switch (action) {
        case "register-user":
          registerUser(message);
          break;
        default:
          message.fail(1, "Unkown action: " + message.body());
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
    message.reply(new JsonObject()
      .put("email", "jake@jake.jake")
      .put("username", "Jacob"));
  }
}
