package dominio;

import java.io.Serializable;

public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nif;
    private String nombre;
    private String apellidos;

    public Cliente(){
        
	//holaquetal
    }
    public Cliente(String nombre, String apellidos, String nif){
        this();
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nif = nif;
    }

    public String getNif() {
        return this.nif;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-15s %-2s %-2s", this.nif, this.nombre, this.apellidos));
        return sb.toString();
    }
    
}
