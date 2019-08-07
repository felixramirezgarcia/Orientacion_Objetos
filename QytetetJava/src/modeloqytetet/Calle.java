/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modeloqytetet;

/**
 *
 * @author felix
 */
public class Calle extends Casilla{
    
    protected int coste;
    protected int numHoteles;
    protected int numCasas;
    protected TituloPropiedad titulo;

    public Calle(int numeroCasilla, int coste, TipoCasilla tipo, TituloPropiedad titulo) {
        super(numeroCasilla, tipo);
        this.coste = coste;
        this.titulo = titulo;
        titulo.setCasilla(this);
        this.numHoteles = 0;
        this.numCasas = 0;
        if (tipo == TipoCasilla.IMPUESTO)
            this.coste = 300;
    }
    
    public void setTitulo(TituloPropiedad titulo) {
        this.titulo = titulo;
    }

    public void setNumHoteles(int numHoteles) {
        this.numHoteles = numHoteles;
    }

    public void setNumCasas(int numCasas) {
        this.numCasas = numCasas;
    }
    
    public TituloPropiedad getTitulo() {
        return titulo;
    }
    
    TituloPropiedad asignarPropietario(Jugador jugador) {
        titulo.setPropietario(jugador);    
        return titulo;
    }
    
    int calcularValorHipoteca() {
        int valorHipotecaBase = titulo.getHipotecaBase();
        int total = (int) (valorHipotecaBase + numCasas * 0.5 * valorHipotecaBase + numHoteles * valorHipotecaBase);
        
        return total;
    }
    
    int cancelarHipoteca() {
        int pago = 0;
        
        if (this.estaHipotecada()) {
           titulo.setHipotecada(false);
           pago = (int) ( this.calcularValorHipoteca() * 1.1);
       }
        
        return pago;
    }
    
    int cobrarAlquiler() {
        int costeAlquilerBase = titulo.getAlquilerBase();
        int costeAlquiler = costeAlquilerBase + (int)(numCasas * 0.5 + numHoteles * 2);
        
        titulo.cobrarAlquiler(costeAlquiler);
        
        return costeAlquiler;
    }
    
    int edificarCasa() {
        int costeEdificarCasa;
        this.setNumCasas(numCasas + 1);
        
        costeEdificarCasa = titulo.getPrecioEdificar();
        
        return costeEdificarCasa;
    }
    
    int edificarHotel() {
        int costeEdificarHotel;
        this.setNumHoteles(numHoteles + 1);
        
        costeEdificarHotel = titulo.getPrecioEdificar();
        
        return costeEdificarHotel;
    }
    
    boolean estaHipotecada() {
        return titulo.getHipotecada();
    }
    
    int getCoste() {
        return coste;
    }
    
    int getCosteHipoteca() {
        return titulo.getHipotecaBase();
    }
    
    int getNumCasas() {
        return numCasas;
    }
    
    int getNumHoteles() {
        return numHoteles;
    }
    
    int getPrecioEdificar() {
        return titulo.getPrecioEdificar();
    }
    
    int hipotecar() {
        titulo.setHipotecada(true);
        int hipotecaBase = this.calcularValorHipoteca();
        int cantidadRecibida = (int) (hipotecaBase + this.getNumCasas() * 0.5 * hipotecaBase + this.getNumHoteles() * hipotecaBase);
        
        return cantidadRecibida;
    }
    
    int precioTotalComprar() {
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    boolean propietarioEncarcelado() {
        return titulo.propietarioEncarcelado();
    }
    
    boolean sePuedeEdificarCasa(int factorEspeculador){
        int cuantas = 4 * factorEspeculador; 
        return numCasas < cuantas;
    }
    
    boolean sePuedeEdificarHotel(int factorEspeculador){
         int cuantasCasas = 4 * factorEspeculador; 
         int cuantosHoteles = 4 * factorEspeculador; 
         return numCasas == cuantasCasas && numHoteles < cuantosHoteles;
    }
     
    boolean tengoPropietario() {
        return titulo.tengoPropietario();
    }
    
    int venderTitulo() {
        float precioVenta;
        int precioCompra;
        
        titulo.setPropietario(null);
        this.setNumHoteles(0);
        this.setNumCasas(0);
        
        precioCompra = coste + (numCasas+numHoteles) * titulo.getPrecioEdificar();
        precioVenta = precioCompra + titulo.getFactorRevalorizacion() * precioCompra;
        
        return (int)precioVenta;
    }
    
    void asignarTituloPropiedad() {
        if(this.tipo == TipoCasilla.CALLE)
            this.titulo.setCasilla(this);
    }  
    
    @Override
    public String toString() {
        return "\nCasilla{" + "numeroCasilla=" + numeroCasilla + ", coste=" + coste + ", numHoteles=" + numHoteles + ", numCasas=" + numCasas + ", tipo=" + tipo + ", titulo=" + titulo + '}';
    }
    
}
