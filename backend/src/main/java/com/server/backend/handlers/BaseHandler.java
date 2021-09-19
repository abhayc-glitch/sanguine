package com.server.backend.handlers;

import io.vertx.reactivex.core.Vertx;

// A class for handling all "base" requests.
// This is an extendable class
public abstract class BaseHandler {
  protected Vertx vertx;

  public BaseHandler(final Vertx vertx) {
    this.vertx = vertx;
  }
}
