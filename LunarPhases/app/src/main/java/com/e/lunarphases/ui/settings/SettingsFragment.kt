package com.e.lunarphases.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.e.lunarphases.MainActivity
import com.e.lunarphases.R
import kotlinx.android.synthetic.main.fragment_settings.*
import java.io.File


class SettingsFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_settings, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        readSetup()
        hemiGroup.setOnCheckedChangeListener { _, _ ->
            writeSetup()
        }
        algGroup.setOnCheckedChangeListener { _, _ ->
            writeSetup()
        }
    }
    private fun readSetup() {
        val file = context?.filesDir?.let { MainActivity().getFile(it) }
        val data = file?.readLines()
        data?.get(0)?.toInt().let { hemiGroup.check(it!!) }
        data?.get(1)?.toInt().let { algGroup.check(it!!) }
    }
    private fun writeSetup() {
        val setup = hemiGroup.checkedRadioButtonId.toString() + "\n" + algGroup.checkedRadioButtonId.toString()
        val file = context?.filesDir?.let { MainActivity().getFile(it) }
        file?.writeText(setup)
        Toast.makeText(activity, R.string.settings_toast, Toast.LENGTH_SHORT).show()
    }
//    hemiGroup
//    NorthernHemisphereRadioButton.isChecked = true
//    SouthernHemisphereRadioButton
//
//    algGroup
//    alg0RadioButton
//    alg1RadioButton
//    alg2RadioButton
//    alg3RadioButton.isChecked = true

}
