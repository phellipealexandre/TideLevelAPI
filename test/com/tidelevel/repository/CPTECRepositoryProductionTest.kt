package com.tidelevel.repository

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class CPTECRepositoryProductionTest {

    private lateinit var cptecRepository: CPTECRepository

    @Before
    fun setUp() {
        cptecRepository = CPTECRepository()
    }

    @Test
    fun shouldFetchDocumentFromProductionServer() {
        val document = cptecRepository.fetchDocument("30461", "05", "19")

        val elements = document.select("li[class\$=dados]:has(strong)")
        val unformattedFirstDayDate = elements.first().select("strong").first().ownText()

        assertEquals(31, elements.size)
        assertEquals("01/5/", unformattedFirstDayDate)
    }
}