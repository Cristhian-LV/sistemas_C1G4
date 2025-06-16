import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

public class norkys_v2 {

    Scanner sc=new Scanner(System.in);

    private ArrayList<String> platos;
    private ArrayList<Double> precios;
    private ArrayList<String> descripciones;
    private ArrayList<String> tiposPlatos;
    private ArrayList<String> categorias;
    private ArrayList<Integer> carritoIndice;
    private ArrayList<Integer> carritoCantidad;

    private ArrayList<String> emails;
    private ArrayList<String> contrase帽as;
    private ArrayList<String> usuarios;
    private ArrayList<String> roles;

    private String usuarioActual;

    boolean cuentaRegistrada=false;

    boolean sesionIniciada =true;

    public static void main(String[] args){
        norkys_v2 n2=new norkys_v2();
        n2.cargarDatos();
        n2.menuPrincipal();
    }

    private void cargarDatos() {
        platos=new ArrayList<>(Arrays.asList("plato 1","plato 2","Plato 3","plato 4"));
        precios=new ArrayList<>(Arrays.asList(10.00,20.00,30.00,40.00));
        descripciones=new ArrayList<>(Arrays.asList("descripcion 1","descripcion 2","Descripcion 3","descripcion 4"));
        tiposPlatos=new ArrayList<>(Arrays.asList("TIPO 1","TIPO 1","TIPO 2","TIPO 2"));
        categorias=new ArrayList<>(Arrays.asList("tipo 1","Tipo 2"));
        carritoIndice =new ArrayList<>();
        carritoCantidad =new ArrayList<>();

        emails=new ArrayList<>(Arrays.asList("jclv@gmail.com","ama@gmail.com","as@gmail.com"));
        contrase帽as=new ArrayList<>(Arrays.asList("1234567jclv@","1234567ama@","1234567as@"));
        usuarios=new ArrayList<>(Arrays.asList("Cristhian","Anderson","Armando"));
        roles=new ArrayList<>(Arrays.asList("Administrador","Vendedor","Vendedor"));
        usuarioActual="Cristhian";
    }

    private void menuPrincipal() {
        System.out.println("---------------------------------------------");
        System.out.println("Elige alguna de las siguientes opciones");
        System.out.println(" 1) Ver todos los platos");
        System.out.println(" 2) Ver por categorias");
        System.out.println(" 3) Ver carrito");
        System.out.println(" 4) 馃攽 "+(sesionIniciada? usuarioActual:"Iniciar sesi贸n"));
        System.out.println(" 5) Registrar cuenta");
        String seleccion = sc.nextLine();

        switch (seleccion) {
            case "1":
                verPlatos("");
                break;
            case "2":
                verCategor铆as(platos, precios);
                break;
            case "3":
                verCarrito();
                break;
            case "4":
                if (sesionIniciada){
                    ajustesCuenta();
                }else iniciarSesion();
                break;
            case "5":
                registrarCuenta();
                break;
            default:
                System.out.println("Opci贸n no v谩lida");
                menuPrincipal();
                break;
        }
    }

    private void ajustesCuenta() {
        System.out.println("HOLA, "+usuarioActual);
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
        switch (seleccion){
            case "1":
                System.out.println("EDITARRRRRRRRRRRRRRRRRRRRRRRRR");
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
            sesionIniciada=false;
            menuPrincipal();
            return;
        }
        if (carritoIndice.isEmpty()){
            sesionIniciada=false;
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
                sesionIniciada = false; // poner variable de iniciar sesion en cerrado
            default: menuPrincipal(); //volver al menu principal
        }
    }

