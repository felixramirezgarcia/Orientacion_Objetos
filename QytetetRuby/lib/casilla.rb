#encoding: utf-8
require_relative "tablero"
require_relative "jugador"
require_relative "titulo_propiedad"
require_relative "tipo_casilla"

module ModeloQytetet
  class Casilla
    attr_accessor :numeroCasilla, :tipo
    
    def initialize(numeroCasilla, tipo)
      @numeroCasilla = numeroCasilla
      @tipo = tipo     
    end
    
    def get_tipo
      @tipo
    end
    
    def get_numero_casilla
      @numeroCasilla
    end
    
    def soy_edificable
      @tipo == TipoCasilla::CALLE
    end
    
    

    def to_s
      "Casilla\n { numeroCasilla= #{@numeroCasilla} , tipo= #{@tipo} }\n"
    end
    
    
  end
end
