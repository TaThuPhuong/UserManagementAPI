package ttp.dev

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.tomcat.*
import ttp.dev.config.configureDatabase
import ttp.dev.config.configureRouting
import ttp.dev.config.configureSecurity
import ttp.dev.config.configureSerialization

fun main() {
        embeddedServer(
            Tomcat, port = 8081,
            host = "127.0.0.1",
            watchPaths = listOf("classes"), module = Application::module
        )
        .start(wait = true)
}

fun Application.module() {
    configureDatabase()
    configureSecurity()
    configureSerialization()
    configureRouting()
}
