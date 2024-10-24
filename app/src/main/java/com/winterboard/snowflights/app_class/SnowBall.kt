package com.winterboard.snowflights.app_class

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.util.Log
import com.winterboard.snowflights.MainActivity
import kotlin.random.Random

class SnowBall(val context:Context,val canvas:Canvas,val resources: Bitmap,val pos_x:Int,val pos_y:Int,val win_x:Int,val win_y:Int){
    private var pos_X:Int
    private var pos_Y:Int

    init{
        this.pos_X = this.pos_x
        this.pos_Y = this.pos_y

    }
    fun move(change_x:Int,change_y:Int){

        if (this.pos_X <= -10){
            this.pos_X = this.win_x + 20
            (context as MainActivity).SS += 1
            Log.d("SCORE","score: "+(context as MainActivity).SS)
            val random = Random.nextInt(1,3)

            if (random == 1){
                this.pos_Y = this.win_y*4/5
            }else if (random == 2){
                this.pos_Y = this.win_y*4/8
            }

        }else{
            this.pos_X -= change_x
            this.pos_Y -= change_y
            canvas.drawBitmap(this.resources,this.pos_X.toFloat(),this.pos_Y.toFloat(),null)
        }
    }
    fun get_postition():Array<Int>{
        return arrayOf(this.pos_X,this.pos_Y)
    }
}