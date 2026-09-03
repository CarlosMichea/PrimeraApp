package com.example.primeraapp

import android.content.Intent
import android.graphics.ImageDecoder
import android.graphics.drawable.AnimatedImageDrawable
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val edtUsuario = findViewById<EditText>(R.id.edtUsuario)
        val edtPassword = findViewById<EditText>(R.id.edtPassword)
        val chkRecordarme = findViewById<CheckBox>(R.id.chkRecordarme)
        val btnIngresar = findViewById<Button>(R.id.btnIngresar)
        val imgBreakdance = findViewById<ImageView>(R.id.imgBreakdance)

        // Reproducir animación GIF en Android 9+ (API 28+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            try {
                val source = ImageDecoder.createSource(resources, R.drawable.breakdance)
                val drawable = ImageDecoder.decodeDrawable(source)
                imgBreakdance.setImageDrawable(drawable)
                if (drawable is AnimatedImageDrawable) {
                    drawable.start()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        btnIngresar.setOnClickListener {
            val usuario = edtUsuario.text.toString().trim()
            val password = edtPassword.text.toString().trim()
            val recordarme = chkRecordarme.isChecked

            if (usuario.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Completa usuario y contraseña", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, BienvenidaActivity::class.java).apply {
                    putExtra("usuario", usuario)
                }
                startActivity(intent)
            }
        }
    }

    // Método que se ejecuta al presionar el botón Limpiar desde el layout XML (android:onClick)
    fun onLimpiarClick(view: View) {
        // Obtiene la referencia del campo de texto de usuario por su ID
        val edtUsuario = findViewById<EditText>(R.id.edtUsuario)
        // Obtiene la referencia del campo de texto de contraseña por su ID
        val edtPassword = findViewById<EditText>(R.id.edtPassword)
        // Obtiene la referencia de la casilla de verificación Recordarme por su ID
        val chkRecordarme = findViewById<CheckBox>(R.id.chkRecordarme)
        // Limpia el contenido del campo de texto de usuario
        edtUsuario.setText("")
        // Limpia el contenido del campo de texto de contraseña
        edtPassword.setText("")
        // Desmarca la casilla de verificación estableciendo su estado en falso
        chkRecordarme.isChecked = false
    }
}