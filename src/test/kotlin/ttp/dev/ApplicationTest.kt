package ttp.dev

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.server.testing.*
import kotlin.test.*
import io.ktor.http.*
import ttp.dev.config.*

class ApplicationTest {
    @Test
    fun testRoot() = testApplication {
        application {
            configureRouting(environment.config)
        }
        client.get("/").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals("Hello World!", bodyAsText())
        }
    }
}
