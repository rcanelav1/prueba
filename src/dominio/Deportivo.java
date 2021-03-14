package dominio;

public class Deportivo extends Vehiculo {
    private static final long serialVersionUID = 1L;
    int cilindrada;

    public Deportivo(String marca, String modelo, String color, String matricula,
                    float tarifa, boolean disponibilidad, int cilindrada){
        super(marca, modelo, color, matricula, tarifa, disponibilidad);
        this.cilindrada = cilindrada;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t\tEXTRA: " + "Cilindrada = " + this.cilindrada);
        return super.toString() +  sb.toString();
    }
}