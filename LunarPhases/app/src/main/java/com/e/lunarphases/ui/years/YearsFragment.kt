package com.e.lunarphases.ui.years

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.e.lunarphases.Calculator
import com.e.lunarphases.R
import kotlinx.android.synthetic.main.fragment_years.*
import java.util.*

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
        }

    }
    private fun next() {
        if (year < 2200) {
            year++
            showYear()
        }
    }

}
