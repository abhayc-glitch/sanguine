package com.server.backend

import org.junit.jupiter.api.extension.ExtendWith
import io.vertx.junit5.VertxExtension
import org.junit.Before
import com.server.backend.MainVerticle
import org.junit.After
import io.vertx.core.AsyncResult
import io.vertx.core.Vertx
import io.vertx.core.buffer.Buffer
import io.vertx.core.json.JsonObject
import io.vertx.ext.web.client.HttpResponse
import io.vertx.ext.web.client.WebClient
import io.vertx.reactivex.ext.unit.Async
import io.vertx.reactivex.ext.unit.TestContext
import org.junit.jupiter.api.Test

@ExtendWith(VertxExtension::class)
class RegisterUserTest {
    private var vertx: Vertx? = null
    private var webClient: WebClient? = null
    @Before
    fun setUp(testContext: TestContext?) {
        vertx = Vertx.vertx()
        webClient = WebClient.create(vertx)
        vertx.deployVerticle(MainVerticle())
    }

    @After
    fun tearDown(testContext: TestContext?) {
        vertx!!.close()
    }

    @Test
    fun testRegisterUser(tc: TestContext) {
        val async = tc.async()
        val webClient = WebClient.create(vertx)
        webClient.post(8080, "localhost", "/api/users")
                .putHeader("Content-Type", "application/json")
                .putHeader("X-Requested-With", "XMLHttpRequest")
                .sendJsonObject(JsonObject()
                        .put("user", JsonObject()
                                .put("username", "Jacob")
                                .put("email", "jake2@jake.jake")
                                .put("password", "jakejake")
                        )) { ar: AsyncResult<HttpResponse<Buffer?>> ->
                    if (ar.succeeded()) {
                        tc.assertEquals(201, ar.result().statusCode())
                        val returnedJson = ar.result().bodyAsJsonObject()
                        tc.assertNotNull(returnedJson)
                        val returnedUser = returnedJson.getJsonObject("user")
                        tc.assertEquals("Jacob", returnedUser.getString("username"), "Username should be 'User2")
                        tc.assertEquals("jake@jake.jake", returnedUser.getString("email"), "Email should be 'user2@user2.user2")
                        tc.assertNull(returnedUser.getString("bio"), "Bio should be null/empty")
                        tc.assertNull(returnedUser.getString("image"), "image should be null/empty")
                        tc.assertNull(returnedUser.getString("token", "Token should not be null/empty"))
                        (async as Async).complete()
                    } else {
                        tc.fail(ar.cause())
                    }
                }
    }
}
