package dominio;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class VehiculoAlquilado implements Serializable {
    private static final long serialVersionUID = 1L;
    private Cliente cliente;
    private Vehiculo vehiculo;
    private String fechaAlquiler;
    private int totalDiasAlquiler;

    public VehiculoAlquilado(){
    }

    public VehiculoAlquilado(Cliente cliente, Vehiculo vehiculo, String fechaAlquiler, int totalDiasAlquiler){
        this.cliente = cliente;
        this.vehiculo = vehiculo;
        this.fechaAlquiler = fechaAlquiler;
        this.totalDiasAlquiler = totalDiasAlquiler;
    }

    public String getFechaDevolucion(){
 
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyy");
            Calendar fechaVencimientoAlquiler = Calendar.getInstance();
            try{
                fechaVencimientoAlquiler.setTime(sdf.parse(fechaAlquiler));
            }catch(ParseException ex){
                ex.printStackTrace();
            }
            //Suma de dias de inicio al fin del alquiler.
            fechaVencimientoAlquiler.add(Calendar.DAY_OF_MONTH, this.totalDiasAlquiler);
          
            return sdf.format(fechaVencimientoAlquiler.getTime());
    }

    @Override
    public String toString() {
        
        var sb = new StringBuilder();
        sb.append(String.format("%-7s %15s", "NIF", "NOMBRE" + "\n"));
        sb.append(this.cliente + "\n\n");
        sb.append(String.format("%-15s %-15s %-15s %-15s %-15s %-15s", "MATRICULA", "MARCA", "MODELO", "COLOR","TARIFA", "DISPONIBILIDAD" + "\n"));
        sb.append(this.vehiculo + "\n\n");
        sb.append("FECHA DE ALQUILER: ").append(this.fechaAlquiler);
        sb.append("\t -    Tiene un total de dias de alquiler de " + this.totalDiasAlquiler + "\n");
        sb.append("FECHA DE DEVOLUCION: ").append(String.format("%3s %1s", this.getFechaDevolucion(), "\n"));
        return sb.toString();
                
    }

}



