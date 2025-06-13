import java.util.*;
import java.util.regex.Pattern;

public class norkys {
    Scanner sc = new Scanner(System.in);

    ArrayList<String> platos;
    ArrayList<Double> precios;
    ArrayList<String> descripciones;
    ArrayList<String> tiposPlatos;
    ArrayList<String> categorias;

    public void cargarDatos(){
        platos=new ArrayList<>(Arrays.asList(
                "Pollo a la Brasa Entero",
                "1/2 Pollo a la Brasa",
                "1/4 Pollo a la Brasa",
                "Mostrito",
                "Parrilla Clásica",
                "1/2 Parrilla Brasa",
                "Parrilla para Dos",
                "Churrasco",
                "Bife a la Parrilla",
                "Chuleta a la Parrilla",
                "Pechuga a la Parrilla",
                "Lomo Saltado",
                "Pollo Saltado",
                "Tallarín Saltado de Carne",
                "Tallarín Saltado de Pollo",
                "Arroz Chaufa de Pollo",
                "Arroz Chaufa de Carne",
                "Arroz Chaufa Mixto",
                "Salchipapa",
                "Alitas (Wings)",
                "Piezas Broaster",
                "Tamal",
                "Papa Rellena",
                "Causa Rellena de Pollo",
                "Anticuchos",
                "Piqueo Norky's",
                "GASEOSA COCA COLA 1.5 LT",
                "GASEOSA INCA KOLA 1.5 LT",
                "GASEOSA COCA COLA PERSONAL",
                "GASEOSA INCA KOLA PERSONAL",
                "CHICHA MORADA JUGUERA",
                "LIMONADA NATURAL",
                "AGUA EMBOTELLADA (SIN GAS)",
                "AGUA EMBOTELLADA (CON GAS)",
                "CERVEZA CRISTAL",
                "CERVEZA CUSQUEÑA"
        ));
        precios=new ArrayList<>(Arrays.asList(
                70.00,
                38.00,
                25.00,
                28.00,
                65.00,
                40.00,
                120.00,
                45.00,
                50.00,
                42.00,
                40.00,
                35.00,
                30.00,
                30.00,
                28.00,
                20.00,
                22.00,
                25.00,
                12.00,
                25.00,
                20.00,
                8.00,
                7.00,
                10.00,
                20.00,
                45.00,
                10.00, // GASEOSA COCA COLA 1.5 LT
                10.00, // GASEOSA INCA KOLA 1.5 LT
                5.00,  // GASEOSA COCA COLA PERSONAL
                5.00,  // GASEOSA INCA KOLA PERSONAL
                8.00,  // CHICHA MORADA JUGUERA (vaso grande o jarra pequeña)
                7.00,  // LIMONADA NATURAL (vaso grande)
                4.00,  // AGUA EMBOTELLADA (SIN GAS)
                5.00,  // AGUA EMBOTELLADA (CON GAS)
                12.00, // CERVEZA CRISTAL (botella personal)
                14.00  // CERVEZA CUSQUEÑA (botella personal)
        ));
        descripciones=new ArrayList<>(Arrays.asList(
                "El clásico pollo a la brasa entero, jugoso y dorado, acompañado de papas fritas crocantes y ensalada fresca.",
                "Media porción del inconfundible pollo a la brasa, servido con papas fritas y ensalada.",
                "Un cuarto de pollo a la brasa, ideal para una porción individual, acompañado de papas fritas y ensalada.",
                "La combinación perfecta: un cuarto de pollo a la brasa, acompañado de una porción de arroz chaufa y papas fritas.",
                "Variedad de carnes a la parrilla como bife, chuleta, pollo, chorizos y más, servido con papas fritas y ensalada.",
                "Media porción de la parrilla con una selección de carnes asadas y sus acompañamientos.",
                "Una generosa porción de parrilla pensada para compartir entre dos personas, con diversas carnes y guarniciones.",
                "Tierno corte de carne a la parrilla, usualmente acompañado de arroz, papas fritas y ensalada.",
                "Un jugoso bife cocinado a la parrilla, servido con sus guarniciones tradicionales.",
                "Deliciosa chuleta de cerdo a la parrilla, acompañada de papas fritas y ensalada.",
                "Filete de pechuga de pollo a la parrilla, una opción ligera y sabrosa, con sus acompañamientos.",
                "Clásico plato criollo con trozos de lomo de res salteados al wok con cebolla, tomate y ají amarillo, acompañado de arroz y papas fritas.",
                "Tiernos trozos de pollo salteados al wok con verduras, servido con arroz blanco y papas fritas.",
                "Fideos salteados al wok con trozos de carne de res, verduras frescas y salsa de soya.",
                "Fideos salteados al wok con trozos de pollo, verduras y un toque oriental.",
                "Arroz frito al estilo oriental con trozos de pollo, huevo revuelto y cebolla china.",
                "Arroz frito al estilo oriental con trozos de carne, huevo revuelto y cebolla china.",
                "Arroz frito al estilo oriental con la combinación de carne, pollo, huevo y cebolla china.",
                "Clásicas salchichas frankfurter picadas y fritas, mezcladas con papas fritas.",
                "Crujientes alitas de pollo, bañadas en diferentes salsas (ej. BBQ, picante), ideales como piqueo.",
                "Piezas de pollo empanizadas y fritas estilo broaster, con una cubierta crujiente y jugosas por dentro.",
                "Masa de maíz rellena de pollo o cerdo, cocida al vapor, un plato tradicional peruano.",
                "Papa cocida y prensada, rellena de un guiso sazonado de carne, huevo y aceituna, luego frita.",
                "Causa de papa amarilla prensada, rellena de una mezcla de pollo deshilachado y mayonesa.",
                "Brochetas de corazón de res marinado, cocinadas a la parrilla, servidas con papa dorada y choclo.",
                "Una selección variada de piqueos y bocadillos, ideal para compartir y probar un poco de todo.",
                "Refrescante gaseosa Coca Cola en presentación de 1.5 litros, ideal para compartir.",
                "La bebida de sabor nacional más emblemática del Perú, Inca Kola, en botella de 1.5 litros.",
                "Bebida personal de Coca Cola, perfecta para acompañar tu comida.",
                "Inca Kola en formato personal, la bebida dorada que complementa cualquier plato.",
                "Tradicional bebida peruana a base de maíz morado, refrescante y natural.",
                "Refrescante bebida preparada con limones frescos, ideal para calmar la sed.",
                "Agua mineral embotellada sin gas, una opción saludable para hidratarse.",
                "Agua mineral embotellada con gas, para quienes prefieren un toque efervescente.",
                "Cerveza peruana tipo lager, una de las más populares del país, en botella personal.",
                "Cerveza premium peruana, reconocida por su calidad y variedad de estilos, en botella personal."
        ));
        tiposPlatos=new ArrayList<>(Arrays.asList(
                "POLLOS A LA BRASA",
                "POLLOS A LA BRASA",
                "POLLOS A LA BRASA",
                "POLLOS A LA BRASA",
                "PARRILLAS",
                "PARRILLAS",
                "PARRILLAS",
                "PARRILLAS",
                "PARRILLAS",
                "PARRILLAS",
                "PARRILLAS",
                "SALTADOS Y ARROCES CHAUFA",
                "SALTADOS Y ARROCES CHAUFA",
                "SALTADOS Y ARROCES CHAUFA",
                "SALTADOS Y ARROCES CHAUFA",
                "SALTADOS Y ARROCES CHAUFA",
                "SALTADOS Y ARROCES CHAUFA",
                "SALTADOS Y ARROCES CHAUFA",
                "PIQUEOS Y ENTRADAS",
                "PIQUEOS Y ENTRADAS",
                "PIQUEOS Y ENTRADAS",
                "PIQUEOS Y ENTRADAS",
                "PIQUEOS Y ENTRADAS",
                "PIQUEOS Y ENTRADAS",
                "PIQUEOS Y ENTRADAS",
                "PIQUEOS Y ENTRADAS",
                "GASEOSAS",
                "GASEOSAS",
                "GASEOSAS",
                "GASEOSAS",
                "BEBIDAS NATURALES",
                "BEBIDAS NATURALES",
                "AGUAS",
                "AGUAS",
                "CERVEZAS",
                "CERVEZAS"
        ));
        categorias=new ArrayList<>(Arrays.asList(
                "Pollos a la brasa","Parrillas","Saltados y arroces chaufa","Piqueos y entradas","Gaseosas","Bebidas Naturales","Aguas","Cervezas"
        ));
        carrito = new int[platos.size()];
    }

