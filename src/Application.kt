package com.example.ktorOauthPractice

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.features.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.auth.*
import com.fasterxml.jackson.databind.*
import io.ktor.jackson.*
import io.ktor.client.*
import io.ktor.client.engine.jetty.*
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import com.example.ktorOauthPractice.auth.*


fun main(args: Array<String>): Unit  {
    io.ktor.server.netty.EngineMain.main(args)
    embeddedServer(Netty, 8080) {
        routing {
            get("/") {
                call.authentication
            }
        }
    }
    //comeback and make into an embed Server afeter configuring the environments



@Suppress("unused") // Referenced in application.conf
fun Application.module(testing: Boolean = false) {
    install(AutoHeadResponse)

    install (Authentication) {
        oauth("IdentityServer4") {
            
        }
    }
    install(ConditionalHeaders)

    install(CORS) {
        method(HttpMethod.Options)
        method(HttpMethod.Put)
        method(HttpMethod.Delete)
        method(HttpMethod.Patch)
        header(HttpHeaders.Authorization)
        header("MyCustomHeader")
        allowCredentials = true
        anyHost() // @TODO: Don't do this in production if possible. Try to limit it.
    }

    install(DefaultHeaders) {
        header("X-Engine", "Ktor") // will send this header with each response
    }

    install(Authentication) {
    }

    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }

    val client = HttpClient(Jetty) {
    }

    routing {
        get("/") {
            call.respondText("HELLO WORLD!", contentType = ContentType.Text.Plain)
        }

        get("/json/jackson") {
            call.respond(mapOf("hello" to "world"))
        }
    }
}
}
