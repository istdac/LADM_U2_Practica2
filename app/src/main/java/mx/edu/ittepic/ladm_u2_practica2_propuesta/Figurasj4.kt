package mx.edu.ittepic.ladm_u2_practica2_propuesta

import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class Figurasj4(lienzo: LienzoJ4, image: Int, x: Float, y: Float) {
    var lienzo = lienzo
    var image = BitmapFactory.decodeResource(lienzo.resources, image)
    var x = x
    var y = y
    var invisible=false

    val scope= CoroutineScope(Job() + Dispatchers.Main)

    fun pintar(c: Canvas) {
        if (invisible) return
        val p = Paint()
        c.drawBitmap(image, x, y, p)
    }

    fun mover(xToque: Float, yToque: Float) {
        x = xToque - image.width / 2
        y = yToque - image.height / 2
    }

    fun determinarArea(xToque: Float, yToque: Float): Boolean {
        var x2 = x + image.width
        var y2 = y + image.height

        if(xToque>=x && xToque<=x2){
            if(yToque>=y && yToque<=y2){
                return true
            }
        }
        return false
    }
    fun colision(figuraB: Figuras_estaticasJ4):Boolean{
        var x2=x+image.width
        var y2=y+image.height
        var xIntermedia=x2/2
        var yIntermedia=y2/2
        //horillas de la imagen
        if(figuraB.determinarArea(x2,y2))return true
        if (figuraB.determinarArea(x2,y))return true
        if (figuraB.determinarArea(x,y2))return true
        if (figuraB.determinarArea(x,y))return true
        return false
    }
    fun reposicionar(xInicial: Float , yInicial:Float){
        x=xInicial
        y=yInicial
    }

}