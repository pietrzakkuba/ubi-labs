package com.e.lunarphases.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.e.lunarphases.R
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.fragment_settings, container, false)
        return view
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        hemiGroup
        NorthernHemisphereRadioButton.isChecked = true
        SouthernHemisphereRadioButton

        algGroup
        alg0RadioButton
        alg1RadioButton
        alg2RadioButton
        alg3RadioButton.isChecked = true


    }
}
