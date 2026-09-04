package com.example.primeraapp

import android.content.Intent
import android.graphics.ImageDecoder
import android.graphics.drawable.AnimatedImageDrawable
import android.os.Build
import android.os.Bundle
import android.text.InputType
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    // Variable booleana a nivel de clase para rastrear el estado de visibilidad de la contraseña
    var mostrandoPassword: Boolean = false
    // Variable numérica a nivel de clase para contar los intentos fallidos de inicio de sesión
    var intentosFallidos: Int = 0
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
            onIngresarClick(it)
        }
    }

    // Método que atiende el clic del botón btnIngresar desde el layout XML o programáticamente
    fun onIngresarClick(view: View) {
        // Obtiene la referencia del campo de texto de usuario por su identificador
        val edtUsuario = findViewById<EditText>(R.id.edtUsuario)
        // Obtiene la referencia del campo de texto de contraseña por su identificador
        val edtPassword = findViewById<EditText>(R.id.edtPassword)
        // Extrae el texto del campo de usuario removiendo espacios al inicio y final
        val usuario = edtUsuario.text.toString().trim()
        // Extrae el texto del campo de contraseña removiendo espacios al inicio y final
        val password = edtPassword.text.toString().trim()
        // Bandera para rastrear si algún campo tiene error; comienza asumiendo que todo es válido
        var hayError = false
        // Verifica si el campo de usuario está vacío
        if (usuario.isEmpty()) {
            // Incrementa el contador de intentos fallidos por campo vacío
            intentosFallidos++
            // Muestra el error directamente sobre el campo de usuario indicando el intento número actual
            edtUsuario.error = "Ingresa tu correo (intento $intentosFallidos)"
            // Marca que se encontró al menos un error para no proceder con el inicio de sesión
            hayError = true
        // Verifica si el texto ingresado en usuario no coincide con un formato de email válido
        } else if (!Patterns.EMAIL_ADDRESS.matcher(usuario).matches()) {
            // Incrementa el contador de intentos fallidos por formato inválido
            intentosFallidos++
            // Muestra el error directamente sobre el campo de usuario con el número de intento
            edtUsuario.error = "Correo no válido (intento $intentosFallidos)"
            // Marca que se encontró al menos un error
            hayError = true
        }
        // Verifica si el campo de contraseña está vacío
        if (password.isEmpty()) {
            // Incrementa el contador de intentos fallidos por campo vacío solo si no hubo error previo en este intento
            if (!hayError) intentosFallidos++
            // Muestra el error directamente sobre el campo de contraseña indicando que es obligatorio
            edtPassword.error = "Ingresa tu contraseña (intento $intentosFallidos)"
            // Marca que se encontró al menos un error
            hayError = true
        // Verifica si la contraseña ingresada tiene menos de 6 caracteres
        } else if (password.length < 6) {
            // Incrementa el contador de intentos fallidos por contraseña muy corta solo si no hubo error previo
            if (!hayError) intentosFallidos++
            // Muestra el error directamente sobre el campo de contraseña con el requisito mínimo
            edtPassword.error = "Mínimo 6 caracteres (intento $intentosFallidos)"
            // Marca que se encontró al menos un error
            hayError = true
        }
        // Si no se detectó ningún error de validación, procede con el inicio de sesión
        if (!hayError) {
            // Limpia cualquier error previo del campo de usuario antes de navegar
            edtUsuario.error = null
            // Limpia cualquier error previo del campo de contraseña antes de navegar
            edtPassword.error = null
            // Crea la intención para navegar hacia la pantalla de bienvenida
            val intent = Intent(this, BienvenidaActivity::class.java).apply {
                // Pasa el nombre de usuario autenticado como parámetro extra
                putExtra("usuario", usuario)
            }
            // Ejecuta la transición para abrir la actividad de bienvenida
            startActivity(intent)
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

    // Método que atiende el evento onClick del botón btnMostrarPassword desde el layout XML
    fun onMostrarPasswordClick(view: View) {
        // Obtiene la referencia del campo de texto de contraseña por su ID
        val edtPassword = findViewById<EditText>(R.id.edtPassword)
        // Comprueba si actualmente la contraseña no se está mostrando
        if (!mostrandoPassword) {
            // Cambia el inputType del campo para hacer visible el texto de la contraseña
            edtPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            // Actualiza la variable de estado indicando que ahora está visible
            mostrandoPassword = true
            // Cambia el icono a visibilidad desactivada para indicar que al presionar se ocultará
            (view as? ImageButton)?.setImageResource(R.drawable.ic_visibility_off_24)
        } else {
            // Cambia el inputType del campo para enmascarar y ocultar la contraseña
            edtPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            // Actualiza la variable de estado indicando que vuelve a estar oculta
            mostrandoPassword = false
            // Cambia el icono a visibilidad activa para indicar que al presionar se mostrará
            (view as? ImageButton)?.setImageResource(R.drawable.ic_visibility_24)
        }
        // Ubica el cursor al final del texto actual para no perder la posición de escritura
        edtPassword.setSelection(edtPassword.text.length)
    }
}