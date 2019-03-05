package com.tidelevel

import com.fasterxml.jackson.databind.SerializationFeature
import com.tidelevel.repository.CPTECHtmlParser
import com.tidelevel.repository.CPTECRepository
import com.tidelevel.service.TideLevelService
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.http.HttpStatusCode
import io.ktor.jackson.jackson
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.tomcat.Tomcat
import java.lang.Exception

fun main(args: Array<String>) {
    val server = embeddedServer(Tomcat, port = 8080) {
        mainModule()
    }

    server.start(wait = true)
}

fun Application.mainModule() {
    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }

    routing {
        get("/tidelevel") {
            val regionCode: String? = call.request.queryParameters["regionCode"]
            val month: String? = call.request.queryParameters["month"]
            val year: String? = call.request.queryParameters["year"]

            val cptecHtmlParser = CPTECHtmlParser()
            val cptecRepository = CPTECRepository()
            val tideLevelService = TideLevelService(cptecHtmlParser, cptecRepository)

            try {
                val tideDays = tideLevelService.fetchTideDays(regionCode, month, year)
                call.respond(HttpStatusCode.OK, tideDays)
            } catch (e: IllegalArgumentException) {
                call.respond(HttpStatusCode.BadRequest, e.message.orEmpty())
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, e.message.orEmpty())
            }
        }
    }
}

