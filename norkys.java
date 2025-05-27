import java.util.*;
import java.util.regex.Pattern;

public class norkys {
    Scanner sc = new Scanner(System.in);

    String[] platos = {"Estofado de pollo", "Pollo al horno", "Lentejas con pescado","Ceviche", "Kankacho", "Rocoto relleno", "Pastel de papa"};
    double[] precios = {10.0, 11.00, 12.00, 25.0, 30.0, 22.0, 20.0};
    String[] descripciones=new String[platos.length];
    String[] tiposPlatos = {"MENU","MENU","MENU","EXTRA","EXTRA","EXTRA","EXTRA"};
    String[] categorias= {"Menus","Extras"};

    int[] carrito = new int[platos.length];

    double tasaIgv = 0.18;

    boolean cuentaRegistrada=false;

    boolean sesionIniciada =false;
    String emailGuardado = "";
    String contraseñaGuardada = "";
    String nombreUsuario="";

    public void menuPrincipal() {
        System.out.println("\n--------- BIENVENIDO  A NORKYS -----------");
        if (sesionIniciada){
            System.out.println("Hola "+nombreUsuario);
        }
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
                verPlatos("CARTA COMPLETA");
                break;
            case 2:
                verCategorías(platos, precios);
                break;
            case 3:
                verCostoEnCarrito();
                break;
            case 4:
                verificarInicioSesion();
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
        if(sesionIniciada){
            System.out.println(nombreUsuario+" tu sesión está activa");
            cerrarSesion();
        }else {
            iniciarSesion();
        }
    }

    private void cerrarSesion() {
        System.out.println("¿Cerrar sesión?");
        System.out.println(" Se perderán todos los datos ingresados");
        System.out.println(" 1) Si");
        System.out.println(" 2) No");
        int seleccion=sc.nextInt();
        sc.nextLine();
        switch (seleccion){
            case 1:
                vaciarCarrito();
                sesionIniciada = false;
            default: menuPrincipal();
        }
    }

    private void vaciarCarrito() {
        for (int i = 0; i < carrito.length; i++) {
            carrito[i]=0;
        }
        System.out.println("El carrito ahora esta vacío");
    }



    public void iniciarSesion() {
        if (!cuentaRegistrada){
            System.out.println("No hay una cuenta registrada");
            registrarCuenta();
            return; //termina el metodo
        }
        System.out.println("-----------------------------");
        System.out.println("INICIAR SESIÓN");
        if (regresar()){
            return;
        }
        sc.nextLine(); // para evitar error
        int intento = 3;
        while (intento > 0) {
            System.out.println("Ingrese su correo:");
            String correo = sc.nextLine();
            System.out.println("Ingrese su contraseña:");
            String contra = sc.nextLine();
            if (correo.equals(emailGuardado) && contra.equals(contraseñaGuardada)) {
                System.out.println("Inicio de sesión exitoso.");
                sesionIniciada =true;
                menuPrincipal();
                return; //termina el metodo
            } else {
                intento--;
                System.out.println("Correo o contraseña incorrecta. Intentos restantes: " + intento);
            }
        }
        System.out.println("Se agotaron los intentos.");
        menuPrincipal();
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
                default: menuPrincipal();
            }
            menuPrincipal();
        }else{
            System.out.println("REGISTRAR CUENTA");
            if (regresar()){
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

        System.out.println("0 <-- Regresar al menú principal");
        System.out.println("---------------------------------");

        Integer[] indices=new Integer[platos.length];
        int contador=0;
        if (categoria.equals("CARTA COMPLETA")){
            for (int i = 0; i < platos.length; i++) {
                System.out.println((i+1) + " --> ("+ tiposPlatos[i]+") "+ platos[i] +" - Precio: S/." + precios[i]);
                indices[contador]=i;
                contador++;
            }
        }else{
            for (int i = 0; i < platos.length; i++) {
                if (tiposPlatos[i].toUpperCase().equals(categoria.toUpperCase())){
                    System.out.println((contador+1) + " --> " + platos[i] + " - Precio: S/." + precios[i]);
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

    private void verCategorías(String[] platos, double[] precios) {
        System.out.println("--------------------------------------");
        System.out.println("Elige entre las siguientes categorías");
        System.out.println("-----------------------------");
        System.out.println("0) Regresar al menú principal");
        System.out.println("-----------------------------");
        for (int i = 0; i < categorias.length; i++) {
            System.out.println((i+1)+") "+categorias[i]);
        }
        int seleccion=sc.nextInt();

        switch (seleccion){
            case 0: menuPrincipal(); break;
            case 1: verPlatos("MENU"); break;
            case 2: verPlatos("EXTRA"); break;
            default:
                System.out.println("Opción inválida");
                menuPrincipal();
        }

    }


    private void mostrarDesripcion(int indice, String categoria) {
        System.out.println("-------------------------------------");
        System.out.println(platos[indice].toUpperCase()+":");
        System.out.println("----------------------------");
        System.out.println("* Descripción:\n"+descripciones[indice]);
        System.out.println("\n* Tipo: "+tiposPlatos[indice]);
        System.out.println("* Precio: S/."+precios[indice]);
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

    private double verCostoEnCarrito() {
        System.out.println("------------------------------------");
        System.out.println("PRODUCTOS EN SU CARRITO:");
        double total=0.0;
        for (int i = 0; i < carrito.length; i++) {
            if (carrito[i]!=0){
                double subtotal= carrito[i]*precios[i];
                System.out.println("* "+carrito[i]+" platos de "+platos[i]+" Subotal: S/."+ subtotal);
                total+=subtotal;
            }
        }
        if (total>0){
            return total;
        }else{
            System.out.println("Usted aún no añadió platos a su carrito");
            menuPrincipal();
        }
        return total;
    }


    private void añadirAlCarrito(int indice, String categoria) {

        if (!sesionIniciada){
            iniciarSesion();
            return;// termina el metodo
        }

        System.out.println("¿Cuántos platos de " + platos[indice] + " añadirá a su carrito?");
        int cantidad = sc.nextInt();
        if (cantidad<=0){
            System.out.println("Cantidad inválida");
            return; //termina el metodo
        }
        carrito[indice] += cantidad;

        System.out.println("Ud ha pedido " + carrito[indice] + " platos de " + platos[indice]);
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
                double subtotalProducto = carrito[i] * precios[i];
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
        menuPrincipal();
    }


    private void llenarDescripciones() {
        for (int i = 0; i < descripciones.length; i++) {
            descripciones[i]="Descripción del plato "+platos[i];
        }
    }

    public static void main(String[] args) {
        norkys comidita = new norkys();
        comidita.llenarDescripciones();
        comidita.menuPrincipal();
    }

}