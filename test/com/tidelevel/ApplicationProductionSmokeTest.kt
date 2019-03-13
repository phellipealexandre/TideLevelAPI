package com.tidelevel

import com.jayway.jsonpath.JsonPath
import io.ktor.application.Application
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.withTestApplication
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class ApplicationProductionSmokeTest {

    @Test
    fun shouldFetchTideLevelsCorrectlyWithHttpStatus200WhenParametersAreCorrect() {
        withTestApplication(Application::mainModule) {
            with(handleRequest(HttpMethod.Get, "/tidelevel?regionCode=30461&month=01&year=19")) {
                assertEquals(HttpStatusCode.OK, response.status())
                assertNotNull(response.content)
                assertEquals(31, JsonPath.parse(response.content).read<List<Any>>("$.tideDays").size)
                assertEquals("Porto de Natal-RN", JsonPath.parse(response.content).read<String>("$.region"))
                assertEquals("01/01/19", JsonPath.parse(response.content).read<String>("$.tideDays[0].date"))
                assertEquals(4, JsonPath.parse(response.content).read<List<Any>>("$.tideDays[0].levelList").size)
                assertEquals("01:00", JsonPath.parse(response.content).read<String>("$.tideDays[0].levelList[0].hour"))
                assertEquals("2.0", JsonPath.parse(response.content).read<String>("$.tideDays[0].levelList[0].level"))
            }
        }
    }

    @Test
    fun shouldReturnHttpStatus400WhenParametersAreMissing() {
        withTestApplication(Application::mainModule) {
            with(handleRequest(HttpMethod.Get, "/tidelevel?regionCode=&month=&year=")) {
                assertEquals(HttpStatusCode.BadRequest, response.status())
            }
        }
    }

    @Test
    fun shouldReturnHealthCheckOKWhenCallingServiceInTheRootPath() {
        withTestApplication(Application::mainModule) {
            with(handleRequest(HttpMethod.Get, "")) {
                assertEquals("Health check OK!", response.content)
            }
        }
    }
}