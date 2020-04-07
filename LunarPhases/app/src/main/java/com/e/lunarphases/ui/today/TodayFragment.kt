package com.e.lunarphases.ui.today

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.e.lunarphases.Calculator
import com.e.lunarphases.R
import kotlinx.android.synthetic.main.fragment_today.*
import java.lang.Math.round
import java.util.*
import kotlin.math.roundToInt

@Suppress("DEPRECATION")
class TodayFragment : Fragment() {
    val calendar: Calendar = Calendar.getInstance()
    var year: Int = 0
    var month: Int = 0
    var day: Int = 0
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        year = calendar.get(Calendar.YEAR)
        month = calendar.get(Calendar.MONTH) + 1
        day = calendar.get(Calendar.DAY_OF_MONTH)
        val layout = R.layout.fragment_today
        return inflater.inflate(layout, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getToday()
        getPrevious()
        getNext()

    }
    private fun getToday() {
        val currentText = getString(R.string.today_today) + " " +  valueToPercent(Calculator().trig2(year, month, day)) + "%"
        currentTextView.text = currentText
    }
    private fun valueToPercent(value: Int) :Int{
            return (value / 30.0 * 100).roundToInt()
    }
    private fun previousValue(year: Int, month: Int, day: Int) :Date {
        val calendar: Calendar = Calendar.getInstance()
        var local_year = year
        var local_month = month
        var local_day = day
        var current_date = Date(local_year - 1900, local_month - 1, local_day)
        while (Calculator().trig2(local_year, local_month, local_day) != 0) {
            current_date = Date(current_date.time - 86400000)
            calendar.setTime(current_date)
            local_year = calendar.get(Calendar.YEAR)
            local_month = calendar.get(Calendar.MONTH) + 1
            local_day = calendar.get(Calendar.DAY_OF_MONTH)
        }
        return current_date
    }
    private fun nextValue(year: Int, month: Int, day: Int) :Date {
        val calendar: Calendar = Calendar.getInstance()
        var local_year = year
        var local_month = month
        var local_day = day
        var current_date = Date(local_year - 1900, local_month - 1, local_day)
        while (Calculator().trig2(local_year, local_month, local_day) != 15) {
            current_date = Date(current_date.time + 86400000)
            calendar.setTime(current_date)
            local_year = calendar.get(Calendar.YEAR)
            local_month = calendar.get(Calendar.MONTH) + 1
            local_day = calendar.get(Calendar.DAY_OF_MONTH)
        }
        return current_date
    }
    private fun getPrevious() {
        val date = previousValue(year, month, day)
        calendar.time = date
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val previousText = getString(R.string.today_previous) + " " + year + "-" + String.format("%02d", month) + "-" + String.format("%02d", day)
        previousNewMoonTextView.text = previousText
    }
    private fun getNext() {
        val date = nextValue(year, month, day)
        calendar.time = date
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val nextText = getString(R.string.today_next) + " " + year + "-" + String.format("%02d", month) + "-" + String.format("%02d", day)
        nextFullMoonTextView.text = nextText
    }
}
