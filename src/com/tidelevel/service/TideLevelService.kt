package com.tidelevel.service

import com.tidelevel.models.TideDay
import com.tidelevel.models.TideResponse
import com.tidelevel.repository.CPTECHtmlParser
import com.tidelevel.repository.CPTECRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class TideLevelService @Inject constructor(private val parser: CPTECHtmlParser, private val repository: CPTECRepository) {

    fun fetchTideDays(regionCode: String?, month: String?, year: String?): TideResponse {
        validateArguments(regionCode, month, year)

        val document = repository.fetchDocument(regionCode.toString(), month.toString(), year.toString())
        return parser.parseHTMLPageToTideDays(document, month.toString(), year.toString())
    }

    private fun validateArguments(regionCode: String?, month: String?, year: String?) {
        when {
            regionCode == null || regionCode.isBlank() -> throw IllegalArgumentException("Please, provide a region code in the GET parameter")
            month == null || month.isBlank() -> throw IllegalArgumentException("Please, provide a month number between 1-12 in the GET parameter")
            year == null || year.isBlank() -> throw IllegalArgumentException("Please, provide a the year with 2 digits in the GET parameter")
        }
    }
}