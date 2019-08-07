
package modeloqytetet;

import java.util.ArrayList;


public class PruebaQytetet2 {
    public static Tablero t = new Tablero();
    public static ArrayList<Sorpresa> mazo = new ArrayList();
    
    void mostrarTablero() {    
        for (int i = 0; i < 20; i++)
            System.out.println(t.obtenerCasillaNumero(i).getTitulo().toString());
        
    }
    
    void mostrarSorpresas() {
        System.out.println(mazo.toString());
    }
    
    public static void main(String[] args) {
        PruebaQytetet2 p = new PruebaQytetet2();
        Qytetet q = Qytetet.getInstance();
        
        System.out.println("Muestro las casillas del tablero:");
        
        System.out.println(t.toString());
        
        
    }
    
}