    public void iniciarSesion() {

        System.out.println("-----------------------------");
        System.out.println("INICIAR SESI脫N");

        int intentos = 3;
        while (intentos > 0) {
            System.out.println("Ingrese su correo:");
            String correo = sc.nextLine(); //pedir al usuario
            if (emails.contains(correo)){
                String contrase帽aGuardada=contrase帽as.get(emails.indexOf(correo));
                System.out.println("Ingrese su contrase帽a:");
                String contrase帽a = sc.nextLine(); //pedir al usuario
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
        if(emails.isEmpty()){
            System.out.println("REGISTRAR CUENTA");
            if (regresar()){ //este if pide si regresar o no
                return;
            }
            String email, contra;
            sc.nextLine();// para evitar error
            do {
                System.out.println("Ingrese su correo electr贸nico:");
                email = sc.nextLine();
                if (!validarCorreo(email)) {
                    System.out.println("Correo inv谩lido.");
                }
            } while (!validarCorreo(email));

            do {
                System.out.println("Ingrese su contrase帽a:");
                contra = sc.nextLine();
                if (!validarContrase帽a(contra)) {
                    System.out.println("Contrase帽a inv谩lida. Debe tener al menos 8 caracteres, incluir letras, n煤meros y un car谩cter especial.");
                }
            } while (!validarContrase帽a(contra));

            System.out.println("Ingrese su nombre de usuario:");
            usuarioActual=sc.nextLine().toUpperCase();

            cuentaRegistrada=true;
            System.out.println("\nCuenta registrada con 茅xito.");

            menuPrincipal();

        }else{
            System.out.println("Ya existe una cuenta registrada");
            System.out.println("* Nombre de ususario: "+usuarioActual);
            System.out.println("  驴Deseas eliminarla?");
            System.out.println("   Tambi茅n terminar谩s sesi贸n y se borraran tus pedidos registrados");
            System.out.println("   1) Si");
            System.out.println("   2) No");
            int seleccion=sc.nextInt();
            sc.nextLine();
            switch (seleccion){
                case 1:
                    usuarioActual="";
                    cuentaRegistrada=false;
                    carritoIndice.clear();
                    carritoCantidad.clear();
                    sesionIniciada=false;
                    break;
                case 2:
                    menuPrincipal();
                    break;
                default: menuPrincipal();
            }
            menuPrincipal();
        }
    }

    private boolean validarCorreo(String email) {
        String regex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        return Pattern.compile(regex).matcher(email).matches();
    }

    private boolean validarContrase帽a(String contrase帽a) {
        String regex = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        return Pattern.compile(regex).matcher(contrase帽a).matches();
    }


    public void verPlatos(String categoria) {
        System.out.println("------------ "+categoria+" ------------");
        System.out.println("0) Regresar al men煤 principal");
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
            System.out.println("Opci贸n inv谩lida");
            menuPrincipal();
        }
    }

    private void verCategor铆as(ArrayList<String> platos, ArrayList<Double> precios) {
        System.out.println("--------- CATEGOR脥AS ---------");
        System.out.println("0) Regresar al men煤 principal");
        System.out.println("------------------------------");
        for (int i = 0; i < categorias.size(); i++) {
            System.out.println((i+1)+") "+categorias.get(i));
        }

        int seleccion = sc.nextInt();

        if (seleccion<=0){
            menuPrincipal();
        } else if (seleccion>categorias.size()) {
            System.out.println("Opci贸n inv谩lida");
            menuPrincipal();
        } else {
            verPlatos(categorias.get(seleccion-1).toUpperCase());
        }
    }


    private void mostrarDesripcion(int indice, String categoria) {
        System.out.println("-------------------------------------");
        System.out.println(platos.get(indice).toUpperCase()+":");
        System.out.println("----------------------------");
        System.out.println("* Descripci贸n:\n"+descripciones.get(indice));
        System.out.println("\n* Tipo: "+tiposPlatos.get(indice));
        System.out.println("* Precio: S/."+String.format("%.2f", precios.get(indice)));
        System.out.println("-------------------------------------");
        System.out.println("\n驴Quieres a帽adir este plato a tu carrito?");
        System.out.println(" 1) Si");
        System.out.println(" 2) Regresar");
        int seleccion=sc.nextInt();
        sc.nextLine();
        switch (seleccion){
            case 1: a帽adirAlCarrito(indice, categoria);
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
            System.out.println("驴Que desea hacer?");
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
                    System.out.println("Opci贸n inv谩lida");
                    menuPrincipal();
                    break;
            }
        }else{
            System.out.println("Usted a煤n no a帽adi贸 platos a su carrito");
            menuPrincipal();
        }
    }


    private void a帽adirAlCarrito(int indice, String categoria) {

        if (!sesionIniciada){
            iniciarSesion();
            return;// termina el metodo
        }

        System.out.println("驴Cu谩ntos platos de " + platos.get(indice) + " a帽adir谩 a su carrito?");
        int cantidad = sc.nextInt();
        if (cantidad<=0){
            System.out.println("Cantidad inv谩lida");
            return; //termina el metodo
        }
        carritoCantidad.set(indice,carritoCantidad.get(indice)+cantidad);

        System.out.println("Ud ha pedido " + carritoCantidad.get(indice) + " platos de " + platos.get(indice));
        System.out.println("-------------------------------------");
        System.out.println("驴Qu茅 desea hacer ahora?");
        System.out.println(" 1) Elejir otro plato");
        System.out.println(" 2) Ir a pagar ");
        System.out.println(" 3) Volver al menu principal");

        int seleccion = sc.nextInt();
        sc.nextLine();

        switch (seleccion) {
            case 1: verPlatos(categoria); break;
            case 2: realizarPago(); break;
            case 3: menuPrincipal(); break;
            default: System.out.println("Opci贸n inv谩lida"); menuPrincipal(); break;
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

        System.out.println("隆Compra exitosa!");
        double vuelto = montoPagado - costoTotal;

        generarBoleta(subtotal, igv, costoTotal, montoPagado, vuelto);
    }


    private void generarBoleta(double subtotal, double igv, double costoTotal, double montoPagado, double vuelto) {
        System.out.println("------------ BOLETA DE VENTA ------------");
        System.out.println(" SUBTOTAL       :     S/." + String.format("%.2f", subtotal));
        System.out.println(" IGV (18%)      :     S/." + String.format("%.2f", igv));
        System.out.println(" TOTAL A PAGAR  :     S/." + String.format("%.2f", costoTotal));
        System.out.println(" MONTO PAGADO   :     S/." + String.format("%.2f", montoPagado));
        System.out.println(" VUELTO         :     S/." + String.format("%.2f", vuelto));
        System.out.println("-----------------------------------------");

        carritoIndice.clear();
        carritoCantidad.clear();
        System.out.println("驴Terminar el programa?");
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

}
