package com.winterboard.snowflights

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.winterboard.snowflights.fragments.Home

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    var IS_PAUSE = false
    var SPIER_UP:Boolean = true
    var JAMPER:Boolean = false
    var GAME_OWER:Boolean = false
    var BOARD_TYPE:Int = 1
    var SS:Int = 0
    var GAME_MUSIC = 1
    var WINDOW = "Main"
    lateinit var backgroud_music:MediaPlayer
    lateinit var main_frame_layout:FrameLayout
    @RequiresApi(Build.VERSION_CODES.R)
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        backgroud_music = MediaPlayer.create(this,R.raw.all_screen)
        backgroud_music.isLooping = true

        GAME_MUSIC = music_p("GAME_DATA","music")

        if (music_p("GAME_DATA","music") == 1){
            backgroud_music.start()
        }

        main_frame_layout = findViewById(R.id.main_frame_layout)

        full_screen()
        read_data("GAME_DATA","board_type")
        to_fragment()
    }

    private fun to_fragment() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(main_frame_layout.id,Home())
        fragmentTransaction.commit()
    }


    @Suppress("DEPRECATION")
    private fun full_screen(){
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        val decorView = window.decorView
        val uiOption = View.SYSTEM_UI_FLAG_FULLSCREEN or
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        decorView.systemUiVisibility = uiOption
    }

    private fun read_data(name:String,id:String){
        val sharedPreferences = getSharedPreferences(name, MODE_PRIVATE)
        val result = sharedPreferences.getInt(id,1)
        BOARD_TYPE = result
    }
    private fun music_p(name:String,id:String):Int{
        val sharedPreferences = getSharedPreferences(name, MODE_PRIVATE)
        val r = sharedPreferences.getInt(id,1)
        return r
    }
    override fun onPause() {
        super.onPause()
        if (::backgroud_music.isInitialized){
            if (music_p("GAME_DATA","music") == 1){
                backgroud_music.pause()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if (music_p("GAME_DATA","music") == 1){
            backgroud_music.start()
        }
    }

    @Deprecated("This method has been deprecated in favor of using the\n      {@link OnBackPressedDispatcher} via {@link #getOnBackPressedDispatcher()}.\n      The OnBackPressedDispatcher controls how back button events are dispatched\n      to one or more {@link OnBackPressedCallback} objects.")
    override fun onBackPressed() {

        if (WINDOW.hashCode() == "Main".hashCode()){
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Warning")
            builder.setMessage("Closed the app ?")
            builder.setPositiveButton("Yes"){_,_ ->
                super.onBackPressed()
            }
            builder.setNegativeButton("No"){dialog,_->
                dialog.dismiss()
            }
            val dialog = builder.create()
            dialog.show()
        }else if (WINDOW.hashCode() == "HOME".hashCode()){
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Warning")
            builder.setMessage("Closed the app ?")
            builder.setPositiveButton("Yes"){_,_ ->
                super.onBackPressed()
            }
            builder.setNegativeButton("No"){dialog,_->
                dialog.dismiss()
            }
            val dialog = builder.create()
            dialog.show()
        }else if (WINDOW.hashCode() == "Score".hashCode()){
            to_fragment()
        }else if (WINDOW.hashCode() == "Setting".hashCode()){
            to_fragment()
        }else if (WINDOW.hashCode() == "Boards".hashCode()){
            to_fragment()
        }
    }
}