package com.example.iainteracitvemovies

import java.io.InputStreamReader

/**
 * Created by Joao Betancourth on 23,enero,2022
 */
class MockResponseFileReader(path: String) {
    val content: String
    init {
        val reader = InputStreamReader(this.javaClass.classLoader!!.getResourceAsStream(path))
        content = reader.readText()
        reader.close()
    }
}