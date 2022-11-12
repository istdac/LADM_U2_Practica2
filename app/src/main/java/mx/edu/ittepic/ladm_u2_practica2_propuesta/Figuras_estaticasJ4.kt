package mx.edu.ittepic.ladm_u2_practica2_propuesta

import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint

class Figuras_estaticasJ4(lienzo: LienzoJ4, image: Int, x: Float, y: Float) {
    var lienzo = lienzo
    var image = BitmapFactory.decodeResource(lienzo.resources, image)
    var x = x
    var y = y


    fun pintar(c: Canvas) {
        val p = Paint()
        c.drawBitmap(image, x, y, p)
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
}