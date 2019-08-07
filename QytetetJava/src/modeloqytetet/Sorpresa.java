
package modeloqytetet;

import java.util.ArrayList;

public class Sorpresa {
    private String texto;
    private TipoSorpresa tipo;
    private int valor;
    //private static ArrayList<Sorpresa> mazo;
    
    public Sorpresa(String texto, int valor, TipoSorpresa tipo) {
        this.texto = texto;
        this.tipo = tipo;
        this.valor = valor;
    }
    
    public String getTexto() {
        return texto;
    }
    
    public int getValor() {
        return this.valor;
    }
    
    public TipoSorpresa getTipo() {
        return this.tipo;
    }
        
    @Override
    public String toString() {
        return "\nSorpresa{" + "texto=" + texto + ", valor=" + Integer.toString(valor) + ", tipo=" + tipo + "}";
    }
}