    int[] carrito;

    double tasaIgv = 0.18;

    boolean cuentaRegistrada=false;

    boolean sesionIniciada =false;

    ArrayList<String> emails;
    ArrayList<String> contraseñas;
    ArrayList<String> roles;
    String usuarioActual;
    String emailGuardado = "";
    String contraseñaGuardada = "";
    String nombreUsuario="";

    public void menuPrincipal() {
        System.out.println("\n--------- BIENVENIDO  A NORKYS -----------");
        System.out.println(",~.\n" +
                "         ,-'__ `-,\n" +
                "        {,-'  `. }              ,')\n" +
                "       ,( a )   `-.__         ,',')~,\n" +
                "      <=.) (         `-.__,==' ' ' '}\n" +
                "        (   )                      /\n" +
                "         `-'\\   ,                    )\n" +
                "             |  \\        `~.        /\n" +
                "             \\   `._        \\      /\n" +
                "              \\     `._____,'    ,'\n" +
                "               `-.             ,'\n" +
                "                  `-._      _,'\n" +
                "                      `\"'\"");
        if (sesionIniciada){
            System.out.println("Hola "+nombreUsuario);
        }
        System.out.println("---------------------------------------------");
        System.out.println("Elige alguna de las siguientes opciones");
        System.out.println(" 1) Ver todos los platos");
        System.out.println(" 2) Ver por categorias");
        System.out.println(" 3) Ver carrito");
        System.out.println(" 4) Iniciar sesión");
        System.out.println(" 5) Registrar cuenta");
        int opcion = sc.nextInt();
        sc.nextLine();

        switch (opcion) {
            case 1:
                verPlatos("");
                break;
            case 2:
                verCategorías(platos, precios);
                break;
            case 3:
                verCarrito();
                break;
            case 4:
                verificarInicioSesion(); //ir al metodo
                break;
            case 5:
                registrarCuenta();
                break;
            default:
                System.out.println("Opción no válida");
                menuPrincipal();
                break;
        }
    }

