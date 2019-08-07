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
public class Especulador extends Jugador {
    
    protected static int factorEspeculador = 2;
    private int fianza ;

    protected Especulador(Jugador j , int fianza) {
        super(j);
        this.fianza = fianza;
    }
    
    @Override
    protected void pagarImpuestos(int cantidad){
         this.modificarSaldo((cantidad/2));
    }
    
    @Override
    protected void irACarcel(Casilla casilla){
        boolean puede = this.pagarFianza(fianza);
        if(!puede){
            this.setCasillaActual(casilla);
            this.setEncarcelado(true);
        }
    }
    
    @Override
    protected Especulador convertirme(int fianza){
        return this;   
    }
    
    private boolean pagarFianza(int cantidad){
        if(this.saldo >= cantidad){
            this.modificarSaldo(-cantidad);
            return true;
        }
        else
            return false;
    }
    
}
