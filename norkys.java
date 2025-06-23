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
    ArrayList<String> contrase帽as;
    ArrayList<String> usuarios;
    ArrayList<String> roles;

    String usuarioActual;

    boolean cuentaRegistrada=false;

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
        contrase帽as=new ArrayList<>(Arrays.asList("","1234567jclv@","1234567ama@","1234567as@"));
        usuarios=new ArrayList<>(Arrays.asList("INVITADO","CRISTHIAN","ANDERSON","ARMANDO"));
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
        String iniciar = usuarioActual.equals("Invitado") ? "馃攽 INGRESAR":"馃 "+usuarioActual;
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
                if (usuarioActual.equals("Invitado")){
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
                "Informaci贸n Personal:"+
                "\n  * Usuario  : "+ usuarioActual+
                "\n  * E-mail   : "+ email+
                "\n  * Rol      : "+ rol+
                "\n-----------------------------------------");
        System.out.println(" 1) 鉁� Editar");
        System.out.println(" 2) 馃攼 Cerrar sesi贸n");
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
                System.out.println("Opci贸n inv谩lida");
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
        System.out.println("驴Cerrar sesi贸n?");
        System.out.println(" Se vaciar谩 el carrito");
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
        System.out.println("INICIAR SESI脫N");

        int intentos = 3;
        while (intentos > 0) {
            System.out.println("Ingrese su correo:");
            String correo = sc.nextLine(); //pedir al usuario
            opciones_Superior(correo);
            if (emails.contains(correo)){
                String contrase帽aGuardada=contrase帽as.get(emails.indexOf(correo));
                System.out.println("Ingrese su contrase帽a:");
                String contrase帽a = sc.nextLine(); //pedir al usuario
                opciones_Superior(contrase帽a);
                if (contrase帽a.equals(contrase帽aGuardada)) {
                    sesionIniciada =true; //poner variable de iniciar sesion a iniciado
                    usuarioActual=usuarios.get(emails.indexOf(correo));
                    System.out.println("Inicio de sesi贸n exitoso.");
                    menuPrincipal(); // volver al menu principal
                    return; //termina el metodo
                } else {
                    intentos--; //disminuir en 1 los intentos
                    System.out.println("Correo o contrase帽a incorrecta. Intentos restantes: " + intentos);
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
                System.out.println("Opcion inv谩lida del Admin");
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
        System.out.println("Ingrese la descripci贸n del producto:");
        String descripcion=sc.nextLine();
        System.out.println("Ingrese el stock del producto:");
        int stock=sc.nextInt();
        sc.nextLine();
        System.out.println("Ingrese la categor铆a del producto:");
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
            System.out.println("Ingrese la nueva descripci贸n:");
            String descripcion=sc.nextLine();
            System.out.println("Ingrese el nuevo stock:");
            int stock=sc.nextInt();
            sc.nextLine();
            System.out.println("Ingrese la nueva categor铆a:");
            String categoria=sc.nextLine();

            platos.set(indice,nombreNuevo);
            precios.set(indice, precioNuevo);
            descripciones.set(indice, descripcion);
            stocks.set(indice, stock);
            tiposPlatos.set(indice, categoria);
            System.out.println("Producto modificado!");
        }else{
            System.out.println("No se encontr贸 el producto");
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
            System.out.println("No se encontr贸 el producto");
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

    private boolean validarContrase帽a(String contrase帽a) {
        String regex = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        return Pattern.compile(regex).matcher(contrase帽a).matches();
    }

    private String pedirCorreo() {
        String correo;
        do {
            System.out.println("Ingrese el correo del empleado:");
            correo=sc.nextLine();
            if (!validarCorreo(correo)) {
                System.out.println("Correo inv谩lido.");
            }
        } while (!validarCorreo(correo));
        return correo;
    }
    private String pedirContrase帽a() {
        String contrase帽a;
        do {
            System.out.println("Ingrese la contrase帽a del empleado:");
            contrase帽a=sc.nextLine();
            if (!validarContrase帽a(contrase帽a)) {
                System.out.println("Contrase帽a inv谩lida. Debe tener al menos 8 caracteres, incluir letras, n煤meros y un car谩cter especial.");
            }
        } while (!validarContrase帽a(contrase帽a));
        return contrase帽a;
    }
    private String pedirNombreUsuario() {
        String usuario;
        do{
            System.out.println("Ingrese el nombre de usuario del empleado");
            usuario=sc.nextLine().toUpperCase();
            if (usuarios.contains(usuario)){
                System.out.println("El nombre de usuario ya est谩 en uso");
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
                    System.out.println("Opci贸n no v谩lida");
            }
        }while (rol.equals(""));
        return rol;
    }

    private void registrar_Empleado(){
        String correo = pedirCorreo();
        String contrase帽a = pedirContrase帽a();
        String usuario = pedirNombreUsuario();
        String rol = pedirRol();

        emails.add(correo);
        contrase帽as.add(contrase帽a);
        usuarios.add(usuario);
        roles.add(rol);

        System.out.println("\nCuenta registrada con 茅xito.");
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
                "\n   4) Contrase帽a  : "+ contrase帽as.get(indice).substring(0,4)+"*********"+
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
            case "4": contrase帽as.set(indice,pedirContrase帽a());
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
        System.out.println("驴Est谩s seguro de eliminar al empleado "+usuario+"?");
        System.out.println("1) Si");
        System.out.println("2) Cancelar");
        String selecci贸n=sc.nextLine();
        switch (selecci贸n){
            case "1":
                usuarios.remove(indice);
                emails.remove(indice);
                contrase帽as.remove(indice);
                roles.remove(indice);
                break;
            case "2":
            default:
        }
    }

    // CLIENTE --------------------------------------------------------------------------------------------------------------------------------------------------

    public void menu_Cliente() {

        System.out.println("soy cliente");
        System.out.println("    驴Como prefieres pedir?                                                                                 e) DELIVERY          f)RECOJO EN TIENDA");

        System.out.println("    EXPLORA NUESTRO MEN脷");
        for (int i=0; i<categorias.size();i++){
            System.out.println(""+categorias.get(i));
        }
    }

    public void opciones_Cliente(String seleccion) {
        switch (seleccion){
            case "1":
                break;
            case "2":
                break;
            case "3":
                break;
            case "4":
                break;
            default:
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


    // OTROS --------------------------------------------------------------------------------------------------------------------------------------------------------------
    private String formatoTexto(String texto, String estilo, String f, String b) {
        //estilo de texto ASCII https://patorjk.com/software/taag/#p=display&v=1&f=Standard&t=Norky's
        final String RESET = "\u001B[0m";
        final String NEGRITA = "\u001B[1m";
        final String SUBRAYADO = "\u001B[1m";
        final String INVERTIR = "\u001B[1m";
        final String NO_NEGRITA = "\u001B[1m";
        final String NO_SUBRAYADO = "\u001B[1m";
        final String NO_INVERTIR = "\u001B[1m";

        final String TEXTO_negro = "\u001B[30m";
        final String TEXTO_rojo = "\u001B[31m";
        final String TEXTO_verde = "\u001B[32m";
        final String TEXTO_amarillo = "\u001B[33m";
        final String TEXTO_azul = "\u001B[34m";
        final String TEXTO_magenta = "\u001B[35m";
        final String TEXTO_cyan = "\u001B[36m";
        final String TEXTO_blanco = "\u001B[37m";

        final String FONDO_negro = "\u001B[40m";
        final String FONDO_rojo = "\u001B[41m";
        final String FONDO_verde = "\u001B[42m";
        final String FONDO_amarillo = "\u001B[43m";
        final String FONDO_azul = "\u001B[44m";
        final String FONDO_magenta = "\u001B[45m";
        final String FONDO_cyan = "\u001B[46m";
        final String FONDO_blanco = "\u001B[47m";

        switch (estilo){
            case "negrita":texto=NEGRITA+texto;
                break;
            default:
        }
        switch (f){
            case "negro": texto=TEXTO_negro+texto;
                break;
            case "rojo": texto=TEXTO_rojo+texto;
                break;
            case "verde": texto=TEXTO_verde+texto;
                break;
            case "amarillo": texto=TEXTO_amarillo+texto;
                break;
            case "azul": texto=TEXTO_azul+texto;
                break;
            case "magenta": texto=TEXTO_magenta+texto;
                break;
            case "cyan": texto=TEXTO_cyan+texto;
                break;
            case "blanco": texto=TEXTO_blanco+texto;
                break;
            default:
        }
        switch (b){
            case "negro": texto=FONDO_negro+texto;
                break;
            case "rojo": texto=FONDO_rojo+texto;
                break;
            case "verde": texto=FONDO_verde+texto;
                break;
            case "amarillo": texto=FONDO_amarillo+texto;
                break;
            case "azul": texto=FONDO_azul+texto;
                break;
            case "magenta": texto=FONDO_magenta+texto;
                break;
            case "cyan": texto=FONDO_cyan+texto;
                break;
            case "blanco": texto=FONDO_blanco+texto;
                break;
            default:
        }
        return (texto+RESET);

    }
}

