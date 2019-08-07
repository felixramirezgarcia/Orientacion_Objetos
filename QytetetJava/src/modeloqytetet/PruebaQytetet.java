
package modeloqytetet;

import java.util.ArrayList;

public class PruebaQytetet {

    private static ArrayList<Sorpresa> mazo = new ArrayList();
    private static Tablero tablero = new Tablero();
    
    private ArrayList<Sorpresa> sorpresasMayor0 () {
        ArrayList<Sorpresa> res = new ArrayList();
        
        for (int i = 0; i < mazo.size(); i++) {
            if (mazo.get(i).getValor() > 0)
                res.add(mazo.get(i));
        }
        
        return res;
    }
    
    private ArrayList<Sorpresa> IRACASILLA () {
        ArrayList <Sorpresa> res = new ArrayList();
        
        for (int i = 0; i < mazo.size(); i++) {
            if (mazo.get(i).getTipo() == TipoSorpresa.IRACASILLA)
                res.add(mazo.get(i));
        }
        
        return res;
    }
    
    private ArrayList<Sorpresa> TipoSorpresa(TipoSorpresa t) {
        ArrayList <Sorpresa> res = new ArrayList();
        
        for (int i = 0; i < mazo.size(); i++) {
            if (mazo.get(i).getTipo() == t)
                res.add(mazo.get(i));
        }
        
        return res;
    }
    
    /*private static ArrayList<Casilla> todoTablero() {
        return tablero.casillas;
    }*/
    
    private static void inicializarSorpresas() {
       mazo.add(new Sorpresa("Te hemos pillado con chanclas y calcetines, lo  sentimos, ¡debes ir a la carcel!", 9, TipoSorpresa.IRACASILLA));
       mazo.add(new Sorpresa("Pagas por la sorpresa",-1500,TipoSorpresa.PAGARCOBRAR));
       mazo.add(new Sorpresa("Ganas por la sorpresa",3000,TipoSorpresa.PAGARCOBRAR));
       mazo.add(new Sorpresa("Vas a la casilla porque...",5,TipoSorpresa.IRACASILLA));
       mazo.add(new Sorpresa("Vas a la carcel por corrupto",9,TipoSorpresa.IRACASILLA));
       mazo.add(new Sorpresa("Pagas por tener tanto y ganar tan poco",2000,TipoSorpresa.PORCASAHOTEL));
       mazo.add(new Sorpresa("A pagar impuestos",1000,TipoSorpresa.PORCASAHOTEL));
       mazo.add(new Sorpresa("Tus amigos estan generosos",300,TipoSorpresa.PORJUGADOR));
       mazo.add(new Sorpresa("Le debes pasta a tus colegas",-250,TipoSorpresa.PORJUGADOR));
       mazo.add(new Sorpresa("Vas a la casilla porque...",15,TipoSorpresa.IRACASILLA));
       mazo.add(new Sorpresa("Una pena que te vallas, sigue asi y volveras",0,TipoSorpresa.SALIRCARCEL));
    }
    
    /*public Casilla getCarcel() {
                
        for (Casilla c : tablero.casillas)
            if (c.getTipo() == TipoCasilla.CARCEL)
                return c;
        return null;
    }*/
    
    
    public static void main(String[] args) {
        inicializarSorpresas();
       // ArrayList<Casilla> tab = todoTablero();
        
        System.out.println("Vamos a ver el mazo de sorpresas:");
        System.out.println(mazo.toString());
        
        System.out.println("Vamos a ver el tablero: ");
        System.out.println(tablero.toString());
        
        System.out.println("Número de la casilla de la cárcel:");
        System.out.println(tablero.getCarcel().getNumeroCasilla());
    }
    
}
