package mx.edu.ittepic.ladm_u2_practica2_propuesta

import android.graphics.Canvas
import android.view.MotionEvent
import android.view.View
import kotlin.random.Random

class LienzoJ1(j:Juego1):View(j) {
    var Prin = Figura(this,R.drawable.n1,100f,100f,"Principal")
    var suit2 = Figura(this,R.drawable.n2,500f,500f,"Incorrecto")
    var pos=1
    var punteroFigura:Figura?=null
    var naipes = ArrayList<Int>()
    var ronda=1
    var naiArr = ArrayList<Figura>()
    init{
        //Obtener imagenes
        obtenerNaipes()
        barajear()

    }

    override fun onDraw(c: Canvas) {
        super.onDraw(c)

        for(n in naiArr){
            n.pintar(c)
        }

    }

    override fun onTouchEvent(event: MotionEvent): Boolean {

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {

                if(Prin.detArea(event.x,event.y)){
                    punteroFigura=Prin
                }

            }
            MotionEvent.ACTION_MOVE -> {
                if (punteroFigura!=null){
                    punteroFigura!!.mover(event.x,event.y)
                }
            }
            MotionEvent.ACTION_UP -> {
                if(punteroFigura==Prin){
                    if(Prin.col(suit2)) suit2.invisible=true
                }
                punteroFigura=null
                suit2.invisible=false
                suit2.x=500f
                suit2.y=500f
            }
        }

        invalidate()
        return true
    }

    fun obtenerNaipes(){
        naipes.clear()
        naipes.add(R.drawable.n1)
        naipes.add(R.drawable.n2)
        naipes.add(R.drawable.n3)
        naipes.add(R.drawable.n4)
        naipes.add(R.drawable.n5)
        naipes.add(R.drawable.n6)
        naipes.add(R.drawable.n7)
        naipes.add(R.drawable.n8)
        naipes.add(R.drawable.n9)
        naipes.add(R.drawable.n10)
    }//obtenerNaipes


    fun barajear(){
        //Crear naipes
        var posX= arrayOf(100f,400f,700f)
        var posY= arrayOf(500f,900f,1300f)
        var ix=0
        var iy=0
        var c=1
        for(n in naipes){
            if(c<4){
                iy=0
            }else if(c<7){
                iy=1
            }else{
                iy=2
            }
            if(ix==2){ix=0}else{ix++}
            naiArr.add(Figura(this,n,posX[ix],posY[iy],"naipes"))
            c++
        }
        println(naiArr)
        naiArr[naiArr.size-1].correcto=true
        naiArr[naiArr.size-1].x=400f
        naiArr[naiArr.size-1].y=100f
        println(naiArr)
        naiArr.shuffle()
        println(naiArr)
    }

}