package mx.edu.ittepic.ladm_u2_practica2_propuesta

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.view.MotionEvent
import android.view.View

class Lienzo(a:Context):View(a) {
    var suit1 = Figura(this,R.drawable.suit,100f,100f,"Test1")
    var suit2 = Figura(this,R.drawable.suit,500f,500f,"Test2")
    var punteroFigura:Figura?=null

    override fun onDraw(c: Canvas) {
        super.onDraw(c)
        suit1.pintar(c)
        suit2.pintar(c)

    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {

                if(suit1.detArea(event.x,event.y)){
                    punteroFigura=suit1
                }

            }
            MotionEvent.ACTION_MOVE -> {
                if (punteroFigura!=null){
                    punteroFigura!!.mover(event.x,event.y)

                    if(punteroFigura==suit1){
                        if(suit1.col(suit2)) suit2.invisible=true
                    }
                }
            }
            MotionEvent.ACTION_UP -> {
                punteroFigura=null
                suit2.invisible=false
                suit2.x=500f
                suit2.y=500f
            }
        }

        invalidate()
        return true
    }
}