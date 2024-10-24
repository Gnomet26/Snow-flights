package com.winterboard.snowflights.app_class

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.os.Handler
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.View
import com.winterboard.snowflights.MainActivity
import com.winterboard.snowflights.R
import kotlin.random.Random

@Suppress("DEPRECATION")
class GameFrame(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    val win_x:Int
    val win_y:Int

    //========================= siker ==================
    var helper_s_x:Int = 100
    var helper_s_y:Int = 0
    //==================================================

    //========================== ball_1 ================
    var random_1:Int = 0
    var helper_1_x:Int = 0
    var helper_1_y:Int = 0
    //==================================================

    //========================== ball_2 ================
    var random_2:Int = 0
    var helper_2_x:Int = 0
    var helper_2_y:Int = 0
    //==================================================

    //========================== ball_3 ================
    var random_3:Int = 0
    var helper_3_x:Int = 0
    var helper_3_y:Int = 0
    //==================================================

    var SPEED:Int = 15

    lateinit var skier:Skier
    lateinit var scaleBitmap:Bitmap

    lateinit var ball1:SnowBall
    lateinit var ball2:SnowBall
    lateinit var ball3:SnowBall
    var ball_img:Bitmap

    init {
        val metrix = DisplayMetrics()
        (context as Activity).windowManager.defaultDisplay.getMetrics(metrix)
        win_x = metrix.widthPixels
        win_y = metrix.heightPixels

        helper_s_y = (win_y*3/4) - 20

        val bitmap = BitmapFactory.decodeResource(this.resources, R.drawable.img_20)
        ball_img = Bitmap.createScaledBitmap(bitmap,120,120,true)

        if ((context as MainActivity).BOARD_TYPE == 1){
            val bit_map = BitmapFactory.decodeResource(this.resources, R.drawable.img_17)
            scaleBitmap = Bitmap.createScaledBitmap(bit_map,120,160,true)
        }else if (context.BOARD_TYPE == 2){
            val bit_map = BitmapFactory.decodeResource(this.resources, R.drawable.img_3)
            scaleBitmap = Bitmap.createScaledBitmap(bit_map,120,160,true)
        }else if (context.BOARD_TYPE == 3){
            val bit_map = BitmapFactory.decodeResource(this.resources, R.drawable.img_18)
            scaleBitmap = Bitmap.createScaledBitmap(bit_map,120,160,true)
        }else if (context.BOARD_TYPE == 4){
            val bit_map = BitmapFactory.decodeResource(this.resources, R.drawable.img_19)
            scaleBitmap = Bitmap.createScaledBitmap(bit_map,120,160,true)
        }

        //=============================== for ball_1 =========================
        val random = Random.nextInt(1,3)
        if (random == 1){
            random_1 = 5
            helper_1_y = win_y*4/random_1
        }else if (random == 2){
            random_1 = 8
            helper_1_y = win_y*4/random_1
        }
        helper_1_x = win_x + 20
        // ====================================================================

        //=============================== for ball_2 =========================
        val random2 = Random.nextInt(1,3)
        if (random2 == 1){
            random_2 = 5
            helper_2_y = win_y*4/random_2
        }else if (random2 == 2){
            random_2 = 8
            helper_2_y = win_y*4/random_2
        }
        helper_2_x = win_x + 620
        //=====================================================================

        //=============================== for ball_3 =========================
        val random3 = Random.nextInt(1,3)
        if (random3 == 1){
            random_3 = 5
            helper_3_y = win_y*4/random_3
        }else if (random3 == 2){
            random_3 = 8
            helper_3_y = win_y*4/random_3
        }
        helper_3_x = win_x + 1220
        // ====================================================================
    }
    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        //============================== Skier =====================================
        skier = Skier(context,canvas,scaleBitmap,helper_s_x,helper_s_y,win_x,win_y)
        skier.jump(0,SPEED+10)
        helper_s_x = skier.get_postition().get(0)
        helper_s_y = skier.get_postition().get(1)
        //==========================================================================

        //============================== Snow_1 =====================================
        ball1 = SnowBall(context,canvas,ball_img,helper_1_x,helper_1_y,win_x,win_y)
        ball1.move(SPEED,0)
        helper_1_x = ball1.get_postition().get(0)
        helper_1_y = ball1.get_postition().get(1)
        //===========================================================================

        //============================== Snow_2 =====================================
        ball2 = SnowBall(context,canvas,ball_img,helper_2_x,helper_2_y,win_x,win_y)
        ball2.move(SPEED,0)
        helper_2_x = ball2.get_postition().get(0)
        helper_2_y = ball2.get_postition().get(1)
        //===========================================================================

        //============================== Snow_3 =====================================
        ball3 = SnowBall(context,canvas,ball_img,helper_3_x,helper_3_y,win_x,win_y)
        ball3.move(SPEED,0)
        helper_3_x = ball3.get_postition().get(0)
        helper_3_y = ball3.get_postition().get(1)
        //===========================================================================
        val handler = Handler()
        val runnable = object : Runnable{
            override fun run(){
                if (!(context as MainActivity).IS_PAUSE){
                    invalidate()
                }else{
                    handler.post(this)
                }
            }
        }
        val chegara = 100
        if ((((ball1.get_postition().get(0)-chegara) <= (skier.get_postition().get(0))) and ((ball1.get_postition().get(0)+chegara) >= (skier.get_postition().get(0)))) and (((ball1.get_postition().get(1)-chegara) <= (skier.get_postition().get(1))) and ((ball1.get_postition().get(1)+chegara) >= (skier.get_postition().get(1)))))
        {
            (context as MainActivity).GAME_OWER = true
        }
        if ((((ball2.get_postition().get(0)-chegara) <= (skier.get_postition().get(0))) and ((ball2.get_postition().get(0)+chegara) >= (skier.get_postition().get(0)))) and (((ball2.get_postition().get(1)-chegara) <= (skier.get_postition().get(1))) and ((ball2.get_postition().get(1)+chegara) >= (skier.get_postition().get(1)))))
        {
            (context as MainActivity).GAME_OWER = true
        }
        if ((((ball3.get_postition().get(0)-chegara) <= (skier.get_postition().get(0))) and ((ball3.get_postition().get(0)+chegara) >= (skier.get_postition().get(0)))) and (((ball3.get_postition().get(1)-chegara) <= (skier.get_postition().get(1))) and ((ball3.get_postition().get(1)+chegara) >= (skier.get_postition().get(1)))))
        {
            (context as MainActivity).GAME_OWER = true
        }

        handler.postDelayed(runnable,1000/300)
    }
}