package com.winterboard.snowflights.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.winterboard.snowflights.MainActivity
import com.winterboard.snowflights.R

class ScoreWIn : Fragment() {

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_score_w_in, container, false)

        (context as MainActivity).WINDOW = "Score"

        val score:TextView = view.findViewById(R.id.g_score)
        val back_button:ImageButton = view.findViewById(R.id.back_button)

        score.text = (context as MainActivity).SS.toString()

        back_button.setOnClickListener {
            (context as MainActivity).SS = 0
            (context as MainActivity).GAME_OWER = false
            (context as MainActivity).SPIER_UP = true
            (context as MainActivity).JAMPER = false
            to_fragment(Home())
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