package com.example.alp_vp_dev1.data

import java.io.FileInputStream
import java.util.Properties

object PropertyLoader {
    private val properties = Properties()

    init {
        val inputStream = FileInputStream("secrets.properties")
        properties.load(inputStream)
        inputStream.close()
    }
    fun getProperty(key: String): String = properties.getProperty(key) ?: ""
}