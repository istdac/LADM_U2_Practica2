package mx.edu.ittepic.ladm_u2_practica2_propuesta

import android.graphics.Canvas
import android.view.MotionEvent
import android.view.View
import kotlin.random.Random

class LienzoJ1(j:Juego1):View(j) {
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
        var Prin = naiArr.find { n->n.correcto }
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {

                if(Prin!!.detArea(event.x,event.y)){
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
                    var coli= naiArr.filter { f->Prin!!.col(f) }
                    for(f in coli){
                        if(f.nom=="Par"){
                            println("Correct")
                        }
                    }
                }
                punteroFigura=null
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
    }//obtenerNaipes


    fun barajear(){
        //Crear naipes
        var posX= arrayOf(25f,450f,875f)
        var posY= arrayOf(500f,1000f,1500f)
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
            println(""+posX[ix]+""+posY[iy])
        }
        println(naiArr)

        val corN = Random(System.currentTimeMillis())
        var nCopiar=corN.nextInt(0,9)
        naiArr.add(Figura(this,naipes[nCopiar],400f,100f,"Principal"))
        naiArr[nCopiar].nom="Par"
        naiArr[naiArr.size-1].correcto=true
        naiArr.shuffle(corN)

    }

}