package com.example.boxslasher

import android.util.Log

class NortController{
    val nort_list = mutableListOf<FloatArray>()
    var act = 50f

    fun Add_To_List(x:Float = 0.5f,y:Float = 0f,xspeed:Float = 50f,yspeed:Float = 50f,type:Float = 0f){//xはx座標 yはy座標 speedは進む速度 typeは関数のタイプ
        val nort = floatArrayOf(x,y,xspeed,yspeed,type)
        nort_list.add(nort)
    }
    fun saveValue(x:Float){
        Log.d("savevalue","savevalue now")
        act = x
    }
}