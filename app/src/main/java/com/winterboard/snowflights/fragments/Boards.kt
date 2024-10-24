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

class Boards : Fragment() {

    lateinit var back_button:ImageButton

    lateinit var board_1:ImageButton
    lateinit var board_2:ImageButton
    lateinit var board_3:ImageButton
    lateinit var board_4:ImageButton

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_boards, container, false)

        (context as MainActivity).WINDOW = "Boards"

        back_button = view.findViewById(R.id.back_button)
        back_button.setOnClickListener {
            to_fragment(Home())
        }

        board_1 = view.findViewById(R.id.board_1)
        board_2 = view.findViewById(R.id.board_2)
        board_3 = view.findViewById(R.id.board_3)
        board_4 = view.findViewById(R.id.board_4)

        if (read_data("GAME_DATA","board_type") == 1){
            board_1.setBackgroundResource(R.drawable.img_8)
            board_2.setBackgroundResource(R.drawable.img_5)
            board_3.setBackgroundResource(R.drawable.img_6)
            board_4.setBackgroundResource(R.drawable.img_7)

        }else if(read_data("GAME_DATA","board_type") == 2){
            board_1.setBackgroundResource(R.drawable.img_4)
            board_2.setBackgroundResource(R.drawable.img_9)
            board_3.setBackgroundResource(R.drawable.img_6)
            board_4.setBackgroundResource(R.drawable.img_7)
        }else if(read_data("GAME_DATA","board_type") == 3){
            board_1.setBackgroundResource(R.drawable.img_4)
            board_2.setBackgroundResource(R.drawable.img_5)
            board_3.setBackgroundResource(R.drawable.img_10)
            board_4.setBackgroundResource(R.drawable.img_7)

        }else if(read_data("GAME_DATA","board_type") == 4){
            board_1.setBackgroundResource(R.drawable.img_4)
            board_2.setBackgroundResource(R.drawable.img_5)
            board_3.setBackgroundResource(R.drawable.img_6)
            board_4.setBackgroundResource(R.drawable.img_11)
        }

        board_1.setOnClickListener {
            board_1.setBackgroundResource(R.drawable.img_8)
            board_2.setBackgroundResource(R.drawable.img_5)
            board_3.setBackgroundResource(R.drawable.img_6)
            board_4.setBackgroundResource(R.drawable.img_7)
            (requireContext() as MainActivity).BOARD_TYPE = 1
            save_data("GAME_DATA","board_type",1)

        }
        board_2.setOnClickListener {
            board_1.setBackgroundResource(R.drawable.img_4)
            board_2.setBackgroundResource(R.drawable.img_9)
            board_3.setBackgroundResource(R.drawable.img_6)
            board_4.setBackgroundResource(R.drawable.img_7)
            (requireContext() as MainActivity).BOARD_TYPE = 2
            save_data("GAME_DATA","board_type",2)

        }
        board_3.setOnClickListener {
            board_1.setBackgroundResource(R.drawable.img_4)
            board_2.setBackgroundResource(R.drawable.img_5)
            board_3.setBackgroundResource(R.drawable.img_10)
            board_4.setBackgroundResource(R.drawable.img_7)
            (requireContext() as MainActivity).BOARD_TYPE = 3
            save_data("GAME_DATA","board_type",3)

        }
        board_4.setOnClickListener {
            board_1.setBackgroundResource(R.drawable.img_4)
            board_2.setBackgroundResource(R.drawable.img_5)
            board_3.setBackgroundResource(R.drawable.img_6)
            board_4.setBackgroundResource(R.drawable.img_11)
            (requireContext() as MainActivity).BOARD_TYPE = 4
            save_data("GAME_DATA","board_type",4)
        }


        return view
    }
    private fun to_fragment(fragment:Fragment){
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.main_frame_layout,fragment)
        fragmentTransaction.commit()
    }
    private fun save_data(name:String,id:String,value:Int){
        val sharedPreferences = requireActivity().getSharedPreferences(name,MODE_PRIVATE)
        val editro = sharedPreferences.edit()
        editro.putInt(id,value)
        editro.apply()
    }
    private fun read_data(name:String,id:String):Int{
        val sharedPreferences = requireActivity().getSharedPreferences(name, MODE_PRIVATE)
        val result = sharedPreferences.getInt(id,1)
        return result
    }
}