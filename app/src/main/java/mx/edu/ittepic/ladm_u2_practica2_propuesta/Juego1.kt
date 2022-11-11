package mx.edu.ittepic.ladm_u2_practica2_propuesta

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Juego1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(Lienzo(this))
    }
}