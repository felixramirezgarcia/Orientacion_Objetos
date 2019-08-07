
package modeloqytetet;

public class TituloPropiedad {
    private final String nombre;
    private boolean hipotecada;
    private final int alquilerBase;
    private final float factorRevalorizacion;
    private final int hipotecaBase;
    private final int precioEdificar;
    private Casilla casilla;
    private Jugador propietario;
    
    public TituloPropiedad(String nom, int alquiler, float factor, int hip, int precioE) {
        nombre = nom;
        hipotecada = false;
        alquilerBase = alquiler;
        factorRevalorizacion = factor;
        hipotecaBase = hip;
        precioEdificar = precioE;
    }
    
    void cobrarAlquiler(int coste) {
        propietario.modificarSaldo(-coste);
    }

    public String getNombre() {
        return nombre;
    }

    boolean getHipotecada() {
        return hipotecada;
    }

    int getAlquilerBase() {
        return alquilerBase;
    }

    float getFactorRevalorizacion() {
        return factorRevalorizacion;
    }

    int getHipotecaBase() {
        return hipotecaBase;
    }

    int getPrecioEdificar() {
        return precioEdificar;
    }
    
    Jugador getPropietario() {
        return propietario;
    }
    
    public Casilla getCasilla() {
        return casilla;
    }
    
    boolean propietarioEncarcelado() {
        return propietario.getEncarcelado();
    }

    void setHipotecada(boolean hipotecada) {
        this.hipotecada = hipotecada;
    }
    
    void setCasilla(Casilla casilla) {
        this.casilla = casilla;
    }
    
    void setPropietario(Jugador propietario) {
        this.propietario = propietario;
    }
    
    public boolean tengoPropietario() {
        return propietario != null;
    }

    @Override
    public String toString() {
        return "TituloPropiedad{" + "nombre=" + nombre + ", hipotecada=" + hipotecada + ", alquilerBase=" + alquilerBase + ", factorRevalorizacion=" + factorRevalorizacion + ", hipotecaBase=" + hipotecaBase + ", precioEdificar=" + precioEdificar + '}';
    }
    
    
}
