package mx.edu.ittepic.ladm_u2_practica2_propuesta

import android.content.ContentValues.TAG
import android.graphics.Canvas
import android.media.MediaPlayer
import android.os.Message
import android.provider.MediaStore
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import kotlinx.coroutines.*
import kotlin.concurrent.timer
import kotlin.coroutines.EmptyCoroutineContext

class LienzoJ4(p: Juego4) : View(p) {
    var pantalla = 1
    var posLetras = 50f

    //voz
    var vozA = MediaPlayer.create(context, R.raw.j4a)
    var vozE = MediaPlayer.create(context, R.raw.j4e)
    var vozI = MediaPlayer.create(context, R.raw.j4i)
    var vozO = MediaPlayer.create(context, R.raw.j4o)
    var vozU = MediaPlayer.create(context, R.raw.j4u)
    var vozCasa=MediaPlayer.create(context,R.raw.j4casa)
    var vozGato=MediaPlayer.create(context,R.raw.j4gato)
    var vozLeon=MediaPlayer.create(context,R.raw.j4leon)
    var vozFelicidades=MediaPlayer.create(context,R.raw.j4felicidades)

    //animales
    val gato = Figuras_estaticasJ4(this, R.drawable.j4gato, 300f, 200f)
    val casa = Figuras_estaticasJ4(this, R.drawable.j4casa, 300f, 200f)
    val leon = Figuras_estaticasJ4(this, R.drawable.j4leon, 300f, 200f)

    //vocales

    val letraA = Figurasj4(this, R.drawable.j4a, posLetras, 800f)
    val letraE = Figurasj4(this, R.drawable.j4e, posLetras + 200, 800f)
    val letraI = Figurasj4(this, R.drawable.j4i, posLetras + 400, 800f)
    val letraO = Figurasj4(this, R.drawable.j4o, posLetras + 600, 800f)
    val letraU = Figurasj4(this, R.drawable.j4u, posLetras + 800, 800f)
    //fondo
    val fondo=Figuras_estaticasJ4(this,R.drawable.fondoj4,0f,0f)

    //correcto
    val bien = Figuras_estaticasJ4(this, R.drawable.j4bien, 500f, 500f)

    //GATO
    val letraG = Figuras_estaticasJ4(this, R.drawable.j4g, posLetras, 1500f)
    val letraAes = Figuras_estaticasJ4(this, R.drawable.j4a, posLetras + 200, 1500f)
    val letraT = Figuras_estaticasJ4(this, R.drawable.j4t, posLetras + 400, 1500f)
    val input = Figuras_estaticasJ4(this, R.drawable.j4input, posLetras + 600, 1450f)

    //Casa
    val letraC = Figuras_estaticasJ4(this, R.drawable.j4c, posLetras, 1500f)
    val inputCasa = Figuras_estaticasJ4(this, R.drawable.j4input, posLetras + 200, 1450f)
    val letraS = Figuras_estaticasJ4(this, R.drawable.j4s, posLetras + 500, 1500f)
    val letraAcasa = Figuras_estaticasJ4(this, R.drawable.j4a, posLetras + 700, 1500f)

    //leon
    val letraL = Figuras_estaticasJ4(this, R.drawable.j4l, posLetras, 1500f)
    val letraEleon = Figuras_estaticasJ4(this, R.drawable.j4e, posLetras + 200, 1500f)
    val inputLeon = Figuras_estaticasJ4(this, R.drawable.j4input, posLetras + 400, 1450f)
    val letraN = Figuras_estaticasJ4(this, R.drawable.j4n, posLetras + 700, 1500f)
    //felicidaddes
    val felicidades=Figuras_estaticasJ4(this,R.drawable.buentrabajo,20f,400f)

//

    //puntero
    var punteroFigura: Figurasj4? = null

    //verificaciones
    var correcto = false

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        fondo.pintar(canvas)
        if (pantalla == 1 ) {//gato

            invalidate()
            // letraA.pintar(canvas)
            gato.pintar(canvas)
            //vocales
            vocales(canvas)
            //palabra
            letraG.pintar(canvas)
            letraT.pintar(canvas)
            letraAes.pintar(canvas)
            input.pintar(canvas)
        }
        if (pantalla == 2) {//casa

            invalidate()

            //vocales
            vocales(canvas)
            //palabra
            casa.pintar(canvas)
            letraC.pintar(canvas)
            inputCasa.pintar(canvas)
            letraS.pintar(canvas)
            letraAcasa.pintar(canvas)

        }
        if (pantalla == 3) {//leon

            invalidate()
            vocales(canvas)
            leon.pintar(canvas)
            letraL.pintar(canvas)
            letraEleon.pintar(canvas)
            inputLeon.pintar(canvas)
            letraN.pintar(canvas)
        }
        if (pantalla == 4) {//
            invalidate()
            felicidades.pintar(canvas)

        }
    }
