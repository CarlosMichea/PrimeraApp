# PrimeraApp - Aplicación Android (Kotlin)

Proyecto de aplicación móvil nativa para Android desarrollado en **Kotlin**, correspondiente a un flujo de autenticación e inicio de sesión con pantalla de bienvenida.

---

## 🛠️ Tecnologías y Requisitos

* **Lenguaje:** Kotlin (v2.0.21)
* **SDK de Android:**
  * Compile SDK: 36
  * Target SDK: 36
  * Min SDK: 24 (Android 7.0 Nougat)
* **Diseño UI:** ConstraintLayout, Material Components, soporte Edge-to-Edge.
* **Sistema de compilación:** Gradle con Kotlin DSL (`.kts`) y Version Catalog (`libs.versions.toml`).
* **Control de versiones:** Git & GitHub.

---

## 📋 Resumen del Trabajo Realizado

### 1. Interfaz de Inicio de Sesión (`activity_main.xml` y `colors.xml`)
* **Banner institucional:** Se incorporó un `ImageView` superior utilizando el recurso `@drawable/inacap_banner` con ajuste de proporciones (`adjustViewBounds="true"`).
* **Título:** `TextView` estilizado con texto *"Iniciar sesión"*, tamaño de `24sp` y estilo en negrita.
* **Campos de entrada de datos:**
  * `EditText` (`edtUsuario`) para el nombre de usuario con margen horizontal de `24dp` y ancho adaptable (`0dp`).
  * `EditText` (`edtPassword`) para contraseña con `android:inputType="textPassword"` para enmascaramiento seguro de caracteres.
* **Casilla de verificación:** `CheckBox` (`chkRecordarme`) con la opción *"Recordarme"*.
* **Botones de acción (Cadena horizontal):**
  * **Botón Limpiar (`btnLimpiar`):** Ubicado a la izquierda, color de fondo rojo (`@color/rojo_limpiar`), texto blanco y evento `android:onClick="onLimpiarClick"`.
  * **Botón Ingresar (`btnIngresar`):** Ubicado a la derecha, color de fondo azul (`@color/azul_ingresar`), texto blanco y ancho simétrico (`0dp`).
* **Animación GIF:** Se integró un `ImageView` con el recurso animado `@drawable/breakdance.gif` ubicado debajo de los botones de acción.

---

### 2. Lógica de Navegación, Validación y Limpieza (`MainActivity.kt`)
* **Validación de campos:** Al pulsar `btnIngresar`, se validan las entradas de usuario y contraseña:
  * Si alguno está vacío, se muestra una notificación `Toast` con el mensaje: *"Completa usuario y contraseña"*.
* **Navegación con `Intent` y paso de parámetros:** Cuando los campos son válidos, se inicia la actividad `BienvenidaActivity` enviando el nombre de usuario a través de un extra (`putExtra("usuario", usuario)`).
* **Limpieza de formulario (`onLimpiarClick`):** Método que atiende el clic del botón Limpiar para reiniciar los campos `edtUsuario`, `edtPassword` y desmarcar `chkRecordarme`, con código comentado detalladamente línea a línea.
* **Animación de GIF en Android 9+ (API 28+):** Implementación de `ImageDecoder` y `AnimatedImageDrawable` para decodificar y reproducir en bucle la animación del estudiante bailando breakdance.

---

### 3. Pantalla de Bienvenida (`BienvenidaActivity` y `activity_bienvenida.xml`)
* **Diseño:** `ConstraintLayout` con un `TextView` (`txtBienvenida`) centrado horizontal y verticalmente en pantalla.
* **Lógica:** Recuperación del extra `"usuario"` proveniente del `Intent` para personalizar el mensaje en tiempo de ejecución (`Bienvenido, <usuario>`).
* **Registro en Manifiesto:** Declaración de `BienvenidaActivity` en `AndroidManifest.xml`.

---

### 4. Control de Versiones y Publicación en GitHub
* Inicialización del repositorio Git local y configuración de exclusiones en `.gitignore`.
* Publicación del repositorio público en GitHub: [https://github.com/CarlosMichea/PrimeraApp](https://github.com/CarlosMichea/PrimeraApp).
