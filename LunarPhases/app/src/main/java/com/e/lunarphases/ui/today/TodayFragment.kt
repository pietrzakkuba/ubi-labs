package com.e.lunarphases.ui.today

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.e.lunarphases.R
import kotlinx.android.synthetic.main.fragment_today.*
class TodayFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = R.layout.fragment_today
        return inflater.inflate(layout, container, false)
    }
}
