package com.tidelevel.repository

import com.tidelevel.models.TideDay
import com.tidelevel.models.TideLevel
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

private const val TABLE_DATA_QUERY = "li[class$=dados]:has(strong)"
private const val DATE_ELEMENT_QUERY = "strong"

class CPTECHtmlParser {

    fun parseHTMLPageToTideDays(doc: Document, actualMonth: String, actualYear: String): List<TideDay> {
        return doc.select(TABLE_DATA_QUERY).map {
            val day = it.select(DATE_ELEMENT_QUERY).first().ownText().split('/').first()
            val date = "$day/$actualMonth/$actualYear"
            val waveRecords = extractTideLevels(it)
            TideDay(date, waveRecords)
        }
    }

    private fun extractTideLevels(levelData: Element): List<TideLevel> {
        val rawLevelRecordsArray = levelData.ownText().replace("\u00a0", "").split(" ")

        val tideLevels = mutableListOf<TideLevel>()
        for (i in 0 until rawLevelRecordsArray.size step 2) {
            val tideLevel = TideLevel(hour = rawLevelRecordsArray[i], level = rawLevelRecordsArray[i+1])
            tideLevels.add(tideLevel)
        }

        return tideLevels.toList()
    }
}