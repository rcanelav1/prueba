package test;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import dominio.*;


public class Test {
    static final Scanner consola = new Scanner(System.in);
    public static void main(String[] args)  {
        
        EmpresaAlquilerVehiculos easydrive = null;
        Vehiculo vehiculo;
        
      
        boolean empleado = false;
        int option = -1;
        String defaultOption = "Opcion no reconocida";
        //Carga los datos de la empresa.
        
            final Path path = FileSystems.getDefault().getPath("easydrive.dat");
            if(Files.isReadable(path))
                easydrive = cargarDatos();

            if(easydrive == null){
                easydrive = new EmpresaAlquilerVehiculos("easydrive", "A-28-18 189");
                Cliente cliente1 = new Cliente("RAMON", "CANELA VILLAMIZAR", "1A");
                Cliente cliente2 = new Cliente("CARLOS", "BLAKE LUAR", "2B");
                Cliente cliente3 = new Cliente("ANA", "TOT HOLSEN", "3C");
                Vehiculo vehiculo1 = new Vehiculo("HONDA", "BOCHECHA", "BLANCO", "ACP 832", 600, false);
                Deportivo vehiculoDeportivo1 = new Deportivo("AUDI", "A03", "NEGRO", "666 696", 753, true, 500);
                var vehiculoTurismo1 = new Turismo("PEUGEOT", "503", "NEGRO", "ASD 832", 500, false, 4, true);
                easydrive.registrarCliente(cliente1);
                easydrive.registrarCliente(cliente2);
                easydrive.registrarCliente(cliente3);
                easydrive.registrarVehiculo(vehiculo1);
                easydrive.registrarVehiculo(vehiculoDeportivo1);
                easydrive.registrarVehiculo(vehiculoTurismo1);
                easydrive.alquilarVehiculo(vehiculoDeportivo1.getMatricula(), cliente1.getNif(), 50);
            }
        
        clear();

        do{
            do{
                option = menu();
                switch(option){
                    case 0:
                        System.out.println("Gracias por utilizar EasyDrive.");
                        System.exit(0);
                        break;
                    case 1:
                        //Ingresar como empleado de Easydrive
                        empleado = easydrive.autenticacionUsuario();
                        clear();
                        break;
                    case 2:
                        //Ingresar como cliente
                        clear();
                        break;
                    default:
                        System.out.println(defaultOption);
                }
            }while(!empleado && option == 1);
        
            if(empleado && option == 1){
                do{
                    option =  menuEmpleado();
                    switch(option){
                        case 1:
                            easydrive.imprimirVehiculos();
                            continuar();
                            clear();
                            break;
                        case 2:
                            easydrive.imprimirVehiculosAlquilados();
                            continuar();
                            clear();
                            break;
                        case 3:
                            easydrive.imprimirClientes();
                            continuar();
                            clear();
                            break;
                        case 4:
                            System.out.println("Menu de registro de vehiculo" + "\n");
                            vehiculo = crearVehiculo();
                            if(vehiculo != null){
                                easydrive.registrarVehiculo(vehiculo);
                                guardarDatos(easydrive);
                            }
                            continuar();
                            clear();
                            break;
                        case 5:
                            System.out.println("Menu de recibo de vehiculo - HABILITA LA DISPONIBILIDAD DEL MISMO.\n");
                            easydrive.imprimirVehiculos();
                            System.out.println("\nIngrese el numero de matricula del coche que desee habilitar.");
                            String matriculaUsuario = consola.nextLine().toUpperCase();
                            Boolean matriculaCorroborada = easydrive.getMatricula(matriculaUsuario);
                            if(Boolean.FALSE.equals(matriculaCorroborada)){
                                System.out.println("Matricula incorrecta.");
                                continuar();
                                clear();
                                break;
                            }else
                                easydrive.recibirVehiculo(matriculaUsuario);
                                guardarDatos(easydrive);
                            continuar();
                            clear();
                            break;
                        case 0:
                            clear();
                            break;
                        default:
                            System.out.println(defaultOption);
                    }
                    
                }while(option != 0);
            }else if(option == 2){
                do{
                    option = menuCliente();
                    switch(option){
                        case 1:
                            Cliente cliente = crearCliente();
                            easydrive.registrarCliente(cliente);
                            System.out.println("Pulse ENTER para crear su usuario");
                            consola.nextLine();
                            System.out.println("Usuario creado.");
                            guardarDatos(easydrive);
                            continuar();
                            clear();
                            break;
                        case 2:
                            boolean vehiculosDisponibles = easydrive.imprimirVehiculosClientes();
                            if(vehiculosDisponibles){
                                List<Object> datos = alquilarVehiculo();
                                String nif = (String) datos.get(0);
                                if(!easydrive.verificarCliente(nif)){
                                    System.out.println("Su NIF no existe, por favor registrese en el sistema");
                                    continuar();
                                    break;
                                }
                                String matricula = (String) datos.get(1);
                                Integer dias = (Integer) datos.get(2);
                                easydrive.alquilarVehiculo(matricula, nif, dias);
                                guardarDatos(easydrive);
                            }
                            continuar();
                            clear();
                            break;
                        case 0:
                            clear();
                            break;
                        default:
                            System.out.println(defaultOption);
                    }
                }while(option != 0);
            }
        empleado = false;
        }while(option != 1 && option != 2);
    }
    
