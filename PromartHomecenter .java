import java.util.*;

public class PromartHomecenter {
    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();
    static String correoRegistrado = "";
    static String contraseñaRegistrada = "";

    static String[] marcasLaptop = {
            "Acer", "Acer", "Acer",
            "Dell", "Dell", "Dell",
            "Lenovo", "Lenovo", "Lenovo",
            "HP", "HP", "HP",
            "Asus", "Asus", "Asus",
            "MSI", "MSI", "MSI"
    };
    static double[] preciosLaptop = {
            1200, 1350, 1400,
            1500, 1600, 1750,
            1100, 1250, 1350,
            1300, 1450, 1600,
            1400, 1550, 1700,
            1700, 1850, 2000
    };

    public static void main(String[] args) {
        mostrarOfertas();
        if (login()) {
            menuPrincipal();
        }
    }

    static void mostrarOfertas() {
        System.out.println("🎉 ¡Ofertas especiales del día en PROMART HOMECENTER! 🎉");
        String[] ofertas = {
                "🔧 20% de descuento en herramientas eléctricas.",
                "🪑 15% de descuento en muebles para el hogar.",
                "🎨 10% de descuento en pinturas y acabados.",
                "💡 25% de descuento en iluminación LED.",
                "🖥️ 30% de descuento en laptops seleccionadas."
        };
        int cantidadOfertas = random.nextInt(ofertas.length) + 1;
        for (int i = 0; i < cantidadOfertas; i++) {
            int ofertaAleatoria = random.nextInt(ofertas.length);
            System.out.println("- " + ofertas[ofertaAleatoria]);
        }
        System.out.println("───────────────────────────────────────");
    }

    static boolean validarCorreo(String correo) {
        return correo.contains("@") && correo.endsWith(".com");
    }

    static boolean login() {
        System.out.println("Bienvenido a PROMART HOMECENTER");
        System.out.println("1. Registrarse");
        System.out.println("2. Iniciar sesión");
        System.out.print("Seleccione una opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        if (opcion == 1) {
            System.out.print("Ingrese su correo: ");
            String correo = scanner.nextLine();
            while (!validarCorreo(correo)) {
                System.out.print("Correo inválido. Ingrese un correo válido (debe contener '@' y terminar en '.com'): ");
                correo = scanner.nextLine();
            }
            correoRegistrado = correo;

            System.out.print("Ingrese su contraseña: ");
            contraseñaRegistrada = scanner.nextLine();
            System.out.println("✅ Registro exitoso. Inicie sesión.");
            return login();
        } else if (opcion == 2) {
            System.out.print("Ingrese su correo: ");
            String correo = scanner.nextLine();
            while (!validarCorreo(correo)) {
                System.out.print("Correo inválido. Ingrese un correo válido (debe contener '@' y terminar en '.com'): ");
                correo = scanner.nextLine();
            }
            System.out.print("Ingrese su contraseña: ");
            String contraseña = scanner.nextLine();
            if (correo.equals(correoRegistrado) && contraseña.equals(contraseñaRegistrada)) {
                System.out.println("✅ Inicio de sesión exitoso.");
                return true;
            } else {
                System.out.println("❌ Datos incorrectos. Intente de nuevo.");
                return login();
            }
        } else {
            System.out.println("❌ Opción no válida.");
            return login();
        }
    }

    static void menuPrincipal() {
        System.out.println("───────────────────────────────────────");
        System.out.println("Menú Principal:");
        System.out.println("1. Categoría: Tecnología");
        System.out.println("2. Categoría: Electrodomésticos");
        System.out.println("3. Categoría: Decoración");
        System.out.print("Seleccione una opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                categoriaTecnologia();
                break;
            case 2:
                categoriaElectrodomesticos();
                break;
            case 3:
                categoriaDecoracion();
                break;
            default:
                System.out.println("❌ Opción no válida.");
                menuPrincipal();
        }
    }

