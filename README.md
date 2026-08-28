# PrimeraApp - Aplicacion Android (Kotlin)

Proyecto de aplicacion movil nativa para Android desarrollado en **Kotlin**, correspondiente a un flujo de autenticacion e inicio de sesion con pantalla de bienvenida.

---

## Tecnologias y Requisitos

* **Lenguaje:** Kotlin (v2.0.21)
* **SDK de Android:**
  * Compile SDK: 36
  * Target SDK: 36
  * Min SDK: 24 (Android 7.0 Nougat)
* **Diseno UI:** ConstraintLayout, Material Components, soporte Edge-to-Edge.
* **Sistema de compilacion:** Gradle con Kotlin DSL (`.kts`) y Version Catalog (`libs.versions.toml`).
* **Control de versiones:** Git & GitHub.

---

## Resumen del Trabajo Realizado

### 1. Interfaz de Inicio de Sesion (`activity_main.xml`)
* **Banner institucional:** Se incorporo un `ImageView` superior utilizando el recurso `@drawable/inacap_banner` con ajuste de proporciones (`adjustViewBounds="true"`).
* **Titulo:** `TextView` estilizado con texto *"Iniciar sesion"*, tamano de `24sp` y estilo en negrita.
* **Campos de entrada de datos:**
  * `EditText` (`edtUsuario`) para el nombre de usuario con margen horizontal de `24dp` y ancho adaptable (`0dp`).
  * `EditText` (`edtPassword`) para contrasena con `android:inputType="textPassword"` para enmascaramiento seguro de caracteres.
+ **Casilla de verificacion:** `CheckBox` (`chkRecordarme`) con la opcion *"Recordarme".
* **Boton de accion:** `Button` (`btnIngresar`) con texto *"Ingresar" y ancho completo.
+ **Animacion GIF:** Se integro un `ImageView` con el recurso animado `@drawable/breakdance.gif` ubicado debajo del boton de inicio de sesion.

---

### 2. Logica de Navegacion y Validacion (`MainActivity.kt`)
* **Validacion de campos:** Al pulsar `btnIngresar`, se validan las entradas de usuario y contrasena:
  * Si alguno esta vacio, se muestra una notificacion `Toast` con el mensaje: *"Completa usuario y contrasena"*.
* **Navegacion con `Intent` y paso de parametros:** Cuando los campos son validos, se inicia la actividad `BienvenidaActivity` enviando el nombre de usuario a traves de un extra (`putExtra("usuario", usuario)`).
* **Animacion de GIF en Android 9+ (API 28+):** Implementacion de `ImageDecoder` y `AnimatedImageDrawable` para decodificar y reproducir en bucle la animacion del estudiante bailando breakdance.

---

### 3. Pantalla de Bienvenida (`BienvenidaActivity` y `activity_bienvenida.xml`)
* **Diseno:** `ConstraintLayout` con un `TextView` (`txtBienvenida`) centrado horizontal y verticalmente en pantalla.
* **Logica:** Recuperacion del extra `"usuario"` proveniente del `Intent` para personalizar el mensaje en tiempo de ejecucion (`Bienvenido, <usuario>`).
* **Registro en Manifiesto:** Declaracion de `BienvenidaActivity` en `AndroidManifest.xml`.

---

### 4. Control de Versiones y Publicacion en GitHub
*Inicializacion del repositorio Git local y configuracion de exclusiones en `.gitignore`.
*Publicacion del repositorio publico en GitHub: https://github.com/CarlosMichea/PrimeraApp
