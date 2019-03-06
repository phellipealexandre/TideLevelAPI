package com.tidelevel.repository

import com.tidelevel.models.TideDay
import com.tidelevel.models.TideLevel
import org.apache.commons.io.IOUtils
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.contains
import org.jsoup.Jsoup
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class CPTECHtmlParserTest {

    private lateinit var cptecHtmlParser: CPTECHtmlParser

    @Before
    fun setUp() {
        cptecHtmlParser = CPTECHtmlParser()
    }

    @Test
    fun shouldParseDocumentToTideDaysWithoutError() {
        val mockedHtml = IOUtils.toString(javaClass.getResourceAsStream("/responses/response_jan_19.html"), "UTF-8");
        val document = Jsoup.parseBodyFragment(mockedHtml)

        val tideDays = cptecHtmlParser.parseHTMLPageToTideDays(document, "01", "19")

        assertEquals(31, tideDays.size)
    }

    @Test
    fun shouldParseAllDaysInMonth() {
        val mockedHtml = IOUtils.toString(javaClass.getResourceAsStream("/responses/response_jan_19.html"), "UTF-8");
        val document = Jsoup.parseBodyFragment(mockedHtml)

        val tideDays: List<TideDay> = cptecHtmlParser.parseHTMLPageToTideDays(document, "01", "19")

        assertEquals("01/01/19", tideDays[0].date)
        assertEquals("02/01/19", tideDays[1].date)
        assertEquals("03/01/19", tideDays[2].date)
        assertEquals("04/01/19", tideDays[3].date)
        assertEquals("05/01/19", tideDays[4].date)
        assertEquals("06/01/19", tideDays[5].date)
        assertEquals("07/01/19", tideDays[6].date)
        assertEquals("08/01/19", tideDays[7].date)
        assertEquals("09/01/19", tideDays[8].date)
        assertEquals("10/01/19", tideDays[9].date)
        assertEquals("11/01/19", tideDays[10].date)
        assertEquals("12/01/19", tideDays[11].date)
        assertEquals("13/01/19", tideDays[12].date)
        assertEquals("14/01/19", tideDays[13].date)
        assertEquals("15/01/19", tideDays[14].date)
        assertEquals("16/01/19", tideDays[15].date)
        assertEquals("17/01/19", tideDays[16].date)
        assertEquals("18/01/19", tideDays[17].date)
        assertEquals("19/01/19", tideDays[18].date)
        assertEquals("20/01/19", tideDays[19].date)
        assertEquals("21/01/19", tideDays[20].date)
        assertEquals("22/01/19", tideDays[21].date)
        assertEquals("23/01/19", tideDays[22].date)
        assertEquals("24/01/19", tideDays[23].date)
        assertEquals("25/01/19", tideDays[24].date)
        assertEquals("26/01/19", tideDays[25].date)
        assertEquals("27/01/19", tideDays[26].date)
        assertEquals("28/01/19", tideDays[27].date)
        assertEquals("29/01/19", tideDays[28].date)
        assertEquals("30/01/19", tideDays[29].date)
        assertEquals("31/01/19", tideDays[30].date)
    }

    @Test
    fun shouldParseAllTideLevelsInDay() {
        val mockedHtml = IOUtils.toString(javaClass.getResourceAsStream("/responses/response_jan_19.html"), "UTF-8");
        val document = Jsoup.parseBodyFragment(mockedHtml)

        val tideDays: List<TideDay> = cptecHtmlParser.parseHTMLPageToTideDays(document, "01", "19")

        assertThat(tideDays.first().levelList, contains(
            TideLevel("01:00", "2.0"),
            TideLevel("07:00", "0.6"),
            TideLevel("13:30", "2.0"),
            TideLevel("19:30", "0.6")
        ))
    }
}
