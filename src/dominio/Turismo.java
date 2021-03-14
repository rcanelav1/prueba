package dominio;

public class Turismo extends Vehiculo{
    private static final long serialVersionUID = 1L;
    int numPuertas;
    boolean automatico;

    public Turismo(String marca, String modelo, String color, String matricula,
                    float tarifa, boolean disponibilidad, int numPuertas, boolean automatico){
        super(marca, modelo, color, matricula, tarifa, disponibilidad);
        this.numPuertas = numPuertas;
        this.automatico = automatico;

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t\tEXTRA: Numero de puertas: " + this.numPuertas + ", Automatico: " + this.automatico);
        return super.toString() + String.format("%-70s", sb.toString());
    }
    
}

