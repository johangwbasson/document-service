package net.johanbasson.document.integration

import io.kotest.core.spec.style.FunSpec
import io.restassured.RestAssured
import io.restassured.http.ContentType
import net.johanbasson.document.infrastructure.Server
import net.johanbasson.document.infrastructure.config.ServerConfig

abstract class BaseIntegrationTest : FunSpec() {
    private var server: Server? = null

    init {
        beforeSpec {
            val testConfig =
                ServerConfig(
                    port = 0, // Random available port
                )

            server = Server(testConfig).start()
            val port = server?.port() ?: throw IllegalStateException("Server not started")
            RestAssured.port = port
            RestAssured.baseURI = "http://localhost"
        }

        afterSpec {
            server?.stop()
            RestAssured.reset()
        }
    }

    protected fun baseUrl() = "http://localhost:${RestAssured.port}"

    protected fun given() =
        RestAssured
            .given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
}