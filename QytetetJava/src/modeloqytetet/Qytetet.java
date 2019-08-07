
package modeloqytetet;

import java.util.ArrayList;
import java.util.List;
import GUIQytetet.*;

public class Qytetet {
    private static final Qytetet INSTANCE = new Qytetet();
    public static final int MAX_JUGADORES = 4;
    static int MAX_CARTAS = 10;
    static int MAX_CASILLAS = 20;
    static int PRECIO_LIBERTAD = 200;
    static int SALDO_SALIDA = 1000;
    private Dado dado;
    private Sorpresa cartaActual;
    private ArrayList<Sorpresa> mazo;
    private Tablero tablero;
    private Jugador jugadorActual;
    private ArrayList<Jugador> jugadores = new ArrayList();
    
    private Qytetet() { 
        this.inicializarCartasSorpresa();
        this.inicializarTablero();   
    }
    
    public static Qytetet getInstance() {
        return INSTANCE;
    }
    
    public Tablero getTablero() {
        return tablero;
    }
    
    public ArrayList<Sorpresa> getMazo() {
        return mazo;
    }
            
    public boolean aplicarSorpresa() {
        boolean tienePropietario = false;
        int valor = cartaActual.getValor();
        
        if (cartaActual.getTipo() == TipoSorpresa.PAGARCOBRAR) {
            jugadorActual.modificarSaldo(valor);
            
            if (valor > 0)
                System.out.println("¡Has recibido dinero! --> " + valor);
            else
                System.out.println("Has perdido dinero --> " + valor);
        }
        else if (cartaActual.getTipo() == TipoSorpresa.IRACASILLA) {
            boolean esCarcel = tablero.esCasillaCarcel(valor);
            
            if (esCarcel) {
                this.encarcelarJugador();
                System.out.println("Vas a la cárcel.");
            }
            else {
               Casilla nuevaCasilla = tablero.obtenerCasillaNumero(valor);
               tienePropietario = jugadorActual.actualizarPosicion(nuevaCasilla);
               System.out.println("Vas a la casilla " + valor);
            }
        }
        else if (cartaActual.getTipo() == TipoSorpresa.PORCASAHOTEL) {
            jugadorActual.pagarCobrarPorCasaYHotel(valor);
            
            if (valor > 0) {
                System.out.println("Ganas " + valor + " por cada casa y hotel en tus propiedades");
            }
            else
                System.out.println("Pierdes " + valor + "por cada casa y hotel en tus propiedades");
        }
        else if (cartaActual.getTipo() == TipoSorpresa.PORJUGADOR) {
            for (Jugador jugador : jugadores) {
                if (jugador.getNombre().equals(jugadorActual.getNombre())) {
                    jugador.modificarSaldo(valor);
                    jugadorActual.modificarSaldo(-valor);
                }
            }
            
            if (valor > 0) {
                System.out.println("Ganas " + valor + " por cada jugador");
            }
            else
                System.out.println("Pierdes " + valor + "por cada jugador"); 
        }
        else if (cartaActual.getTipo() == TipoSorpresa.CONVERTIRME) {
           jugadorActual.convertirme(valor);      
        }
        
        if (cartaActual.getTipo() == TipoSorpresa.SALIRCARCEL) {
            jugadorActual.setCartaLibertad(cartaActual);
            System.out.println("Recibes una carta de libertad");
        }
        else
            mazo.add(cartaActual);
        
        return tienePropietario;
    }
    
    public boolean comprarTituloPropiedad() {
        boolean puedoComprar = jugadorActual.comprarTitulo();
        
        return puedoComprar;
    }
    
    public boolean edificarCasa(Casilla casilla) {
        boolean sePuedeEdificar;
        boolean puedoEdificar = false;
        boolean puedoEdificarCasa;
        int costeEdificarCasa = 0;
        
        if (casilla.soyEdificable()) {
            sePuedeEdificar = casilla.sePuedeEdificarCasa(jugadorActual.factorEspeculador);
            
            if (sePuedeEdificar) {
                puedoEdificar = jugadorActual.puedoEdificarCasa(casilla);
                
                if (puedoEdificar) {
                    costeEdificarCasa = casilla.edificarCasa();
                    jugadorActual.modificarSaldo(-costeEdificarCasa);
                }
            }
        }
        
        puedoEdificarCasa = puedoEdificar;
        
        return puedoEdificarCasa;
    }
    
