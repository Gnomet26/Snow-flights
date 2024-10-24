package com.winterboard.snowflights.fragments

import android.annotation.SuppressLint
import android.app.AlertDialog.Builder
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.winterboard.snowflights.MainActivity
import com.winterboard.snowflights.R


@Suppress("DEPRECATION")
class GameWin : Fragment() {
    lateinit var back_button:ImageButton
    lateinit var skier_jump:ImageButton
    lateinit var score_text:TextView
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_game_win, container, false)

        score_text = view.findViewById(R.id.score_text)
        score_text.text = (context as MainActivity).SS.toString()

        val handler = Handler()
        val runnable = object : Runnable{
            override fun run() {
                score_text.text = (context as MainActivity).SS.toString()
                handler.postDelayed(this,1000/50)

                if ((context as MainActivity).GAME_OWER){
                    handler.removeCallbacks(this)
                    to_fragment(ScoreWIn())
                }
            }
        }
        handler.postDelayed(runnable,1000/100)

        back_button = view.findViewById(R.id.back_button)
        back_button.setOnClickListener {
            (context as MainActivity).IS_PAUSE = true
            val builder = Builder(context)
            builder.setTitle("Warning")
            builder.setMessage("Stop the game ?")
            builder.setCancelable(false)
            builder.setPositiveButton("Yes"){ _, _ ->
                to_fragment(ScoreWIn())
                handler.removeCallbacks(runnable)
            }
            builder.setNegativeButton("No"){dialog,_ ->
                (context as MainActivity).IS_PAUSE = false
                dialog.dismiss()
            }
            val dialog = builder.create()
            dialog.show()
        }

        skier_jump = view.findViewById(R.id.skier_jump)
        skier_jump.setOnClickListener {
            if ((context as MainActivity).SPIER_UP){
                skier_jump.rotation = 180f
                (context as MainActivity).JAMPER = true
            }else{
                skier_jump.rotation = 0f
                (context as MainActivity).JAMPER = true
            }
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