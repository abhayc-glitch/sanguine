package com.server.backend;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.WebClient;
import io.vertx.reactivex.ext.unit.Async;
import io.vertx.reactivex.ext.unit.TestContext;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;

@RunWith(VertxExtension.class)
public class PersistenceTest {
  private Vertx vertx;

  @Before
  public void setUp(TestContext testContext){
    vertx = Vertx.vertx();
    vertx.deployVerticle(PersistenceVerticle.class.getName(), testContext.asyncAssertSuccess());
  }

  @After
  public void teardown(TestContext testContext){
    vertx.close();
  }

  @Test
  public void testRegisterUserMessage(TestContext testContext){


    Async async = testContext.async();

    JsonObject userToRegister = new JsonObject()
      .put("username", "Jacob")
      .put("email", "jake2@jake.jake")
      .put("password", "jakejake");

    JsonObject message = new JsonObject()
      .put("action", "register-user")
      .put("user", userToRegister);

    vertx.<JsonObject>eventBus().send("persistence-address", message, ar -> {
      if (ar.succeeded()) {
        testContext.assertNotNull(ar.result().body());
        JsonObject returnedUser = (JsonObject) ar.result().body();
        testContext.assertEquals("jake@jake.jake", returnedUser.getString("email"));
        testContext.assertEquals("Jacob", returnedUser.getString("username"));
        testContext.assertEquals("jwt.token.here", returnedUser.getString("token"));
        async.complete();
      }
      else{
        testContext.assertTrue(ar.succeeded());
        async.complete();
      }
    });
  }


}
