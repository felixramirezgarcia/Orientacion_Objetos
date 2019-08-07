
package modeloqytetet;

import java.util.ArrayList;

public class Tablero {
    private ArrayList<Casilla> casillas;
    private Casilla carcel;   
    
    public Tablero() {
        casillas = new ArrayList();
        this.inicializar();
    }
    
    boolean esCasillaCarcel(int numeroCasilla) {
        return carcel.getNumeroCasilla() == numeroCasilla;
    }

    public Casilla getCarcel() {
        return carcel;
    }
    
    Casilla obtenerCasillaNumero(int numeroCasilla) {
        Casilla res = null;
        
        for (Casilla c: casillas)
            if (c.getNumeroCasilla() == numeroCasilla)
                res = c;
        
        return res;
    }
    
    Casilla obtenerNuevaCasilla(Casilla casilla, int desplazamiento) {
        Casilla res = null;
        int num;
        
        num = casilla.getNumeroCasilla() + desplazamiento;
        
        if (num > 19)
            num = num - 20;
        
        for (Casilla c: casillas)
            if (c.getNumeroCasilla() == num)
                res = c;
        
        return res;
    }
    
    private void inicializar() {
        
        casillas.add(new OtraCasilla(0,TipoCasilla.SALIDA));
        casillas.add(new Calle(1,200,TipoCasilla.CALLE,new TituloPropiedad("El perro andaluz",50,10,150,250)));
        casillas.add(new Calle(2,250,TipoCasilla.CALLE,new TituloPropiedad("La guarida del lobo",55,11,200,270)));
        casillas.add(new OtraCasilla(3,TipoCasilla.IMPUESTO));
        casillas.add(new Calle(4,300,TipoCasilla.CALLE,new TituloPropiedad("Sawarma Boabdil",60,12,300,290)));
        casillas.add(new OtraCasilla(5,TipoCasilla.PARKING));
        casillas.add(new Calle(6,300,TipoCasilla.CALLE,new TituloPropiedad("Alimentacion Estudiante",65,13,350,310)));
        casillas.add(new OtraCasilla(7,TipoCasilla.SORPRESA));
        casillas.add(new Calle(8,350,TipoCasilla.CALLE,new TituloPropiedad("Sawarma Asador Real",65,14,450,350)));
        casillas.add(new OtraCasilla(9,TipoCasilla.CARCEL));
        casillas.add(new Calle(10,400,TipoCasilla.CALLE,new TituloPropiedad("Pub la rocka",70,15,550,400)));
        casillas.add(new Calle(11,450,TipoCasilla.CALLE,new TituloPropiedad("Bazar china city",75,16,650,430)));
        casillas.add(new OtraCasilla(12,TipoCasilla.SORPRESA));
        casillas.add(new Calle(13,450,TipoCasilla.CALLE,new TituloPropiedad("Sawarma poco loco",80,17,750,460)));
        casillas.add(new Calle(14,500,TipoCasilla.CALLE,new TituloPropiedad("Alimentacion Pekin",85,18,850,500)));
        casillas.add(new OtraCasilla(15,TipoCasilla.JUEZ));
        casillas.add(new Calle(16,550,TipoCasilla.CALLE,new TituloPropiedad("The sawarma king",90,19,950,580)));
        casillas.add(new Calle(17,600,TipoCasilla.CALLE,new TituloPropiedad("Pub Lscandalo",95,20,1000,650)));
        casillas.add(new OtraCasilla(18,TipoCasilla.SORPRESA));
        casillas.add(new Calle(19,700,TipoCasilla.CALLE,new TituloPropiedad("Chupiteria 69",100,20,1000,750)));
        
        this.carcel = casillas.get(9);
        
        for (Casilla c : casillas){
            if(c.getTipo() == TipoCasilla.CALLE){
                c.asignarTituloPropiedad();
            }
        }
    }
    
    
    @Override
    public String toString() {
        String cadena = " ";
        for (Casilla c : casillas){
                cadena = cadena + c + " ";        
        }
        return cadena;
    }
    
}