    static void categoriaTecnologia() {
        System.out.println("───────────────────────────────────────");
        System.out.println("Dispositivos disponibles:");
        System.out.println("1. Laptop");
        System.out.println("2. Smartphone");
        System.out.println("3. Tablet");
        System.out.print("Seleccione un dispositivo: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                seleccionarLaptopMarca();
                break;
            case 2:
                seleccionarDispositivo("Smartphone");
                break;
            case 3:
                seleccionarDispositivo("Tablet");
                break;
            default:
                System.out.println("❌ Opción no válida.");
                categoriaTecnologia();
        }
    }

    static void seleccionarLaptopMarca() {
        System.out.println("───────────────────────────────────────");
        System.out.println("Seleccione la marca de laptop:");
        System.out.println("1. Acer");
        System.out.println("2. Dell");
        System.out.println("3. Lenovo");
        System.out.println("4. HP");
        System.out.println("5. Asus");
        System.out.println("6. MSI");
        System.out.print("Ingrese su opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        String marca = switch (opcion) {
            case 1 -> "Acer";
            case 2 -> "Dell";
            case 3 -> "Lenovo";
            case 4 -> "HP";
            case 5 -> "Asus";
            case 6 -> "MSI";
            default -> {
                System.out.println("❌ Opción no válida.");
                seleccionarLaptopMarca();
                yield "";
            }
        };
        if (!marca.isEmpty()) opcionBusquedaPrecio(marca);
    }

    static void opcionBusquedaPrecio(String marca) {
        System.out.println("───────────────────────────────────────");
        System.out.println("Marca seleccionada: " + marca);
        System.out.println("¿Desea filtrar por rango de precio?");
        System.out.println("1. Sí");
        System.out.println("2. No");
        System.out.print("Ingrese su opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        if (opcion == 1) {
            filtrarPrecioLaptop(marca);
        } else if (opcion == 2) {
            mostrarProductosLaptop(marca, -1, -1);
        } else {
            System.out.println("❌ Opción no válida.");
            opcionBusquedaPrecio(marca);
        }
    }

    static void filtrarPrecioLaptop(String marca) {
        System.out.println("───────────────────────────────────────");
        double min, max;
        while (true) {
            System.out.print("Ingrese el precio mínimo: S/");
            min = scanner.nextDouble();
            System.out.print("Ingrese el precio máximo: S/");
            max = scanner.nextDouble();
            scanner.nextLine();
            if (min <= max) break;
            System.out.println("❌ El precio mínimo debe ser menor o igual al máximo. Intente de nuevo.");
        }
        mostrarProductosLaptop(marca, min, max);
    }

    static void mostrarProductosLaptop(String marcaFiltro, double min, double max) {
        System.out.println("───────────────────────────────────────");
        System.out.println("Productos disponibles:");

        int[] indicesValidos = new int[marcasLaptop.length];
        int contador = 0;

        for (int i = 0; i < marcasLaptop.length; i++) {
            boolean coincideMarca = marcasLaptop[i].equalsIgnoreCase(marcaFiltro);
            boolean coincidePrecio = (min == -1 && max == -1) || (preciosLaptop[i] >= min && preciosLaptop[i] <= max);
            if (coincideMarca && coincidePrecio) {
                indicesValidos[contador++] = i;
            }
        }

        if (contador == 0) {
            System.out.println("❌ No hay laptops disponibles con ese filtro.");
            seleccionarLaptopMarca();
            return;
        }

        for (int i = 0; i < contador && i < 5; i++) {
            int idx = indicesValidos[i];
            System.out.println((i + 1) + ". Marca: " + marcasLaptop[idx] + " - Precio: S/" + preciosLaptop[idx]);
        }

        System.out.print("Seleccione el producto que desea comprar: ");
        int seleccion = scanner.nextInt();
        scanner.nextLine();

        if (seleccion < 1 || seleccion > contador || seleccion > 5) {
            System.out.println("❌ Opción no válida.");
            mostrarProductosLaptop(marcaFiltro, min, max);
            return;
        }

        int idx = indicesValidos[seleccion - 1];
        seleccionarColor(marcasLaptop[idx] + " Laptop", preciosLaptop[idx]);
    }