//
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                if (letraA.determinarArea(event.x, event.y)) {
                    punteroFigura = letraA
                    try{ vozA.start() }catch(e:Exception){ Log.d(TAG, "voz", e);}
                }
                if (letraE.determinarArea(event.x, event.y)) {
                    punteroFigura = letraE

                    try{ vozE.start() }catch(e:Exception){}

                }
                if (letraI.determinarArea(event.x, event.y)) {
                    punteroFigura = letraI

                    try{ vozI.start() }catch(e:Exception){}

                }
                if (letraO.determinarArea(event.x, event.y)) {
                    punteroFigura = letraO

                    try{  vozO.start() }catch(e:Exception){}

                }
                if (letraU.determinarArea(event.x, event.y)) {
                    punteroFigura = letraU

                    try{  vozU.start() }catch(e:Exception){}

                }
            }
            MotionEvent.ACTION_MOVE -> {
                if (punteroFigura != null) {
                    punteroFigura!!.mover(event.x, event.y)
                    if (pantalla == 1) {
                        if (punteroFigura == letraO) {
                            if (letraO.colision(input)) {
                                punteroFigura = null
                                reposicionarVocales()
                                pantalla = 2
                                invalidate()

                                try{ vozCasa.start() }catch(e:Exception){}

                            }
                        }
                        if (punteroFigura == letraA) {
                            if (letraA.colision(input)) {
                                punteroFigura = null
                               reposicionarVocales()
                                invalidate()
                            }

                        }
                        if (punteroFigura == letraE) {
                            if (letraE.colision(input)) {
                                punteroFigura = null
                              reposicionarVocales()
                                invalidate()
                            }
                        }
                        if (punteroFigura == letraI) {
                            if (letraI.colision(input)) {
                                punteroFigura = null
                                reposicionarVocales()
                                invalidate()
                            }
                        }
                        if (punteroFigura == letraU) {
                            if (letraU.colision(input)) {
                                punteroFigura=null
                               reposicionarVocales()
                                invalidate()
                            }
                        }
                    } else
                        if (pantalla == 2) {
                            if (punteroFigura == letraO) {
                                if (letraO.colision(input)) {
                                    punteroFigura=null
                                    reposicionarVocales()
                                    invalidate()
                                }
                            }
                            if (punteroFigura == letraA) {
                                if (letraA.colision(inputCasa)) {
                                    punteroFigura=null
                                    reposicionarVocales()
                                    pantalla = 3

                                    try{ vozLeon.start() }catch(e:Exception){}

                                    invalidate()
                                }
                            }
                            if (punteroFigura == letraE) {
                                if (letraE.colision(input)) {
                                    punteroFigura=null
                                    reposicionarVocales()
                                    invalidate()
                                }
                            }
                            if (punteroFigura == letraI) {
                                if (letraI.colision(input)) {
                                    punteroFigura=null
                                    reposicionarVocales()
                                    invalidate()
                                }
                            }
                            if (punteroFigura == letraU) {
                                if (letraU.colision(input)) {
                                    punteroFigura=null
                                    reposicionarVocales()
                                    invalidate()
                                }
                            }
                        } else
                            if (pantalla == 3) {
                                if (punteroFigura == letraO) {
                                    if (letraO.colision(inputLeon)) {
                                        punteroFigura=null
                                        reposicionarVocales()
                                        pantalla = 4
                                        invalidate()
                                        try{ vozFelicidades.start() }catch(e:Exception){}

                                    }
                                }
                                if (punteroFigura == letraA) {
                                    if (letraA.colision(inputCasa)) {
                                        punteroFigura=null
                                        reposicionarVocales()
                                        invalidate()
                                    }
                                }
                                if (punteroFigura == letraE) {
                                    if (letraE.colision(input)) {
                                        punteroFigura=null
                                        reposicionarVocales()
                                        invalidate()
                                    }
                                }
                                if (punteroFigura == letraI) {
                                    if (letraI.colision(input)) {
                                        punteroFigura=null
                                        reposicionarVocales()
                                        invalidate()
                                    }
                                }
                                if (punteroFigura == letraU) {
                                    if (letraU.colision(input)) {
                                        punteroFigura=null
                                        reposicionarVocales()
                                        invalidate()
                                    }
                                }
                            }
                }
            }
            MotionEvent.ACTION_UP -> {
                punteroFigura = null
            }
        }
        invalidate()
        return true
    }

    private fun vocales(c: Canvas) {
        letraA.pintar(c)
        letraE.pintar(c)
        letraI.pintar(c)
        letraO.pintar(c)
        letraU.pintar(c)
    }

    fun reposicionarVocales() {
        letraA.reposicionar(posLetras,800f)
        letraE.reposicionar(posLetras+200,800f)
        letraI.reposicionar(posLetras+400,800f)
        letraO.reposicionar(posLetras+600,800f)
        letraU.reposicionar(posLetras+800,800f)
    }
}