    private void verificarInicioSesion() {
        if(sesionIniciada){//si la sesion esta iniciada
            System.out.println(nombreUsuario+" tu sesión está activa");
            cerrarSesion();
        }else { // si la sesion no esta iniciada
            iniciarSesion();
        }
    }

    private void cerrarSesion() {
        System.out.println("¿Cerrar sesión?");
        System.out.println(" Se perderán todos los datos ingresados");
        System.out.println(" 1) Si");
        System.out.println(" 2) No");
        int seleccion=sc.nextInt(); //pedir al usuario del sistema
        sc.nextLine(); //evitar error
        switch (seleccion){
            case 1:
                vaciarCarrito(); //de anderson
                sesionIniciada = false; // poner variable de iniciar sesion en cerrado
            default: menuPrincipal(); //volver al menu principal
        }
    }



    public void iniciarSesion() {
        if (!cuentaRegistrada){ //si no hay cuenta registrada
            System.out.println("No hay una cuenta registrada");
            registrarCuenta();
            return; //termina el metodo
        }
        System.out.println("-----------------------------");
        System.out.println("INICIAR SESIÓN");
        if (regresar()){ //mostrar menu para continuar o registrar
            return;
        }
        sc.nextLine(); // para evitar error
        int intento = 3;
        while (intento > 0) {
            System.out.println("Ingrese su correo:");
            String correo = sc.nextLine(); //pedir al usuario
            System.out.println("Ingrese su contraseña:");
            String contra = sc.nextLine(); //pedir al usuario
            if (correo.equals(emailGuardado) && contra.equals(contraseñaGuardada)) {
                System.out.println("Inicio de sesión exitoso.");
                sesionIniciada =true; //poner variable de iniciar sesion a iniciado
                menuPrincipal(); // volver al menu principal
                return; //termina el metodo
            } else {
                intento--; //disminuir en 1 los intentos
                System.out.println("Correo o contraseña incorrecta. Intentos restantes: " + intento);
            }
        }
        System.out.println("Se agotaron los intentos.");
        menuPrincipal(); //volver al menu principal
    }



    public boolean regresar(){
        System.out.println(" 1) Continuar");
        System.out.println(" 2) Regresar al menu principal");
        int seleccion=sc.nextInt();
        switch (seleccion){
            case 1: return false;
            case 2:
            default:
                menuPrincipal();
                return true;

        }
    }

