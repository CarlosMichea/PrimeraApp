package com.example.primeraapp // Paquete de la aplicación

import android.content.Intent  // Permite crear intents para navegar entre actividades
import android.os.Bundle       // Necesario para el ciclo de vida de la Activity
import android.view.View       // Necesario para recibir el parámetro View en los métodos onClick
import android.widget.TextView // Permite referenciar el TextView del layout
import androidx.activity.enableEdgeToEdge          // Habilita el diseño edge-to-edge
import androidx.appcompat.app.AppCompatActivity    // Clase base de la Activity con soporte AppCompat
import androidx.core.view.ViewCompat               // Compatibilidad con vistas según versión de Android
import androidx.core.view.WindowInsetsCompat       // Manejo de insets (barras del sistema)

class BienvenidaActivity : AppCompatActivity() { // Declaración de la Activity de bienvenida

    // Variable que guarda el nombre de usuario recibido desde MainActivity
    private var usuario: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) // Llama al onCreate del padre para inicialización base
        enableEdgeToEdge()                 // Activa el modo edge-to-edge (pantalla completa)
        setContentView(R.layout.activity_bienvenida) // Asocia este Activity con su layout XML

        // Aplica padding automático para que el contenido no quede oculto tras las barras del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars()) // Obtiene el tamaño de las barras
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom) // Aplica padding
            insets // Retorna los insets para que el sistema continúe su procesamiento
        }

        val txtBienvenida = findViewById<TextView>(R.id.txtBienvenida) // Referencia al TextView del layout
        usuario = intent.getStringExtra("usuario") ?: "" // Recupera el extra "usuario" enviado desde MainActivity
        txtBienvenida.text = "Bienvenido, $usuario"      // Personaliza el mensaje con el nombre del usuario
    }

    // Método vinculado al atributo android:onClick="onPreferenciasClick" del botón en el XML
    fun onPreferenciasClick(view: View) {
        val intent = Intent(this, PreferenciasActivity::class.java) // Crea un Intent hacia PreferenciasActivity
        intent.putExtra("usuario", usuario)  // Pasa el nombre del usuario como extra al siguiente Activity
        startActivity(intent)                // Lanza PreferenciasActivity
    }
}