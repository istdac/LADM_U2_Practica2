package mx.edu.ittepic.ladm_u2_practica2_propuesta

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mx.edu.ittepic.ladm_u2_practica2_propuesta.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding :ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnJ1.setOnClickListener {
            var j1 = Intent(this,Juego1::class.java)
            startActivity(j1)
        }
        binding.btnJ2.setOnClickListener {
            var j2 = Intent(this,Juego2::class.java)
            startActivity(j2)
        }
        binding.btnJ3.setOnClickListener {
            var j3 = Intent(this,Juego3::class.java)
            startActivity(j3)
        }
        binding.btnJ4.setOnClickListener {
            var j4 = Intent(this,Juego4::class.java)
            startActivity(j4)
        }

    }//onCreate
}