package com.example.boxslasher

import android.content.Context
import android.view.GestureDetector
import android.view.SurfaceView
import android.view.View
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent





class GestureSurfaceView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0):SurfaceView(context, attrs, defStyleAttr), View.OnTouchListener, GestureDetector.OnGestureListener{
    private val SWIPE_MIN_DISTANCE = 50
    // Y軸最低スワイプスピード
    private val SWIPE_THRESHOLD_VELOCITY = 200
    // X軸の移動距離 これ以上なら縦移動を判定しない
    private val SWIPE_MAX_OFF_PATH = 200

    private lateinit var gesture:GestureDetector
    init{
        gesture = GestureDetector(context,this)
        setOnTouchListener(this)
    }
    var callback: SwipeGesture? = null
    var dir :Int = 10
    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
            return if (event?.pointerCount == 1)  gesture.onTouchEvent(event) else true
    }

    override fun onShowPress(e: MotionEvent?) {
    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {
        return false
    }

    override fun onDown(e: MotionEvent?): Boolean {
        Log.d("down","down now")
        return true
    }

    override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
        Log.d("swipe_x",velocityX.toString())
        Log.d("swipe_y",velocityY.toString())
        val distance_y = Math.abs(e1!!.y - e2!!.y)
        val velocity_y = Math.abs(velocityY)
        Log.d("onFling", "縦移動距離:$distance_y 縦の移動スピード:$velocity_y" )

        // X軸の移動距離が大きすぎる場合
        if (e1.x > e2.x && distance_y > SWIPE_MIN_DISTANCE) {
            Log.d("onFling", "左")

            // 開始位置から終了位置の移動距離が指定値より大きい
            // Y軸の移動速度が指定値より大きい
        }else if (e2.x > e1.x&& distance_y > SWIPE_MIN_DISTANCE) {
            Log.d("onFling","右")

            // 終了位置から開始位置の移動距離が指定値より大きい
            // Y軸の移動速度が指定値より大きい
        }else if (e2.y < e1.y) {
            Log.d("onFling","上から下")

            // 終了位置から開始位置の移動距離が指定値より大きい
            // Y軸の移動速度が指定値より大きい
        } else if (e1.y  < e2.y) {
            Log.d("onFling","下から上")
        }
        return false//ここにスワイプの値が返ってくる
    }

    override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
        return false
    }

    override fun onLongPress(e: MotionEvent?) {
    }

    fun onSwipe(direction:Int){
        callback?.onSwipe(direction)
    }
}