import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

public class norkys_v3 {

    Scanner sc = new Scanner(System.in);

    ArrayList<String> platos;
    ArrayList<Double> precios;
    ArrayList<String> descripciones;
    ArrayList<Integer> stocks;
    ArrayList<String> tiposPlatos;
    ArrayList<String> categorias;
    ArrayList<Integer> carritoCantidad;

    ArrayList<String> emails;
    ArrayList<String> contraseñas;
    ArrayList<String> usuarios;
    ArrayList<String> roles;

    String usuarioActual;

    int numeroPedido=1;
    boolean registrarCliente=false;

    boolean sesionIniciada =true;

    private void cargarDatos() {

        platos = new ArrayList<>(Arrays.asList(
                "POLLO A LA BRASA ENTERO",
                "1/2 POLLO A LA BRASA",
                "1/4 POLLO A LA BRASA",
                "MOSTRITO",
                "PARRILLA CLÁSICA",
                "1/2 PARRILLA BRASA",
                "PARRILLA PARA DOS",
                "CHURRASCO",
                "BIFE A LA PARRILLA",
                "CHULETA A LA PARRILLA",
                "PECHUGA A LA PARRILLA",
                "LOMO SALTADO",
                "POLLO SALTADO",
                "TALLARÍN SALTADO DE CARNE",
                "TALLARÍN SALTADO DE POLLO",
                "ARROZ CHAUFA DE POLLO",
                "ARROZ CHAUFA DE CARNE",
                "ARROZ CHAUFA MIXTO",
                "SALCHIPAPA",
                "ALITAS (WINGS)",
                "PIEZAS BROASTER",
                "TAMAL",
                "PAPA RELLENA",
                "CAUSA RELLENA DE POLLO",
                "ANTICUCHOS",
                "PIQUEO NORKY'S",
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

        precios = new ArrayList<>(Arrays.asList(
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
                10.00,
                10.00,
                5.00,
                5.00,
                8.00,
                7.00,
                4.00,
                5.00,
                12.00,
                14.00
        ));

        tiposPlatos = new ArrayList<>(Arrays.asList(
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

        categorias = new ArrayList<>(Arrays.asList(
                "POLLOS A LA BRASA",
                "PARRILLAS",
                "SALTADOS Y ARROCES CHAUFA",
                "PIQUEOS Y ENTRADAS",
                "GASEOSAS",
                "BEBIDAS NATURALES",
                "AGUAS",
                "CERVEZAS"
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
        stocks=new ArrayList<>(Arrays.asList(
                5,15,14,13,23,10,7,21,15,10,11,21,23,21,31,12,23,31,21,22,23,24,25,21,26,17,18,19,21,20,22,23,21,9,10,36));
        carritoCantidad =new ArrayList<>();
        for (int i = 0; i < platos.size(); i++) {
            carritoCantidad.add(0);
        }

        emails=new ArrayList<>(Arrays.asList("","jclv@gmail.com","armando@gmail.com","raul@gmail.com"));
        contraseñas=new ArrayList<>(Arrays.asList("","1234567q@","1234567q@","1234567q@"));
        usuarios=new ArrayList<>(Arrays.asList("INVITADO","CRISTHIAN","ARMANDO","RAUL"));
        roles=new ArrayList<>(Arrays.asList("CLIENTE","ADMINISTRADOR","VENDEDOR","VENDEDOR"));
        usuarioActual="CRISTHIAN";
    }


    public static void main(String[] args) {
        norkys_v3 norkys = new norkys_v3();
        norkys.cargarDatos();
        norkys.menuPrincipal();
    }

    public void menuPrincipal(){
        menu_Superior();
        String rolActual = roles.get(usuarios.indexOf(usuarioActual));
        switch (rolActual){
            case "CLIENTE": menu_Cliente();
                break;
            case "ADMINISTRADOR": menu_Administrador();
                break;
            case "VENDEDOR": menu_Vendedor();
                break;
        }
        String seleccion=sc.nextLine().toUpperCase();
        opciones_Superior(seleccion);
        switch (rolActual){
            case "CLIENTE": opciones_Cliente(seleccion);
                break;
            case "ADMINISTRADOR": opciones_Administrador(seleccion);
                break;
            case "VENDEDOR": opciones_Vendedor(seleccion);
                break;
        }
    }

    // MENU SUPERIOR ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public void menu_Superior() {
        final String RESET = "\u001B[0m";
        final String NEGRITA = "\u001B[1m";
        final String FONDO_negro = "\u001B[40m";
        final String TEXTO_verde = "\u001B[32m";

        System.out.println(NEGRITA + FONDO_negro + TEXTO_verde +"\n");
        System.out.println(
                "                                                         "+",--.  ,--.              ,--.             ,--.       \n" +
                        "                                                         "+"|  ,'.|  | ,---. ,--.--.|  |,-. ,--. ,--.|  |,---.  \n" +
                        "                                                         "+"|  |' '  || .-. ||  .--'|     /  \\  '  / `-'(  .-'  \n" +
                        "                                                         "+"|  | `   |' '-' '|  |   |  \\  \\   \\   '     .-'  `) \n" +
                        "                                                         "+"`--'  `--' `---' `--'   `--'`--'.-'  /      `----'  \n" +
                        "                                                         "+"                                `---'               "
        );
        String iniciar = usuarioActual.equals("Invitado") ? "🔑 INGRESAR":"🧑 "+usuarioActual;
        System.out.println("____________________________________________________________________________________________________________________________________________________________________");
        System.out.println("\n    a) INICIO         b) CARTA         c) CATEGORIAS                                                                                          d) "+iniciar.toUpperCase());
        System.out.println("\n"+RESET);
    }

    public void opciones_Superior(String seleccion) {
        switch (seleccion){
            case "A":
                menuPrincipal();
                System.out.println("Menu principal");
                break;
            case "B":
                System.out.println("Ver carta");
                mostrarProductos("");
                break;
            case "C":
                System.out.println("Categorias");
                verCategorías();
                break;
            case "D":
                if (usuarioActual.equals("INVITADO")){
                    iniciarSesion();
                }else ajustesCuenta();
                break;
        }
    }

    private void ajustesCuenta() {
        menu_Superior();
        System.out.println("AJUSTES DE LA CUENTA");
        String email=emails.get(usuarios.indexOf(usuarioActual));
        String rol=roles.get(usuarios.indexOf(usuarioActual));
        System.out.println("-----------------------------------------\n" +
                "Información Personal:"+
                "\n  * Usuario  : "+ usuarioActual+
                "\n  * E-mail   : "+ email+
                "\n  * Rol      : "+ rol+
                "\n-----------------------------------------");
        System.out.println(" 1) ✏ Editar");
        System.out.println(" 2) 🔐 Cerrar sesión");
        String seleccion=sc.nextLine();
        opciones_Superior(seleccion);
        switch (seleccion){
            case "1":
                modificarEmpleado(usuarioActual);
                menuPrincipal();
                break;
            case "2":
                cerrarSesion();
                break;
            default:
                System.out.println("Opción inválida");
                menuPrincipal();
        }
    }

    private void cerrarSesion() {
        String rol=roles.get(usuarios.indexOf(usuarioActual));
        if (rol.equalsIgnoreCase("Administrador")){
            usuarioActual="INVITADO";
            System.out.println("Sesion cerrada");
            menuPrincipal();
            return; //termina el metodo
        }
        if (carritoCantidad.isEmpty()){
            usuarioActual="Invitado";
            System.out.println("Sesion cerrada");
            menuPrincipal();
            return; //termina el metodo
        }
        System.out.println("¿Cerrar sesión?");
        System.out.println(" Se vaciará el carrito");
        System.out.println(" 1) Si");
        System.out.println(" 2) No");
        String seleccion=sc.nextLine(); //pedir al usuario del sistema
        switch (seleccion){
            case "1":
                for (int i = 0; i < carritoCantidad.size(); i++) {
                    carritoCantidad.set(i,0);
                }
                usuarioActual="INVITADO";
                sesionIniciada=false;
            default: menuPrincipal();
        }
    }

    public void iniciarSesion() {
        menu_Superior();
        System.out.println("INICIAR SESIÓN");
        System.out.println("¿Es usuario nuevo?");
        System.out.println(" e) Registrar cuenta");

        int intentos = 3;
        while (intentos > 0) {
            System.out.println("Ingrese su correo:");
            String correo = sc.nextLine(); //pedir al usuario
            opciones_Superior(correo);
            if (correo.equalsIgnoreCase("e")){
                menu_Superior();
                registrar_Empleado();
            }
            else if (emails.contains(correo)){
                String contraseñaGuardada=contraseñas.get(emails.indexOf(correo));
                System.out.println("Ingrese su contraseña:");
                String contraseña = sc.nextLine(); //pedir al usuario
                opciones_Superior(contraseña);
                if (contraseña.equals(contraseñaGuardada)) {
                    sesionIniciada =true; //poner variable de iniciar sesion a iniciado
                    usuarioActual=usuarios.get(emails.indexOf(correo));
                    System.out.println("Inicio de sesión exitoso.");
                    menuPrincipal(); // volver al menu principal
                    return; //termina el metodo
                } else {
                    intentos--; //disminuir en 1 los intentos
                    System.out.println("Correo o contraseña incorrecta. Intentos restantes: " + intentos);
                }
            }else {
                intentos--;
                System.out.println("Correo no registrado");
            }
        }
        System.out.println("Se agotaron los intentos.");
        menuPrincipal(); //volver al menu principal
    }

    // ADMINISTRADOR ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    public void menu_Administrador() {
        System.out.println("Hola, "+usuarioActual.toUpperCase()+"!\n" +
                "("+roles.get(usuarios.indexOf(usuarioActual))+")\n");
        System.out.println("1. Agregar producto");
        System.out.println("2. Modificar producto por nombre");
        System.out.println("3. Eliminar producto por nombre");
        System.out.println("4. Mostrar productos");
        //--------------------------------------------
        System.out.println("5. Registrar empleado");
        System.out.println("6. Modificar empleado por nombre");
        System.out.println("7. Eliminar empleado por nombre");
        System.out.println("8. Mostrar lista de empleados");
    }

    public void opciones_Administrador(String seleccion) {
        switch (seleccion){
            case "1": agregarProducto();
                break;
            case "2": modificarProducto();
                break;
            case "3": eleminarProducto();
                break;
            case "4": mostrarProductos("");
                break;
            //--------------------------------------
            case "5": registrar_Empleado();
                break;
            case "6": modificarEmpleadoNombre();
                break;
            case "7": eliminarEmpleadoNombre();
                break;
            case "8": mostrarEmleados();
                break;
            default:
                System.out.println("Opcion inválida del Admin");
        }
        menuPrincipal();
    }


    private void agregarProducto() {

        System.out.println("\n------- AGREGAR PRODUCTO --------");
        System.out.println("Ingrese el nombre del producto:");
        String nombre=sc.nextLine();
        System.out.println("Ingrese el precio del producto:");
        Double precio=sc.nextDouble();
        sc.nextLine();
        System.out.println("Ingrese la descripción del producto:");
        String descripcion=sc.nextLine();
        System.out.println("Ingrese el stock del producto:");
        int stock=sc.nextInt();
        sc.nextLine();
        System.out.println("Ingrese la categoría del producto:");
        String categoria=sc.nextLine().toUpperCase();

        platos.add(nombre);
        precios.add(precio);
        descripciones.add(descripcion);
        stocks.add(stock);
        tiposPlatos.add(categoria);
        if (!categorias.contains(categoria)){
            categorias.add(categoria);
        }

        System.out.println("Producto agregado!");
        System.out.println("---------------------------------");
    }

    private void modificarProducto() {
        System.out.println("\n------ MODIFICAR PRODUCTO -------");
        System.out.println("Ingrese el nombre del producto:");
        String nombre=sc.nextLine();
        if (platos.contains(nombre)){
            int indice= platos.indexOf(nombre);
            System.out.println("Ingrese el nuevo nombre:");
            String nombreNuevo=sc.nextLine();
            System.out.println("Ingrese el nuevo precio:");
            double precioNuevo=sc.nextDouble();
            sc.nextLine();
            System.out.println("Ingrese la nueva descripción:");
            String descripcion=sc.nextLine();
            System.out.println("Ingrese el nuevo stock:");
            int stock=sc.nextInt();
            sc.nextLine();
            System.out.println("Ingrese la nueva categoría:");
            String categoria=sc.nextLine();

            platos.set(indice,nombreNuevo);
            precios.set(indice, precioNuevo);
            descripciones.set(indice, descripcion);
            stocks.set(indice, stock);
            tiposPlatos.set(indice, categoria);
            System.out.println("Producto modificado!");
        }else{
            System.out.println("No se encontró el producto");
        }
        System.out.println("---------------------------------");
    }

    private void eleminarProducto() {

        System.out.println("\n------ ELIMINAR  PRODUCTO -------");
        System.out.println("Ingrese el nombre del producto:");
        String nombre=sc.nextLine();
        if (platos.contains(nombre)){
            int indice= platos.indexOf(nombre);

            platos.remove(indice);
            precios.remove(indice);
            descripciones.remove(indice);
            stocks.remove(indice);
            tiposPlatos.remove(indice);
            System.out.println("Producto eliminado!");
        }else{
            System.out.println("No se encontró el producto");
        }
        System.out.println("---------------------------------");
    }

    private void mostrarProductos(String categoria) {
        menu_Superior();
        System.out.println("------------ "+categoria+" ------------");
        ArrayList<Integer> indices=new ArrayList<>();
        int contador=0;
        if (categoria.equals("")){
            for (int i = 0; i < platos.size(); i++) {
                System.out.println((i+1) + ") ("+ tiposPlatos.get(i)+") - "+ platos.get(i) +" - Precio: S/." + precios.get(i)+" - Stock: "+stocks.get(i));
                indices.add(i);
                contador++;
            }
        }else{
            for (int i = 0; i < platos.size(); i++) {
                if (tiposPlatos.get(i).equals(categoria)){
                    System.out.println((contador+1) + ") " + platos.get(i) + " - Precio: S/." + precios.get(i));
                    indices.add(i);
                    contador++;
                }
            }
        }
        if (contador==0){
            System.out.println("No hay productos a mostrar");
            menuPrincipal();
            return; //termina el método
        }
        System.out.println("Selecciona el producto para ver");
        String seleccion = sc.nextLine().toUpperCase();
        try{
            int opcion= indices.get(Integer.parseInt(seleccion)-1);
            if (opcion >= 0 && opcion < platos.size()) {
                mostrarDesripcion(opcion, categoria);
            } else {
                System.out.println("Opción inválida");
                menuPrincipal();
            }
        }catch (Exception e){
            opciones_Superior(seleccion);
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

    private String pedirCorreo() {
        String correo;
        do {
            System.out.println("Ingrese el correo del empleado:");
            correo=sc.nextLine();
            if (!validarCorreo(correo)) {
                System.out.println("Correo inválido.");
            }
        } while (!validarCorreo(correo));
        return correo;
    }
    private String pedirContraseña() {
        String contraseña;
        do {
            System.out.println("Ingrese la contraseña del empleado:");
            contraseña=sc.nextLine();
            if (!validarContraseña(contraseña)) {
                System.out.println("Contraseña inválida. Debe tener al menos 8 caracteres, incluir letras, números y un carácter especial.");
            }
        } while (!validarContraseña(contraseña));
        return contraseña;
    }
    private String pedirNombreUsuario() {
        String usuario;
        do{
            System.out.println("Ingrese el nombre de usuario del empleado");
            usuario=sc.nextLine().toUpperCase();
            if (usuarios.contains(usuario)){
                System.out.println("El nombre de usuario ya está en uso");
            }
        }while (usuarios.contains(usuario));
        return usuario;
    }
    private String pedirRol() {
        String rol;
        do{
            System.out.println("Seleccione el rol del empleado:");
            System.out.println("1) Administrador");
            System.out.println("2) Vendedor");
            rol=sc.nextLine();
            switch (rol){
                case "1": rol="ADMINISTRADOR"; break;
                case "2": rol="VENDEDOR"; break;
                default: rol="";
                    System.out.println("Opción no válida");
            }
        }while (rol.equals(""));
        return rol;
    }

    private void registrar_Empleado(){
        System.out.println("REGISTRAR EMPLEADO:");
        String correo = pedirCorreo();
        String contraseña = pedirContraseña();
        String usuario = pedirNombreUsuario();
        String rol = pedirRol();

        emails.add(correo);
        contraseñas.add(contraseña);
        usuarios.add(usuario);
        roles.add(rol);

        System.out.println("\nCuenta registrada con éxito.");
    }

    private void modificarEmpleadoNombre() {
        System.out.println("Ingrese el nombre de usuario del empleado:");
        String nombre=sc.nextLine().toUpperCase();
        if (usuarios.contains(nombre)){
            modificarEmpleado(nombre);
        }else {
            System.out.println("Empleado no encontrado");
        }
    }

    private void eliminarEmpleadoNombre() {
        System.out.println("Ingrese el nombre de usuario del empleado:");
        String nombre=sc.nextLine().toUpperCase();
        if (usuarios.contains(nombre)){
            eliminarEmpleado(nombre);
        }else {
            System.out.println("Empleado no encontrado");
        }
    }

    private void mostrarEmleados() {
        menu_Superior();
        System.out.println("----------------------------------------\n LISTA DE EMPLEADOS:");
        for (int i = 1; i < emails.size(); i++) {
            System.out.println("-----------------------------------------\n" +
                    i+") * Usuario  : "+ usuarios.get(i)+
                    "\n   * Rol      : "+ roles.get(i)+
                    "\n-----------------------------------------");
        }
        System.out.println("Selecciona el numero de empleado para editar");
        String seleccion=sc.nextLine();
        try{
            int opcion=Integer.parseInt(seleccion);
            String empleado=usuarios.get(opcion);
            modificarEmpleado(empleado);
        }catch (Exception e){
            opciones_Superior(seleccion);
        }
    }

    private void modificarEmpleado(String usuario) {
        menu_Superior();
        int indice=usuarios.indexOf(usuario);
        System.out.println("-----------------------------------------\nMODIFICAR EMPLEADO:" +
                "\n   1) Usuario     : "+ usuarios.get(indice)+
                "\n   2) E-mail      : "+ emails.get(indice)+
                "\n   3) Rol         : "+ roles.get(indice)+
                "\n   4) Contraseña  : "+ contraseñas.get(indice).substring(0,4)+"*********"+
                "\n-----------------------------------------");
        String rolActual = roles.get(usuarios.indexOf(usuarioActual));
        if (rolActual.equals("ADMINISTRADOR")){
            System.out.println("5) Eliminar empleado");
        }

        String seleccion=sc.nextLine();
        opciones_Superior(seleccion);
        switch (seleccion){
            case "1": usuario=pedirNombreUsuario();
                usuarios.set(indice,usuario);
                break;
            case "2": emails.set(indice,pedirCorreo());
                break;
            case "3": roles.set(indice,pedirRol());
                break;
            case "4": contraseñas.set(indice,pedirContraseña());
                break;
        }
        if (rolActual.equals("ADMINISTRADOR")){
            if (seleccion.equals("5")){
                eliminarEmpleado(usuario);
                menuPrincipal();
                return;
            }
        }
        System.out.println("Hecho!");
        modificarEmpleado(usuario);
    }

    private void eliminarEmpleado(String usuario){
        int indice=usuarios.indexOf(usuario);
        System.out.println("¿Estás seguro de eliminar al empleado "+usuario+"?");
        System.out.println("1) Si");
        System.out.println("2) Cancelar");
        String selección=sc.nextLine();
        switch (selección){
            case "1":
                usuarios.remove(indice);
                emails.remove(indice);
                contraseñas.remove(indice);
                roles.remove(indice);
                break;
            case "2":
            default:
        }
    }

    // CLIENTE --------------------------------------------------------------------------------------------------------------------------------------------------

    public void menu_Cliente() {

        System.out.println("\n---------------- CLIENTE -----------------");
        System.out.println(" 1) Ver todos los platos");
        System.out.println(" 2) Ver por categorias");
        System.out.println(" 3) Ver carrito");
        System.out.println(" 4) Deliveri");
    }

    public void opciones_Cliente(String seleccion) {

        switch (seleccion){
            case "1": mostrarProductos("");
                break;
            case "2": verCategorías();
                break;
            case "3": verCarrito();
                break;
            case "4": delivery();
                break;
            default:
        }

    }


    // Ver Mostrar Productos

    private void verCategorías() {
        System.out.println("--------- CATEGORÍAS ---------");
        System.out.println("0) Regresar al menú principal");
        System.out.println("------------------------------");
        for (int i = 0; i < categorias.size(); i++) {
            System.out.println((i+1)+") "+categorias.get(i));
        }

        int seleccion = sc.nextInt();
        sc.nextLine();

        if (seleccion<=0){
            menuPrincipal();
        } else if (seleccion>categorias.size()) {
            System.out.println("Opción inválida");
            menuPrincipal();
        } else {
            mostrarProductos(categorias.get(seleccion-1));
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
            default: mostrarProductos(categoria);
        }
    }


    // VENDEDOR----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public void menu_Vendedor() {
        System.out.println("\n---------------- VENDEDOR -----------------");
        System.out.println(" 1) Ver todos los platos");
        System.out.println(" 2) Ver por categorias");
        System.out.println(" 3) Ver carrito");
    }

    public void opciones_Vendedor(String seleccion) {
        switch (seleccion){
            case "1": mostrarProductos("");
                break;
            case "2": verCategorías();
                break;
            case "3": verCarrito();
                break;
            default:
        }
    }

    private void verCarrito() {
        System.out.println("------------------------------------");
        System.out.println("DETALLE:");

        double total=0.0;
        for (int i = 0; i < carritoCantidad.size(); i++) {
            if (carritoCantidad.get(i)!=0){
                double subtotal= carritoCantidad.get(i)*precios.get(i);
                System.out.println("* "+ carritoCantidad.get(i)+" platos de "+platos.get(i)+" Subotal: S/."+ subtotal);
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
                    carritoCantidad.clear();
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
        sc.nextLine();
        if (cantidad<=0 || cantidad > stocks.get(indice)){
            System.out.println("Cantidad inválida o no hay stock");
            return; //termina el metodo
        }
        carritoCantidad.set(indice,carritoCantidad.get(indice)+cantidad);
        stocks.set(indice,stocks.get(indice)-cantidad);

        System.out.println("Ud ha pedido " + carritoCantidad.get(indice) + " platos de " + platos.get(indice));
        System.out.println("-------------------------------------");
        System.out.println("¿Qué desea hacer ahora?");
        System.out.println(" 1) Elejir otro plato");
        System.out.println(" 2) Ir a pagar ");
        System.out.println(" 3) Volver al menu principal");

        int seleccion = sc.nextInt();
        sc.nextLine();

        switch (seleccion) {
            case 1: mostrarProductos(categoria); break;
            case 2: realizarPago(); break;
            case 3: menuPrincipal(); break;
            default: System.out.println("Opción inválida"); menuPrincipal(); break;
        }
    }

    public void realizarPago() {
        double costoTotal = 0.00;
        System.out.println("----------- DETALLE DE SU COMPRA -----------");
        for (int i = 0; i < carritoCantidad.size(); i++) {
            if (carritoCantidad.get(i) > 0) {
                double subtotalProducto = carritoCantidad.get(i) * precios.get(i);
                costoTotal += subtotalProducto;
            }
        }

        double igv = costoTotal * 0.18;
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

    private void formasDePago(double costoTotal) {
        System.out.println("Seleccione forma de pago:");
        System.out.println("1) Pagar en efectivo");
        System.out.println("2) Pagar con tarjeta");

        int opcion = sc.nextInt();
        sc.nextLine();

        double igv = costoTotal * 0.18;
        double subtotal = costoTotal - igv;

        switch (opcion) {
            case 1:
                double montoPagado;
                do {
                    System.out.println("Ingrese el monto con el que va a pagar:");
                    montoPagado = sc.nextDouble();
                    sc.nextLine();
                    if (montoPagado < costoTotal) {
                        System.out.println("Monto insuficiente.");
                    }
                } while (montoPagado < costoTotal);
                double vuelto = montoPagado - costoTotal;
                System.out.println("Pago en efectivo realizado.");
                generarBoleta(subtotal, igv, costoTotal, montoPagado, vuelto);
                break;

            case 2:
                System.out.println("Ingrese los 16 dígitos de la tarjeta:");
                String numeroTarjeta = sc.nextLine();
                while (!numeroTarjeta.matches("\\d{16}")) {
                    System.out.println("Número inválido. Debe tener 16 dígitos.");
                    numeroTarjeta = sc.nextLine();
                }

                System.out.println("Ingrese el CVV (3 dígitos):");
                String cvv = sc.nextLine();
                while (!cvv.matches("\\d{3}")) {
                    System.out.println("CVV inválido.");
                    cvv = sc.nextLine();
                }

                System.out.println("Ingrese la fecha de vencimiento (MM/AA):");
                String fecha = sc.nextLine();
                while (!fecha.matches("(0[1-9]|1[0-2])/\\d{2}") || !validarFechaVencimiento(fecha)) {
                    System.out.println("Fecha inválida o vencida. Ingrese en formato MM/AA:");
                    fecha = sc.nextLine();
                }

                System.out.println("Pago con tarjeta realizado con éxito.");
                generarBoleta(subtotal, igv, costoTotal, costoTotal, 0);
                break;

            default:
                System.out.println("Opción inválida.");
                menuPrincipal();
        }
    }

    private boolean validarFechaVencimiento(String fecha) {
        String[] partes = fecha.split("/");
        int mes = Integer.parseInt(partes[0]);
        int año = Integer.parseInt(partes[1]) + 2000;

        java.util.Calendar ahora = java.util.Calendar.getInstance();
        int mesActual = ahora.get(java.util.Calendar.MONTH) + 1;
        int añoActual = ahora.get(java.util.Calendar.YEAR);

        return (año > añoActual) || (año == añoActual && mes >= mesActual);
    }

    private void delivery() {
        System.out.println("Ingrese la dirección de entrega:");
        String direccion = sc.nextLine();
        double total = 0.0;
        for (int i = 0; i < carritoCantidad.size(); i++) {
            if (carritoCantidad.get(i) > 0) {
                total += carritoCantidad.get(i) * precios.get(i);
            }
        }

        if (total == 0) {
            System.out.println("Carrito vacío. No se puede procesar el delivery.");
            menuPrincipal();
            return;
        }

        System.out.println("Procesando pedido a entregar en: " + direccion);
        formasDePago(total);
    }


    private void generarBoleta(double subtotal, double igv, double costoTotal, double montoPagado, double vuelto) {

        System.out.println(verDetalleCompra());
        System.out.println("\n-----------------------------------------");
        System.out.println(" SUBTOTAL       :     S/." + String.format("%.2f", subtotal));
        System.out.println(" IGV (18%)      :     S/." + String.format("%.2f", igv));
        System.out.println(" TOTAL A PAGAR  :     S/." + String.format("%.2f", costoTotal));
        System.out.println(" MONTO PAGADO   :     S/." + String.format("%.2f", montoPagado));
        System.out.println(" VUELTO         :     S/." + String.format("%.2f", vuelto));
        System.out.println("-----------------------------------------");

        carritoCantidad.clear();
        System.out.println("¿Exportar boleta?");
        System.out.println(" 1) NO (Regresar al menu principal)");
        System.out.println(" 2) SI");
        int seleccion = sc.nextInt();
        sc.nextLine();
        if (seleccion == 1){
            menuPrincipal();
        }else{
            exportarBoleta(verDetalleCompra(), costoTotal, montoPagado);
        }
    }

    private String verDetalleCompra() {
        String texto="";
        texto+="-----------------------------------------" +
                "\n                 NORKYS" +
                "\n           Real Plaza Juliaca" +
                "\n_      _     _      _     _     _\n" +
                "  ( \">   ( \">  ( \">   ( \">  ( \">  ( \">\n" +
                " /(  )\\ <( )| <(  )> >(  )> (> )< (><)\n" +
                "   ^^     ^^    ^^     ^^    ^^    ^^"+
                "\n                PEDIDO: "+numeroPedido +
                "\n Recepción: "+usuarioActual +
                "\n Pedido:"+numeroPedido+
                "\n"
        ;

        double total=0.0;
        for (int i = 0; i < carritoCantidad.size(); i++) {
            if (carritoCantidad.get(i)!=0){
                double subtotal= carritoCantidad.get(i)*precios.get(i);
                texto+="* "+ carritoCantidad.get(i)+" platos de "+platos.get(i)+"          S/."+ subtotal;
                total+=subtotal;
            }
        }
        return texto;
    }

    private static void exportarBoleta(String texto, double total, double monto) {
        double igv=total*0.18;
        double subTotal=total-igv;
        double vuelto = monto-total;

        System.out.println("\n-----------------------------------------");
        System.out.println(" SUBTOTAL       :     S/." + String.format("%.2f", subTotal));
        System.out.println(" IGV (18%)      :     S/." + String.format("%.2f", igv));
        System.out.println(" TOTAL A PAGAR  :     S/." + String.format("%.2f", total));
        System.out.println(" MONTO PAGADO   :     S/." + String.format("%.2f", monto));
        System.out.println(" VUELTO         :     S/." + String.format("%.2f", vuelto));
        System.out.println("-----------------------------------------");
        try {
            FileWriter archivo=new FileWriter("Boleta.txt");
            archivo.write(

                    texto+

                    "\n-----------------------------------------\n" +
                    "\n SUBTOTAL       :     S/." + String.format("%.2f", subTotal) +
                    "\n IGV (18%)      :     S/." + String.format("%.2f", igv) +
                    "\n TOTAL A PAGAR  :     S/." + String.format("%.2f", total) +
                    "\n MONTO PAGADO   :     S/." + String.format("%.2f", monto) +
                    "\n VUELTO         :     S/." + String.format("%.2f", vuelto)+
                    "\n----------------------------------------\n\n"+
                    "¡Gracias por su compra!");
            archivo.close();
        }catch (Exception e){
            System.out.println("Error al exportar boleta");
        }
    }
}
