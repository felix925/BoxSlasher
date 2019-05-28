package com.example.boxslasher

import android.view.SurfaceHolder
import android.graphics.*
import android.graphics.Bitmap
import android.util.Log
import android.view.SurfaceView
import java.util.*


open class SampleSurfaceHolder(
    private val _surface: SurfaceView,
    private val rig: Bitmap,
    private val ncon: NortController,
    private val lef: Bitmap,
    private val up: Bitmap,
    private val dow: Bitmap,
    private val waku: Bitmap) : SwipeGesture,SurfaceHolder.Callback, Runnable{

    //private val _migrationLength : Int = 50
    private val _holder: SurfaceHolder

    private var _thread: Thread? = null
    private var _isCancel = false
    var score = 0
    var judge = 0
    init {
        _holder = _surface.holder
        _holder.addCallback(this)
    }

    override fun onSwipe(direction: Int) {
        this.judge = direction
        Log.d("judge",judge.toString())
    }

    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
    }

    override fun surfaceDestroyed(holder: SurfaceHolder?) {
        _isCancel = true
        _thread = null
    }

    override fun surfaceCreated(holder: SurfaceHolder?) {
        _thread = Thread(this)
        _thread?.start()
    }
    override fun run() {
        var cnt = 0
        var buildtime = 0
        var randomInt = 0
        //val runner = Runner()

        while (!_isCancel) {
            val canvas = _holder.lockCanvas()
            //val bitmap = runner.createBitmap()
            var x = -rig.width
            var y = -rig.height
            val centerx = (_surface.width / 2 + x / 2).toFloat()
            val centery = (_surface.height/ 2 + y / 2).toFloat()
            val nlist = mutableListOf<Int>()
            var score = 0
            for(i in ncon.nort_list) {
                if ((_surface.width <= i[0])||(0 >= i[0])||(_surface.height <= i[1])||(0 >= i[1])){
                    //Log.d("death", "GameOver")

                }
                    //Log.d("add","add_posi_now")
                    i[0] +=  i[2]
                    i[1] +=  i[3]
            }
            val rand = Random()
            val randomint = rand.nextInt(100) + 1
            if(buildtime >= randomint + 20){
                //Log.d("random",randomInt.toString()
                randomInt += cnt
                if(randomInt % 4 == 0) {
                    Log.d("cnt",cnt.toString())
                    if(cnt % 4 == 0) {
                        ncon.Add_To_List(centerx, 1f, 0f, 10f, 0f)//down
                    }
                    if(cnt % 4 == 1) {
                        ncon.Add_To_List(_surface.width.toFloat(), centery, -10f, 0f, 0f)//left
                    }
                    if(cnt % 4 == 2) {
                        ncon.Add_To_List(0f, centery, 10f, 0f, 0f)//right
                    }
                    if(cnt % 4 == 3) {
                        ncon.Add_To_List(centerx, (_surface.width.toFloat()), 0f, -10f, 0f)//up
                    }
                    nlist.add(0)
                    randomInt ++
                }
                else if(randomInt % 4 == 1) {
                    if(cnt % 4 == 0) {
                        ncon.Add_To_List(centerx, 1f, 0f, 10f, 1f)//down
                    }
                    if(cnt % 4 == 1) {
                        ncon.Add_To_List(_surface.width.toFloat(), centery, -10f, 0f, 1f)//left
                    }
                    if(cnt % 4 == 2) {
                        ncon.Add_To_List(0f, centery, 10f, 0f, 1f)//right
                    }
                    if(cnt % 4 == 3) {
                        ncon.Add_To_List(centerx, (_surface.width.toFloat()), 0f, -10f, 1f)//up
                    }
                    nlist.add(1)
                    randomInt++
                }
                else if(randomInt % 4 == 2) {
                    if(cnt % 4 == 0) {
                        ncon.Add_To_List(centerx, 1f, 0f, 10f, 2f)//down
                    }
                    if(cnt % 4 == 1) {
                        ncon.Add_To_List(_surface.width.toFloat(), centery, -10f, 0f, 2f)//left
                    }
                    if(cnt % 4 == 2) {
                        ncon.Add_To_List(0f, centery, 10f, 0f, 2f)//right
                    }
                    if(cnt % 4 == 3) {
                        ncon.Add_To_List(centerx, (_surface.width.toFloat() - 0.0000000001f), 0f, -10f, 2f)//up
                    }
                    nlist.add(2)
                    randomInt++
                }
                else if(randomInt % 4 == 3) {
                    if(cnt % 4 == 0) {
                        ncon.Add_To_List(centerx, 1f, 0f, 10f, 3f)//down
                    }
                    if(cnt % 4 == 1) {
                        ncon.Add_To_List(_surface.width.toFloat(), centery, -10f, 0f, 3f)//left
                    }
                    if(cnt % 4 == 2) {
                        ncon.Add_To_List(0f, centery, 10f, 0f, 3f)//right
                    }
                    if(cnt % 4 == 3) {
                        ncon.Add_To_List(centerx, (_surface.width.toFloat() - 0.0000000001f), 0f, -10f, 3f)//up
                    }
                    nlist.add(3)
                    randomInt++
                }
                buildtime = 0
            }
            val paint = Paint()
            canvas.drawColor(Color.WHITE/*, PorterDuff.Mode.CLEAR*/)
            canvas.drawBitmap(waku, (_surface.width / 2 - waku.width / 2).toFloat(), (_surface.height / 2 - waku.height / 2).toFloat(), paint)
            for (i in ncon.nort_list) {
                if(i[4] == 0f) {
                    canvas.drawBitmap(rig, i[0], i[1], paint)
                }
                if(i[4] == 1f) {
                    canvas.drawBitmap(lef, i[0], i[1], paint)
                }
                if(i[4] == 2f) {
                    canvas.drawBitmap(up, i[0], i[1], paint)
                }
                if(i[4] == 3f) {
                    canvas.drawBitmap(dow, i[0], i[1], paint)
                }
            }

            //canvas.drawBitmap(rig,ncon.nort_list[0][0],ncon.nort_list[0][1],paint)
            _holder.unlockCanvasAndPost(canvas)
            Thread.sleep(20L)

            cnt++
            buildtime++
        }
    }
}
