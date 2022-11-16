package mx.edu.ittepic.ladm_u2_practica2_propuesta

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.media.MediaPlayer
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintSet

class LienzoJ2(j:Juego2):View(j) {
    var imagenes = ArrayList<Figura>()
    var textos = ArrayList<Figura>()
    var imgsT = arrayOf(
        R.drawable.leon,
        R.drawable.lobo,
        R.drawable.pez,
        R.drawable.quetzal,
        R.drawable.rana,
        R.drawable.toro,
    )
    var imgs = arrayOf(
        R.drawable.leo,
        R.drawable.lob,
        R.drawable.pezc,
        R.drawable.quetz,
        R.drawable.ran,
        R.drawable.tor,
    )
    var audios = arrayOf(
        R.raw.leon,
        R.raw.lobo,
        R.raw.pez,
        R.raw.quetzal,
        R.raw.rana,
        R.raw.toro,
    )
    var nams = arrayOf("Leon","Lobo","Pez","Quetzal","Rana","Toro")
    var punteroFigura : Figura?=null
    val fondo= BitmapFactory.decodeResource(resources,R.drawable.fondoj2)

    init{
        obtenerImg()
        ajustarPuntosTextos()
        ajustarVisibilidad()
    }

    override fun onDraw(c: Canvas) {
        super.onDraw(c)
        var p=Paint()
        c.drawBitmap(fondo,1f,1f,p)

        if(imagenes.isNotEmpty() && textos.isNotEmpty()){
            for(n in textos){
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
                for(t in textos){
                    if(t.detArea(event.x,event.y)){
                        punteroFigura=t
                        var ai = 0
                        when(t.nom){
                            "Leon"->{ ai=0 }
                            "Lobo"->{ ai=1 }
                            "Pez"->{ ai=2 }
                            "Quetzal"->{ ai=3 }
                            "Rana"->{ ai=4 }
                            "Toro"->{ ai=5 }
                        }//when
                            try {
                                var mp = MediaPlayer()
                                mp.setDataSource(resources.openRawResourceFd(audios[ai]))
                                mp.prepare()
                                mp.start()
                            }catch(e:Exception){
                                Toast.makeText(this.context,e.message,Toast.LENGTH_LONG).show()
                            }
                        println("nombre"+punteroFigura!!.nom)
                        break
                    }//if
                    else{
                        ajustarPuntosTextos()
                    }
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
                                println("correcto!!!")
                                imagenes.remove(i)
                                textos.remove(punteroFigura)
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
    }

    fun obtenerImg(){
        for(i in (0..5)){
            imagenes.add(Figura(this,imgs[i],350f,0f,nams[i]))
            textos.add(Figura(this,imgsT[i],400f,100f,nams[i]))
        }
    }//obtenerImg
    fun ajustarPuntosTextos(){
        var posX= arrayOf(50f,650f)
        var posY= arrayOf(500f,900f,1300f)
        var ix=0
        var iy=0
        var c=0
        for (n in textos){
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

    }
    fun ajustarVisibilidad(){
        for (i in imagenes){
            i.invisible=true
            i.x=350f
            println("x "+i.x)
            println("y "+i.y)
        }
        imagenes.get(imagenes.size-1).invisible=false
    }

}//Lienzo2