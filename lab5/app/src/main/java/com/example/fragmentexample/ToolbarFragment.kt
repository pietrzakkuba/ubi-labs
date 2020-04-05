package com.example.fragmentexample

import android.content.Context
import android.os.Bundle
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.toolbar_layout.*
import java.lang.ClassCastException
import java.text.FieldPosition

class ToolbarFragment: Fragment(), SeekBar.OnSeekBarChangeListener{

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.toolbar_layout, container, false)
        val seekBar: SeekBar? = view?.findViewById(R.id.seekBar1)
        val button: Button? = view?.findViewById(R.id.button1)
        seekBar?.setOnSeekBarChangeListener(this)
        button?.setOnLongClickListener { v: View -> buttonClicked(v) }
        return view
    }

    interface ToolbarListener {
        fun onButtonClick(position: Int, text: String)
    }

    var seekvalue = 10
    var activityCallback: ToolbarFragment.ToolbarListener? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try {
            activityCallback = context as ToolbarListener
        } catch (e: ClassCastException) {
            throw ClassCastException(context?.toString() + " musi implemetować interfejs")
        }
    }

    private fun buttonClicked(view: View) {
        activityCallback?.onButtonClick(seekvalue, editText1.text.toString())
    }

    override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
        seekvalue = progress
    }

    override fun onStartTrackingTouch(seekBar: SeekBar) {
    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
    }
}