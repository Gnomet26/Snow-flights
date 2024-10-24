package com.winterboard.snowflights.fragments

import android.annotation.SuppressLint
import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import com.winterboard.snowflights.MainActivity
import com.winterboard.snowflights.R

class Setting : Fragment() {

    lateinit var back_button:ImageButton
    lateinit var sound_switch:ImageButton

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_setting, container, false)

        (context as MainActivity).WINDOW = "Setting"

        back_button = view.findViewById(R.id.setting_button)
        back_button.setOnClickListener {
            to_fragment(Home())
        }

        sound_switch = view.findViewById(R.id.sound_switch)

        if (music_p("GAME_DATA","music") == 1){
            sound_switch.setBackgroundResource(R.drawable.img_12)
        }else if (music_p("GAME_DATA","music") == 0){
            sound_switch.setBackgroundResource(R.drawable.img_13)
        }

        sound_switch.setOnClickListener {
            if (music_p("GAME_DATA","music") == 1){
                (context as MainActivity).backgroud_music.pause()
                sound_switch.setBackgroundResource(R.drawable.img_13)
                save_music("GAME_DATA","music",0)
            }else if (music_p("GAME_DATA","music") == 0){
                sound_switch.setBackgroundResource(R.drawable.img_12)
                (context as MainActivity).backgroud_music.start()
                save_music("GAME_DATA","music",1)
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

    private fun music_p(name:String,id:String):Int{
        val sharedPreferences = requireActivity().getSharedPreferences(name, MODE_PRIVATE)
        val r = sharedPreferences.getInt(id,1)
        return r
    }
    private fun save_music(name:String,id:String,v:Int){
        val sharedPreferences = requireActivity().getSharedPreferences(name, MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt(id,v)
        editor.apply()
    }
}