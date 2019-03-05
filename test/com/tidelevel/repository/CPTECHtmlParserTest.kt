package com.tidelevel.repository

import com.tidelevel.models.TideLevel
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
        val document = Jsoup.connect("http://ondas.cptec.inpe.br/~rondas/mares/index.php?cod=30461&mes=01&ano=19").get()

        val tideDays = cptecHtmlParser.parseHTMLPageToTideDays(document, "01", "19")

        assertEquals(31, tideDays.size)
        assertEquals("01/01/19", tideDays.first().date)
        assertEquals(TideLevel("01:00", "2.0"), tideDays.first().levelList.first())
    }
}
