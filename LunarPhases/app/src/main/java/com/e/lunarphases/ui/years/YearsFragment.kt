package com.e.lunarphases.ui.years

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.e.lunarphases.Calculator
import com.e.lunarphases.R
import kotlinx.android.synthetic.main.fragment_years.*
import java.util.*

@Suppress("DEPRECATION")
class YearsFragment : Fragment() {
    private var year : Int = 0
    private var year_name : String = ""
    private var year_text : String = ""
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        year = Calendar.getInstance().get(Calendar.YEAR)
        year_name = getString(R.string.years_year)
        return inflater.inflate(R.layout.fragment_years, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        showYear()
        calc(year)
        previousYearButton.setOnClickListener {
            previous()
        }
        nextYearButton.setOnClickListener {
            next()
        }
    }

    private fun showYear() {
        year_text = "$year_name $year"
        yearTextView.text = year_text
    }
    private fun previous() {
        if (year > 1900) {
            year--
            showYear()
            calc(year)
        }

    }
    private fun next() {
        if (year < 2200) {
            year++
            showYear()
            calc(year)
        }
    }
    private fun calc(year: Int) {
        val list = listOf<TextView>(fullMoon1, fullMoon2, fullMoon3, fullMoon4, fullMoon5, fullMoon6, fullMoon7, fullMoon8, fullMoon9, fullMoon10, fullMoon11, fullMoon12, fullMoon13)
        val list2 = listOf(getString(R.string.t1), getString(R.string.t2), getString(R.string.t3), getString(R.string.t4), getString(R.string.t5), getString(R.string.t6), getString(R.string.t7), getString(R.string.t8), getString(R.string.t9), getString(R.string.t10), getString(R.string.t11), getString(R.string.t12), getString(R.string.t13))
        val calendar: Calendar = Calendar.getInstance()
        var local_year: Int
        var local_month: Int
        var local_day: Int
        var current_date = Date(year - 1900, 0, 1)
        var i = 0
        while (current_date != Date(year - 1900, 11, 31)) {
            calendar.time = current_date
            local_year = calendar.get(Calendar.YEAR)
            local_month = calendar.get(Calendar.MONTH) + 1
            local_day = calendar.get(Calendar.DAY_OF_MONTH)
            if (Calculator().trig2(local_year, local_month, local_day) == 15) {
                val text = list2[i] + " " + local_year + "-" + String.format("%02d", local_month) + "-" + String.format("%02d", local_day)
                list[i].text = text
                i++
            }
            current_date = Date(current_date.time + 86400000)
        }
        if (i < 13) {
            list[12].visibility = View.GONE
        } else {
            list[12].visibility = View.VISIBLE
        }
    }
}
