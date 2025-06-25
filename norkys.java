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
    ArrayList<Integer> carritoIndice;
    ArrayList<Integer> carritoCantidad;

    ArrayList<String> emails;
    ArrayList<String> contraseñas;
    ArrayList<String> usuarios;
    ArrayList<String> roles;

    String usuarioActual;

    boolean registrarCliente=false;

    boolean sesionIniciada =false;

    private void cargarDatos() {
        platos=new ArrayList<>(Arrays.asList("PLATO 1","PLATO 2","PLATO 3","PLATO 4"));
        precios=new ArrayList<>(Arrays.asList(10.00,20.00,30.00,40.00));
        descripciones=new ArrayList<>(Arrays.asList("descripcion 1","descripcion 2","Descripcion 3","descripcion 4"));
        stocks=new ArrayList<>(Arrays.asList(5,5,4,3));
        tiposPlatos=new ArrayList<>(Arrays.asList("TIPO 1","TIPO 1","TIPO 2","TIPO 2"));
        categorias=new ArrayList<>(Arrays.asList("TIPO 1","TIPO 2"));
        carritoIndice =new ArrayList<>();
        carritoCantidad =new ArrayList<>();

        emails=new ArrayList<>(Arrays.asList("","jclv@gmail.com","ama@gmail.com","as@gmail.com"));
        contraseñas=new ArrayList<>(Arrays.asList("","1234567jclv@","1234567ama@","1234567as@"));
        usuarios=new ArrayList<>(Arrays.asList("INVITADO","CRISTHIAN","ANDERSON","ARMANDO"));
        roles=new ArrayList<>(Arrays.asList("CLIENTE","ADMINISTRADOR","VENDEDOR","VENDEDOR"));
        usuarioActual="INVITADO";
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
        String seleccion=sc.nextLine();
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
        System.out.println("\n    a) INICIO         b) CARTA         c) PROMOCIONES                                                                                          d) "+iniciar.toUpperCase());
        System.out.println("\n"+RESET);
    }

    public void opciones_Superior(String seleccion) {
        switch (seleccion){
            case "a":
                menuPrincipal();
                System.out.println("Menu principal");
                break;
            case "b":
                System.out.println("Ver menu");
                break;
            case "c":
                System.out.println("Promociones");
                break;
            case "d":
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
            usuarioActual="Invitado";
            System.out.println("Sesion cerrada");
            menuPrincipal();
            return;
        }
        if (carritoIndice.isEmpty()){
            usuarioActual="Invitado";
            System.out.println("Sesion cerrada");
            menuPrincipal();
            return;
        }
        System.out.println("¿Cerrar sesión?");
        System.out.println(" Se vaciará el carrito");
        System.out.println(" 1) Si");
        System.out.println(" 2) No");
        String seleccion=sc.nextLine(); //pedir al usuario del sistema
        switch (seleccion){
            case "1":
                carritoIndice.clear();
                carritoCantidad.clear();
                usuarioActual="Invitado"; // poner variable de iniciar sesion en cerrado
            default: menuPrincipal(); //volver al menu principal
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
                System.out.println((i+1) + ") ("+ tiposPlatos.get(i)+") - "+ platos.get(i) +" - Precio: S/." + precios.get(i));
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
        System.out.println("Selecciona el producto para ver");
        String seleccion = sc.nextLine();
        try{
            int opcion=Integer.parseInt(seleccion);
            String empleado=usuarios.get(opcion);
            modificarEmpleado(empleado);
        }catch (Exception e){
            opciones_Superior(seleccion);
        }
        opciones_Superior(seleccion);
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
    }

    public void opciones_Cliente(String seleccion) {

        switch (seleccion){
            case "1": verPlatos("");
            break;
            case "2": verCategorías();
            break;
            case "3": verCarrito();
            break;
            default:
        }

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

    private void verCategorías() {
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
                    carritoIndice.clear();
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
        if (cantidad<=0){
            System.out.println("Cantidad inválida");
            return; //termina el metodo
        }
        carritoCantidad.set(indice,carritoCantidad.get(indice)+cantidad);

        System.out.println("Ud ha pedido " + carritoCantidad.get(indice) + " platos de " + platos.get(indice));
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


    private void generarBoleta(double subtotal, double igv, double costoTotal, double montoPagado, double vuelto) {

        verDetalleCompra();
        System.out.println("------------ BOLETA DE VENTA ------------");
        System.out.println(" SUBTOTAL       :     S/." + String.format("%.2f", subtotal));
        System.out.println(" IGV (18%)      :     S/." + String.format("%.2f", igv));
        System.out.println(" TOTAL A PAGAR  :     S/." + String.format("%.2f", costoTotal));
        System.out.println(" MONTO PAGADO   :     S/." + String.format("%.2f", montoPagado));
        System.out.println(" VUELTO         :     S/." + String.format("%.2f", vuelto));
        System.out.println("-----------------------------------------");

        carritoIndice.clear();
        carritoCantidad.clear();
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

    private void verDetalleCompra() {
        System.out.println("------------------------------------");
        System.out.println("PRODUCTOS EN SU CARRITO:");
        double total=0.0;
        for (int i = 0; i < carritoCantidad.size(); i++) {
            if (carritoCantidad.get(i)!=0){
                double subtotal= carritoCantidad.get(i)*precios.get(i);
                System.out.println("* "+ carritoCantidad.get(i)+" platos de "+platos.get(i)+" Subotal: S/."+ subtotal);
                total+=subtotal;
            }
        }
    }


    // VENDEDOR----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public void menu_Vendedor() {
        System.out.println("Soy vendedor");
    }

    public void opciones_Vendedor(String seleccion) {
        switch (seleccion){
            case "a":
                break;
            case "b":
                break;
            case "c":
                break;
            case "d":
                break;
            default:
        }
    }

}

