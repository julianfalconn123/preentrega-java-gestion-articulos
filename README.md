# Preentrega Java: Sistema de Gestión de Artículos

Proyecto de consola desarrollado en **Java** aplicando los conceptos de **Programación Orientada a Objetos (POO)**:
- Herencia mediante `extends`
- Clases y métodos abstractos
- Polimorfismo
- Sobrescritura de métodos (`@Override`)
- Métodos `toString()`
- Encapsulamiento con constructores, getters y setters
- Uso de `super` y `this`
- Manejo de colecciones con `ArrayList`
- Validaciones y estructuras de control

---

## Estructura del Proyecto

```
herencia_polimorfismo_tostring/
├── bin/                             # Archivos .class compilados
├── src/                             # Código fuente
│   └── com/techlab/articulo/
│       ├── App.java                 # Clase principal interactiva y menú
│       └── model/
│           ├── Articulo.java             # Clase abstracta base
│           ├── ArticuloElectronico.java  # Subclase para artículos electrónicos
│           ├── ArticuloAlimenticio.java  # Subclase para artículos alimenticios
│           └── Categoria.java            # Clase de categorías
└── README.md
```

---

## Compilación y Ejecución

### Desde la consola (Terminal / PowerShell / Bash)

1. **Ubicarse en el directorio del proyecto**:
   ```bash
   cd herencia_polimorfismo_tostring
   ```

2. **Compilar el código fuente**:
   ```bash
   javac -encoding UTF-8 -d bin src/com/techlab/articulo/model/*.java src/com/techlab/articulo/*.java
   ```

3. **Ejecutar la aplicación**:
   ```bash
   java -cp bin com.techlab.articulo.App
   ```

---

## Funcionalidades del Menú

1. **Ingresar artículo**: Permite seleccionar si es *Electrónico* (pide garantía en meses) o *Alimenticio* (pide días para vencimiento). Valida que el código no esté duplicado, que el nombre no esté vacío, que el precio sea mayor o igual a 0, y asocia una categoría existente.
2. **Listar artículos**: Muestra todos los artículos registrados utilizando polimorfismo y el método `toString()`.
3. **Consultar un artículo**: Busca un artículo por su código e imprime su representación completa más su detalle específico (y mesa de ayuda si aplica).
4. **Modificar un artículo**: Permite cambiar nombre, precio, categoría y el atributo específico del subtipo correspondiente mediante `instanceof` y casting.
5. **Eliminar un artículo**: Remueve un artículo del listado por su código.
6. **Listar categorías**: Muestra las 4 categorías precargadas por defecto.
0. **Salir**: Finaliza la ejecución del programa.
