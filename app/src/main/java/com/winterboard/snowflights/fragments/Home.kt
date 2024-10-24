package com.winterboard.snowflights.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import com.winterboard.snowflights.MainActivity
import com.winterboard.snowflights.R

class Home : Fragment() {
    lateinit var setting_button:ImageButton
    lateinit var start_button:ImageButton
    lateinit var boards_button:ImageButton
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        (context as MainActivity).WINDOW = "HOME"

        setting_button = view.findViewById(R.id.setting_button)
        start_button = view.findViewById(R.id.start_button)
        boards_button = view.findViewById(R.id.boards_button)

        setting_button.setOnClickListener {
            to_fragment(Setting())
        }
        start_button.setOnClickListener {
            to_fragment(GameWin())
        }
        boards_button.setOnClickListener {
            to_fragment(Boards())
        }
        return view
    }

    private fun to_fragment(fragment:Fragment){
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.main_frame_layout,fragment)
        fragmentTransaction.commit()
    }
}