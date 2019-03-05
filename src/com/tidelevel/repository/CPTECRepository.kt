package com.tidelevel.repository

import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class CPTECRepository {

    fun fetchDocument(regionCode: String, month: String, year: String): Document {
        val url = "http://ondas.cptec.inpe.br/~rondas/mares/index.php?cod=$regionCode&mes=$month&ano=$year"
        return Jsoup.connect(url).get()
    }
}