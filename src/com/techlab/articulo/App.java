package com.techlab.articulo;

import java.util.ArrayList;
import java.util.Scanner;
import com.techlab.articulo.model.Articulo;
import com.techlab.articulo.model.ArticuloAlimenticio;
import com.techlab.articulo.model.ArticuloElectronico;
import com.techlab.articulo.model.Categoria;

/**
 * Clase principal que administra la interacción con el usuario mediante la consola.
 */
public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Articulo> articulos = new ArrayList<>();
        ArrayList<Categoria> categorias = new ArrayList<>();

        precargarCategorias(categorias);

        int opcion;
        do {
            System.out.println("======================================================");
            System.out.println("SISTEMA DE ARTÍCULOS - CLASE 4 (HERENCIA Y TO_STRING)");
            System.out.println("======================================================");
            System.out.println("1 - Ingresar artículo");
            System.out.println("2 - Listar artículos");
            System.out.println("3 - Consultar un artículo");
            System.out.println("4 - Modificar un artículo");
            System.out.println("5 - Eliminar un artículo");
            System.out.println("6 - Listar categorías");
            System.out.println("0 - Salir");
            System.out.println("======================================================");

            opcion = leerEntero(scanner, "Ingrese una opción: ");

            switch (opcion) {
                case 1:
                    ingresarArticulo(scanner, articulos, categorias);
                    break;
                case 2:
                    listarArticulos(articulos);
                    break;
                case 3:
                    consultarArticulo(scanner, articulos);
                    break;
                case 4:
                    modificarArticulo(scanner, articulos, categorias);
                    break;
                case 5:
                    eliminarArticulo(scanner, articulos);
                    break;
                case 6:
                    listarCategorias(categorias);
                    break;
                case 0:
                    System.out.println("Gracias por utilizar el sistema. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción incorrecta. Por favor, ingrese una opción válida.");
                    break;
            }
            System.out.println();
        } while (opcion != 0);

        scanner.close();
    }

    /**
     * Precarga las categorías iniciales en la lista recibida por parámetro.
     */
    public static void precargarCategorias(ArrayList<Categoria> categorias) {
        categorias.add(new Categoria(1, "Electrónica", "Productos tecnológicos y electrónicos"));
        categorias.add(new Categoria(2, "Periféricos", "Accesorios para computadora"));
        categorias.add(new Categoria(3, "Alimentos", "Productos alimenticios"));
        categorias.add(new Categoria(4, "Limpieza", "Artículos de limpieza del hogar"));
    }

    /**
     * Permite registrar un nuevo artículo (electrónico o alimenticio) aplicando polimorfismo.
     */
    public static void ingresarArticulo(
            Scanner scanner,
            ArrayList<Articulo> articulos,
            ArrayList<Categoria> categorias
    ) {
        System.out.println("\n--- INGRESAR ARTÍCULO ---");
        System.out.println("1 - Artículo electrónico");
        System.out.println("2 - Artículo alimenticio");

        int tipoArticulo;
        while (true) {
            tipoArticulo = leerEntero(scanner, "Seleccione el tipo de artículo: ");
            if (tipoArticulo == 1 || tipoArticulo == 2) {
                break;
            }
            System.out.println("Tipo de artículo inválido. Debe seleccionar 1 o 2.");
        }

        int codigo = leerEnteroNoNegativo(scanner, "Ingrese el código del artículo: ");

        if (buscarArticuloPorCodigo(articulos, codigo) != null) {
            System.out.println("Error: Ya existe un artículo con el código " + codigo + ". Ingreso cancelado.");
            return;
        }

        String nombre = leerTextoNoVacio(scanner, "Ingrese el nombre del artículo: ");
        double precio = leerDoubleNoNegativo(scanner, "Ingrese el precio del artículo: ");

        System.out.println("\n--- CATEGORÍAS DISPONIBLES ---");
        for (Categoria cat : categorias) {
            System.out.println(cat.getCodigo() + " - " + cat.getNombre());
        }

        Categoria categoria = pedirCategoriaExistente(scanner, categorias);

        Articulo articulo;
        if (tipoArticulo == 1) {
            int garantiaMeses = leerEnteroNoNegativo(scanner, "Ingrese la garantía en meses: ");
            articulo = new ArticuloElectronico(codigo, nombre, precio, categoria, garantiaMeses);
        } else {
            int diasVencimiento = leerEnteroNoNegativo(scanner, "Ingrese los días restantes para el vencimiento: ");
            articulo = new ArticuloAlimenticio(codigo, nombre, precio, categoria, diasVencimiento);
        }

        articulos.add(articulo);

        System.out.println("\nArtículo ingresado correctamente.");
        System.out.println("Resumen del objeto creado:\n");
        System.out.println(articulo);
    }

    /**
     * Muestra en pantalla el listado de todos los artículos cargados usando polimorfismo y toString().
     */
    public static void listarArticulos(ArrayList<Articulo> articulos) {
        if (articulos.isEmpty()) {
            System.out.println("No hay artículos cargados.");
            return;
        }

        System.out.println("\n--- LISTADO DE ARTÍCULOS ---");
        for (Articulo articulo : articulos) {
            System.out.println(articulo);
            System.out.println();
        }
    }

    /**
     * Consulta un artículo por su código y muestra su representación y detalle específico.
     */
    public static void consultarArticulo(Scanner scanner, ArrayList<Articulo> articulos) {
        if (articulos.isEmpty()) {
            System.out.println("No hay artículos cargados.");
            return;
        }

        int codigo = leerEntero(scanner, "Ingrese el código del artículo a consultar: ");
        Articulo articulo = buscarArticuloPorCodigo(articulos, codigo);

        if (articulo == null) {
            System.out.println("El artículo no existe.");
            return;
        }

        System.out.println("\n--- INFORMACIÓN DEL ARTÍCULO ---");
        System.out.println(articulo);
        System.out.println("Detalle específico: " + articulo.getDetalleEspecifico());
        if (articulo instanceof ArticuloElectronico) {
            ArticuloElectronico electronico = (ArticuloElectronico) articulo;
            System.out.println("Mesa de ayuda para reclamos: " + electronico.nroTelMesaDeAyudaParaReclamos());
        }
    }

    /**
     * Modifica los atributos de un artículo existente utilizando setters y casteo con instanceof.
     */
    public static void modificarArticulo(
            Scanner scanner,
            ArrayList<Articulo> articulos,
            ArrayList<Categoria> categorias
    ) {
        if (articulos.isEmpty()) {
            System.out.println("No hay artículos cargados.");
            return;
        }

        int codigo = leerEntero(scanner, "Ingrese el código del artículo a modificar: ");
        Articulo articulo = buscarArticuloPorCodigo(articulos, codigo);

        if (articulo == null) {
            System.out.println("El artículo no existe.");
            return;
        }

        System.out.println("\nModificando artículo: " + articulo.getNombre() + " (Código: " + articulo.getCodigo() + ")");
        String nuevoNombre = leerTextoNoVacio(scanner, "Ingrese el nuevo nombre del artículo: ");
        double nuevoPrecio = leerDoubleNoNegativo(scanner, "Ingrese el nuevo precio del artículo: ");

        System.out.println("\n--- CATEGORÍAS DISPONIBLES ---");
        for (Categoria cat : categorias) {
            System.out.println(cat.getCodigo() + " - " + cat.getNombre());
        }

        Categoria nuevaCategoria = pedirCategoriaExistente(scanner, categorias);

        articulo.setNombre(nuevoNombre);
        articulo.setPrecio(nuevoPrecio);
        articulo.setCategoria(nuevaCategoria);

        if (articulo instanceof ArticuloElectronico) {
            ArticuloElectronico electronico = (ArticuloElectronico) articulo;
            int nuevaGarantia = leerEnteroNoNegativo(scanner, "Ingrese la nueva garantía en meses: ");
            electronico.setGarantiaMeses(nuevaGarantia);
        } else if (articulo instanceof ArticuloAlimenticio) {
            ArticuloAlimenticio alimenticio = (ArticuloAlimenticio) articulo;
            int nuevosDias = leerEnteroNoNegativo(scanner, "Ingrese los nuevos días para vencimiento: ");
            alimenticio.setDiasParaVencimiento(nuevosDias);
        }

        System.out.println("Artículo modificado correctamente.");
    }

    /**
     * Elimina un artículo de la lista por su código si existe.
     */
    public static void eliminarArticulo(Scanner scanner, ArrayList<Articulo> articulos) {
        if (articulos.isEmpty()) {
            System.out.println("No hay artículos cargados.");
            return;
        }

        int codigo = leerEntero(scanner, "Ingrese el código del artículo a eliminar: ");
        Articulo articulo = buscarArticuloPorCodigo(articulos, codigo);

        if (articulo == null) {
            System.out.println("El artículo no existe.");
            return;
        }

        articulos.remove(articulo);
        System.out.println("Artículo eliminado correctamente.");
    }

    /**
     * Recorre la lista de categorías y muestra cada una utilizando su método toString().
     */
    public static void listarCategorias(ArrayList<Categoria> categorias) {
        if (categorias.isEmpty()) {
            System.out.println("No hay categorías cargadas.");
            return;
        }

        System.out.println("\n--- LISTADO DE CATEGORÍAS ---");
        for (Categoria cat : categorias) {
            System.out.println(cat);
            System.out.println();
        }
    }

    // =========================================================================
    // Métodos auxiliares obligatorios
    // =========================================================================

    /**
     * Busca un artículo por su código en la lista.
     * @return El objeto Articulo encontrado o null si no existe.
     */
    public static Articulo buscarArticuloPorCodigo(ArrayList<Articulo> articulos, int codigo) {
        for (Articulo articulo : articulos) {
            if (articulo.getCodigo() == codigo) {
                return articulo;
            }
        }
        return null;
    }

    /**
     * Busca una categoría por su código en la lista.
     * @return El objeto Categoria encontrado o null si no existe.
     */
    public static Categoria buscarCategoriaPorCodigo(ArrayList<Categoria> categorias, int codigo) {
        for (Categoria categoria : categorias) {
            if (categoria.getCodigo() == codigo) {
                return categoria;
            }
        }
        return null;
    }

    /**
     * Solicita al usuario un código de categoría y verifica que exista.
     * Repite la solicitud hasta que se ingrese un código válido.
     */
    public static Categoria pedirCategoriaExistente(Scanner scanner, ArrayList<Categoria> categorias) {
        while (true) {
            int codigo = leerEntero(scanner, "Ingrese el código de la categoría: ");
            Categoria categoria = buscarCategoriaPorCodigo(categorias, codigo);
            if (categoria != null) {
                return categoria;
            }
            System.out.println("Categoría inexistente. Debe ingresar un código de categoría válido.");
        }
    }

    /**
     * Lee un número entero de la consola con control de excepciones.
     */
    public static int leerEntero(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine();
            try {
                return Integer.parseInt(entrada.trim());
            } catch (NumberFormatException e) {
                System.out.println("Error: Formato de número incorrecto. Debe ingresar un número entero.");
            }
        }
    }

    /**
     * Lee un número entero mayor o igual a cero.
     */
    public static int leerEnteroNoNegativo(Scanner scanner, String mensaje) {
        while (true) {
            int valor = leerEntero(scanner, mensaje);
            if (valor >= 0) {
                return valor;
            }
            System.out.println("Error: El valor no puede ser negativo.");
        }
    }

    /**
     * Lee un número decimal mayor o igual a cero con control de NaN, infinitos y negativos.
     */
    public static double leerDoubleNoNegativo(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine().trim().replace(',', '.');
            try {
                double valor = Double.parseDouble(entrada);
                if (Double.isNaN(valor) || Double.isInfinite(valor)) {
                    System.out.println("Error: El valor ingresado debe ser un número real finito.");
                    continue;
                }
                if (valor < 0) {
                    System.out.println("Error: El precio no puede ser negativo.");
                    continue;
                }
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Error: Formato de número incorrecto. Debe ingresar un número decimal válido.");
            }
        }
    }

    /**
     * Lee un texto que no puede estar vacío ni contener solo espacios en blanco.
     */
    public static String leerTextoNoVacio(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine().trim();
            if (!entrada.isEmpty()) {
                return entrada;
            }
            System.out.println("Error: El campo no puede estar vacío.");
        }
    }
}
