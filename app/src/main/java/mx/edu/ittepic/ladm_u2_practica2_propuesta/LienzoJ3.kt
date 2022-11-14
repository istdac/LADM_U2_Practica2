package mx.edu.ittepic.ladm_u2_practica2_propuesta

import android.annotation.SuppressLint
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.media.MediaPlayer
import android.view.MotionEvent
import android.view.View
import android.widget.Toast

class LienzoJ3(t:Juego3):View(t) {

    var imagenes=ArrayList<Figura>()
    var numeros=ArrayList<Figura>()

    var imagNum= arrayOf(
        R.drawable.uno,
        R.drawable.dos,
        R.drawable.tres,
        R.drawable.cuatro,
        R.drawable.cinco,
        R.drawable.seis,
        R.drawable.siete,
        R.drawable.ocho,
        R.drawable.nueve,
        R.drawable.diez,
    )

    var imgns= arrayOf(
        R.drawable.bloque1,
        R.drawable.bloque2,
        R.drawable.bloque3,
        R.drawable.bloque4,
        R.drawable.bloque5,
        R.drawable.bloque6,
        R.drawable.bloque7,
        R.drawable.bloque8,
        R.drawable.bloque9,
        R.drawable.bloque10,
    )


    var audios=arrayOf(
        R.raw.numuno,
        R.raw.numdos,
        R.raw.numtres,
        R.raw.numcuatro,
        R.raw.numcinco,
        R.raw.numseis,
        R.raw.numsiete,
        R.raw.numocho,
        R.raw.numnueve,
        R.raw.numdiez,
    )


    var nams = arrayOf("Uno","Dos","Tres","Cuatro","Cinco","Seis","Siete","Ocho","Nueve","Diez")
    var punteroFigura : Figura?=null

    init {
        obtenerImg()
        ajustarPuntosNumeros()
        ajustarVisibilidad()
    }

    override fun onDraw(c: Canvas) {
        super.onDraw(c)

        val p= Paint()
        p.color = Color.BLACK
        c.drawText("Une un numero con la imagen de bloques", 20f,15f,p)

        if(imagenes.isNotEmpty() && numeros.isNotEmpty()){
            for(n in numeros){
                n.pintar(c)
            }
            for(i in imagenes){
                i.pintar(c)
            }
        }
    }//onDraw

    @SuppressLint("NewApi")
    override fun onTouchEvent(event: MotionEvent): Boolean {

        when(event.action){
            MotionEvent.ACTION_DOWN->{
                for(t in numeros){
                    if(t.detArea(event.x,event.y)){
                        punteroFigura=t
                        var ai = 0
                        when(t.nom){
                            "Uno"->{ ai=0 }
                            "Dos"->{ ai=1 }
                            "Tres"->{ ai=2 }
                            "Cuatro"->{ ai=3 }
                            "Cinco"->{ ai=4 }
                            "Seis"->{ ai=5 }
                            "Siete"->{ ai=6 }
                            "Ocho"->{ ai=7 }
                            "Nueve"->{ ai=8 }
                            "Diez"->{ ai=9 }
                        }//when
                        try {
                            var aud = MediaPlayer()
                            aud.setDataSource(resources.openRawResourceFd(audios[ai]))
                            aud.prepare()
                            aud.start()
                        }catch(e:Exception){
                            Toast.makeText(this.context,e.message, Toast.LENGTH_LONG).show()
                        }
                        println("nombre"+punteroFigura!!.nom)
                        break
                    }//if
                }//for
            }//actiondown
            MotionEvent.ACTION_MOVE->{
                if(punteroFigura!=null){
                    punteroFigura!!.mover(event.x,event.y)
                }//if
            }//move
            MotionEvent.ACTION_UP->{
                if(punteroFigura!=null){
                    var coli = imagenes.filter { i->punteroFigura!!.col(i) }
                    if (coli.isNotEmpty()){
                        for (i in coli){
                            if(i.nom==punteroFigura!!.nom){
                                println("¡CORRECTO!")
                                imagenes.remove(i)
                                numeros.remove(punteroFigura)
                                if(imagenes.isNotEmpty()) {
                                    ajustarVisibilidad()
                                }
                                invalidate()


                            }
                        }
                    }
                }//if
            }
        }//when
        invalidate()
        return true
    }//onTouchEvent

    fun obtenerImg(){
        for(i in (0..10)){
            imagenes.add(Figura(this,imgns[i],350f,20f,nams[i]))
            numeros.add(Figura(this,imagNum[i],400f,100f,nams[i]))
        }
    }//obtenerImg

    fun ajustarPuntosNumeros(){
        var posX= arrayOf(50f,650f)
        var posY= arrayOf(500f,900f,1300f)
        var ix=0
        var iy=0
        var c=0
        for (n in numeros){
            if(c<2){iy=0}else if (c<4){iy=1}else{iy=2}
            println(n.x)
            n.x=posX[ix].toFloat()
            n.y=posY[iy].toFloat()
            n.correcto=true
            c++
            if(ix==1) {
                ix = 0
            }
            else {
                ix++
            }
        }

    } //Ajustar Numeros

    fun ajustarVisibilidad(){
        for (i in imagenes){
            i.invisible=true
            i.x=350f
            println("x "+i.x)
            println("y "+i.y)
        }
        imagenes.get(imagenes.size-1).invisible=false
    }//Visibilidad

}//Clase lienzo J3