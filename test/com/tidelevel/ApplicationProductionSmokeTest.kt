package com.tidelevel

import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Ignore
import org.junit.Test

class ApplicationProductionSmokeTest {

    @Ignore("Figure out a way to deploy the server before test or use ktor-server-tests")
    @Test
    fun shouldFetchTideLevelsCorrectlyWithHttpStatus200WhenParametersAreCorrect() {
        given()
            .queryParam("regionCode", "30461")
            .queryParam("month", "01")
            .queryParam("year", "19")
        .`when`()
            .get("/tidelevel")
        .then()
            .statusCode(200)
            .body("date[0]", equalTo("01/01/19"))
            .body("date.size", equalTo(31))
    }

    @Ignore("Figure out a way to deploy the server before test or use ktor-server-tests")
    @Test
    fun shouldReturnHttpStatus400WhenParametersAreMissing() {
        given()
            .queryParam("regionCode", "")
            .queryParam("month", "")
            .queryParam("year", "")
        .`when`()
            .get("/tidelevel")
        .then()
            .statusCode(400)
    }
}