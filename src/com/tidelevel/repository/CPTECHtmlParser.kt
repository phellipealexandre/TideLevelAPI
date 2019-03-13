package com.tidelevel.repository

import com.tidelevel.models.TideDay
import com.tidelevel.models.TideLevel
import com.tidelevel.models.TideResponse
import dagger.Reusable
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import javax.inject.Inject

private const val TABLE_DATA_QUERY = "li[class$=dados]:has(strong)"
private const val REGION_NAME_QUERY = "div[class$=nome]"
private const val DATE_ELEMENT_QUERY = "strong"

@Reusable
class CPTECHtmlParser @Inject constructor() {

    fun parseHTMLPageToTideDays(doc: Document, actualMonth: String, actualYear: String): TideResponse {
        val regionName = doc.select(REGION_NAME_QUERY).first().ownText()
        val tideDays = doc.select(TABLE_DATA_QUERY).map {
            val day = it.select(DATE_ELEMENT_QUERY).first().ownText().split('/').first()
            val date = "$day/$actualMonth/$actualYear"
            val waveRecords = extractTideLevels(it)
            TideDay(date, waveRecords)
        }

        return TideResponse(regionName, tideDays)
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