package dominio;
import java.io.Serializable;

public class Vehiculo implements Serializable{
    private static final long serialVersionUID = 1L;
    private String matricula;
    private String marca;
    private String modelo;
    private String color;
    private float tarifa;
    private boolean disponibilidad;

    public Vehiculo(){}
    public Vehiculo(String marca, String modelo, String color, String matricula, float tarifa, boolean disponibilidad){
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.matricula = matricula;
        this.tarifa = tarifa;
        this.disponibilidad = disponibilidad;
    }

    public String getVehiculo(){
        return this.matricula;
    }

    public String getMatricula() {
        return this.matricula;
    }
    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }
    public Boolean getDisponibilidad(){
        return this.disponibilidad;
    }
    @Override
    public String toString() {
        return String.format("%-15s %-15s %-15s %-15s %-15s %-15s", this.matricula, this.marca, this.modelo, this.color, this.tarifa+"€", this.disponibilidad);        
    }
    
}