    static void seleccionarColor(String producto, double precio) {
        System.out.println("───────────────────────────────────────");
        System.out.println("Seleccione un color:");
        System.out.println("1. Negro");
        System.out.println("2. Gris");
        System.out.println("3. Azul");
        System.out.print("Ingrese su opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        String color = switch (opcion) {
            case 1 -> "Negro";
            case 2 -> "Gris";
            case 3 -> "Azul";
            default -> {
                System.out.println("❌ Opción no válida.");
                seleccionarColor(producto, precio);
                yield "";
            }
        };

        if (!color.isEmpty()) seleccionarMetodoPago(producto, color, precio);
    }

    static void seleccionarDispositivo(String dispositivo) {
        double precio = (dispositivo.equals("Smartphone")) ? 800 : 600;
        System.out.println("───────────────────────────────────────");
        System.out.println("Precio del " + dispositivo + ": S/" + precio);
        seleccionarColor(dispositivo, precio);
    }

    static void seleccionarMetodoPago(String producto, String color, double precio) {
        System.out.println("───────────────────────────────────────");
        System.out.println("Seleccione un método de pago:");
        System.out.println("1. Tarjeta");
        System.out.println("2. Efectivo");
        System.out.print("Ingrese su opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        String metodo = "";
        String numeroTarjeta = "";
        String cvv = "";

        if (opcion == 1) {
            metodo = "Tarjeta";
            do {
                System.out.print("Ingrese el número de tarjeta (16 dígitos): ");
                numeroTarjeta = scanner.nextLine();
            } while (!numeroTarjeta.matches("\\d{16}"));

            do {
                System.out.print("Ingrese el CVV (3 dígitos): ");
                cvv = scanner.nextLine();
            } while (!cvv.matches("\\d{3}"));
        } else if (opcion == 2) {
            metodo = "Efectivo";
        } else {
            System.out.println("❌ Opción no válida.");
            seleccionarMetodoPago(producto, color, precio);
            return;
        }

        generarBoleta(producto, color, precio, metodo, numeroTarjeta, cvv);
    }

    static void generarBoleta(String producto, String color, double precio, String metodo, String numeroTarjeta, String cvv) {
        System.out.println("───────────────────────────────────────");
        System.out.println("🧾 Boleta de compra:");
        System.out.println("Producto: " + producto);
        System.out.println("Color: " + color);
        System.out.println("Precio: S/" + precio);
        System.out.println("Método de pago: " + metodo);

        if (metodo.equals("Tarjeta")) {
            System.out.println("Número de tarjeta: ** ** ** " + numeroTarjeta.substring(12));
            System.out.println("CVV: *");
        }

        double descuento = random.nextInt(21); // 0-20%
        double total = precio - (precio * descuento / 100);
        System.out.println("Descuento aplicado: " + descuento + "%");
        System.out.println("Total a pagar: S/" + total);

        if (metodo.equals("Efectivo")) {
            double efectivo;
            do {
                System.out.print("Ingrese el monto con el que paga en efectivo: S/");
                efectivo = scanner.nextDouble();
                scanner.nextLine();
                if (efectivo < total) {
                    System.out.println("❌ Monto insuficiente. Intente de nuevo.");
                }
            } while (efectivo < total);
            System.out.println("Cambio: S/" + (efectivo - total));
        }

        System.out.println("Gracias por su compra en PROMART HOMECENTER.");
    }

    static void categoriaElectrodomesticos() {
        System.out.println("Categoría en desarrollo.");
        menuPrincipal();
    }

    static void categoriaDecoracion() {
        System.out.println("Categoría en desarrollo.");
        menuPrincipal();
    }
}
