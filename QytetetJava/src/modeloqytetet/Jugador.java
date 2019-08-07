
package modeloqytetet;

import java.util.ArrayList;

public class Jugador {
    protected boolean encarcelado;
    protected String nombre;
    protected int saldo;
    protected Sorpresa cartaLibertad;
    protected ArrayList<TituloPropiedad> propiedades;
    protected Casilla casillaActual;
    protected static int factorEspeculador = 1;
    
    public Jugador(String n) {
        nombre = n;
        saldo = 7500;
        encarcelado = false;
        cartaLibertad = null;
        propiedades = new ArrayList();
        casillaActual = new OtraCasilla(0, TipoCasilla.SALIDA);
    }
    
    protected Jugador(Jugador j){
        this.encarcelado = j.getEncarcelado();
        this.nombre = j.getNombre();
        this.saldo = j.getSaldo();
        this.cartaLibertad = j.getCartaLibertad();
        this.propiedades = j.getPropiedades();
        this.casillaActual = j.getCasillaActual();
    }
    
    protected Especulador convertirme(int fianza){
        Especulador especulador = new Especulador(this,fianza);
        return especulador;
    }
    
    public Sorpresa getCartaLibertad(){
        return cartaLibertad;
    }
    
    public Casilla getCasillaActual() {
        return casillaActual;
    }
    
