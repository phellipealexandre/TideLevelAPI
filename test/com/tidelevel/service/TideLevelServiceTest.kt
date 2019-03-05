package com.tidelevel.service

import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.tidelevel.repository.CPTECHtmlParser
import com.tidelevel.repository.CPTECRepository
import org.jsoup.nodes.Document
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TideLevelServiceTest {

    private lateinit var tideLevelService: TideLevelService

    @Mock
    private lateinit var cptecRepository: CPTECRepository

    @Mock
    private lateinit var cptecHtmlParser: CPTECHtmlParser

    @Mock
    private lateinit var document: Document

    @Before
    fun setUp() {
        tideLevelService = TideLevelService(cptecHtmlParser, cptecRepository)
    }

    @Test(expected = IllegalArgumentException::class)
    fun shouldExpectIllegalArgumentExceptionWhenRegionCodeIsNull() {
        tideLevelService.fetchTideDays(null, "02", "19")
    }

    @Test(expected = IllegalArgumentException::class)
    fun shouldExpectIllegalArgumentExceptionWhenRegionCodeIsBlank() {
        tideLevelService.fetchTideDays(" ", "02", "19")
    }

    @Test(expected = IllegalArgumentException::class)
    fun shouldExpectIllegalArgumentExceptionWhenMonthIsNull() {
        tideLevelService.fetchTideDays("123", null, "19")
    }

    @Test(expected = IllegalArgumentException::class)
    fun shouldExpectIllegalArgumentExceptionWhenMonthIsBlank() {
        tideLevelService.fetchTideDays("123", " ", "19")
    }

    @Test(expected = IllegalArgumentException::class)
    fun shouldExpectIllegalArgumentExceptionWhenYearIsNull() {
        tideLevelService.fetchTideDays("123", "03", null)
    }

    @Test(expected = IllegalArgumentException::class)
    fun shouldExpectIllegalArgumentExceptionWhenYearIsBlank() {
        tideLevelService.fetchTideDays("123", "03", " ")
    }

    @Test
    fun shouldParseTideDaysWithGivenDocumentWhenParametersAreValid() {
        whenever(cptecRepository.fetchDocument("123", "01", "19")).thenReturn(document)

        tideLevelService.fetchTideDays("123", "01", "19")

        verify(cptecHtmlParser).parseHTMLPageToTideDays(document, "01", "19")
    }
}