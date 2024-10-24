package com.winterboard.snowflights.app_class

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import com.winterboard.snowflights.MainActivity

class Skier(val context:Context,val canvas: Canvas,val resources: Bitmap,val pos_x:Int,val pos_y:Int,val win_x:Int,val win_y:Int) {
    private var pos_X:Int
    private var pos_Y:Int
    init{
        this.pos_X = this.pos_x
        this.pos_Y = this.pos_y
    }
    fun jump(change_x:Int,change_y:Int){
        if ((this.context as MainActivity).JAMPER){
            if (this.context.SPIER_UP){
                if (this.pos_Y <= (this.win_y/2 - 20)){
                    this.context.JAMPER = false
                    this.context.SPIER_UP = false
                }else{
                    this.pos_X -= change_x
                    this.pos_Y -= change_y
                }

            }else{
                if (this.pos_Y >= (this.win_y*3/4 - 20)){
                    this.context.JAMPER = false
                    this.context.SPIER_UP = true

                }else{
                    this.pos_X += change_x
                    this.pos_Y += change_y
                }
            }
        }
        canvas.drawBitmap(this.resources,this.pos_X.toFloat(),this.pos_Y.toFloat(),null)
    }
    fun get_postition():Array<Int>{
        return arrayOf(this.pos_X,this.pos_Y)
    }
}