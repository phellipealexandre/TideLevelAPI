package com.tidelevel.repository

import dagger.Reusable
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import javax.inject.Inject

@Reusable
class CPTECRepository @Inject constructor() {

    fun fetchDocument(regionCode: String, month: String, year: String): Document {
        val url = "http://ondas.cptec.inpe.br/~rondas/mares/index.php?cod=$regionCode&mes=$month&ano=$year"
        return Jsoup.connect(url).get()
    }
}