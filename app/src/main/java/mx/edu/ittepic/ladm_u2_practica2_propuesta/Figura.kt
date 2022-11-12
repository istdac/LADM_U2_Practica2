package mx.edu.ittepic.ladm_u2_practica2_propuesta

import android.graphics.*
import android.view.View

class Figura(l:View,i:Int,x:Float,y:Float,n:String) {
    var li = l
    var img = BitmapFactory.decodeResource(li.resources, i)
    var x = x
    var y = y
    var r = RectF(x,y,(x+img.width),(y+img.height))
    var invisible=false
    var correcto=false
    var nom=n

    fun pintar(c:Canvas){
        if (invisible){
            x=0f
            y=0f
            r.set(x,y,x,y)
        }else{
            val p=Paint()
            c.drawBitmap(img,x,y,p)
            r.set(x,y,(x+img.width),(y+img.height))

        }

    }
    fun mover(xT:Float,yT:Float){
        x=xT-img.width/2
        y=yT-img.height/2
        r.set(x,y,(x+img.width),(y+img.height))
    }
    fun detArea(xT:Float,yT:Float):Boolean{
        var x2=x+img.width
        var y2=y+img.height
        if(xT>=x && xT<=x2){
            if(yT>=y&&yT<=y2){
                return true
            }
        }//if
        return false
    }//detArea

    fun col(f2:Figura):Boolean{
        var r2=f2.r
        if(r.intersect(r2)){
            return true
            //Revisar si naipes iguales
        }
        return false
    }

}