    public static int menu(){
        StringBuilder sb = new StringBuilder();
        // Scanner consola = new Scanner(System.in);
        int option = -1;
        sb.append("\tBienvenido a EasyDrive." + "\n\n");
        sb.append("1. Ingresar como empleado." + "\n");
        sb.append("2. Ingresar como cliente." + "\n");
        sb.append("0. Salir de EasyDrive.");
        System.out.println(sb);
        
        
        option = Integer.parseInt(consola.nextLine());
          
        return option;
    }
    
    public static int  menuEmpleado(){
        
        StringBuilder sb = new StringBuilder();
        
        int option = -1;
        sb.append("Bienvenido al menu de empleados de Easydrive."+"\n\n");
        sb.append("1. Imprimir lista de vehiculos." + "\n");
        sb.append("2. Imprimir lista de vehiculos alquilados." + "\n");
        sb.append("3. Imprimir lista de clientes." + "\n");
        sb.append("4. Registrar vehiculo." + "\n");
        sb.append("5. Recibir vehiculo" + "\n");
        sb.append("0. Salir.");
        System.out.println(sb);

        option = Integer.parseInt(consola.nextLine());

        return option;
    }

    public static int menuCliente(){
        StringBuilder sb = new StringBuilder();
        
        int option = -1;
        sb.append("Bienvenido al menu de clientes de Easydrive."+"\n\n");
        sb.append("1. Registrarse." + "\n");
        sb.append("2. Alquilar vehiculo." + "\n");
        sb.append("0. Salir.");
        System.out.println(sb);
        option = Integer.parseInt(consola.nextLine());

        return option;

    }

    public static Cliente crearCliente(){
        String nif;
        String nombre;
        String apellidos;

        
        System.out.println("Ingrese su nombre:");
        nombre = consola.nextLine();
        System.out.println("Ingrese sus apellidos");
        apellidos = consola.nextLine();
        System.out.println("Ingrese su NIF");
        nif = consola.nextLine();
        return new Cliente(nombre, apellidos, nif);
    }

