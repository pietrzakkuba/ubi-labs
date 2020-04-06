package com.e.lunarphases

import java.util.*
import kotlin.math.floor


@Suppress("DEPRECATION")
class Calculator {
    fun simple(year: Int, month: Int, day: Int) :Int {
        val lp = 2551443
        val now = Date(year, month - 1, day, 20, 35, 0)
        val new_moon = Date(1970, 0, 7, 20, 35, 0)
        val phase = ((now.time - new_moon.time) / 1000.0) % lp
        return floor(phase / (24.0 * 3600.0)).toInt() + 2
    }
    fun conway(year: Int, month: Int, day: Int) :Int {
        var r : Double= (year % 100).toDouble()
        r %= 19.0
        if (r > 9.0)
            r -= 19.0
        r = ((r * 11.0) % 30.0) + month + day
        if (month < 3.0)
            r += 2.0
        if (year < 2000.0)
            r -= 4.0
        else
            r -= 8.3
        r = floor(r + 0.5) % 30
        if (r < 0)
            return (r + 30).toInt()
        return r.toInt()
    }
}

