package com.example.boxslasher

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.SurfaceView
import android.graphics.BitmapFactory

class MainActivity : AppCompatActivity(){

    private var mainSurfaceView : SampleSurfaceHolder? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.event_get)
    }
    override fun onResume(){
        super.onResume()
        val ncon = NortController()
        val r = resources
        val rig = BitmapFactory.decodeResource(r, R.drawable.nort_rig)
        val r2 = resources
        val lef = BitmapFactory.decodeResource(r2, R.drawable.nort_lef)
        val r3 = resources
        val up = BitmapFactory.decodeResource(r3, R.drawable.nort_up)
        val r4 = resources
        val dow = BitmapFactory.decodeResource(r4, R.drawable.nort_dow)
        val r5 = resources
        val waku = BitmapFactory.decodeResource(r5, R.drawable.waku)
        val surfaceView = findViewById<SurfaceView>(R.id.surface_disp)
        mainSurfaceView = SampleSurfaceHolder(surfaceView,rig,ncon,lef,up,dow,waku)
    }

}