    public boolean getEncarcelado() {
        return encarcelado;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public int getSaldo() {
        return saldo;
    }
    
    public ArrayList<TituloPropiedad> getPropiedades() {
        return propiedades;
    }
    
    public boolean tengoPropiedades() {
        return propiedades.size() > 0;
    }
    
    protected boolean actualizarPosicion(Casilla casilla) {
        if (casilla.getNumeroCasilla() < casillaActual.getNumeroCasilla())
            this.modificarSaldo(Qytetet.SALDO_SALIDA);
        
        boolean tienePropietario = false;
        int costeAlquiler = 0;
        int coste = 0;
        
        this.setCasillaActual(casilla);
        
        if (casilla.soyEdificable()) {
            tienePropietario = casilla.tengoPropietario();
            
            if (tienePropietario) {
                encarcelado = casilla.propietarioEncarcelado();
                
                if (!encarcelado) {
                    costeAlquiler = casilla.cobrarAlquiler();
                    this.modificarSaldo(-costeAlquiler);
                }
            }
        }
        else {
            if (casilla.getTipo() == TipoCasilla.IMPUESTO) {
                coste = casilla.getCoste();
                this.pagarImpuestos(-coste);
                //this.modificarSaldo(-coste);
                System.out.println("IMPUESTO --> Tu saldo se reduce en " + coste);
            }
        }
        
        return tienePropietario;
    }
    
    protected void pagarImpuestos(int cantidad){
            this.modificarSaldo(cantidad);
    }
    
    boolean comprarTitulo() {
        boolean puedoComprar = false;
        boolean tengoPropietario = false;
        int costeCompra;
        TituloPropiedad titulo;
        
        if (casillaActual.soyEdificable()) {
            tengoPropietario = casillaActual.tengoPropietario();
            
            if (!tengoPropietario) {
                costeCompra = casillaActual.getCoste();
                if (costeCompra <= saldo) {
                    titulo = casillaActual.asignarPropietario(this);
                    propiedades.add(titulo);
                    this.modificarSaldo(-costeCompra);
                    
                    puedoComprar = true;
                }
            }
        }
        
        return puedoComprar;
    }
    
    Sorpresa devolverCartaLibertad() {
        Sorpresa carta = cartaLibertad;
        cartaLibertad = null;
        
        return carta;
    }
    
    void irACarcel(Casilla casilla) {
        this.setCasillaActual(casilla);
        this.setEncarcelado(true);
    }
    
    int obtenerCapital() {
        int capital = 0;
        int valorPropiedades = 0;
        
        for (TituloPropiedad t : propiedades) {
            valorPropiedades += t.getCasilla().getCoste() + this.cuantasCasasHotelesTengo()*t.getPrecioEdificar();
            
            if (t.getHipotecada())
                valorPropiedades -= t.getHipotecaBase();
        }
        
        capital = saldo + valorPropiedades;
        
        return capital;
    }
    
    ArrayList<TituloPropiedad> obtenerPropiedadesHipotecadas(boolean hipotecada) {
        ArrayList<TituloPropiedad> hipotecadas = new ArrayList();
        
        for (TituloPropiedad t : propiedades) {
            if (t.getHipotecada())
                hipotecadas.add(t);
        }
        
        return hipotecadas;                
    }
    
    void pagarCobrarPorCasaYHotel(int cantidad) {
        int numeroTotal = this.cuantasCasasHotelesTengo();
        this.modificarSaldo(cantidad);
    }
    
    boolean puedoEdificarCasa(Casilla casilla) {
        boolean esMia = this.esDeMiPropiedad(casilla);
        boolean tengoSaldo = false;
        int costeEdificarCasa = 0;
        
        if (esMia && casilla.sePuedeEdificarCasa(factorEspeculador)) {
            costeEdificarCasa = casilla.getPrecioEdificar();
            tengoSaldo = this.tengoSaldo(costeEdificarCasa);
        }
        
        return esMia && tengoSaldo;
    }
    
    boolean puedoEdificarHotel(Casilla casilla) {
        boolean esMia = this.esDeMiPropiedad(casilla);
        boolean tengoSaldo = false;
        int costeEdificarHotel = 0;
        
        if (esMia && casilla.sePuedeEdificarHotel(factorEspeculador)) {
            costeEdificarHotel = casilla.getPrecioEdificar();
            tengoSaldo = this.tengoSaldo(costeEdificarHotel);
            
        }
        
        return esMia && tengoSaldo;
    }
    
    boolean puedoHipotecar(Casilla casilla) {
        boolean esMia = this.esDeMiPropiedad(casilla);
        
        return esMia;
    }
    
    boolean pagarLibertad (int cantidad) {
        boolean tengoSaldo = this.tengoSaldo(cantidad);
        
        if (tengoSaldo) {
            this.modificarSaldo(-cantidad);
        }
        
        return tengoSaldo;
    }
    
    boolean puedoPagarHipoteca(Casilla casilla) {
        return this.tengoSaldo((int) 1.1 * casilla.calcularValorHipoteca());
    }
    
    boolean puedoVenderPropiedad(Casilla casilla) {
        boolean esMia = this.esDeMiPropiedad(casilla);
        boolean hipotecada = casilla.estaHipotecada();

        return esMia &&!hipotecada;
    }
    
    void setCartaLibertad(Sorpresa carta) {
        cartaLibertad = carta;
    }
    
    void setCasillaActual(Casilla casilla) {
        casillaActual = casilla;
    }
    
    public void setEncarcelado(boolean encarcelado) {
        this.encarcelado = encarcelado;
    }
    
    boolean tengoCartaLibertad() {
        return cartaLibertad != null;
    }
    
    void venderPropiedad(Casilla casilla) {
        int precioVenta = casilla.venderTitulo();
        this.modificarSaldo(precioVenta);
        this.eliminarDeMisPropiedades(casilla);
    }
    
    private int cuantasCasasHotelesTengo() {
        int total = 0;
        
        for (TituloPropiedad t : propiedades)
            total += t.getCasilla().getNumCasas() + t.getCasilla().getNumHoteles();
        
        return total;
    }

    private void eliminarDeMisPropiedades(Casilla casilla) {
        for (TituloPropiedad t : propiedades)
            if (t.getNombre().equals(casilla.getTitulo().getNombre()))
                propiedades.remove(t);
    }
    
    private boolean esDeMiPropiedad(Casilla casilla) {
        boolean loEs = false;
        for (TituloPropiedad t : propiedades) {
            if (casilla.getTitulo().getNombre().equals(t.getNombre()))
                loEs = true;
        }
        
        return loEs;
    }
    
    private boolean tengoSaldo(int cantidad) {
        return saldo >= cantidad;
    }
    
    void modificarSaldo(int cantidad) {
        saldo += cantidad;
    }
    
    @Override
    public String toString () {
        return "Jugador {nombre = " + nombre + ", saldo = " + saldo + ", encarcelado = " + encarcelado + "}";
    }

}
