package dominio;

import java.io.*;

import java.text.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class EmpresaAlquilerVehiculos  implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nombre;
    private String cif;
    private List<Cliente> clientes;
    private List<Vehiculo> vehiculos;
    private List<VehiculoAlquilado> vehiculosAlquilados;
    transient Scanner consola = new Scanner(System.in);
    
    private EmpresaAlquilerVehiculos() {
        this.clientes = new ArrayList<>();
        this.vehiculos = new ArrayList<>();
        this.vehiculosAlquilados = new ArrayList<>();

    }

    public EmpresaAlquilerVehiculos(String nombre, String cif) {
        this();
        this.nombre = nombre;
        this.cif = cif;
    }

    public Vehiculo getVehiculo(String matricula) {
        for (int i = 0; i < vehiculos.size(); i++) {
            if (vehiculos.get(i).getVehiculo().equals(matricula))
                return vehiculos.get(i);
        }
        return null;
    }

    public Boolean getMatricula(String matricula) {
        Boolean matriculaCorroborada = false;
        for (int i = 0; i < vehiculos.size(); i++)
            if (vehiculos.get(i).getMatricula().equals(matricula)) {
                matriculaCorroborada = true;
                break;
            } else {
                matriculaCorroborada = false;

            }
        return matriculaCorroborada;
    }

    public Cliente getCliente(String nif) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getNif().equals(nif))
                return clientes.get(i);
        }
        return null;
    }

    public boolean verificarCliente(String nif) {
        boolean nifCorroborado = false;
        for (int i = 0; i < clientes.size(); i++) {
            if ((clientes.get(i).getNif().equals(nif)))
                nifCorroborado = true;
            else
                nifCorroborado = false;
        }
        return nifCorroborado;
    }

    public void registrarCliente(Cliente cliente) {
         
    int maxClientes = 50;
    String limiteClientes = "Esta empresa no acepta más clientes.";

    if (clientes.size() < maxClientes) {
        clientes.add(cliente);
    } else
        System.out.println(limiteClientes);
        
    }

    public void registrarVehiculo(Vehiculo vehiculo) {
        if(vehiculos.size() <= 50){
            vehiculos.add(vehiculo);
        }
    }

    public void imprimirClientes() {
        System.out.println("NIF\t\t CLIENTES\n");
        clientes.forEach(System.out::println);
    }

    public void imprimirVehiculos() {
        System.out.println(String.format("%-15s %-15s %-15s %-15s %-15s %-15s", "MATRICULA", "MARCA", "MODELO", "COLOR",
                "TARIFA", "DISPONIBILIDAD"));
        for (int i = 0; i < vehiculos.size(); i++)
            System.out.println(vehiculos.get(i));
    }

    public boolean imprimirVehiculosClientes() {
        int contadorDisponibilidad = 0;
        boolean vehiculosDisponibles = true;
        System.out.println(String.format("%-15s %-15s %-15s %-15s %-15s %-15s", "MATRICULA", "MARCA", "MODELO", "COLOR",
                "TARIFA", "DISPONIBILIDAD"));
        for (int i = 0; i < vehiculos.size(); i++) {
            boolean disponibilidad = vehiculos.get(i).getDisponibilidad();
            if (disponibilidad) {
                System.out.println(vehiculos.get(i));
                contadorDisponibilidad++;
            }
        }
        if (contadorDisponibilidad == 0) {
            System.out.println("\nLO SENTIMOS, NO TENEMOS COCHES DISPONIBLES.");
            vehiculosDisponibles = false;
        }
        return vehiculosDisponibles;
    }

    public void alquilarVehiculo(String matricula, String nif, int dias) {
        Cliente cliente = getCliente(nif);
        Vehiculo vehiculo = getVehiculo(matricula);
        LocalDate fecha = null;
        String fechaAlquiler = null;
        String fechaEntrega = null;
        boolean disponible = false;

        if (vehiculo != null) {
            disponible = vehiculo.getDisponibilidad();
        } else
            System.out.println("Debe introducir una matricula valida.");

        if (disponible) {
            vehiculo.setDisponibilidad(false);
            // Formato de fecha del alquiler.
            fecha = LocalDate.now();
            DateTimeFormatter fechaFormateada = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            fechaAlquiler = fechaFormateada.format(fecha);
            System.out.println("La fecha del inicio de su alquiler es: " + fechaAlquiler);

            vehiculosAlquilados.add(new VehiculoAlquilado(cliente, vehiculo, fechaAlquiler, dias));
            // Formato de fecha de vencimiento del alquiler.
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyy");
            Calendar fechaVencimientoAlquiler = Calendar.getInstance();
            try {
                fechaVencimientoAlquiler.setTime(sdf.parse(fechaAlquiler));
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            // Suma de dias de inicio al fin del alquiler.
            fechaVencimientoAlquiler.add(Calendar.DAY_OF_MONTH, dias);
            fechaEntrega = sdf.format(fechaVencimientoAlquiler.getTime());
            System.out.println("Recuerde que la entrega de su vehiculo será el día: " + fechaEntrega);
            System.out.println("Gracias por contar con EasyDrive");

        }
    }

    public void imprimirVehiculosAlquilados() {

        for (int i = 0; i < vehiculosAlquilados.size(); i++) {
            System.out.println("-----------------VEHICULO " + (i + 1) + "-----------------\n");
            System.out.println(vehiculosAlquilados.get(i));
            System.out.println("\n");
        }

    }

    public void recibirVehiculo(String matricula) {

        Vehiculo vehiculo = getVehiculo(matricula);
        if (vehiculo != null)
            vehiculo.setDisponibilidad(true);
        System.out.println("Vehiculo habilitado.");
    }
    
    public boolean autenticacionUsuario()  {
        String usuario = "easydrive";
        String pass = "123";
        String cuentaUsuario = "";
        String passUsuario = "";
        int intentosAut = 3;

        do{
            System.out.println("Ingrese su nombre de usuario: (Posee "+ intentosAut + " intentos)");
            cuentaUsuario = consola.nextLine();
            System.out.println("Ingrese su contrasena");
            passUsuario = consola.nextLine();

            if(!cuentaUsuario.equals(usuario))
                System.out.println("Usuario incorrecto.");
            if(!passUsuario.equals(pass))
                System.out.println("Contrasena incorrecta");
            //Limpiar consola.
            System.out.print("\033[H\033[2J");
            System.out.flush();
            intentosAut--;
        }while((!cuentaUsuario.equals(usuario) && !passUsuario.equals(pass)) &&intentosAut > 0);

        return cuentaUsuario.equals(usuario);
    }
}
