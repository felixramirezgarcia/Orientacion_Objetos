# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
require_relative "tablero"
require_relative "jugador"
require_relative "titulo_propiedad"
require_relative "tipo_casilla"
require_relative "casilla"


module ModeloQytetet

  class Calle 
    attr_accessor :numeroCasilla , :tipo , :coste ,:numCasas, :numHoteles, :titulo
    
    def initialize(numeroCasilla, coste, tipo, titulo)
      @numeroCasilla = numeroCasilla
      @coste = coste
      @numHoteles = 0
      @numCasas = 0
      @titulo = titulo 
      @tipo = tipo
      if (tipo == TipoCasilla::IMPUESTO) then
        @coste = 300
      end
    end
    
     def set_titulo(titulo)
      @titulo = titulo
    end
    
    def set_num_hoteles(nuevoNumero)
      @numHoteles = nuevoNumero
    end
    
    def set_num_casas(nuevoNumero)
      @numCasas = nuevoNumero
    end
    
    def get_titulo
      @titulo
    end
    
    def asignar_propietario(jugador)
      @titulo.propietario = jugador
      
      @titulo
    end
    
    def calcular_valor_hipoteca
      valor_hipoteca_base = @titulo.hipotecaBase
      total = valor_hipoteca_base + @numCasas * 0.5 * valor_hipoteca_base + @numHoteles * valor_hipoteca_base
      
      total
    end
    
    def cancelar_hipoteca
      pago = 0
      
      if esta_hipotecada then
        @titulo.hipotecada = false
        pago = calcular_valor_hipoteca * 1.1
      end
      
      pago
    end
    
    def cobrar_alquiler
      coste_alquiler_base = @titulo.alquilerBase
      coste_alquiler = coste_alquiler_base + @numCasas * 0.5 + @numHoteles * 2
    
      @titulo.cobrar_alquiler(coste_alquiler)
      
      coste_alquiler
    end
    
    def edificar_casa
      set_num_casas(@numCasas + 1)
      
      coste_edificar_casa = @titulo.precioEdificar

      coste_edificar_casa
    end
    
    def edificar_hotel
      set_num_hoteles(@numHoteles + 1)
      
      coste_edificar_hotel = @titulo.precioEdificar
      
      coste_edificar_hotel
    end
    
    def esta_hipotecada
      @titulo.get_hipotecada
    end
    
    def get_coste
      @coste
    end
    
    def get_coste_hipoteca
      @titulo.hipotecaBase
    end
    
    def get_num_casas
      @numCasas
    end
    
    def get_num_hoteles
      @numHoteles
    end
    
    def get_precio_edificar
      @titulo.get_precio_edificar
    end
    
    def hipotecar
      @titulo.set_hipotecada(true)
      hipoteca_base = calcular_valor_hipoteca
      cantidad_recibida = hipoteca_base + @numCasas * 0.5 * hipoteca_base + @numHoteles * hipoteca_base
    
      cantidad_recibida
    end
    
    def precio_total_comprar
      
    end
    
    def propietario_encarcelado
      @titulo.propietario_encarcelado
    end
    
    def se_puede_edificar_casa(factorEspeculador)
      cuantas = 4 * factorEspeculador
      if @numCasas < cuantas then 
        return true
      else
        return false
      end 
    end
    
    def se_puede_edificar_hotel(factorEspeculador)
      cuantas_casas = 4 * factorEspeculador
      cuantos_hoteles = 4 * factorEspeculador
      return @numCasas == cuantas_casas && @numHoteles < cuantos_hoteles
    end
    
    def tengo_propietario
      @titulo.tengo_propietario
    end
    
    def vender_titulo
      @titulo.set_propietario(nil)
      set_num_hoteles(0)
      set_num_casas(0)
      
      precio_compra = @coste + (@numCasas + @numHoteles) * @titulo.precioEdificar
      precio_venta = precio_compra + @titulo.factorRevalorizacion * precio_compra
    
      precio_venta
    end
    
    def asignar_titulo_propiedad
      if @tipo == TipoCasilla::CALLE then
        @titulo.casilla = self
      end
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
      "Calle\n { numeroCasilla= #{@numeroCasilla}, coste= #{@coste}, numHoteles= #{@numHoteles}, numCasas= #{@numCasas}, tipo= #{@tipo}, titulo= #{@titulo} }\n"
    end
    
    private :set_titulo
  end
  
end
