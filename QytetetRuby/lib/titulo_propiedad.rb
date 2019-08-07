#encoding: utf-8
require_relative "jugador"
require_relative "casilla"

module ModeloQytetet
  class TituloPropiedad
    attr_accessor :hipotecada, :casilla, :propietario
    attr_reader :nombre, :alquilerBase, :factorRevalorizacion, :hipotecaBase, :precioEdificar
    
    def initialize(nom, alquiler, factor, hip, precioE)
      @nombre = nom
      @hipotecada = false
      @alquilerBase = alquiler
      @factorRevalorizacion = factor
      @hipotecaBase = hip
      @precioEdificar = precioE
    end
    
    def cobrar_alquiler(coste)
      @propietario.modificar_saldo(-coste)
    end
    
    def get_nombre
      @nombre
    end
    
    def get_hipotecada
      @hipotecada
    end
    
    def get_alquiler_base
      @alquilerBase
    end
    
    def get_factor_revalorizacion
      @factorRevalorizacion
    end
    
    def get_hipoteca_base
      @hipotecaBase
    end
    
    def get_precio_edificar
      @precioEdificar
    end
    
    def get_propietario
      @propietario
    end
    
    def propietario_encarcelado
      @propietario.get_encarcelado
    end
    
    def set_hipotecada(hipotecada)
      @hipotecada = hipotecada
    end
    
    def set_casilla(casilla)
      @casilla = casilla
    end
    
    def set_propietario(propietario)
      @propietario = propietario
    end
    
    def tengo_propietario
      @propietario != nil
    end
        
    def to_s
      "TituloPropiedad { nombre = #{@nombre}, hipotecada = #{@hipotecada}, alquilerBase = #{@alquilerBase}, factorRevalorizacion = #{@factorRevalorizacion}, hipotecaBase = #{@hipotecaBase}, precioEdificar = #{@precioEdificar}"
    end
  end
end
