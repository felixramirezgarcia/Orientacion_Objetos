
package modeloqytetet;

 public abstract class Casilla {
    protected int numeroCasilla;
    protected TipoCasilla tipo;

    public Casilla (int numeroCasilla, TipoCasilla tipo) {
        this.numeroCasilla = numeroCasilla;
        this.tipo = tipo;
    }

    public TipoCasilla getTipo() {
        return tipo;
    }
   

    public int getNumeroCasilla() {
        return numeroCasilla;
    }
    
    boolean soyEdificable() {
        return tipo == TipoCasilla.CALLE;
    }  
    
    abstract void setTitulo(TituloPropiedad titulo);
    abstract void setNumHoteles(int numHoteles);
    abstract void setNumCasas(int numCasas);
    abstract public TituloPropiedad getTitulo();
    abstract TituloPropiedad asignarPropietario(Jugador jugador);
    abstract int calcularValorHipoteca();
    abstract int cancelarHipoteca();
    abstract int cobrarAlquiler();
    abstract int edificarCasa();
    abstract int edificarHotel();
    abstract boolean estaHipotecada();
    abstract int getCoste();
    abstract int getCosteHipoteca();
    abstract int getNumCasas();
    abstract int getNumHoteles();
    abstract int getPrecioEdificar();
    abstract int hipotecar();
    abstract int precioTotalComprar();
    abstract boolean propietarioEncarcelado();
    abstract boolean sePuedeEdificarCasa(int factorEspeculador);
    abstract boolean sePuedeEdificarHotel(int factorEspeculador);
    abstract boolean tengoPropietario();
    abstract int venderTitulo();
    abstract void asignarTituloPropiedad();
    
}
