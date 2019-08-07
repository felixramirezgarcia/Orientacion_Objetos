
package InterfazTextualQytetet;

import static java.lang.System.in;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import modeloqytetet.Casilla;
import modeloqytetet.Jugador;
import modeloqytetet.MetodoSalirCarcel;
import modeloqytetet.Qytetet;
import modeloqytetet.TipoCasilla;
import modeloqytetet.TituloPropiedad;


public class ControladorQytetet {
    private VistaTextualQytetet vista = new VistaTextualQytetet();
    private Qytetet juego;
    private Jugador jugador;
    private Casilla casilla;
    private static final Scanner in = new Scanner (System.in);
    
    public void desarrolloJuego() {
        int metodo;
        int opcion;
        boolean libre;
        boolean bancarrota = false;
        boolean noTienePropietario;
        boolean encarcelado = false;
        boolean elegirQuieroComprar = false;
        boolean tengoPropiedades = false;
        MetodoSalirCarcel metodosalir = null;
        ArrayList<TituloPropiedad> propiedadesJugador;
        List<Casilla>casillaPropiedades = new ArrayList();
    //    List<String> nombrePropiedades = new ArrayList();
        
        /*for (TituloPropiedad t: propiedadesJugador)
            casillaPropiedades.add(t.getCasilla());*/
        
        vista.mostrar("Turno de " + jugador.getNombre());
        vista.mostrar("Posición: "+ casilla.toString());
        vista.mostrar("Cantidad propiedades: " + jugador.getPropiedades().size() + "\n");
        
        do {
            encarcelado = jugador.getEncarcelado();
            propiedadesJugador = jugador.getPropiedades();
            
            if (encarcelado) {
                metodo = vista.menuSalirCarcel();

                switch(metodo) {
                    case 1:
                        metodosalir = MetodoSalirCarcel.TIRANDODADO;
                        break;
                    case 2:
                        metodosalir = MetodoSalirCarcel.PAGANDOLIBERTAD;
                        break;
                }


                libre = juego.intentarSalirCarcel(metodosalir);
                
                
                encarcelado = !libre;
            }
            
            if (!encarcelado) {
                noTienePropietario = !juego.jugar();
                casilla = jugador.getCasillaActual();

                bancarrota = jugador.getSaldo() <= 0;
                if (!bancarrota) {
                    encarcelado = jugador.getEncarcelado();
                    if (!encarcelado) {
                        if (casilla.getTipo() == TipoCasilla.SORPRESA) {
                            noTienePropietario = juego.aplicarSorpresa();
                            encarcelado = jugador.getEncarcelado();
                            if (!encarcelado) {
                                bancarrota = jugador.getSaldo() <= 0;
                                if (!bancarrota) {
                                    if (casilla.getTipo() == TipoCasilla.CALLE) {
                                        System.out.println("noTienePropietario = " + noTienePropietario);
                                        if (noTienePropietario) {
                                            elegirQuieroComprar = vista.elegirQuieroComprar();
                                            if (elegirQuieroComprar)
                                                juego.comprarTituloPropiedad();
                                        }
                                    }

                                }
                            }
                        }
                        else if (casilla.getTipo() == TipoCasilla.CALLE) {
                            System.out.println("noTienePropietario = " + noTienePropietario);
                            if (noTienePropietario) {
                                elegirQuieroComprar = vista.elegirQuieroComprar();
                                if (elegirQuieroComprar)
                                    juego.comprarTituloPropiedad();
                            }
                        }
                        
                        tengoPropiedades = jugador.tengoPropiedades();
                        
                        if (!encarcelado && !bancarrota && tengoPropiedades) {
                            for (TituloPropiedad t: propiedadesJugador)
                                casillaPropiedades.add(t.getCasilla());
                            
                            opcion = vista.menuGestionInmobiliaria();
                            if (opcion > 0) {
                                casilla = this.elegirPropiedad(casillaPropiedades);

                                switch(opcion) {
                                    case 1:
                                        juego.edificarCasa(casilla);
                                        break;
                                    case 2:
                                        juego.edificarHotel(casilla);
                                        break;
                                    case 3:
                                        juego.venderPropiedad(casilla);
                                        break;
                                    case 4:
                                        juego.hipotecarPropiedad(casilla);
                                        break;
                                    case 5:
                                        juego.cancelarHipoteca(casilla);
                                        break;
                                }
                            }
                            casillaPropiedades.clear(); //Limpiamos para que no se acumulen valores
                        }
                    }
                }
            }
            
            bancarrota = jugador.getSaldo() <= 0;

            if (!bancarrota) {
                vista.mostrar("Termina " + jugador.getNombre());

                jugador = juego.siguienteJugador();
                casilla = jugador.getCasillaActual();

                vista.mostrar("\n\nTurno de " + jugador.getNombre());
                vista.mostrar(jugador.toString());
                vista.mostrar("Posición: "+ casilla.toString());
                vista.mostrar("Cantidad propiedades: " + jugador.getPropiedades().size());

                in.nextLine();
            }
            else {
                juego.obtenerRanking();
                break;
            }
        } while(!bancarrota);
    }    
    
    
    public void inicializacionJuego() {
        juego = Qytetet.getInstance();
        
        System.out.println("Introduce los nombres de los jugadores");
                
        juego.inicializarJuego(vista.obtenerNombreJugadores());
        
        jugador = juego.getJugadorActual();
        casilla = jugador.getCasillaActual();
        
        //Muestro por pantalla el estado completo del juego
        System.out.println("Vista del tablero: ");
        vista.mostrar(juego.getTablero().toString()); //crear los consultores públicos que se necesiten
        //in.nextLine(); //pausa pulsando el teclado
        vista.mostrar(juego.getMazo().toString());
        //in.nextLine();
        vista.mostrar(jugador.toString());
        //in.nextLine();
        vista.mostrar(casilla.toString());
        //in.nextLine();
        
            
    }
    
    private Casilla elegirPropiedad(List<Casilla> propiedades){ 
    //este metodo toma una lista de propiedades y genera una lista de strings, con el numero y nombre de las propiedades
    //luego llama a la vista para que el usuario pueda elegir.
        vista.mostrar("\tCasilla\tTitulo");
        int seleccion;
        ArrayList<String> listaPropiedades= new ArrayList();
        
        for ( Casilla casilla: propiedades) {
                listaPropiedades.add( "\t"+casilla.getNumeroCasilla()+"\t"+casilla.getTitulo().getNombre()); 
        }
        
        seleccion=vista.menuElegirPropiedad(listaPropiedades);  
        
        return propiedades.get(seleccion);
    }
    
    public static void main(String args[]) {
        ControladorQytetet c = new ControladorQytetet();
        
        c.inicializacionJuego();
        c.desarrolloJuego();
    }
 
}

