package com.example.primeraapp // Paquete de la aplicación

import android.content.Intent   // Permite crear intents para navegar entre actividades y aplicar flags
import android.os.Bundle        // Necesario para el ciclo de vida de la Activity
import android.os.Handler       // Permite programar tareas para ejecutarse en un hilo después de un retardo
import android.os.Looper        // Proporciona el bucle de mensajes del hilo principal (main thread)
import android.view.View        // Necesario para recibir el parámetro View en los métodos onClick
import android.widget.ProgressBar  // Permite referenciar y controlar el ProgressBar del layout
import android.widget.RadioGroup   // Permite leer qué RadioButton está seleccionado dentro del grupo
import android.widget.TextView     // Permite referenciar el TextView del layout
import android.widget.Toast        // Permite mostrar mensajes emergentes breves al usuario
import androidx.activity.enableEdgeToEdge          // Habilita el diseño edge-to-edge
import androidx.appcompat.app.AppCompatActivity    // Clase base de la Activity con soporte AppCompat
import androidx.core.view.ViewCompat               // Compatibilidad con vistas según versión de Android
import androidx.core.view.WindowInsetsCompat       // Manejo de insets (barras del sistema)

class PreferenciasActivity : AppCompatActivity() { // Declaración de la Activity de preferencias

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) // Llama al onCreate del padre para inicialización base
        enableEdgeToEdge()                 // Activa el modo edge-to-edge (pantalla completa)
        setContentView(R.layout.activity_preferencias) // Asocia este Activity con su layout XML

        // Aplica padding automático para que el contenido no quede oculto tras las barras del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars()) // Obtiene el tamaño de las barras
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom) // Aplica padding
            insets // Retorna los insets para que el sistema continúe su procesamiento
        }

        val txtPreferencias = findViewById<TextView>(R.id.txtPreferencias) // Referencia al TextView del layout
        val usuario = intent.getStringExtra("usuario") ?: "" // Recupera el extra "usuario" enviado desde BienvenidaActivity
        txtPreferencias.text = "Preferencias de $usuario"   // Personaliza el mensaje con el nombre del usuario
    }

    // Método vinculado al atributo android:onClick="onGuardarPreferenciaClick" del botón Guardar en el XML
    fun onGuardarPreferenciaClick(view: View) {

        val pbGuardando = findViewById<ProgressBar>(R.id.pbGuardando) // Referencia al ProgressBar del layout
        val rgUnidad    = findViewById<RadioGroup>(R.id.rgUnidad)      // Referencia al RadioGroup del layout

        pbGuardando.visibility = View.VISIBLE // Hace visible el ProgressBar para indicar que se está procesando

        // Handler asociado al hilo principal: garantiza que la tarea postDelayed actualice la UI sin errores
        Handler(Looper.getMainLooper()).postDelayed({

            pbGuardando.visibility = View.GONE // Oculta el ProgressBar después de que transcurrió el retardo

            // Determina cuál RadioButton está seleccionado dentro del RadioGroup y asigna la etiqueta correspondiente
            val unidad = when (rgUnidad.checkedRadioButtonId) {
                R.id.rbCelsius     -> "Celsius"     // Si rbCelsius está marcado, la unidad elegida es Celsius
                R.id.rbFahrenheit  -> "Fahrenheit"  // Si rbFahrenheit está marcado, la unidad elegida es Fahrenheit
                else               -> "desconocida" // Caso de seguridad: ningún RadioButton seleccionado
            }

            // Muestra un Toast breve confirmando la unidad de temperatura guardada
            Toast.makeText(this, "Unidad guardada: $unidad", Toast.LENGTH_SHORT).show()

        }, 1000) // Retardo de 1000 milisegundos (1 segundo) antes de ejecutar el bloque anterior
    }

    // Método vinculado al atributo android:onClick="onCerrarSesionClick" del botón Cerrar sesión en el XML
    fun onCerrarSesionClick(view: View) {

        // Crea un Intent que apunta a MainActivity (pantalla de login)
        val intent = Intent(this, MainActivity::class.java)

        // FLAG_ACTIVITY_NEW_TASK: inicia MainActivity en una nueva tarea del sistema
        // FLAG_ACTIVITY_CLEAR_TASK: destruye todas las Activities que había en el back stack antes de lanzar la nueva
        // Combinados, garantizan que el usuario no pueda volver atrás con el botón Back tras cerrar sesión
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

        startActivity(intent) // Lanza MainActivity limpiando el historial de pantallas
    }
}
