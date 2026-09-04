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

### 4. Navegación hacia Preferencias (`BienvenidaActivity.kt` y `activity_bienvenida.xml`)
* **Botón `btnPreferencias`** agregado en `activity_bienvenida.xml`, posicionado `32dp` debajo de `txtBienvenida`, centrado horizontalmente, con evento `android:onClick="onPreferenciasClick"`.
* **Propiedad de clase `usuario`:** El campo fue promovido de variable local a propiedad privada de la clase (`private var usuario: String = ""`) para que `onPreferenciasClick` pueda acceder a él.
* **Método `onPreferenciasClick(view: View)`:** Crea un `Intent` hacia `PreferenciasActivity` y pasa el nombre del usuario como extra (`putExtra("usuario", usuario)`), luego lanza la actividad con `startActivity(intent)`.
* **Código comentado línea a línea** en todo el archivo.

---

### 5. Pantalla de Preferencias — estructura base (`PreferenciasActivity.kt` y `activity_preferencias.xml`)
* **Archivos nuevos creados** para la pantalla de preferencias del usuario.
* **Diseño (`activity_preferencias.xml`):**
  * `TextView` (`txtPreferencias`) como título, anclado al tope del padre con margen superior de `32dp`.
  * `LinearLayout` vertical (`layoutOpciones`), ancho completo (`match_parent`), posicionado `24dp` debajo del título, con `paddingHorizontal="16dp"`.
  * `Switch` (`swNotificaciones`) dentro del `LinearLayout`, con texto *"Recibir notificaciones"* y ancho completo.
  * Cada línea del XML está comentada con su propósito (en bloques `<!-- -->` fuera de las etiquetas).
* **Lógica (`PreferenciasActivity.kt`):** Recupera el extra `"usuario"` enviado desde `BienvenidaActivity` y personaliza el mensaje del `TextView` (`Preferencias de <usuario>`).
* **Registro en Manifiesto:** Declaración de `PreferenciasActivity` en `AndroidManifest.xml` con `android:exported="false"`.

---

### 6. Selector de idioma con Spinner (`strings.xml` y `activity_preferencias.xml`)
* **`strings.xml`:** Se añadió un `string-array` llamado `idiomas` con dos ítems: `Español` e `Inglés`, comentado línea a línea.
* **`activity_preferencias.xml`:** Se agregó un `Spinner` (`spIdioma`) dentro del `LinearLayout`, con `android:entries="@array/idiomas"` para cargar las opciones directamente desde XML sin necesidad de `ArrayAdapter` en Kotlin.

---

### 7. Selección de unidad de temperatura con RadioGroup (`activity_preferencias.xml`)
* **`RadioGroup`** (`rgUnidad`, orientación vertical) agregado dentro del `LinearLayout`, debajo del `Spinner`.
* Contiene dos **`RadioButton`**:
  * `rbCelsius` — texto *"Celsius"*, marcado por defecto (`android:checked="true"`).
  * `rbFahrenheit` — texto *"Fahrenheit"*, desmarcado por defecto.
* El `RadioGroup` garantiza selección mutuamente excluyente: marcar uno desmarca el otro automáticamente.

---

### 8. Guardado con ProgressBar y Toast (`activity_preferencias.xml` y `PreferenciasActivity.kt`)
* **`ProgressBar`** (`pbGuardando`) circular pequeño (`style="?android:attr/progressBarStyleSmall"`), inicialmente oculto (`android:visibility="gone"`), centrado horizontalmente con `layout_gravity`.
* **`Button`** (`btnGuardarPreferencia`), texto *"Guardar"*, con evento `android:onClick="onGuardarPreferenciaClick"`.
* **Método `onGuardarPreferenciaClick(view: View)` en `PreferenciasActivity.kt`:**
  1. Hace visible el `ProgressBar` (`View.VISIBLE`).
  2. Usa `Handler(Looper.getMainLooper()).postDelayed({ ... }, 1000)` para ejecutar tras 1 segundo en el hilo principal.
  3. Dentro del bloque: oculta el `ProgressBar` (`View.GONE`), lee el `RadioButton` seleccionado con `rgUnidad.checkedRadioButtonId` y muestra un `Toast` con la unidad elegida.

---

### 9. Cierre de sesión (`activity_preferencias.xml` y `PreferenciasActivity.kt`)
* **`Button`** (`btnCerrarSesion`), texto *"Cerrar sesión"*, con evento `android:onClick="onCerrarSesionClick"`, agregado debajo del botón Guardar.
* **Método `onCerrarSesionClick(view: View)` en `PreferenciasActivity.kt`:**
  * Crea un `Intent` hacia `MainActivity`.
  * Aplica los flags `FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_CLEAR_TASK` para destruir todo el back stack y dejar el login como pantalla raíz única.
  * El usuario **no puede volver atrás** con el botón Back tras cerrar sesión.

---

### 10. Flujo de navegación completo

```
MainActivity (login)
  └──[credenciales válidas]──→ BienvenidaActivity
                                   └──[btnPreferencias]──→ PreferenciasActivity
                                                               ├── [btnGuardarPreferencia] → ProgressBar 1s → Toast
                                                               └── [btnCerrarSesion] ──→ MainActivity (back stack limpio)
```

Cada actividad recibe el nombre del usuario como extra de `Intent` y lo muestra en pantalla.

---

### 11. Control de Versiones y Publicación en GitHub
* Inicialización del repositorio Git local y configuración de exclusiones en `.gitignore`.
* Publicación del repositorio público en GitHub: [https://github.com/CarlosMichea/PrimeraApp](https://github.com/CarlosMichea/PrimeraApp).
