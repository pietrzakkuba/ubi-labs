package com.e.lunarphases.ui.today

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.e.lunarphases.Calculator
import com.e.lunarphases.MainActivity
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
        val file = context?.filesDir?.let { MainActivity().getFile(it) }
        val data = file?.readLines()
        val hemisphere: Int? = (data?.get(0))?.toInt()
        val algorithm: Int? = (data?.get(1))?.toInt()
        hemisphere?.let { algorithm?.let { it1 -> loadImage(it, it1) } }
        algorithm?.let { getToday(it) }
        algorithm?.let { getPrevious(it) }
        algorithm?.let { getNext(it) }

    }
    private fun loadImage(hemi: Int, alg: Int) {
        val nList:Array<Int> = arrayOf(R.drawable.n0, R.drawable.n1, R.drawable.n2, R.drawable.n3, R.drawable.n4, R.drawable.n5, R.drawable.n6, R.drawable.n7, R.drawable.n8, R.drawable.n9, R.drawable.n10, R.drawable.n11, R.drawable.n12, R.drawable.n13, R.drawable.n14, R.drawable.n15, R.drawable.n16, R.drawable.n17, R.drawable.n18, R.drawable.n19, R.drawable.n20, R.drawable.n21, R.drawable.n22, R.drawable.n23, R.drawable.n24, R.drawable.n25, R.drawable.n26, R.drawable.n27, R.drawable.n28, R.drawable.n29)
        val sList:Array<Int> = arrayOf(R.drawable.s0, R.drawable.s1, R.drawable.s2, R.drawable.s3, R.drawable.s4, R.drawable.s5, R.drawable.s6, R.drawable.s7, R.drawable.s8, R.drawable.s9, R.drawable.s10, R.drawable.s11, R.drawable.s12, R.drawable.s13, R.drawable.s14, R.drawable.s15, R.drawable.s16, R.drawable.s17, R.drawable.s18, R.drawable.s19, R.drawable.s20, R.drawable.s21, R.drawable.s22, R.drawable.s23, R.drawable.s24, R.drawable.s25, R.drawable.s26, R.drawable.s27, R.drawable.s28, R.drawable.s29)
        var fList :Array<Int> = arrayOf()
        when (hemi) {
            2131230726 -> {fList = nList}
            2131230730 -> {fList = sList}
        }
        val value: Int = when (alg) {
            2131230788 -> (Calculator().simple(year, month, day) + 1) % 30
            2131230789 -> Calculator().conway(year, month, day)
            2131230790 -> Calculator().trig1(year, month, day)
            2131230791 -> Calculator().trig2(year, month, day)
            else -> 0
        }
        imageView.setImageResource(fList[value])

    }
    private fun getToday(alg: Int) {
        val currentText :String = when (alg) {
            2131230788 -> getString(R.string.today_today) + " " +  valueToPercent((Calculator().simple(year, month, day) + 1) % 30) + "%"
            2131230789 -> getString(R.string.today_today) + " " +  valueToPercent(Calculator().conway(year, month, day)) + "%"
            2131230790 -> getString(R.string.today_today) + " " +  valueToPercent(Calculator().trig1(year, month, day)) + "%"
            2131230791 -> getString(R.string.today_today) + " " +  valueToPercent(Calculator().trig2(year, month, day)) + "%"
            else -> "error"
        }

        currentTextView.text = currentText
    }
    private fun valueToPercent(value: Int) :Int{
            return (value / 30.0 * 100).roundToInt()
    }
    private fun previousValue(year: Int, month: Int, day: Int, alg: Int) :Date {
        val calendar: Calendar = Calendar.getInstance()
        var local_year = year
        var local_month = month
        var local_day = day
        var current_date = Date(local_year - 1900, local_month - 1, local_day)
        when (alg) {
            2131230788 -> {
                while (Calculator().simple(local_year, local_month, local_day) != 0) {
                    current_date = Date(current_date.time - 86400000)
                    calendar.setTime(current_date)
                    local_year = calendar.get(Calendar.YEAR)
                    local_month = calendar.get(Calendar.MONTH) + 1
                    local_day = calendar.get(Calendar.DAY_OF_MONTH)
                }
                current_date = Date(current_date.time - 86400000)
            }
            2131230789 -> {
                while (Calculator().conway(local_year, local_month, local_day) != 0) {
                    current_date = Date(current_date.time - 86400000)
                    calendar.setTime(current_date)
                    local_year = calendar.get(Calendar.YEAR)
                    local_month = calendar.get(Calendar.MONTH) + 1
                    local_day = calendar.get(Calendar.DAY_OF_MONTH)
                }
            }
            2131230790 -> {
                while (Calculator().trig1(local_year, local_month, local_day) != 0) {
                    current_date = Date(current_date.time - 86400000)
                    calendar.setTime(current_date)
                    local_year = calendar.get(Calendar.YEAR)
                    local_month = calendar.get(Calendar.MONTH) + 1
                    local_day = calendar.get(Calendar.DAY_OF_MONTH)
                }
            }
            2131230791 -> {
                while (Calculator().trig2(local_year, local_month, local_day) != 0) {
                    current_date = Date(current_date.time - 86400000)
                    calendar.setTime(current_date)
                    local_year = calendar.get(Calendar.YEAR)
                    local_month = calendar.get(Calendar.MONTH) + 1
                    local_day = calendar.get(Calendar.DAY_OF_MONTH)
                }
            }
        }
        return current_date
    }
    private fun nextValue(year: Int, month: Int, day: Int, alg: Int) :Date {
        val calendar: Calendar = Calendar.getInstance()
        var local_year = year
        var local_month = month
        var local_day = day
        var current_date = Date(local_year - 1900, local_month - 1, local_day)

        when (alg) {
            2131230788 -> {
                while (Calculator().simple(local_year, local_month, local_day) != 15) {
                    current_date = Date(current_date.time + 86400000)
                    calendar.setTime(current_date)
                    local_year = calendar.get(Calendar.YEAR)
                    local_month = calendar.get(Calendar.MONTH) + 1
                    local_day = calendar.get(Calendar.DAY_OF_MONTH)
                }
                current_date = Date(current_date.time - 86400000)
            }
            2131230789 -> {
                while (Calculator().conway(local_year, local_month, local_day) != 15) {
                    current_date = Date(current_date.time + 86400000)
                    calendar.setTime(current_date)
                    local_year = calendar.get(Calendar.YEAR)
                    local_month = calendar.get(Calendar.MONTH) + 1
                    local_day = calendar.get(Calendar.DAY_OF_MONTH)
                }
            }
            2131230790 -> {
                while (Calculator().trig1(local_year, local_month, local_day) != 15) {
                    current_date = Date(current_date.time + 86400000)
                    calendar.setTime(current_date)
                    local_year = calendar.get(Calendar.YEAR)
                    local_month = calendar.get(Calendar.MONTH) + 1
                    local_day = calendar.get(Calendar.DAY_OF_MONTH)
                }
            }
            2131230791 -> {
                while (Calculator().trig2(local_year, local_month, local_day) != 15) {
                    current_date = Date(current_date.time + 86400000)
                    calendar.setTime(current_date)
                    local_year = calendar.get(Calendar.YEAR)
                    local_month = calendar.get(Calendar.MONTH) + 1
                    local_day = calendar.get(Calendar.DAY_OF_MONTH)
                }
            }
        }
        return current_date
    }
    private fun getPrevious(alg: Int) {
        val date = previousValue(year, month, day, alg)
        calendar.time = date
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val previousText = getString(R.string.today_previous) + " " + year + "-" + String.format("%02d", month) + "-" + String.format("%02d", day)
        previousNewMoonTextView.text = previousText
    }
    private fun getNext(alg: Int) {
        val date = nextValue(year, month, day, alg)
        calendar.time = date
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val nextText = getString(R.string.today_next) + " " + year + "-" + String.format("%02d", month) + "-" + String.format("%02d", day)
        nextFullMoonTextView.text = nextText
    }
}
