# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
require_relative "tipo_casilla"
require_relative "casilla"
require_relative "titulo_propiedad"
require_relative "qytetet"
require_relative "jugador"

module ModeloQytetet
 
  class Especulador < Jugador
    attr_accessor :fianza , :factorEspeculador
    
    @@factorEspeculador = 2
    def initialize(jugador,fianza)
      super(jugador.nombre,jugador.encarcelado,jugador.saldo,jugador.casillaActual,jugador.cartaLibertad,jugador.propiedades)
      @fianza = fianza
    end 
    
    def get_factor_especulador
      return @@factorEspeculador
    end
    
    def pagarImpuestos(cantidad)        
         modificar_saldo(-(cantidad/2))
    end 
    
    def irACarcel(casilla)
        puede = pagarFianza(@fianza)
        if(!puede) then
            setCasillaActual(casilla)
            setEncarcelado(true)
        end
    end
    
    def convertirme(fianza)
      @fianza = fianza
      return self
    end
    
    private
    def pagarFianza(cantidad)
      if @saldo >= cantidad then
        modificar_saldo(-cantidad)
        return true
      else
        return false
      end
    end
    
  end
  
end