    public void registrarCuenta() {
        System.out.println("-------------------------------------------------");
        if(cuentaRegistrada){
            System.out.println("Ya existe una cuenta registrada");
            System.out.println("* Nombre de ususario: "+nombreUsuario);
            System.out.println("  ¿Deseas eliminarla?");
            System.out.println("   También terminarás sesión y se borraran tus pedidos registrados");
            System.out.println("   1) Si");
            System.out.println("   2) No");
            int seleccion=sc.nextInt();
            sc.nextLine();
            switch (seleccion){
                case 1:
                    emailGuardado="";
                    contraseñaGuardada="";
                    nombreUsuario="";
                    cuentaRegistrada=false;
                    vaciarCarrito();
                    sesionIniciada=false;
                    break;
                case 2:
                    menuPrincipal();
                    break;
                default: menuPrincipal();
            }
            menuPrincipal();
        }else{
            System.out.println("REGISTRAR CUENTA");
            if (regresar()){ //este if pide si regresar o no
                return;
            }
            String email, contra;
            sc.nextLine();// para evitar error
            do {
                System.out.println("Ingrese su correo electrónico:");
                email = sc.nextLine();
                if (!validarCorreo(email)) {
                    System.out.println("Correo inválido.");
                }
            } while (!validarCorreo(email));

            do {
                System.out.println("Ingrese su contraseña:");
                contra = sc.nextLine();
                if (!validarContraseña(contra)) {
                    System.out.println("Contraseña inválida. Debe tener al menos 8 caracteres, incluir letras, números y un carácter especial.");
                }
            } while (!validarContraseña(contra));

            emailGuardado = email;
            contraseñaGuardada = contra;

            System.out.println("Ingrese su nombre de usuario:");
            nombreUsuario=sc.nextLine().toUpperCase();

            cuentaRegistrada=true;
            System.out.println("\nCuenta registrada con éxito.");

            menuPrincipal();
        }
    }

    private boolean validarCorreo(String email) {
        String regex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        return Pattern.compile(regex).matcher(email).matches();
    }

    private boolean validarContraseña(String contraseña) {
        String regex = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        return Pattern.compile(regex).matcher(contraseña).matches();
    }


    public void verPlatos(String categoria) {
        System.out.println("------------ "+categoria+" ------------");
        System.out.println("0) Regresar al menú principal");
        System.out.println("---------------------------------");

        Integer[] indices=new Integer[platos.size()];
        int contador=0;
        if (categoria.equals("")){
            for (int i = 0; i < platos.size(); i++) {
                System.out.println((i+1) + ") ("+ tiposPlatos.get(i)+") - "+ platos.get(i) +" - Precio: S/." + precios.get(i));
                indices[contador]=i;
                contador++;
            }
        }else{
            for (int i = 0; i < platos.size(); i++) {
                if (tiposPlatos.get(i).toUpperCase().equals(categoria.toUpperCase())){
                    System.out.println((contador+1) + ") " + platos.get(i) + " - Precio: S/." + precios.get(i));
                    indices[contador]=i;
                    contador++;
                }
            }
        }

        int seleccion = sc.nextInt();
        sc.nextLine();

        if (seleccion==0){
            menuPrincipal();
        }else if (seleccion >= 1 && seleccion <= contador) {
            int indice=indices[seleccion-1];
            mostrarDesripcion(indice, categoria);
        } else {
            System.out.println("Opción inválida");
            menuPrincipal();
        }
    }

    private void verCategorías(ArrayList<String> platos, ArrayList<Double> precios) {
        System.out.println("--------- CATEGORÍAS ---------");
        System.out.println("0) Regresar al menú principal");
        System.out.println("------------------------------");
        for (int i = 0; i < categorias.size(); i++) {
            System.out.println((i+1)+") "+categorias.get(i));
        }

        int seleccion = sc.nextInt();

        if (seleccion<=0){
            menuPrincipal();
        } else if (seleccion>categorias.size()) {
            System.out.println("Opción inválida");
            menuPrincipal();
        } else {
            verPlatos(categorias.get(seleccion-1).toUpperCase());
        }
    }


    private void mostrarDesripcion(int indice, String categoria) {
        System.out.println("-------------------------------------");
        System.out.println(platos.get(indice).toUpperCase()+":");
        System.out.println("----------------------------");
        System.out.println("* Descripción:\n"+descripciones.get(indice));
        System.out.println("\n* Tipo: "+tiposPlatos.get(indice));
        System.out.println("* Precio: S/."+String.format("%.2f", precios.get(indice)));
        System.out.println("-------------------------------------");
        System.out.println("\n¿Quieres añadir este plato a tu carrito?");
        System.out.println(" 1) Si");
        System.out.println(" 2) Regresar");
        int seleccion=sc.nextInt();
        sc.nextLine();
        switch (seleccion){
            case 1: añadirAlCarrito(indice, categoria);
                break;
            default: verPlatos(categoria);
        }
    }

