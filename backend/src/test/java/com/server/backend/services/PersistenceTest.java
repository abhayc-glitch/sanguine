package com.server.backend.services;

import com.server.backend.verticles.PersistenceVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.ext.unit.Async;
import io.vertx.reactivex.ext.unit.TestContext;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import io.vertx.junit5.VertxExtension;

@ExtendWith(VertxExtension.class)
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

  }


}