    public static Vehiculo crearVehiculo(){
        int option = -1;
        String marca;
        String modelo;
        String color;
        String matricula;
        float tarifa;
        boolean disponibilidad = true;
        boolean automatico;
        int numPuertas;
        int cilindrada;
        Vehiculo vehiculo = null;
        
        
        do{
            System.out.println("Indique el tipo de vehiculo que desea crear:");
            System.out.println("1. Vehiculo standard.");
            System.out.println("2. Deportivo.");
            System.out.println("3. Turismo.");
            System.out.println("0. Salir.");
            
            option = Integer.parseInt(consola.nextLine());
        }while(option < 0 || option > 3);
        if(option == 0)
            return null;

        System.out.println("Ingrese la marca del vehiculo:");
        marca = consola.nextLine().toUpperCase();
        System.out.println("Ingrese el modelo del vehiculo");
        modelo = consola.nextLine().toUpperCase();
        System.out.println("Ingrese el color del vehiculo");
        color = consola.nextLine().toUpperCase();
        System.out.println("Ingrese la matricula del vehiculo");
        matricula = consola.nextLine().toUpperCase();
        System.out.println("Ingrese la tarifa del vehiculo");
        tarifa = Float.parseFloat(consola.nextLine());


        switch(option){
            case 1:
                vehiculo = new Vehiculo(marca, modelo, color, matricula, tarifa, disponibilidad);
                break;
            case 2:
                System.out.println("Ingrese la cilindrada del deportivo");
                cilindrada = Integer.parseInt(consola.nextLine());
                vehiculo = new Deportivo(marca, modelo, color, matricula, tarifa, disponibilidad, cilindrada);
                break;
            case 3:
                System.out.println("Ingrese el numero de puertas");
                numPuertas = Integer.parseInt(consola.nextLine());
                System.out.println("Ingrese TRUE si es automatico o FALSE si es MANUAL");
                automatico = Boolean.parseBoolean(consola.nextLine());
                vehiculo = new Turismo(marca, modelo, color, matricula, tarifa, disponibilidad, numPuertas, automatico);
                break;
            default:
                System.out.println("Opcion incorrecta");
        }
        
        return vehiculo;
    }

    public static List<Object> alquilarVehiculo(){
        boolean repetir = false;
        
        List<Object> datos = new ArrayList<>();
        System.out.println("Bienvenido al menu de alquiler de vehiculos");
        System.out.println("Indique su NIF");
        String nif = consola.nextLine().toUpperCase();
        datos.add(nif);

        System.out.println("Indique la matricula del coche que desea alquilar");
        String matricula = consola.nextLine().toUpperCase();
        datos.add(matricula);

        do {
            repetir = false;
            System.out.println("Indique la cantidad de dias que desea alquilar el coche");
            Integer dias = null;
            try{
            dias = Integer.parseInt(consola.nextLine());
            }catch(NumberFormatException ex){
                System.out.println("Debe ingresar un numero de dias valido.");
                repetir = true;
            } 
            if(Boolean.FALSE.equals(repetir))
                datos.add(dias);
              
        } while (Boolean.TRUE.equals(repetir));
          
        return datos;
    }
    
    public static void clear(){
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }

    public static void continuar(){
        System.out.println("\n\nPulse ENTER para continuar");
        consola.nextLine();
    }

    public static void guardarDatos(EmpresaAlquilerVehiculos empresa) {
        String archivo = "easydrive.dat";
        try(FileOutputStream ficheroSalida = new FileOutputStream(archivo);
            ObjectOutputStream objetoSalida = new ObjectOutputStream(ficheroSalida)) {

            objetoSalida.writeObject(empresa);
            
        } catch (FileNotFoundException ex){
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public static EmpresaAlquilerVehiculos cargarDatos(){
        String archivo = "easydrive.dat";
        EmpresaAlquilerVehiculos empresa = null;
        try(FileInputStream ficheroEntrada = new FileInputStream(archivo);
            ObjectInputStream objetoEntrada = new ObjectInputStream(ficheroEntrada)){
            empresa = (EmpresaAlquilerVehiculos)objetoEntrada.readObject();
            System.out.println("Datos cargados");
            return empresa;
            
        } catch (FileNotFoundException ex){
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    
}
