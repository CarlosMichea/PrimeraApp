package com.example.primeraapp // Paquete de la aplicacion

import android.os.Bundle       // Necesario para el ciclo de vida de la Activity
import android.widget.TextView // Permite referenciar el TextView del layout
import androidx.activity.enableEdgeToEdge          // Habilita el diseno edge-to-edge
import androidx.appcompat.app.AppCompatActivity    // Clase base de la Activity con soporte AppCompat
import androidx.core.view.ViewCompat               // Compatibilidad con vistas segun version de Android
import androidx.core.view.WindowInsetsCompat       // Manejo de insets (barras del sistema)

class PreferenciasActivity : AppCompatActivity() { // Declaracion de la Activity de preferencias

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) // Llama al onCreate del padre para inicializacion base
        enableEdgeToEdge()                 // Activa el modo edge-to-edge (pantalla completa)
        setContentView(R.layout.activity_preferencias) // Asocia este Activity con su layout XML

        // Aplica padding automatico para que el contenido no quede oculto tras las barras del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars()) // Obtiene el tamano de las barras
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom) // Aplica padding
            insets // Retorna los insets para que el sistema continue su procesamiento
        }

        val txtPreferencias = findViewById<TextView>(R.id.txtPreferencias) // Referencia al TextView del layout
        val usuario = intent.getStringExtra("usuario") ?: "" // Recupera el extra "usuario" enviado desde BienvenidaActivity
        txtPreferencias.text = "Preferencias de $usuario"   // Personaliza el mensaje con el nombre del usuario
    }
}