    private void verCarrito() {
        System.out.println("------------------------------------");
        System.out.println("PRODUCTOS EN SU CARRITO:");
        double total=0.0;
        for (int i = 0; i < carrito.length; i++) {
            if (carrito[i]!=0){
                double subtotal= carrito[i]*precios.get(i);
                System.out.println("* "+carrito[i]+" platos de "+platos.get(i)+" Subotal: S/."+ subtotal);
                total+=subtotal;
            }
        }
        if (total>0){
            System.out.println("Total a pagar: "+total);
            System.out.println("-------------------------------------");
            System.out.println("¿Que desea hacer?");
            System.out.println(" 1) Ir a pagar");
            System.out.println(" 2) Vaciar carrito");
            System.out.println(" 3) Regresar al menu principal)");

            int seleccion = sc.nextInt();
            sc.nextLine();

            switch (seleccion) {
                case 1:
                    realizarPago();
                    break;
                case 2:
                    vaciarCarrito();
                    menuPrincipal();
                    break;
                case 3:
                    menuPrincipal();
                    break;
                default:
                    System.out.println("Opción inválida");
                    menuPrincipal();
                    break;
            }
        }else{
            System.out.println("Usted aún no añadió platos a su carrito");
            menuPrincipal();
        }
    }


    private void añadirAlCarrito(int indice, String categoria) {

        if (!sesionIniciada){
            iniciarSesion();
            return;// termina el metodo
        }

        System.out.println("¿Cuántos platos de " + platos.get(indice) + " añadirá a su carrito?");
        int cantidad = sc.nextInt();
        if (cantidad<=0){
            System.out.println("Cantidad inválida");
            return; //termina el metodo
        }
        carrito[indice] += cantidad;

        System.out.println("Ud ha pedido " + carrito[indice] + " platos de " + platos.get(indice));
        System.out.println("-------------------------------------");
        System.out.println("¿Qué desea hacer ahora?");
        System.out.println(" 1) Elejir otro plato");
        System.out.println(" 2) Ir a pagar ");
        System.out.println(" 3) Volver al menu principal");

        int seleccion = sc.nextInt();
        sc.nextLine();

        switch (seleccion) {
            case 1: verPlatos(categoria); break;
            case 2: realizarPago(); break;
            case 3: menuPrincipal(); break;
            default: System.out.println("Opción inválida"); menuPrincipal(); break;
        }
    }

    public void realizarPago() {
        double costoTotal = 0.00;
        System.out.println("----------- DETALLE DE SU COMPRA -----------");
        for (int i = 0; i < carrito.length; i++) {
            if (carrito[i] > 0) {
                double subtotalProducto = carrito[i] * precios.get(i);
                costoTotal += subtotalProducto;
            }
        }

        double igv = costoTotal * tasaIgv;
        double subtotal = costoTotal - igv;

        double montoPagado;
        do {
            System.out.println("TOTAL A PAGAR: S/." + String.format("%.2f", costoTotal));
            System.out.println("Ingrese el monto con el que va a pagar:");
            montoPagado = sc.nextDouble();
            sc.nextLine();
            if (montoPagado < costoTotal) {
                System.out.println("Monto insuficiente");
            }
        } while (montoPagado < costoTotal);

        System.out.println("¡Compra exitosa!");
        double vuelto = montoPagado - costoTotal;

        generarBoleta(subtotal, igv, costoTotal, montoPagado, vuelto);
    }


    private void generarBoleta(double subtotal, double igv, double costoTotal, double montoPagado, double vuelto) {
        System.out.println("------------ BOLETA DE VENTA ------------");
        System.out.println(" SUBTOTAL       :     S/." + String.format("%.2f", subtotal));
        System.out.println(" IGV ("+(int)(tasaIgv*100)+"%)      :     S/." + String.format("%.2f", igv));
        System.out.println(" TOTAL A PAGAR  :     S/." + String.format("%.2f", costoTotal));
        System.out.println(" MONTO PAGADO   :     S/." + String.format("%.2f", montoPagado));
        System.out.println(" VUELTO         :     S/." + String.format("%.2f", vuelto));
        System.out.println("-----------------------------------------");

        vaciarCarrito();
        System.out.println("¿Terminar el programa?");
        System.out.println(" 1) NO (Regresar al menu principal)");
        System.out.println(" 2) SI");
        int seleccion = sc.nextInt();
        sc.nextLine();
        if (seleccion == 1){
            menuPrincipal();
        }else{
            System.out.println("Gracias por usar el programa");
            System.out.println("Saliendo...");
        }
    }

    private void vaciarCarrito() {
        for (int i = 0; i < carrito.length; i++){
            carrito[i]=0;
        }
        System.out.println("El carrito ahora esta vacío");
    }

//    private void llenarDescripciones() {
//        for (int i = 0; i < descripciones.length; i++) {
//            descripciones[i]="Descripción del plato "+platos[i];
//        }
//    }

    public static void main(String[] args) {
        norkys norkys = new norkys();
        norkys.cargarDatos();
        norkys.menuPrincipal();
    }

}