    public boolean edificarHotel(Casilla casilla) {
        boolean sePuedeEdificar;
        boolean puedoEdificar = false;
        boolean puedoEdificarHotel;
        int costeEdificarHotel = 0;
        
        if (casilla.soyEdificable()) {
            sePuedeEdificar = casilla.sePuedeEdificarHotel(jugadorActual.factorEspeculador);
            
            if (sePuedeEdificar) {
                puedoEdificar = jugadorActual.puedoEdificarHotel(casilla);
                
                if (puedoEdificar) {
                    costeEdificarHotel = casilla.edificarHotel();
                    jugadorActual.modificarSaldo(costeEdificarHotel);
                }
            }
        }
        
        puedoEdificarHotel = puedoEdificar;
        
        return puedoEdificarHotel;
    }
    
    public Sorpresa getCartaActual() {
        return cartaActual;
    }
    
    public Jugador getJugadorActual() {
        return jugadorActual;
    }
    
    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }
    
    public boolean hipotecarPropiedad(Casilla casilla) {
        boolean puedoHipotecarPropiedad;
        boolean sePuedeHipotecar;
        boolean puedoHipotecar = false;
        int cantidadRecibida;
        
        if (casilla.soyEdificable()) {
            sePuedeHipotecar = !casilla.estaHipotecada();
            
            if (sePuedeHipotecar) {
                puedoHipotecar = jugadorActual.puedoHipotecar(casilla);
            
                if (puedoHipotecar) {
                    cantidadRecibida = casilla.hipotecar();
                    jugadorActual.modificarSaldo(cantidadRecibida);
                }
            }
            
        }
        
        puedoHipotecarPropiedad = puedoHipotecar;
        
        return puedoHipotecarPropiedad;
    }
    
    public boolean cancelarHipoteca(Casilla casilla) {
        boolean puedoCancelarHipoteca;
        int cantidadAPagar = 0;
        
        puedoCancelarHipoteca = jugadorActual.puedoPagarHipoteca(casilla);
        
        if (puedoCancelarHipoteca) {
            cantidadAPagar = casilla.cancelarHipoteca();
            jugadorActual.modificarSaldo(-cantidadAPagar);
        }
        
        return puedoCancelarHipoteca;
    }
    
    public void inicializarJuego(ArrayList<String> nombres) {
        this.inicializarJugadores(nombres);
        this.inicializarCartasSorpresa();
        this.inicializarTablero();
        this.salidaJugadores();
        jugadorActual = jugadores.get(0);
        dado = Dado.getInstance();
        tablero = new Tablero();
    }
    
    public boolean intentarSalirCarcel(MetodoSalirCarcel metodo) {
        boolean libre = false;
        boolean tengoSaldo;
        int valorDado = 0;
        
        if (metodo == MetodoSalirCarcel.TIRANDODADO) {
            dado = Dado.getInstance();
            valorDado = dado.nextNumber();
            libre = valorDado > 5;
            System.out.println("Valor del dado: " + valorDado);
        }
        else if (metodo == MetodoSalirCarcel.PAGANDOLIBERTAD) {
            tengoSaldo = jugadorActual.pagarLibertad(200);
            libre = tengoSaldo;
        }
        
        if (libre) {
            jugadorActual.setEncarcelado(false);
            System.out.println("Te vas de la cárcel.");
        }
        else
            System.out.println("Te quedas un rato más en la cárcel.");
        
        return libre;            
    }
    
    public boolean jugar() {
        int valorDado = 0;
        boolean tienePropietario = false;
        Casilla casillaPosicion, nuevaCasilla;
        
        dado = Dado.getInstance();
        valorDado = dado.nextNumber();
        casillaPosicion = jugadorActual.getCasillaActual();
        nuevaCasilla = tablero.obtenerNuevaCasilla(casillaPosicion, valorDado);
        tienePropietario = jugadorActual.actualizarPosicion(nuevaCasilla);
        
        System.out.println("valor del dado: " + valorDado);
        System.out.println("avanza hasta: " + nuevaCasilla.toString());
        
   
        System.out.println("tienePropietario = " + tienePropietario);
        
        
        if (!nuevaCasilla.soyEdificable()) {
            if (nuevaCasilla.getTipo() == TipoCasilla.JUEZ) {
                this.encarcelarJugador();
            }
            else if (nuevaCasilla.getTipo() == TipoCasilla.SORPRESA) {
                cartaActual = mazo.get(0);
                mazo.remove(0);
            }
        }
        
        return tienePropietario;
    }
    
    public List obtenerRanking() {
        List ranking = new ArrayList();
        int capital;
        
        for (Jugador jugador : jugadores) {
            capital = jugador.obtenerCapital();
            ranking.add(jugador.getNombre()); //Por separado está bien??
            ranking.add(capital);
        }
        
        return ranking;
    }
    
    public ArrayList<Casilla> propiedadesHipotecadasJugador(boolean hipotecadas) {
        ArrayList<TituloPropiedad> propiedades;
        ArrayList<Casilla> casillas = new ArrayList();
        propiedades = jugadorActual.obtenerPropiedadesHipotecadas(hipotecadas);
        
        //Obtengo las casillas a partir de las propiedades
        for (TituloPropiedad t : propiedades)
            casillas.add(t.getCasilla());
        
        return casillas;
    }
    
    public Jugador siguienteJugador() {
        Jugador res;
        int indice;
        
        indice = jugadores.indexOf(jugadorActual);
        
        if (indice == jugadores.size() - 1) { //Si estoy en el último jugador
            indice = 0;
            res = jugadores.get(indice);         //Obtener el primer jugador
        }
        else {
            indice += 1;
            res = jugadores.get(indice); //adelanto una posición
        }
        
        jugadorActual = res;
                
        return res;
    }
    
    public boolean venderPropiedad(Casilla casilla) {
        boolean puedoVender = false;
        
        if (casilla.soyEdificable()) {
            puedoVender = jugadorActual.puedoVenderPropiedad(casilla);
            
            if (puedoVender) {
                jugadorActual.venderPropiedad(casilla);
            }
        }
        
        return puedoVender;
    }
    
    private void encarcelarJugador() {
        if (!jugadorActual.tengoCartaLibertad()) {
            Casilla casillaCarcel = tablero.getCarcel();
            jugadorActual.irACarcel(casillaCarcel);
        }
        else {
            Sorpresa carta = jugadorActual.devolverCartaLibertad();
            mazo.add(carta);
        }
    }
    
    private void inicializarCartasSorpresa() {
       this.mazo = new ArrayList();
       mazo.add(new Sorpresa("Te conviertes en un Especulador",3000,TipoSorpresa.CONVERTIRME));
       mazo.add(new Sorpresa("Te hemos pillado con chanclas y calcetines, lo  sentimos, ¡debes ir a la carcel!", 9, TipoSorpresa.IRACASILLA));
       mazo.add(new Sorpresa("Pagas por la sorpresa",-1500,TipoSorpresa.PAGARCOBRAR));
       mazo.add(new Sorpresa("Ganas por la sorpresa",3000,TipoSorpresa.PAGARCOBRAR));
       mazo.add(new Sorpresa("Vas a la casilla porque...",5,TipoSorpresa.IRACASILLA));
       mazo.add(new Sorpresa("Vas a la carcel por corrupto",9,TipoSorpresa.IRACASILLA));
       mazo.add(new Sorpresa("Pagas por tener tanto y ganar tan poco",2000,TipoSorpresa.PORCASAHOTEL));
       mazo.add(new Sorpresa("A pagar impuestos",1000,TipoSorpresa.PORCASAHOTEL));
       mazo.add(new Sorpresa("Te conviertes en un Especulador",5000,TipoSorpresa.CONVERTIRME));
       mazo.add(new Sorpresa("Tus amigos estan generosos",300,TipoSorpresa.PORJUGADOR));
       mazo.add(new Sorpresa("Le debes pasta a tus colegas",-250,TipoSorpresa.PORJUGADOR));
       mazo.add(new Sorpresa("Vas a la casilla porque...",15,TipoSorpresa.IRACASILLA));
       mazo.add(new Sorpresa("Una pena que te vallas, sigue asi y volveras",0,TipoSorpresa.SALIRCARCEL));

    }
    
    private void inicializarJugadores(ArrayList<String> nombres) {
        for (String n: nombres)
            jugadores.add(new Jugador(n));
    }
    
    private void inicializarTablero() {
        tablero = new Tablero();
    }
    
    private void salidaJugadores() {
        for (Jugador j : jugadores)
            j.setCasillaActual(new OtraCasilla(0, TipoCasilla.SALIDA));
    }
    
}
