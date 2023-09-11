package ttp.dev.config

import com.typesafe.config.ConfigFactory
import io.ktor.server.config.*
import ttp.dev.repository.DatabaseFactory

fun configureDatabase() {
    val config = HoconApplicationConfig(ConfigFactory.load())
    DatabaseFactory.init(config)
}