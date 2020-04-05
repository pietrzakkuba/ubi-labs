package com.example.fileaccess

import android.content.Context
import android.view.View
import android.widget.Toast
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.Exception
import java.util.*

class Contact {
    var name: String? = null
    var phone: String? = null

    constructor() {}

    constructor(name: String?, phone: String?) {
        this.name = name
        this.phone = phone
    }

    constructor(line: String?) {
        if (line != null) {
            val tokens = line.split(";")
            if (tokens.size == 2) {
                name = tokens[0]
                phone = tokens[1]
            }
        }
    }

    override fun toString(): String {
        return "Contact $name @ $phone"
    }

    fun toCSV(): String {
        return "$name;$phone\n"
    }

    fun saveClick(v: View) {
        val contacts = Arrays.asList(
            Contact("Darth Vader", "555328431"),
            Contact("Luke Skywalker", "555894766")
        )

        val filename = "contacts.csv"
        val file = OutputStreamWriter(OpenFileOutput(filename, Context.MODE_PRIVATE))

        for (c in contacts) {
            file.write(c.toCSV())
        }
        file.flush()
        file.close()
        Toast.makeText(this, "Plik został zapisany", Toast.LENGTH_LONG).show()
    }

    private fun FileExists(path: String): Boolean {
        val file = baseContext.getFileStreamPath(path)
        return file.exists()
    }

    fun readClick(v: View) {
        val contacts = ArrayList<Contact>()
        try {
            val filename = "contacts.csv"
            if (FileExists(filename)) {
                val file = InputStreamReader(openFileInput(filename))
                val br = BufferedReader(file)

                var line = br.readLine()
                while (line != null) {
                    contacts.add(Contact(line))
                    line = br.readLine()
                }
                file.close()
                val count = contacts.size
                Toast.makeText(this, "Wczytano $count kontaktów", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Nie znaleziono pliku", Toast.LENGTH_LONG).show()
            }

        } catch (e: Exception) {

        }
    }
}